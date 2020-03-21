const phantom = require('phantom');
const fileName = 'download.png';

function extractHtmlPage(_ph, _page, url, res) {
    phantom.create().then(function (ph) {
        _ph = ph;
        return _ph.createPage();
    }).then(function (page) {
        _page = page;
        return _page.open(url);
    }).then(function (status) {
        console.log(status);
        return _page.render(fileName)
    }).then(function (_content) {
        res.status(200).set({
            "Content-Type": "application/octet-stream",
            "Content-Disposition": "attachment; filename=" + fileName
        }).sendFile(fileName, {root: __dirname});
        _page.close();
        _ph.exit();
    }).catch(function (e) {
        console.log('Caught error ', e);
        res.type('json').send({message: 'Caught error'});
    });
}


module.exports = {extractHtmlPage};