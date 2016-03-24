//Initializing
var canvas = $("#gamepanel")[0];
var ctx = canvas.getContext("2d");

var WIDTH = canvas.width;
var HEIGHT = canvas.height;

var CELL_SIZE = 30;
var MAP_X = 20;
var MAP_Y = 20;

var map;

buildMap();
drawMap();


//Event on monsedown
$(canvas).click(function(event){
	var mousePos = getMousePos(canvas, event);
	var cell = map[Math.floor(mousePos.y/CELL_SIZE)][Math.floor(mousePos.x/CELL_SIZE)];
	$("#coord").text('Mouse position: ' + cell.x + ', ' + cell.y);
	cell.value = (cell.value+1)%5;
	drawMap();
});

//Hover of the mouse
$(canvas).mousemove(function(event){
	var mousePos = getMousePos(canvas, event);
	var cell = map[Math.floor(mousePos.y/CELL_SIZE)][Math.floor(mousePos.x/CELL_SIZE)];
	$("#coord").text('Mouse position: ' + cell.x + ', ' + cell.y);
	drawMap();
	ctx.fillStyle="rgba(200, 255, 255, 0.5)";
	ctx.fillRect(cell.x*CELL_SIZE+1, cell.y*CELL_SIZE+1, CELL_SIZE-2, CELL_SIZE-2);
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
			switch(map[j][i].value){
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
		}
	}	
}

//Return the position of the table where the user clicked
//as a table of coordinates
function get_cell(abs_x, abs_y){
	return [Math.floor(abs_x/h), Math.floor(abs_y/h)];
}

//Modify the content of the cell at the coordinates x,y with 
//the desired value
function modifyCell(x, y, value){
	map[y][x] = value;
}

function Cell(x, y, value){
	this.x = x;
	this.y = y;
	this.value = value;
}


function login(){
	var login = $("#nickname-input").val();
	var pwd = $("#password-input").val();
	$.get("v1/userdb/"+login, function(data, status){
		console.log("Data: " + data + "\nStatus: " + status);
	});
	//Serveur connection.
	swal("Logged", "Welcome back " + login + ".", "success");
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
