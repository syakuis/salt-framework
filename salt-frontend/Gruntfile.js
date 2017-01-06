/**
 # 퍼미션 오류날 경우
 sudo chown -R $USER:$GROUP ~/.npm
 sudo chown -R $USER:$GROUP ~/.config
 sudo chown -R $USER:$GROUP ~/.cache

 sudo npm install -g grunt-cli bower

 # 최초 설치라면 bower_components, node_modules 폴더 삭제 후 설치한다. 그리고 bower.json, package.json 삭제한다.
 package.root.json -> package.json 복사한다.
 npm update

 # 초기 설치
 grunt install

 # 업데이트
 grunt update

 # 리액트 빌드는 직접해야 한다.
 grunt react | rereact

 # 배포 및 재배포
 grunt deploy | redeploy

 # 개별 빌드 작업.
 grunt build | rebuild | react | rereact
 **/

var fs = require('fs');
var glob = require("glob");
var path = require('path');
var _ = require('lodash');

module.exports = function(grunt) {

	/*
	 하위 전체 폴더에 bower.json 혹은 package.json 파일을 읽어서 설치할 패키지 정보를 수집하여 루트 경로에 bower.json 혹은 package.json 파일을 생성한다.
	 동일한 패키지 정보는 bower.root.json 혹은 package.root.json 정보가 최우선 설정된다.
	 */
	grunt.registerMultiTask("create-json", "", function () {
		var target = this.target;
		var options = this.data;

		var filename = options.filename;
		var rootFilename = options.rootFilename;

		if (_.isEmpty(filename) || _.isEmpty(rootFilename)) {
			grunt.fail.warn("filename and rootFilename is required.");
		}

		// !(assets|node_modules|bower_components)/**/*/
		// react 에 있는 소스만 조회한다.
		var result = _.reduce(glob.sync("./react/**/*/" + filename), function(result, file) {
			grunt.log.writeln(file);
			var json = grunt.file.readJSON(file);
			return _.merge(result, json);
		}, {});

		fs.writeFileSync(filename, JSON.stringify(
			_.merge(result, grunt.file.readJSON(rootFilename))
			, null, 2));
	});

	var pkg = require('./package.root.json');
	// 최종 배포될 경로
	var assets = pkg.config.assets;
	// 리액트가 빌드되면 생성되는 폴더
	var react = pkg.config.react;
	// 배포될 경로
	var deployPath = pkg.config.deployPath;

	var bowerPackage = require('./bower-use-package.json');

	function getBowerPackage(packages, type) {
		return getPackage(bowerPackage, packages, type);
	}

	// 필요한 자원을 미리 정의된 정보를 이용하여 사용한다.
	function getPackage(data, packages, type) {
		return _.reduce(packages, function(result, package) {

			// package name 아닌 경우
			if (/[\/]+/.test(package)) {
				result = _.concat(result, package);
			} else {
				_.forEach(data, function(values, key) {
					if (key === package) {
						result = _.concat(result, values[type]);
					}
				})
			}

			return result;
		}, []);
	}

	// data 각 경로 앞에 dir 값을 추가하여 경로를 완성한다.
	function getPackageAddDir(data, dir) {
		return _.reduce(data, function(result, value) {
			return _.concat(result, dir + value);
		}, []);
	}

	// ie9 하위 브라우저를 지원하기 위한 스크립트
	var jsIE9 = getBowerPackage([
		"html5shiv",
		"respond",
		"es5-shim"
	], "js");

	// 부트스트랩 관련 스타일시트
	var cssBoostrap = getBowerPackage([
		"bootstrap"
	], "css");

	// 공통 스타일시트
	var cssBasic = getBowerPackage([
		"commons/fonts/NanumGothic/css/nanumgothic.css",
		"commons/css/mei.css",
		"font-awesome",
		"ionicons"
	], "css");

	// 공통 스크립트
	var jsBasic = getBowerPackage([
		"modernizr",
		"commons/js/css3-mediaqueries/css3-mediaqueries_src.js",
		"jquery",
		"jquery-migrate"
	],"js");

	grunt.initConfig({
		pkg: pkg,
		assets: assets,
		react: react,
		deployPath: deployPath,

		clean: {
			options: { force: true },
			assets: {
				src: [
					'<%= assets %>/dist',
					'<%= assets %>/node_modules',
					'<%= assets %>/bower_components',
					'<%= assets %>/commons'
				]
			},
			// 각 assets 자원을 모으는 과정에서 임시로 생성된 폴더를 삭제한다.
			temp: {
				src: [
					'<%= assets %>/node_modules',
					'<%= assets %>/bower_components',
					'<%= assets %>/commons'
				]
			},
			deploy: {
				src: [
					'<%= deployPath %>/<%= assets %>'
				]
			},
			bower: {
				src: [
					'bower.json', './bower_components'
				]
			},
			// node_modules 은 지울수 없다.
			npm: {
				src: [
					'package.json'
				]
			},
			webpack: {
				src: [
					'<%= react %>'
				]
			}
		},

		copy: {
			fonts: {
				expand: true,
				dest: '<%= assets %>',
				src: [
					'bower_components/**/*.{otf,eot,svg,ttf,woff,woff2}',
					'commons/**/*.{otf,eot,svg,ttf,woff,woff2}'
				]
			},
			images: {
				expand: true,
				dest: '<%= assets %>',
				src: [
					'bower_components/**/*.{png,jpg,jpeg,gif,webp,svg}',
					'commons/**/*.{png,jpg,jpeg,gif,webp,svg}'
				]
			},
			other: {
				expand: true,
				dest: '<%= assets %>',
				src: [
					'commons/css/non-responsive.css'
				]
			},
			deploy: {
				expand: true,
				dest: '<%= deployPath %>',
				src: [
					'<%= assets %>/**'
				]
			},
			cssBasic: {
				expand: true,
				dest: '<%= assets %>',
				src: cssBasic
			},
			jsBasic: {
				expand: true,
				dest: '<%= assets %>',
				src: jsBasic
			},
			cssBoostrap: {
				expand: true,
				dest: '<%= assets %>',
				src: cssBoostrap
			},
			jsIE9: {
				expand: true,
				dest: '<%= assets %>',
				src: jsIE9
			}
		},

		cssmin: {
			options: {
				rebase: true,
			},
			basic: {
				files: {
					'<%= assets %>/dist/assets.min.css': getPackageAddDir(cssBasic, "<%= assets %>/")
				}
			},
			bootstrap: {
				files: {
					'<%= assets %>/dist/bootstrap.assets.min.css': getPackageAddDir(cssBoostrap, "<%= assets %>/")
				}
			}
		},
		uglify: {
			basic: {
				files: {
					'<%= assets %>/dist/assets.min.js': getPackageAddDir(jsBasic, "<%= assets %>/")
				}
			},
			ie9: {
				files: {
					'<%= assets %>/dist/ie9.assets.min.js': getPackageAddDir(jsIE9, "<%= assets %>/")
				}
			},
		},
		"bower-install-simple": {
			options: {
				color: true,
				production: false
			},
			"install": {
				options: {
					forceLatest: false,
					command: 'install'
				}
			},
			"update": {
				options: {
					forceLatest: false,
					command: 'update'
				}
			}
		},

		// npm install 을 실행한다.
		"install-dependencies": {
			options: {
				isDevelopment: true
			}
		},

		"create-json": {
			bower: {
				filename: 'bower.json',
				rootFilename: 'bower.root.json'
			},
			npm: {
				filename: 'package.json',
				rootFilename: 'package.root.json'
			}
		},

		// webpack 을 빌드한다.
		webpack: {
			options: require('./webpack.config.js'),
			build: {

			}
		}
	});

	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-cssmin');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-install-dependencies');
	grunt.loadNpmTasks("grunt-bower-install-simple");
	grunt.loadNpmTasks('grunt-webpack');

	// delete package.json
	// create package.json
	// npm update
	grunt.registerTask('npm-install', [
		'clean:npm',
		'create-json:npm',
		'install-dependencies'
	]);

	// create package.json
	// npm update
	grunt.registerTask('npm-update', [
		'create-json:npm',
		'install-dependencies'
	]);

	// delete bower_components
	// delete bower.json
	// create bower.json
	// bower install
	grunt.registerTask('bower-install', [
		'clean:bower',
		'create-json:bower',
		'bower-install-simple:install'
	]);

	// create bower.json
	// bower update
	grunt.registerTask('bower-update', [
		'create-json:bower',
		'bower-install-simple:update'
	]);

	// delete react
	// webpack
	// webpack can't use task name.
	grunt.registerTask('react', [
		'clean:webpack', 'rereact'
	]);

	// webpack
	grunt.registerTask('rereact', [
		'webpack'
	]);

	// file copy
	grunt.registerTask('assets', [
		'copy:fonts',
		'copy:images',
		'copy:other'
	]);


	// file copy and js & css minify and temp delete
	grunt.registerTask('basic', [
		'copy:cssBasic',
		'cssmin:basic',
		'copy:jsBasic',
		'uglify:basic',
		'clean:temp'
	]);

	// file copy and js & css minify and temp delete
	grunt.registerTask('bootstrap', [
		'copy:cssBoostrap',
		'cssmin:bootstrap',
		'clean:temp'
	]);

	// file copy and js & css minify and temp delete
	grunt.registerTask('ie9', [
		'copy:jsIE9',
		'uglify:ie9',
		'clean:temp'
	]);

	// 각 빌드마다 임시 폴더를 지우기 때문에 assets 는 마지막에 빌드한다.
	grunt.registerTask('rebuild', [
		'basic', 'bootstrap', 'ie9', 'assets'
	]);

	grunt.registerTask('build', [
		'clean:assets', 'rebuild'
	]);

	grunt.registerTask('install', [
		'npm-install',
		'bower-install',
		'build'
	]);

	grunt.registerTask('update', [
		'npm-update',
		'bower-update',
		'rebuild'
	]);

	grunt.registerTask('redeploy', [
		'copy:deploy'
	]);

	grunt.registerTask('deploy', [
		'clean:deploy', 'copy:deploy'
	]);
};