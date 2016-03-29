//Initializing
var canvas = $("#gamepanel")[0];
var ctx = canvas.getContext("2d");

var WIDTH = canvas.width;
var HEIGHT = canvas.height;

var CELL_SIZE = 20;
var MAP_X;
var MAP_Y;
var MAP_TEXTURE;
var map = [];

var remoteMap = [];

var lastCellUnderMouse;

//Event on monsedown
$(canvas).click(function(event){
	lastCellUnderMouse.player = 1;
	console.log(lastCellUnderMouse);
		
	if(lastCellUnderMouse.level<=95 && lastCellUnderMouse.player!=0){
		lastCellUnderMouse.level+=5;
	}
	drawMap();
});

//Hover of the mouse
$(canvas).mousemove(function(event){
	lastCellUnderMouse = cellUnderMouse(event);
	$("#coord").text('Mouse position: ' + lastCellUnderMouse.x + ', ' + lastCellUnderMouse.y);
	drawMap();
	ctx.fillStyle="rgba(200, 255, 255, 0.5)";
	ctx.fillRect(lastCellUnderMouse.x*CELL_SIZE+1, lastCellUnderMouse.y*CELL_SIZE+1, CELL_SIZE-2, CELL_SIZE-2);
});

function getMousePos(event) {
    var rect = canvas.getBoundingClientRect();
    return {
      	x: event.clientX - rect.left - 2,
      	y: event.clientY - rect.top - 2
    };
}


function cellUnderMouse(event){
	var mousePos = getMousePos(event);
	return map[Math.floor(mousePos.y/CELL_SIZE)][Math.floor(mousePos.x/CELL_SIZE)];
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
	this.player = 0;
	this.pipe = 5;
	this.level = 0;
}

/****************************** Début gestion Map **********************************/

function getMap(){
	var url = "v1/userdb/map";
	
	$.getJSON(url, function(result){
        $.each(result, function(i, field){
            console.log("i:"+i+ " field:" + field);
			if(i=="caseMap"){
				remoteMap = field;
				buildMapNew(remoteMap);
				//printRemoteMap();
				//console.log("inside get:"+remoteMap);
			}

        });
    });
}
/*
function buildMapOld(){
	map = [];
	for(var j = 0 ; j < MAP_Y ; j++){
		var subMap = [];
		for(var i = 0 ; i < MAP_X ; i++){
			subMap.push(new Cell(i, j, Math.floor(Math.random()*5)));
		}
		map.push(subMap);
	}
}
*/
function buildMapNew(remoteMap){
	console.log(remoteMap);
	console.log("Building..");
	console.log(remoteMap);
	console.log("ymax:"+remoteMap.length);
	console.log("xmax:"+remoteMap[0].item.length);
	MAP_X=remoteMap.length;
	MAP_Y=remoteMap[0].item.length;
	canvas.width=MAP_X*CELL_SIZE;
	canvas.height=MAP_Y*CELL_SIZE;
	map = [];
	for(j=0;j<MAP_Y;j++){
		var subMap = [];
		for(i=0;i<MAP_X;i++){
			subMap.push(new Cell(i, j, remoteMap[i].item[j].fieldType));
		}
		map.push(subMap);
	}
	console.log("Building finished");
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
			//Terrain
			switch(map[j][i].type){
				case 0:
					ctx.fillStyle="#008000";
					break;
				case 1:
					ctx.fillStyle="#FFFF99";
					break;
				case 2:
					ctx.fillStyle="#CC9900";
					break;
				case 3:
					ctx.fillStyle="#996600";
					break;
				case 4:
					ctx.fillStyle="#660000";
					break;
				case 5:
					ctx.fillStyle="#66CCFF";
					break;
				case 6:
					ctx.fillStyle="#3366FF";
					break;
				case 7:
					ctx.fillStyle="#000099";
					break;
			}
			//Drawing the floor before the player
			ctx.fillRect(i*CELL_SIZE+1, j*CELL_SIZE+1, CELL_SIZE-2, CELL_SIZE-2);		
		
			//Player
			switch(map[j][i].player){
				case 0:
					//ctx.fillStyle="#000099";
				break;
				case 1:
					ctx.fillStyle="#FF0000";
				break;
				case 2:
					ctx.fillStyle="#0000FF";
				break;
				case 3:
					ctx.fillStyle="#0000FF";
				break;
				case 4:
					ctx.fillStyle="#0000FF";
				break;
				case 5:
					ctx.fillStyle="#0000FF";
				break;
				case 6:
					ctx.fillStyle="#0000FF";
				break;
				case 7:
					ctx.fillStyle="#0000FF";
				break;
				case 8:
					ctx.fillStyle="#000000";
				break;
			}
	
			//TODO Modify Cell size
			drawPlayer(i*CELL_SIZE+1, j*CELL_SIZE+1, CELL_SIZE-2, map[j][i].level);
			//ctx.fillRect(i*CELL_SIZE+1, j*CELL_SIZE+1, CELL_SIZE-2, CELL_SIZE-2);

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

function drawPlayer(xRound, yRound, size, fillLvl){
	var missing= 100-fillLvl;
	var missingCell=missing/100*size;
	ctx.fillRect(xRound+(missingCell/2), yRound+(missingCell/2), size-missingCell, size-missingCell);
}

/*********************************** Fin gestion Map **********************************/


//Makes one cell flowing to another
function flaw(Cell, Cell, value){
		
}

function reduce_cell(){
	
}


function login(){
	var login = $("#nickname-input").val();
	var pwd = $("#password-input").val();
	$.get("v1/userdb/auth/login?name="+login+"&mdp="+pwd, function(data, status){
		if(status=="success"){
			getMap();
			drawMap();
			$("#group-auth").hide();
			$("#div-game").show();
			canvas.focus();
			swal("Logged", "Welcome back " + login + ".", "success");		
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


  	$("#authent-page").hide();
  	$("#lobby-page").show();
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
			canvas.focus();
		},
		error :function(jqXHR, textStatus, errorThrown) {
			swal("Register failed", "The username \"" + username + "\" is already taken.", "error");
		}
	});
}

//DEBUG Function
function printRemoteMap(){
	for(i=0;i<remoteMap.length;i++){
		for(j=0;j<remoteMap[0].item.length;j++){
			console.log(remoteMap[i].item[j]);
		}
	}
}

//Sends a pipe to the remote server

function sendPipe(x, y, sens, player){
	var url = "v1/userdb/mapa/putaction?x="+x+"&y="+y+"&sens="+sens+"&player="+player;
	$.ajax({
   type: "PUT",
   url: url,
   success: function(response) {
     console.log("Send pipe success");
   }
});
}
/*
function deletePipe(){
	var url = "/v1/userdb/mapa/putaction";
	$.ajax({
   url: url,
   type: 'DELETE',
   success: function(response) {
     
   }
});
}
*/
$(function(){

	$(".authent-input").keydown(function(event){
        if((event.which || event.keyCode) === 13){
            login();
        }
    });

	//Create a new pipe when a key is pressed
	$(canvas).keydown(function(event){
        updatePipe(event.which || event.keyCode);
    });

    /*var MAP_TEXTURE; = new Image();
    var MAP_TEXTURE;.src = "food.png";
    var MAP_TEXTURE;.onload = function () {
        buildMap();
    };*/

});
