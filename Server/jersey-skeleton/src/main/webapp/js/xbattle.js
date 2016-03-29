//Initializing
var canvas;
var ctx;

var CELL_SIZE = 20;
var MAP_X;
var MAP_Y;
var MAP_TEXTURE;
var map = [];

var remoteMap = [];

var lastCellUnderMouse;

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
	var pipeNum = null;
	switch(keycode){
		case 97:
		case 87:
			pipeNum = 1;
			break;
		case 98:
		case 88:
			pipeNum = 2;
			break;
		case 99:
		case 67:
			pipeNum = 3;
			break;
		case 100:
		case 81:
			pipeNum = 4;
			break;
		case 101:
		case 83:
			pipeNum = 5;
			break;
		case 102:
		case 68:
			pipeNum = 6;
			break;
		case 103:
		case 65:
			pipeNum = 7;
			break;
		case 104:
		case 90:
			pipeNum = 8;
			break;;
		case 105:
		case 69:
			pipeNum = 9;
			break;
	}
	cell.pipe = pipeNum;

	drawMap();
    $("#coord").text("Pipe : " + cell.pipe);
	return cell;

}

function Cell(x, y, type, pipe=5){
	this.x = x;
	this.y = y;
	this.type = type;
	this.player = 0;
	this.pipe = pipe;
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
	console.log("ymax:"+remoteMap[0].item.length);
	console.log("xmax:"+remoteMap.length);
	MAP_X=remoteMap.length;
	MAP_Y=remoteMap[0].item.length;
	canvas.width=MAP_X*CELL_SIZE;
	canvas.height=MAP_Y*CELL_SIZE;
	map = [];
	for(j=0;j<MAP_Y;j++){
		console.log(map);
		var subMap = [];
		for(i=0;i<MAP_X;i++){
			subMap.push(new Cell(i, j, remoteMap[i].item[j].fieldType, remoteMap[i].item[j].pipes));
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
			    ctx.lineTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2), j*CELL_SIZE+Math.floor(CELL_SIZE/2));
			    ctx.lineWidth = 2;
			    ctx.stroke();
			}
			if(pipe >= 7 && pipe <= 9){
				ctx.beginPath();
			    ctx.moveTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2), j*CELL_SIZE);
			    ctx.lineTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2), j*CELL_SIZE+Math.floor(CELL_SIZE/2));
			    ctx.lineWidth = 2;
			    ctx.stroke();
			}
			if(pipe === 3 || pipe === 6 || pipe === 9){
				ctx.beginPath();
			    ctx.moveTo((i+1)*CELL_SIZE, j*CELL_SIZE+Math.floor(CELL_SIZE/2));
			    ctx.lineTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2), j*CELL_SIZE+Math.floor(CELL_SIZE/2));
			    ctx.lineWidth = 2;
			    ctx.stroke();
			}
			if(pipe >= 1 && pipe <= 3){
				ctx.beginPath();
			    ctx.moveTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2), (j+1)*CELL_SIZE);
			    ctx.lineTo(i*CELL_SIZE+Math.floor(CELL_SIZE/2), j*CELL_SIZE+Math.floor(CELL_SIZE/2));
			    ctx.lineWidth = 2;
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
	console.log(response);
     console.log("Send pipe success");
   },
	error: function(response){
		console.log("Send pipe error");
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

function gameLoop(){

}


/************************* MAIN LOOP *************************/

function mainUpdate(){
	getMap();
	drawMap();
	console.log("Updating");
}

//Draws data from server
function mainLoopDraw(){
	setInterval(mainUpdate(),500);
}

/*************************************************************/


function init(){
	canvas = $("#gamepanel")[0];
	ctx = canvas.getContext("2d");

	

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

	//Create a new pipe when a key is pressed
	$(canvas).keydown(function(event){
        var cell = updatePipe(event.which || event.keyCode);
		sendPipe(cell.x,cell.y,cell.pipe,cell.player);
    });

    getMap();
	drawMap();

	var interval = setInterval(mainUpdate, 1000);




}
