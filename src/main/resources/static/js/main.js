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

function search(type,value){
    sendQUERY("POST","/data/query/room",{type:type,param:value},function (data){
        $("#rooms").empty();
        for (let i = 0; i<data.length; i++) {
            let str = "<div class='teaming-room'>" +
                "<div class='card'>" +
                "<div class='additional'>" +
                "<div class='user-card'>" +
                "<div class='points center'>" +
                "<a href=\"{0}\" >{1}</a>".format("#", data[i].user.user) +
                "</div>" +
                "<img src=\"{0}\" style=\"border-radius: 50%;margin-top: 40px;margin-left: 7px;width: 135px;height: 135px;\"></img>".format(data[i].headShot) +
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
            "<a href='{0}'><h5>{1}</h5></a>".format("#",data[i].teacher.user)+
            "</ul>"+
            "</div>"+
            "<div class='todo-btn'>"+
            "<button class=\"btn btn-info check-btn\" id=\"check_{0}\"><i class='fa fa-user'></i>查看({1}/{2})</button>".format(data[i].room.id,data[i].room.curNum,data[i].room.targetNum)+
            "<button class='btn btn-info join-btn' th:id='join_{0}'>申请加入</button>".format(data[i].room.id)+
            "</div></div></div>"+
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
function queryMember(event){
    var e = $(event.target);
    var id = $(this).id;
}
function joinRoom(){

}
function changecolor(){
    val = $("#color").val();
    $(".modal-header").css("background", val);
}