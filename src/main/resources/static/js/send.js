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
//发送数据为json
function sendJSON(type,url,data,func){
    $.ajax({
        type: type,
        url: url,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function(data){
            if(data.successful) {
                func(data);
            }
            else{
                formPost("/errorPage",{status:data.successful,mes:data.mes,from:window.location.href});
            }
        },
        error:function (XMLHttpRequest) {
            const errorInfo = JSON.parse(XMLHttpRequest.responseText);
            const data = JSON.stringify({
                status:errorInfo.status,
                mes:errorInfo.message,
                from:window.location.href
            });

            formPost("/errorPage",data);
        }
    });
}
//发送数据为普通参数
function sendQUERY(type,url,data,func){
    $.ajax({
        type: type,
        url: url,
        data: data,
        contentType: 'application/x-www-form-urlencoded',
        success: function(data){
            if(data.successful) {
                func(data);
            }
            else{
                formPost("/errorPage",{status:data.successful,mes:data.mes,from:window.location.href});
            }
        },
        error:function (XMLHttpRequest) {
            const errorInfo = JSON.parse(XMLHttpRequest.responseText);
            const data = JSON.stringify({
                status:errorInfo.status,
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