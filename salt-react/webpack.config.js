var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
	
	entry: './src/js/HelloWorld.jsx',

	output: {
		path: './dist',
		filename: 'bundle.js'
	},

	plugins: [
		new webpack.HotModuleReplacementPlugin(),
		new HtmlWebpackPlugin({
			template: './src/bundle.html'
		})
	],

	module: {
		loaders: [
			{
				test: /\.jsx$/, // 로더를 사용할 확장자를 추가합니다.
				exclude: /node_modules/, // 로더 사용을 제외한 대상을 추가합니다.
				loader: 'babel', // 로더를 설정합니다.
				query: {
					presets: ['react', 'es2015']
				}
			}
		]
	},

	devServer: {
		inline: true, // 자동 리로드여부를 선택합니다.
		hot: true, // html 자동 리로드여부를 선택합니다. (정확한 역활을 모르겠네요)
		port:8888,
		contentBase: './' // 서버 웹루트 경로를 설정합니다.
	}
};