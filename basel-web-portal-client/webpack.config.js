var ExtractTextPlugin = require("extract-text-webpack-plugin");
var combineLoaders = require('webpack-combine-loaders');
var HtmlWebpackPlugin = require('html-webpack-plugin');

var extractModuleCss = new ExtractTextPlugin('styles.css');
var extractGlobalCss = new ExtractTextPlugin('global.css');
var html = new HtmlWebpackPlugin({
    template : 'src/index.template.ejs',
    inject : 'body',
});
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
                    test : /\.(png|woff|woff2|eot|ttf|svg)$/,
                    loader : 'url-loader?limit=100000'
                },
                {
                    test : /\.module\.css$/,
                    loader : extractModuleCss.extract('style-loader',
                            combineLoaders([ {
                                loader : 'css-loader',
                                query : {
                                    modules : true,
                                    localIdentName : '[name]_[local]'
                                }
                            } ]))
                },
                // Global CSS
                {
                    test : /\.css$/,
                    exclude : /\.module\.css$/,
                    loader : extractGlobalCss.extract('style-loader','css-loader')
                } ]
    },
    resolve : {
        extensions : [ '', '.js', '.jsx', '.css', '.ejs','.png','.woff','.woff2','.eot','.ttf','.svg' ]
    },
    plugins : [ extractModuleCss, extractGlobalCss, html ],
    output : {
        path : __dirname + '/dist',
        publicPath : '/',
        filename : 'bundle.js'
    },
    devServer : {
        contentBase : './dist'
    }
};
