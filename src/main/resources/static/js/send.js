String.prototype.format = function() {
    var result = this;
    if (arguments.length > 0) {
        for (var i = 0; i < arguments.length; i++) {
            var reg= new RegExp("({)" + i + "(})", "g");
            if (arguments[i] != undefined) {
                result = result.replace(reg, arguments[i]);
            }else{
                result = result.replace(reg, "");
            }
        }
    }
    return result;
}
function sendJSON(type,url,data,func){
    $.ajax({
        type: type,
        url: url,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: func,
        error:function (XMLHttpRequest) {
            const errorInfo = JSON.parse(XMLHttpRequest.responseText);
            const data = JSON.stringify({
                status:errorInfo.code,
                mes:errorInfo.message,
                from:window.location.href
            });

            formPost("/errorPage",data);
        }
    });
}

function sendQUERY(type,url,data,func){
    $.ajax({
        type: type,
        url: url,
        data: data,
        contentType: 'application/x-www-form-urlencoded',
        success: func,
        error:function (XMLHttpRequest) {
            const errorInfo = JSON.parse(XMLHttpRequest.responseText);
            const data = JSON.stringify({
                status:errorInfo.code,
                mes:errorInfo.message,
                from:window.location.href
            });
            formPost("/errorPage",data);
        }
    });
}

function formPost(URL, PARAMS) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "post";
    temp.style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}