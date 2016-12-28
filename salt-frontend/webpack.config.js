var glob = require("glob");
var path = require('path');
var _ = require('lodash');

var webpack = require('webpack');
var ExtractTextPlugin = require("extract-text-webpack-plugin");

var pkg = require("./package.root.json");
var react = pkg.config.react;

var entries = {};
var files = glob.sync("./react/*/src/index.js");

for (var i in files) {
	var entry = files[i];

	var name = path.dirname(entry).replace(/\.\/react\/(.*)\/src/, '$1');

	entries[name] = entry;
}

module.exports = {

	entry: entries,

	output: {
		path: react,
		filename: './[name]/[name].js',
		chunkFilename: './[name]/[id].js'
	},

	plugins: [
		new webpack.HotModuleReplacementPlugin(),
		new webpack.optimize.CommonsChunkPlugin("./commons/commons.js"),
		new ExtractTextPlugin("./[name]/[name].css")
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
				test: /\.css$/,
				loader: ExtractTextPlugin.extract("style-loader", "css-loader")
			},
			{
				test: /\.less$/,
				loader: ExtractTextPlugin.extract("style-loader", "css-loader!less-loader")
			},
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