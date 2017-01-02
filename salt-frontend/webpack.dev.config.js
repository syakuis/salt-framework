var glob = require("glob");
var path = require('path');
var _ = require('lodash');

var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
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

var html = [];
_.forEach(entries, function(value, key) {
	html = _.concat(html,
		new HtmlWebpackPlugin({
			// webpack-dev-server 와 경로가 다름.
			filename: 'react/' + key + '.html',
			template: './react/index.html'
		})
	);
});

module.exports = {

	entry: entries,

	output: {
		path: react,
		filename: './[name]/[name].js',
		chunkFilename: './[name]/[id].js'
	},

	plugins: [
		new webpack.optimize.CommonsChunkPlugin("./commons/commons.js"),
		new ExtractTextPlugin("./[name]/[name].css"),
		new webpack.HotModuleReplacementPlugin(),
		...html
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
					presets: ['react', 'es2015', 'stage-0', 'stage-1']
				},
				"plugins": [
					"transform-object-assign"
				]
			}
		]
	},
	/*resolve: {
    	extensions: ['', '.js', '.jsx']
  	},*/
/*
	devServer: {
		inline: true, // 자동 리로드여부를 선택합니다.
		hot: true, // html 자동 리로드여부를 선택합니다. (정확한 역활을 모르겠네요)
		port:8088,
		host: '0.0.0.0',
		contentBase: './assets' // 서버 웹루트 경로를 설정합니다.
	},*/

	devServer: {
		proxy: {
			'/dashboard': {
				target: 'http://localhost:8080',
				secure: false,
				prependPath: false
			}
		},
		inline: true, // 자동 리로드여부를 선택합니다.
		hot: true, // html 자동 리로드여부를 선택합니다. (정확한 역활을 모르겠네요)
		port:8088,
		host: '0.0.0.0',
		contentBase: './assets' // 서버 웹루트 경로를 설정합니다.
	}
};