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

function showPwd(){
    val = $("#isPwd").val();
    if(val == "none")
    $("#pwd-input").hide();
    else
    $("#pwd-input").show();
}

function changecolor(){
    val = $("#color").val();
    $(".modal-header").css("background", val);
}