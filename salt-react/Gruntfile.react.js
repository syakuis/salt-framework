module.exports = function(grunt) {

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		bower: {
			install: {
			}
		},

		useminPrepare: {
			options: {
				dest: './dist'
			},
			html: 'react.html'
		},

		usemin: {
			html: ['./dist/react.html']
		},

		copy: {
			main: {
				expand: true,
				src: ['bower_components/bootstrap/fonts/*','bower_components/font-awesome/fonts/*'],
				dest: './dist/fonts/',
				flatten: true,
				filter: 'isFile'
			},
		}

	});

	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-usemin');
	grunt.loadNpmTasks('grunt-bower-task');

	grunt.registerTask('bower', ['bower']);

	grunt.registerTask('default', [
		'copy',
		'useminPrepare',
		'concat',
		'cssmin',
		'usemin'
	]);
};