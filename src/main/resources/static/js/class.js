$(function (){
    queryMembers($("select[name='isNotTeam']"),$("input[name='classId']").val());
    sendFORM($("form"),function (data) {
        alert(data.mes);
        location.reload();
    });

})
function updateAnoun(e) {
    sendJSON("post","/update/class/announcement",{announcement:$(e).val(),id:$("input[name='classId']").val()},function (data) {
        location.reload();
    })
}

function createTeamInfos() {
    sendQUERY("post","/create/mission",{classId: $("input[name='classId']").val()},function (data) {
        alert(data.mes);
    })
}
function autoUpload(e) {
    $(e).parent().submit();
}

function GetFile() {
    $("#file").click();
}
function queryMembers(e) {
    const isNotTeam = $(e).val();
    sendQUERY("post","/data/query/classMembers",{classId:$("input[name='classId']").val(),isNotTeam:isNotTeam},function (data) {
        const c = $("#memberList");
        c.empty();
        let str='';
        for(const x in data){
            const y = data[x]
            str+="<li><img src=\"{0} \" /> <a href='/{1}/index'>{2}</a></li>".format(y.headshot,y.no,y.name);
        }
        c.append(str);
        $("#num").text("班级成员 ("+data.length+")");
    });
}