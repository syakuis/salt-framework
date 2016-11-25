var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {

	entry: {
		dashboard: './src/dashboard/Dashboard.jsx'
	},

	output: {
		path: './dist',
		filename: '[name].js'
	},

	plugins: [
		new webpack.HotModuleReplacementPlugin(),
		new HtmlWebpackPlugin({
			template: './src/index.html'
		})
	],

	module: {
		loaders: [
			{
				//test: /\.jsx$/, // 로더를 사용할 확장자를 추가합니다.
				test: [/\.jsx$/, /\.js$/],
				include: [ /src/ ],
				loader: 'babel', // 로더를 설정합니다.
				query: {
					presets: ['react', 'es2015', 'stage-1']
				}
			}
		]
	},
	resolve: {
    	extensions: ['', '.js', '.jsx'],
  	},

	devServer: {
		inline: true, // 자동 리로드여부를 선택합니다.
		hot: true, // html 자동 리로드여부를 선택합니다. (정확한 역활을 모르겠네요)
		port:8888,
		contentBase: './dist' // 서버 웹루트 경로를 설정합니다.
	}
};