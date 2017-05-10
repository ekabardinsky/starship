function objectToPaths(data) {
    var validId = /^[a-z_$][a-z0-9_$]*$/i;
    var result = [];
    doIt(data, "");
    return result;

    function doIt(data, s) {
        if (data && typeof data === "object") {
            if (Array.isArray(data)) {
                for (var i = 0; i < data.length; i++) {
                    doIt(data[i], s + "[" + i + "]");
                }
            } else {
                for (var p in data) {
                    if (validId.test(p)) {
                        doIt(data[p], s + "." + p);
                    } else {
                        doIt(data[p], s + "[\"" + p + "\"]");
                    }
                }
            }
        } else {
            result.push(s);
        }
    }
}

function formatFields(data) {
    return data.map(function (item) {
        var text = capitalizeFirstLetter(item.replace('.', ' ').trim());
        return {
            text: text,
            value: item
        }
    });

    function capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }
}