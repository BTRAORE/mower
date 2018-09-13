const path = require('path');
var jQuery = require("jquery");
var $ = require("jquery");
require("jquery-ui");
var webpack = require("webpack");
const ExtractTextPlugin = require('extract-text-webpack-plugin')
const CleanWebpackPlugin = require('clean-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  entry: {
	  momCommon: './src/js/mom-common-app.js',
	  momUserProfile: './src/js/mom-userprofile-app.js'
	  },
  output: {
    filename: 'js/[name]-bundle.js',
    path: path.resolve(__dirname, '../dist')
  },
  optimization: {
	  splitChunks: {
		  cacheGroups: {
	        default: false,
	        commons: {
	          test: /[\\/]node_modules[\\/]/,
	          name: 'vendor-app',
	          chunks: 'all',
	          minChunks: 2
	        }
	      }
	    },
	  runtimeChunk: false
	},
	resolve : {
	    alias: {
	      // bind version of jquery-ui
	      "jquery-ui": "jquery-ui/jquery-ui.js",      
	      // bind to modules;
	      modules: path.join(__dirname, "node_modules")
	    }
	},
  module: {
    rules: [
    	{
    	      test: /\.js$/,
    	      exclude: /(node_modules|bower_components)/,
    	      use: {
    	        loader: 'babel-loader',
    	        options: {
    	          presets: ['@babel/preset-env']
    	        }
    	      }
    	    },
      {
        test: /\.(scss)$/,
        use: ExtractTextPlugin.extract({
        	fallback: 'style-loader',
        	use: ['css-loader', 'sass-loader']
        })
      },
      {
    	  test: /\.(png|svg|jpg|gif)$/,
    	  use: [
    		  {
    	       loader: 'file-loader'
    	      }
    	      ]
    }
    
//    {
//        test: require.resolve('jquery'),
//        use: [{
//            loader: 'expose-loader',
//            options: 'jQuery'
//        }, {
//            loader: 'expose-loader',
//            options: '$'
//        }]
//    }
    ]
  },
  plugins: [
	  new ExtractTextPlugin('css/style.css'),
	  new CleanWebpackPlugin(['../dist']),
	  new HtmlWebpackPlugin({
		title: 'mom web app'  
	  }),
	  new webpack.ProvidePlugin({
		   $: "jquery",
		   jQuery: "jquery",
		   "window.jQuery": "jquery'",
	        "window.$": "jquery"
		  })
		  
	  ]
}