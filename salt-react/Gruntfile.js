var glob = require("glob");
var path = require('path');
var _ = require('lodash');
var pkg = require("./package.json");

function assertsPlugin() {
	var files = glob.sync("./src/*/asserts.json");
	var asserts = [];

	_.forEach(files, function(file) {
		var dir = path.dirname(file);
		var data = require(file);
		var dest = _.isEmpty(data.dest) ? pkg.config.dist : pkg.config.dist + data.dest;

		_.assign(asserts, data, { expand: true, dest: dest });
	});

	console.log(asserts);

	return asserts;
}

assertsPlugin();


module.exports = function(grunt) {

	grunt.registerTask('bower', 'install the backend and frontend dependencies', function() {
		var bower = require("bower");
		console.log("good");
	});

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		clean: [
			'<%= pkg.config.dist %>'
		],

		copy: {
			fonts: {
				expand: true,
				dest: '<%= pkg.config.dist %>',
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
				dest: '<%= pkg.config.dist %>',
				src: [
					'bower_components/**/*.jpg',
					'bower_components/**/*.gif',
					'bower_components/**/*.png',
					'bower_components/**/*.bmp'
				]
			},
			css: {
				expand: true,
				dest: '<%= pkg.config.dist %>',
				src: [
					'bower_components/bootstrap/dist/css/bootstrap.css',
					'bower_components/font-awesome/css/font-awesome.css'
				]
			},
			js: {
				expand: true,
				dest: '<%= pkg.config.dist %>',
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
					'<%= pkg.config.dist %>/css/assets.min.css': [
						'<%= pkg.config.dist %>/bower_components/**/*.css'
					]
				}
			}
		},
		uglify: {
			target: {
				files: {
					'<%= pkg.config.dist %>/js/assets.min.js': [
						'<%= pkg.config.dist %>/bower_components/**/*.js'
					]
				}
			}
		},
		bower: {
			install: {
				options: {
					targetDir: "./lib",
					bowerOptions: {
						"dependencies": {
							"jquery": "1.12.4"
						}
					}
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
		'uglify',
		'bower:install'
	]);
};