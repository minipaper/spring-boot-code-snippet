const webpack = require('webpack');
const merge = require('webpack-merge');
const baseConfig = require('./webpack.config.base');

const HOST = 'localhost';
const PORT = 8081;

module.exports = merge(baseConfig, {
  mode: 'development',

  devServer: {
    proxy: {
      // proxy all webpack dev-server requests starting with /api to our Spring Boot backend (localhost:8080)
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
    },
    clientLogLevel: 'warning',
    hot: true,
    contentBase: 'dist',
    compress: true,
    host: HOST,
    port: PORT,
    // open: true,
    overlay: { warnings: false, errors: true },
    publicPath: '/',
    // quiet: true,
    watchOptions: {
      poll: true,
    },
    // https://webpack.js.org/configuration/devtool/#development
    // devtool: 'cheap-module-eval-source-map',
    // cssSourceMap: true,
  },
  // devtool: 'cheap-module-eval-source-map',


  module: {
    rules: [
      {
        test: /\.css$/,
        use: ['vue-style-loader', 'css-loader'],
      },
      // {
      //   test: /\.styl(us)?$/,
      //   use: ['vue-style-loader', 'css-loader', 'stylus-loader'],
      // },
    ],
  },

  plugins: [new webpack.HotModuleReplacementPlugin()],
});
