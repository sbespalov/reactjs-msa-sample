var webpack = require('webpack');
var path = require('path');
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
    output : {
        path : __dirname + '/dist',
        publicPath : '/',
        filename : 'bundle.js',
        library : "AppContext",
        libraryTarget : "var"
    },
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
                {
                    test : /\.css$/,
                    exclude : /\.module\.css$/,
                    loader : extractGlobalCss.extract('style-loader',
                            'css-loader')
                } ]
    },
    resolve : {
        extensions : [ '', '.js', '.jsx', '.css', '.ejs', '.png', '.woff',
                '.woff2', '.eot', '.ttf', '.svg' ],
        root : [ path.resolve('./src'), path.resolve('./lib') ]
    },
    plugins : [ extractModuleCss, extractGlobalCss, html,
            new webpack.ProvidePlugin({
                $ : "jquery",
                jQuery : "jquery",
                ReactDOM : "react-dom"
            }) ],
    devServer : {
        contentBase : './dist',
        host : '0.0.0.0',
        port : 8086,
        headers : {
            "Access-Control-Allow-Origin" : "*"
        },
        proxy : {
            '/' : {
                target : 'http://localhost:4010'
            }
        }
    }
};
