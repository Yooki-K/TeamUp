function showRegisterForm(){
    $('.registerBox').fadeIn('fast');
    $('.register-footer').fadeIn('fast');
    $('.modal-title').html('登录');
}
function openRegisterModal(){
    showRegisterForm();
    setTimeout(function(){
    $("#loginModal").modal("show");}  , 230);
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
