/**
 * 
 * @authors KuangYu (1486147017@qq.com)
 * @date    2021-09-17 11:14:27
 * 大于400kb将进行压缩上传
 */


function submitPic() {
    var fileObj = document.getElementById('file').files[0];
    var form = new FormData();
    form.append("user",document.getElementById('user').value)
    //上传图片大于400KB进行压缩
    alert(fileObj.size/1024);
    if(fileObj.size/1024 > 400){
        alert("图片过大，将进行压缩");
        photoCompress(fileObj,{
            quality: 0.2
        },function (base64Codes) {
            var bl = convertBase64UrlToBlob(base64Codes);
            form.append("file", bl); // 文件对象
            //上传
            $.ajax({
            	url:"/t",
            	type:"POST",
            	data:form,
            	cache: false,        // 不缓存数据
				processData: false,  // 不处理数据
				contentType: false,   // 不设置内容类型
			  	success:function (data) {           //成功回调
			  	    console.log(data);
			  	}
            });
        })
    }else{
        form.append("file", fileObj);
        //上传
            $.ajax({
            	url:"/t",
            	type:"POST",
            	data:form,
            	cache: false,        // 不缓存数据
				processData: false,  // 不处理数据
				contentType: false,   // 不设置内容类型
			  	success:function (data) {           //成功回调
			  	    console.log(data);
			  	}
            });
    }
}

/**
 * @param file: 上传的图片
 * @param objCompressed：压缩后的图片规格
 * @param objDiv：容器或回调函数
 */
function photoCompress(file, objCompressed, objDiv){
    var ready=new FileReader();
    ready.readAsDataURL(file);
    ready.onload=function(){
        var fileResult=this.result;
        canvasDataURL(fileResult,objCompressed,objDiv)
    }
}

function canvasDataURL(path, objCompressed, callback) {
    var img = new Image();
    img.src = path;
    img.onload = function () {
        var that = this;
        //默认压缩后图片规格
        var quality = 0.5;
        var w = that.width;
        var h = that.height;
        var scale = w / h;
        //实际要求
        w = objCompressed.width || w;
        h = objCompressed.height || (w / scale);
        if(objCompressed.quality && objCompressed.quality > 0 && objCompressed.quality <= 1){
            quality = objCompressed.quality;
        }

        //生成canvas
        var canvas = document.createElement('canvas');
        var ctx = canvas.getContext('2d');
        // 创建属性节点
        var anw = document.createAttribute("width");
        anw.nodeValue = w;
        var anh = document.createAttribute("height");
        anh.nodeValue = h;
        canvas.setAttributeNode(anw);
        canvas.setAttributeNode(anh);
        ctx.drawImage(that, 0, 0, w, h);

        var base64 = canvas.toDataURL('image/jpeg', quality);
        // 回调函数返回base64的值
        callback(base64);
    }
}

function convertBase64UrlToBlob(urlData) {
    var arr = urlData.split(','), mime = arr[0].match(/:(.*?);/)[1], bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new Blob([u8arr], {type:mime});
}
