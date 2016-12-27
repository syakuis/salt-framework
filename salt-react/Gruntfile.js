var chalk         = require("chalk");
var bower         = require("bower");
var bowerRenderer = require("bower/lib/renderers/StandardRenderer");

var fs = require('fs');
var glob = require("glob");
var path = require('path');
var _ = require('lodash');

module.exports = function(grunt) {
	var pkg = require('./package.root.json');

	grunt.registerMultiTask("bower-json-assign", "", function () {
		var target = this.target;
		var options = this.data;

		var result = {};
		var files = glob.sync("./**!(node_modules|bower_components)/*/bower.data.json");

		_.forEach(files, function(file) {
			var json = grunt.file.readJSON(file);

			result = _.assign(result, { dependencies: json.dependencies, devDependencies: json.devDependencies });
		});

		result = _.assign(result, grunt.file.readJSON("./bower.root.json"));
		var str = JSON.stringify(result);

		grunt.file.write('./bower.json', str, 'utf8');
	});

	grunt.registerMultiTask("package-json-assign", "", function () {
		var target = this.target;
		var options = this.data;

		var result = {};
		var files = glob.sync("./**!(node_modules|bower_components)/*/package.data.json");

		_.forEach(files, function(file) {
			var json = grunt.file.readJSON(file);

			result = _.assign(result, { dependencies: json.dependencies, devDependencies: json.devDependencies });
		});

		result = _.assign(result, grunt.file.readJSON("./package.root.json"));
		var str = JSON.stringify(result);

		console.log(str);

		grunt.file.write('./package.json', str, 'utf8');
	});

	grunt.registerMultiTask("bower", "Install or Update Bower Dependencies", function () {
		/*  prepare options  */
		var options = this.options({
			/*  bower configuration options (renderer specific)  */
			color:        undefined,          /*  bower --config.color=<val>           */
			cwd:          undefined,          /*  bower --config.cwd=<dir>             */

			/*  bower command selection options  */
			command:      "install",          /*  bower <command>                      */

			/*  bower command argument options  */
			forceLatest:  true,              /*  bower <command> --force-latest       */
			production:   false,              /*  bower <command> --production         */

			/*  bower configuration options (general)  */
			interactive:  undefined,          /*  bower --config.interactive=<val>     */
			directory:    undefined           /*  bower --config.directory=<dir>       */
		});
		grunt.verbose.writeflags(options, "Options");

		/*  sanity check option values  */
		if (typeof options.color !== "undefined" && typeof options.color !== "boolean")
			throw new Error("invalid type of value for option \"color\" (expected boolean)");
		if (typeof options.cwd !== "undefined" && typeof options.cwd !== "string")
			throw new Error("invalid type of value for option \"cwd\" (expected string)");
		if (typeof options.command !== "undefined" && typeof options.command !== "string")
			throw new Error("invalid type of value for option \"command\" (expected string)");
		if (typeof bower.commands[options.command] !== "function")
			throw new Error("invalid Bower command \"" + options.command + "\"");
		if (typeof options.forceLatest !== "undefined" && typeof options.forceLatest !== "boolean")
			throw new Error("invalid type of value for option \"forceLatest\" (expected boolean)");
		if (typeof options.production !== "undefined" && typeof options.production !== "boolean")
			throw new Error("invalid type of value for option \"production\" (expected boolean)");
		if (typeof options.interactive !== "undefined" && typeof options.interactive !== "boolean")
			throw new Error("invalid type of value for option \"interactive\" (expected boolean)");
		if (typeof options.directory !== "undefined" && typeof options.directory !== "string")
			throw new Error("invalid type of value for option \"directory\" (expected string)");

		/*  determine renderer options
		 (notice: provide only the real overrides to allow .bowerrc usage)
		 (notice: "cwd" has to be present to let Bower not fail)  */
		var rendererOpts = {};
		if (typeof options.color !== "undefined")
			rendererOpts.color = options.color;
		if (typeof options.cwd !== "undefined")
			rendererOpts.cwd = options.cwd;
		else
			rendererOpts.cwd = process.cwd();

		/*  determine task, task arguments and task options
		 (notice: provide only the real overrides to allow .bowerrc usage)  */
		var task = bower.commands[options.command];
		var taskArgs = {};
		if (options.command.match(/^(?:install|update)$/)) {
			taskArgs.forceLatest = options.forceLatest;
			taskArgs.production  = options.production;
		}
		var taskOpts = {};
		if (typeof options.interactive !== "undefined")
			taskOpts.interactive = options.interactive;
		if (typeof options.directory !== "undefined")
			taskOpts.directory = options.directory;
		if (typeof options.cwd !== "undefined")
			taskOpts.cwd = options.cwd;

		/*  display header to explicitly inform user about our Bower operation  */
		var msg = chalk.blue("Executing Bower") + " (Command: " + chalk.green(options.command) + ")";
		if (options.color !== true)
			msg = chalk.stripColor(msg);
		grunt.log.writeln(msg);

		/*  programatically run the Bower functionality  */
		var done = this.async();
		var renderer = new bowerRenderer(options.command, rendererOpts);

		task([], taskArgs, taskOpts)
			.on("log", function (log) {
				renderer.log(log);
			})
			.on("prompt", function (prompt, callback) {
				renderer.prompt(prompt).then(function(answer) {
					callback(answer);
				});
			})
			.on("error", function (err) {
				renderer.error(err);
				done(false);
			})
			.on("end", function (data) {
				renderer.end(data);
				done();
			});
	});


	var dist = pkg.config.dist;
	var react = pkg.config.react;

	var mod = grunt.option('mod') || '';

	if (mod === 'deploy') {
		dist = pkg.config.deploy_dist;
		react = pkg.config.deploy_react;
	}

	var webpackConfig = require("./webpack.config.js");

	grunt.initConfig({
		pkg: null,
		dist: dist,

		clean: {
			options: { force: true },
			basic: {
				src: [
					'<%= dist %>/bower_components/*','<%= dist %>/css/*','<%= dist %>/js/*'
				]
			},
			npm: {
				src: [
					'./package.json', './node_modules'
				]
			},
			bower: {
				src: [
					'./bower.json', './bower_components'
				]
			}

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
		},
		bower: {
			"install": {
				options: {
					color: true,
					production: true,
					forceLatest: true,
					command: 'install'
				}
			},
			"update": {
				options: {
					color: true,
					production: true,
					forceLatest: true,
					command: 'update'
				}
			}
		},
		"install-dependencies": {
			options: {
				isDevelopment: true
			}
		},

		"bower-json-assign": {
			bower: {

			}
		},

		"package-json-assign": {
			npm: {

			}
		},
		webpack: {
			options: webpackConfig,
			build: {

			}
		}
	});

	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-webpack');
	grunt.loadNpmTasks('grunt-install-dependencies');

	grunt.registerTask('default', [
		'clean',
		'copy:fonts',
		'copy:images',
		'copy:css',
		'copy:js',
		'cssmin',
		'uglify'
	]);

	// webpack can't use task name.
	grunt.registerTask('webpack-build', [
		'webpack'
	]);

	grunt.registerTask('package-install', [
		'clean:npm',
		'package-json-assign',
		'install-dependencies'
	]);

	grunt.registerTask('package-update', [
		'package-json-assign',
		'install-dependencies'
	]);

	grunt.registerTask('bower-install', [
		'clean:bower',
		'bower-json-assign',
		'bower:update'
	]);

	grunt.registerTask('bower-update', [
		'bower-json-assign',
		'bower:update'
	]);
};