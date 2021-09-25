function updateBrief(e) {
    sendJSON("post","/update/team",{teamId:$("input[name='teamId']").val(),brief:$(e).val()},function (data) {
        location.reload();
    })
}

function deleteRoom(){
    sendQUERY("post","/delete/room",{roomId:$("input[name='roomId']").val()},function (data) {
        alert(data.mes);
        window.location.href="/rooms/public";
    })
}
function apply(){
    $("#formApply").submit();
}
function agree(e){
    var id_ = e.id;
    var id = id_.substr(6);
    var id = parseInt(id);
    sendJSON("post","/operate/application/agree/"+$("input[name='roomId']").val(),[id],function (){
       location.reload();
    });
}
function disagree(e){
    var id_ = e.id;
    var id = id_.substr(9);
    var id = parseInt(id);
    sendJSON("post","/operate/application/disagree/"+$("input[name='roomId']").val(),[id],function (){
        location.reload();
    });
}
$(function (){
    sendFORM($("#formApply"),function (data) {
        alert(data.mes);
        location.reload();
    });

})