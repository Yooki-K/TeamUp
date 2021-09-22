$(function (){
    $("#formEdit").ajaxForm({
        success: function(data){
            if(data.successful) {
                alert(data.mes);
                location.reload();
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
    })
})
function editState(e){
    var e =$(e);
    var name = e.text();
    if(name == "编辑"){
        $(".hideDom").each(function(){
            $(this).removeClass("hideDom");
            $(this).addClass("normalDom");
            $("#userDiv").hide();
            $("#headShot").show();
        });
        e.text("确定");
    }else{
        $("#formEdit").submit();
    }

}
function addLabel(e) {
    var label = $(e).text();
    var inp = $("input[name='label']");
    var l1 = inp.val();
    inp.val(l1+";"+label);
    $("#selectLabel").before("<li >{0}<i class=\'fa fa-close\' onclick=\'deleteLabel(this)\'></i></li>".format(label));
}
function deleteLabel(e) {
    var e = $(e).parents().eq(0);
    var text = e.text();
    var inp = $("input[name='label']");
    var l1 = inp.val();
    l1 = l1.replace(text+";","");
    l1 = l1.replace(text,"");
    inp.val(l1);
    e.remove();
}