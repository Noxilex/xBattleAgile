function login(){
	var login = $("#nickname-input").val();
	var pwd = $("#password-input").val();
	$.get("v1/userdb/auth/login?name="+login+"&mdp="+pwd, function(data, status){
		if(status=="success"){
			//getMap();
			//drawMap();
			$("#authent-page").hide();
		  	$("#lobby-page").show();
		  	update();		
		}
		console.log(data);
		console.log(status);
		//console.log(typeof(data));
	})
	.done(function() {
    	//alert( "second success" );
  	})
	.fail(function() {
		swal("Loggin failed", "No match found between this username and password.", "error");
  	});



	//Serveur connection.
}

function register() {

	var username = $("#nickname-input").val();
	var pwd = $("#password-input").val();
	var url = "v1/userdb/";

	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : url,
		dataType : "json",
		data : JSON.stringify({
			"name" : username,
			"password" : pwd,
			"id" : 0
		}),
		success :function(data, textStatus, jqXHR) {
			swal("Registered", "Thanks for registering \"" + username + "\".\nYou have been logged in.", "success");
			$("#group-auth").hide();
			$("#div-game").show();
			login();
		},
		error :function(jqXHR, textStatus, errorThrown) {
			swal("Register failed", "The username \"" + username + "\" is already taken.", "error");
		}
	});
}

$(function(){

	$(".authent-input").keydown(function(event){
        if((event.which || event.keyCode) === 13){
            login();
        }
    });

});
