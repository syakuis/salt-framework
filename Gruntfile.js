module.exports = function(grunt) {

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		clean: [
			'<%= pkg.config.dist %>/dist'
		],

		copy: {
			fonts: {
				expand: true,
				dest: '<%= pkg.config.dist %>/dist/',
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
				dest: '<%= pkg.config.dist %>/dist/',
				src: [
					'bower_components/**/*.jpg',
					'bower_components/**/*.gif',
					'bower_components/**/*.png',
					'bower_components/**/*.bmp'
				]
			},
			css: {
				expand: true,
				dest: '<%= pkg.config.dist %>/dist/',
				src: [
					'bower_components/bootstrap/dist/css/bootstrap.css',
					'bower_components/font-awesome/css/font-awesome.css'
				]
			},
			js: {
				expand: true,
				dest: '<%= pkg.config.dist %>/dist/',
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
					'<%= pkg.config.dist %>/dist/css/assets.min.css': [
						'<%= pkg.config.dist %>/dist/bower_components/**/*.css'
					]
				}
			}
		},
		uglify: {
			target: {
				files: {
					'<%= pkg.config.dist %>/dist/js/assets.min.js': [
						'<%= pkg.config.dist %>/dist/bower_components/**/*.js'
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