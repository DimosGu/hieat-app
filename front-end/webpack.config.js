var fs = require('fs');
var path = require('path');
var CommonsChunkPlugin = require("webpack/lib/optimize/CommonsChunkPlugin");

module.exports = {
    cache: true,
    entry: {
        //vendor: ['react', 'redux', 'react-router', 'react-redux'],
        sign: './src/sign/sign.js',
        app: './src/app/app.js'
    },
    output: {
        path: '../public/main/js/',
        publicPath: '/assets/main/js/',
        filename: '[name].[hash].js',
        sourceMapFilename: '[file].map',
        chunkFilename: '[chunkhash].js'
    },
    plugins: [
        new CommonsChunkPlugin({
            name: 'common'
        }),
        function () {
            this.plugin("done", function (stats) {
                fs.writeFileSync(
                    '../conf/assets-stats.json',
                    JSON.stringify(stats.toJson().assetsByChunkName));
            });
        }
    ],
    module: {
        loaders: [{
            test: /\.js$/,
            exclude: /node_modules/,
            loaders: ['babel-loader']
        }]
    }
};

