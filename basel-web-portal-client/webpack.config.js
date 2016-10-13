var ExtractTextPlugin = require("extract-text-webpack-plugin");
var combineLoaders = require('webpack-combine-loaders');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
	entry : [ './src/index.jsx' ],
	module : {
		loaders : [
				{
					test : /\.jsx?$/,
					exclude : /node_modules/,
					loader : 'babel'
				},
				{
					test : /\.css$/,
					loader : ExtractTextPlugin
							.extract(
									'style-loader',
									combineLoaders([ {
										loader : 'css-loader',
										query : {
											modules : true,
											localIdentName : '[name]__[local]___[hash:base64:5]'
										}
									} ]))
				} ]
	},
	resolve : {
		extensions : [ '', '.js', '.jsx', '.css', '.ejs' ]
	},
	plugins : [ new ExtractTextPlugin('styles.css'), 
		new HtmlWebpackPlugin({
				template : 'src/index.template.ejs',
				inject : 'body',
			})
	],
	output : {
		path : __dirname + '/dist',
		publicPath : '/',
		filename : 'bundle.js'
	},
	devServer : {
		contentBase : './dist'
	}
};
