//Initializing
var canvas = $("#gamepanel")[0];
var ctx = canvas.getContext("2d");

var WIDTH = canvas.width;
var HEIGHT = canvas.height;

var CELL_SIZE = 40;
var MAP_X = 15;
var MAP_Y = 15;

var map;

var lastCellUnderMouse;

buildMap();
drawMap();


//Event on monsedown
$(canvas).click(function(event){
	lastCellUnderMouse.type = (lastCellUnderMouse.type+1)%5;
	drawMap();
});

//Hover of the mouse
$(canvas).mousemove(function(event){
	lastCellUnderMouse = cellUnderMouse();
	$("#coord").text('Mouse position: ' + lastCellUnderMouse.x + ', ' + lastCellUnderMouse.y);
	drawMap();
	ctx.fillStyle="rgba(200, 255, 255, 0.5)";
	ctx.fillRect(lastCellUnderMouse.x*CELL_SIZE+1, lastCellUnderMouse.y*CELL_SIZE+1, CELL_SIZE-2, CELL_SIZE-2);
});

function buildMap(){
	map = [];
	for(var j = 0 ; j < MAP_Y ; j++){
		var subMap = [];
		for(var i = 0 ; i < MAP_X ; i++){
			subMap.push(new Cell(i, j, Math.floor(Math.random()*5)));
		}
		map.push(subMap);
	}
}



function getMousePos(canvas, evt) {
    var rect = canvas.getBoundingClientRect();
    return {
      	x: evt.clientX - rect.left - 2,
      	y: evt.clientY - rect.top - 2
    };
}


function cellUnderMouse(){
	var mousePos = getMousePos(canvas, event);
	return map[Math.floor(mousePos.y/CELL_SIZE)][Math.floor(mousePos.x/CELL_SIZE)];
}


/*
0: land
1: water
2: deep-water
3: mountain
4: high-mountain
*/
function drawMap(){
	for(j = 0 ; j < MAP_Y ; j++){
		for(i = 0 ; i < MAP_X ; i++){
			switch(map[j][i].type){
				case 0:
					ctx.fillStyle="#F6E3CE"; //Beige
					break;
				case 1:
					ctx.fillStyle="#6666FF";
					break;
				case 2:
					ctx.fillStyle="#6666AA";
					break;
				case 3:
					ctx.fillStyle="#D7A25E";
					break;
				case 4:
					ctx.fillStyle="#B78B51";
					break;
			}
			ctx.fillRect(i*CELL_SIZE+1, j*CELL_SIZE+1, CELL_SIZE-2, CELL_SIZE-2);

			var pipe = map[j][i].pipe;
			if(pipe === 1 || pipe === 4 || pipe === 7){
				ctx.beginPath();
			    ctx.moveTo(i*CELL_SIZE, j*CELL_SIZE+Math.floor(CELL_SIZE/2));
			    ctx.lineTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2)+3, j*CELL_SIZE+Math.floor(CELL_SIZE/2));
			    ctx.lineWidth = 5;
			    ctx.stroke();
			}
			if(pipe >= 7 && pipe <= 9){
				ctx.beginPath();
			    ctx.moveTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2), j*CELL_SIZE);
			    ctx.lineTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2), j*CELL_SIZE+Math.floor(CELL_SIZE/2)+3);
			    ctx.lineWidth = 5;
			    ctx.stroke();
			}
			if(pipe === 3 || pipe === 6 || pipe === 9){
				ctx.beginPath();
			    ctx.moveTo((i+1)*CELL_SIZE, j*CELL_SIZE+Math.floor(CELL_SIZE/2));
			    ctx.lineTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2)-3, j*CELL_SIZE+Math.floor(CELL_SIZE/2));
			    ctx.lineWidth = 5;
			    ctx.stroke();
			}
			if(pipe >= 1 && pipe <= 3){
				ctx.beginPath();
			    ctx.moveTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2), (j+1)*CELL_SIZE);
			    ctx.lineTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2), j*CELL_SIZE+Math.floor(CELL_SIZE/2)-3);
			    ctx.lineWidth = 5;
			    ctx.stroke();
			}

		}
	}	
}


function updatePipe(keycode){
	var cell = lastCellUnderMouse;
	switch(keycode){
		case 97:
		case 87:
			cell.pipe = 1;
			break;
		case 98:
		case 88:
			cell.pipe = 2;
			break;
		case 99:
		case 67:
			cell.pipe = 3;
			break;
		case 100:
		case 81:
			cell.pipe = 4;
			break;
		case 101:
		case 83:
			cell.pipe = 5;
			break;
		case 102:
		case 68:
			cell.pipe = 6;
			break;
		case 103:
		case 65:
			cell.pipe = 7;
			break;
		case 104:
		case 90:
			cell.pipe = 8;
			break;;
		case 105:
		case 69:
			cell.pipe = 9;
			break;
	}

	drawMap();
    $("#coord").text("Pipe : " + cell.pipe);

}

function Cell(x, y, type){
	this.x = x;
	this.y = y;
	this.type = type;
	this.player;
	this.pipe = 5;
}




function login(){
	var login = $("#nickname-input").val();
	var pwd = $("#password-input").val();
	$.get("v1/userdb/"+login, function(data, status){
		console.log("Data: " + data + "\nStatus: " + status);
	});
	//Serveur connection.
	swal("Logged", "Welcome back " + login + ".", "success");
	$("#group-auth").hide();
	$("#gamepanel").show();
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
			$("#gamepanel").show();
			afficheUser(data);
		},
		error :function(jqXHR, textStatus, errorThrown) {
			swal("Register failed", "The username \"" + username + "\" is already taken.", "error");
			alert('postUser error: ' + textStatus);
		}
	});
}

$(function(){

	

	$(canvas).keydown(function(event){
        var pressed = event.which || event.keyCode;
        updatePipe(pressed);
    });

    canvas.focus();

});
