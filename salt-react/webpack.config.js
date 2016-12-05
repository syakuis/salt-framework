var fs = require('fs');
var glob = require("glob");
var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {

	entry: {
		dashboard: './src/dashboard/'
	},

	output: {
		path: './dist',
		filename: '[name].js'
	},

	plugins: [
		new webpack.HotModuleReplacementPlugin(),
		new HtmlWebpackPlugin({
			template: './index.html'
		}),
		function() {
				fs.writeFile('./src/dashboard/portlets/index.jsx', '');
				glob('src/dashboard/portlets/**/config.json', null, function(err, files) {

					for(var i in files) {
						var file = __dirname + '/' + files[i];
						fs.readFile(file, 'utf-8', function (err, data) {
							var script = JSON.parse(data).script;
							fs.appendFileSync("./src/dashboard/portlets/index.jsx", script + "\n");
							console.log('Portlets Export add: ' + script);
						});

					}

				});
		}
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
    	extensions: ['', '.js', '.jsx']
  	},

	devServer: {
		inline: true, // 자동 리로드여부를 선택합니다.
		hot: true, // html 자동 리로드여부를 선택합니다. (정확한 역활을 모르겠네요)
		port:8888,
		contentBase: './' // 서버 웹루트 경로를 설정합니다.
	}
};