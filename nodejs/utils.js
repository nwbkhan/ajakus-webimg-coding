const HTTP_SCHEME = 'http://';

function getUrl(url) {
    if (!url) {
        return null;
    }
    const hasHttp = /^http[s]:\/\//.test(url);

    return hasHttp ? url : HTTP_SCHEME + url;
}


module.exports = {getUrl};