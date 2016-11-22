module.exports = function(grunt) {

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		bower: {
			install: {
			}
		},

		useminPrepare: {
			options: {
				dest: '<%= pkg.config.resourcesDir %>/dist'
			},
			html: 'index.html'
		},

		usemin: {
			html: ['<%= pkg.config.resourcesDir %>/dist/index.html']
		},

		copy: {
			main: {
				expand: true,
				src: ['bower_components/bootstrap/fonts/*','bower_components/font-awesome/fonts/*'],
				dest: '<%= pkg.config.resourcesDir %>/fonts/',
				flatten: true,
				filter: 'isFile'
			},
		},

	});

	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-usemin');
	grunt.loadNpmTasks('grunt-bower-task');

	grunt.registerTask('bower', ['bower']);

	grunt.registerTask('build', [
		'useminPrepare',
		'concat',
		'cssmin',
		'uglify',
		'usemin',
		'copy'
	]);
};