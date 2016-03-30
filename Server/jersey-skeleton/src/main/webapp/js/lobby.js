var players = [	{name: "Toto", avatar: "red"},
				{name: "Tata", avatar: "blue"},
				{name: "Titi", avatar: "green"}];


function updateLobby(){

	players = [];

	$.get("v1/userdb/lobby/", function(data, status){
		console.log(data);
		console.log(status);
		for(var i in data){
			console.log(data[i]);
		}
		//console.log(typeof(data));
	})
	.done(function() {
		console.log("Update lobby complete.");
    	//alert( "second success" );
  	})
	.fail(function() {
		console.log("Update lobby failed.");
  	});

	var playersList = $(".queue_container .queue_line .queue_item");
	for(var i = 0 ; i < 8 ; i++){
		var playerItem = $(playersList[i]);
		if(players[i]){
			playerItem.children(".queue_name").text(players[i].name);
			playerItem.children(".queue_avatar").css("background-color", players[i].avatar);
		}else{
			playerItem.children(".queue_name").text("Empty");
			playerItem.children(".queue_avatar").css("background-color", "LightGray");
		}
	}
}

function join(){
	var pseudo = $("#nickname-input").val();
	var url = "v1/userdb/lobby/adduser?pseudo="+pseudo+"&id=0&skin=red";
	$.ajax({
   		type: "PUT",
   		url: url,
   		success: function(response) {
			console.log(response);
     		console.log("User joinded lobby.");
     		updateLobby();
   		},
		error: function(response){
			console.log("Add user error.");
		}
	});
}

function leave(){
	$("#lobby-page").hide();
	$("#game-page").show();
	init();
}

$(function(){
})
