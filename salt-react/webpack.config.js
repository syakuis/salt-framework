var fs = require('fs');
var glob = require("glob");
var path = require('path');
var webpack = require('webpack');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var pkg = require("./package.json");

function entries(target) {
	var entries = {};
	var files = glob.sync(target);

	for (var i in files) {
		var entry = files[i];

		var name = path.dirname(entry).replace(/\.\/src\/(.*)\/src/, '$1');

		entries[name] = entry;
	}

	return entries;
}

function assertsPlugin() {
	var asserts = [];

	var files = glob.sync("./src/*/asserts.json");
	for (var i in files) {
		var file = files[i];
		var data = require(file);

		asserts = asserts.concat(data);
	}
	return asserts;
}
assertsPlugin();
module.exports = {

	entry: entries("./src/*/src/index.js"),

	output: {
		path: pkg.config.asserts,
		filename: './[name]/[name].js'
	},

	plugins: [
		new CopyWebpackPlugin(assertsPlugin()),
		new webpack.HotModuleReplacementPlugin(),
		new webpack.optimize.CommonsChunkPlugin("./commons/commons.js")
	],

	module: {/*
		preLoaders: [
			{
				test: [/\.jsx$/, /\.js$/],
				loader: 'eslint-loader',
				exclude: /(node_modules|bower_components)/
			}
		],*/
		loaders: [
			{
				//test: /\.jsx$/, // 로더를 사용할 확장자를 추가합니다.
				test: [/\.js$/],
				include: [ /src/ ],
				loader: 'babel', // 로더를 설정합니다.
				query: {
					presets: ['react', 'es2015', 'stage-1']
				},
				"plugins": ["transform-object-assign"]
			}
		]
	},
	/*resolve: {
    	extensions: ['', '.js', '.jsx']
  	},*/

	devServer: {
		inline: true, // 자동 리로드여부를 선택합니다.
		hot: true, // html 자동 리로드여부를 선택합니다. (정확한 역활을 모르겠네요)
		port:8889,
		host: '0.0.0.0',
		contentBase: './' // 서버 웹루트 경로를 설정합니다.
	}
};