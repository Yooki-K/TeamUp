function updateBrief(e) {
    sendJSON("post","/update/team",{teamId:$("input[name='teamId']").val(),brief:$(e).val()},function (data) {
        location.reload();
    })
}

function deleteRoom(no){
    sendQUERY("post","/delete/room",{roomId:$("input[name='roomId']").val()},function (data) {
        alert(data.mes);
        window.location.href="/{0}/room".format(no);
    })
}
function agree(e){
    var id_ = e.id;
    var id = id_.substr(6);
    sendJSON("post","/operate/invitation/agree",[id],function (){
       location.reload();
    });
}
function disagree(e){
    var id_ = e.id;
    var id = id_.substr(9);
    sendJSON("post","/operate/invitation/disagree",[id],function (){
        location.reload();
    });
}
$(function (){
    sendFORM($("#formApply"),function (data) {
        alert(data.mes);
        location.reload();
    });

})