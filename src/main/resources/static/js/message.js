function agree(e){
    var id_ = e.id;
    var id = id_.substr(6);
    var id = parseInt(id);
    sendJSON("post","/operate/invitation/agree",id,function (){
        location.reload();
    });
}
function disagree(e){
    var id_ = e.id;
    var id = id_.substr(9);
    var id = parseInt(id);
    sendJSON("post","/operate/invitation/disagree",id,function (){
        location.reload();
    });
}