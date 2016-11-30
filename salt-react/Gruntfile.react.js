module.exports = function(grunt) {

	grunt.initConfig({
		useminPrepare: {
			options: {
				dest: './dist',
				flow: {
					steps: { js: ['concat', 'uglify'], css: ['cssmin'] }, post: {}
				}
			},
			html: 'react.html'
		},

		usemin: {
			html: ['./dist/index.html']
		},

		uglify: {
			options: {
				mangle: false
			}
		},

		cssmin: {
			generated: {
				options: {
					rebase: true
				}
			}
		},
	});

	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-usemin');

	grunt.registerTask('default', [
		'useminPrepare',
		'concat:generated',
		'cssmin:generated',
		'uglify',
		'usemin'
	]);
};