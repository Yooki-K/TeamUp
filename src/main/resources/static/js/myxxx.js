$(function (){
    sendFORM($("#formEdit"),function (data) {
        alert(data.mes);
        location.reload();
    });

    sendFORM($("#formUpload"),function (data) {
        location.reload();
    });
    sendFORM($("#formRz"),function (data) {
        alert(data.mes);
        document.getElementById("formRz").reset();
        $("#closeRz").click();
    });
    $("#uploadimg-file-btn").change(function () {
        $(this).parent().submit();
    });

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
function dissolveTeam(e) {
    var id = e.id;
    id = id.substr(4);
    sendQUERY("post","/delete/team",{teamId:id},function (data) {
       location.reload();
    });
}