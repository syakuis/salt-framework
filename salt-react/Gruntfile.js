module.exports = function(grunt) {
	var pkg = require('./package.json');


	var dist = pkg.config.dist;
	var react = pkg.config.react;

	var mod = grunt.option('mod') || '';

	if (mod === 'deploy') {
		dist = pkg.config.deploy_dist;
		react = pkg.config.deploy_react;
	}

	grunt.initConfig({
		dist: dist,

		clean: {
			options: { force: true },
			src: [
				'<%= dist %>/bower_components/*','<%= dist %>/css/*','<%= dist %>/js/*'
			]
		},

		copy: {
			fonts: {
				expand: true,
				dest: '<%= dist %>',
				src: [
					'bower_components/**/*.otf',
					'bower_components/**/*.eot',
					'bower_components/**/*.svg',
					'bower_components/**/*.ttf',
					'bower_components/**/*.woff',
					'bower_components/**/*.woff2'
				]
			},
			images: {
				expand: true,
				dest: '<%= dist %>',
				src: [
					'bower_components/**/*.jpg',
					'bower_components/**/*.gif',
					'bower_components/**/*.png',
					'bower_components/**/*.bmp'
				]
			},
			css: {
				expand: true,
				dest: '<%= dist %>',
				src: [
					'bower_components/bootstrap/dist/css/bootstrap.css',
					'bower_components/font-awesome/css/font-awesome.css'
				]
			},
			js: {
				expand: true,
				dest: '<%= dist %>',
				src: [
					'bower_components/jquery/dist/jquery.js',
					'bower_components/jquery-migrate/jquery-migrate.js'
				]
			}
		},

		cssmin: {
			options: {
				rebase: true,
			},
			target: {
				files: {
					'<%= dist %>/css/assets.min.css': [
						'<%= dist %>/bower_components/**/*.css'
					]
				}
			}
		},
		uglify: {
			target: {
				files: {
					'<%= dist %>/js/assets.min.js': [
						'<%= dist %>/bower_components/jquery/**/*.js',
						'<%= dist %>/bower_components/**/*.js'
					]
				}
			}
		}
	});

	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-contrib-clean');

	grunt.registerTask('default', [
		'clean',
		'copy:fonts',
		'copy:images',
		'copy:css',
		'copy:js',
		'cssmin',
		'uglify'
	]);
};