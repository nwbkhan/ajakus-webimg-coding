const express = require('express');
const app = express();

const imgConverter = require('./img-converter');
const utils = require('./utils');


app.get('/htmlToImg', function (req, res) {
    const queries = req.query;
    let url = queries['url'];
    if (!url) {
        res.type('json').send({message: "Please supply url as query"})
    } else {
        var _ph, _page;
        url = utils.getUrl(url);
        imgConverter.extractHtmlPage(_ph, _page, url, res);
    }
});

app.get('/', function (req, res) {
    let body = 'Ajakus coding problem, hit /htmlToImg?url=url_name';
    res.type('json').send({message: body});
});


app.listen(4000, function () {
    console.log('running on port - 4000');
});
