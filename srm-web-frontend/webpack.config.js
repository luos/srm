module.exports = {
    entry: "./www/react-components/Index.jsx",
    output: {
        path: __dirname + "/www",
        filename: "bundle.js"
    },
    module: {
        loaders: [
            { test: /\.css$/, loader: "style!css" },
            { test: /\.jsx$/, loader: "jsx-loader?insertPragma=React.DOM&harmony" }
        ]
    },
    resolve: {
        extensions: ['', '.js', '.jsx']
    },
    externals: {
        'react': 'React'
    }
};