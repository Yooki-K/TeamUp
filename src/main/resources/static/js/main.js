function showCreateForm(){
    $('.CreateBox').fadeIn('fast');
    $('.Create-footer').fadeIn('fast');
    $('.modal-title').html('创建团队');
}
function openCreateModal(){
    showCreateForm();
    setTimeout(function(){
    $("#createModal").modal("show");}  , 230);
}

function loginAjax(){
/*   Remove this comments when moving to server
$.post( "/login", function( data ) {
        if(data == 1){
            window.location.replace("/home");            
        } else {
             shakeModal(); 
        }
    });
*/

/*   Simulate error message from the server   */
 shakeModal();
}

function shakeModal(){
$('#loginModal .modal-dialog').addClass('shake');
         $('.error').addClass('alert alert-danger').html("Invalid email/password combination");
         $('input[type="password"]').val('');
         setTimeout( function(){ 
            $('#loginModal .modal-dialog').removeClass('shake'); 
}, 1000 ); 
}
$(function (){
    init();
    $('#search').bind('keypress',function (event){
        if(event.keyCode == 13){
            search("other", $(this).val());
        }
    });
});
function init() {
    var url = window.location.href;
    if(url.indexOf("public")!=-1)
        search("public","");
    else{
        var l = url.split("/");
        for(let i=0; i<l.length; i++){
            if(l[i]=="class" && i+1<l.length){
                const classId = l[i + 1];
                $(".searchbar").remove();//移除搜索框
                search("class",classId);
            }
        }
    }
}
function queryMember(obj){
    var id = obj.id;
    var roomId = Number(id.substr(6));
    const ee = $("#members");
    const text =$(obj).parents("div").eq(2).children("div").eq(0).children("h1").eq(0).html();
    $("#member_teamName").text("你正在查看\"{0}\"的队伍成员...".format(text));
    const len = ee.children().length;
    if(len>0){
        ee.empty();
    }
    sendQUERY("post","/data/query/room/member",{roomId:roomId},function(data){
        var str="";
        for(var i=0;i<data.length;i++){
            str+="<li>" +
                "  <img src=\"{0}\"/><a href='/{1}/index'>{2}</a>".format(data[i].headshot,data[i].no,data[i].user) +
                "</li>";
        }
        $("#members").append(str);
    });

}
function joinRoom(){
    var roomId = Number($("#teamid").text());
    var content = $("#joinContent").val();
    sendJSON("post","/apply/room",{roomId:roomId,content:content},function (data) {
        alert(data.mes);
        $("#joinContent").val("");
        $("#closeJoin").click();
    });
}
function setAlert(obj) {
    const id = obj.id;
    var roomId = Number(id.substr(6));
    const teamName = $(obj).parents("div").eq(2).children("div").eq(0).children("h1").eq(0).html();
    $("#teamname").text("你正在加入\"{0}\"...".format(teamName));
    $("#teamid").text("{0}".format(roomId));
}
function createRoom(){
    var form = $('#form1');
    sendQUERY("post","/create/room",form.serialize(),function (data) {
        alert(data.mes);
        location.reload();//刷新
    });
}

function changecolor(){
    val = $("#color").val();
    $(".modal-header").css("background", val);
}
function search(type,value){
    sendQUERY("POST","/data/query/room",{type:type,param:value},function (data){
        $("#rooms").empty();
        for (let i = 0; i<data.length; i++) {
            let str = "<div class='teaming-room'>" +
                "<div class='card'>" +
                "<div class='additional' style=\"background:{0}\">".format(data[i].room.color) +
                "<div class='user-card'>" +
                "<div class='points center'>" +
                "<a href=\"{0}\" >{1}</a>".format("#", data[i].user.user) +
                "</div>" +
                "<img src=\"{0}\" class='captain-img'></img>".format(data[i].user.headshot) +
                "</div>" +
                "<div class='more-info'>" +
                "<div class='group-name'>" +
                "<h1>{0}</h1>".format(data[i].room.teamName) +
                "</div>" +
                "<div class='base-info'>" +
                "<ul class='tags captain'>" +
                "<li>" +
                "<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;队长：</a>" +
                "</li>";
            if(data[i].user.name!=null)
                str+="<h5>{0}</h5>".format(data[i].user.name);
            else
                str+="<h5>{0}</h5>".format( "未实名认证" );
            str+=
                "</ul>"+
                "<ul class='tags teacher'>"+
                "<li>"+
                "<a >指导老师：</a>"+
                "</li>"+
                "<a href='{0}'><h5>{1}</h5></a>".format("#",data[i].teacher)+
                "</ul>"+
                "</div>"+
                "<div class='todo-btn'>"+
                "<div class=\"dropup\" style=\"display: inline;\">"+
                "<button class=\"btn btn-info check-btn dropdown-toggle\" data-target=\"#check\" data-toggle=\"modal\" id=\"check_{0}\" onclick='queryMember(this)'><i class='fa fa-user'></i>查看({1}/{2})</button>".format(data[i].room.id,data[i].room.curNum,data[i].room.targetNum)+
                "<div class=\"dropdown-menu\" id='member'></div>"+
                "<button class='btn btn-info join-btn' id='alert_{0}' data-toggle='modal' data-target=\"#join\" onclick='setAlert(this)'>申请加入</button>".format(data[i].room.id)+ "</div></div></div></div>"+
                "<div class='general'>"+
                "<h1>{0}</h1><p>{1}</p>".format(data[i].room.name,data[i].room.content)+
                "<ul class='tags'>";
            var tags = data[i].tag;
            if(tags!=null){
                tags = tags.split(";");
                var t='';
                for(var j=0;j<tags.length;j++){
                    t+="<li><a>{0}</a></li>".format(tags[j]);
                }
                str+=t;
            }
            str+="</ul><span class='more'>悬浮以查看更多</span></div></div></div>";
            $("#rooms").append(str);
        }
    });
}
