//Initializing
var c = document.getElementById("gamepanel");
var ctx = c.getContext("2d");

var height = c.height;
var width = c.width;
var numC = 20;
var h = height/numC;
var w = width/numC;
var table = create2DArray(w, h);

console.log("Global width:" + width);
console.log("Global height:" + height);
console.log("Cell width:" + w);
console.log("Cell height:" + h);

//Event on monsedown
 c.addEventListener('mousedown', function(evt) {
    var mousePos = getMousePos(c, evt);
	cell=get_cell(mousePos.x, mousePos.y);
	$("#coord").text('Mouse position: ' + cell[0] + ',' + cell[1]);
	modify_cell(cell[0], cell[1], (table[cell[1]][cell[0]]+1)%5);
	draw2DArray();
	drawGrid(w,h,numC);
});
//Hover of the mouse
c.addEventListener('mousemove', function(evt) {
    var mousePos = getMousePos(c, evt);
	cell=get_cell(mousePos.x, mousePos.y);
	$("#coord").text('Mouse position: ' + cell[0] + ',' + cell[1]);
	draw2DArray();
	drawGrid(w,h,numC);
	ctx.strokeStyle="#330000";
	ctx.strokeRect(cell[0]*w, cell[1]*h, w, h);
	ctx.fillStyle="rgba(200, 255, 255, 0.5)";
	ctx.fillRect(cell[0]*w, cell[1]*h, w, h);
});

ctx.fillStyle= "#000000";
ctx.fillRect(0,0,width,height);

table[3][3]=1;
table[3][4]=2;
table[3][5]=3;
table[3][6]=4;
draw2DArray();
drawGrid(w,h,numC);

function getMousePos(canvas, evt) {
    var rect = canvas.getBoundingClientRect();
    return {
      x: evt.clientX - rect.left,
      y: evt.clientY - rect.top
    };
  }

function create2DArray(w, h){
	var t = new Array(h);
		for(i = 0; i < w; i++){
			t[i]=new Array(w).fill(0);
		}
	return t;
}


/*
0: land
1: water
2: deep-water
3: mountain
4: high-mountain
*/
function draw2DArray(){
	for(j = 0; j < numC; j++){
		for(i = 0; i < numC; i++){
			var test = table[j][i];
			if(test==0){
				ctx.fillStyle="#F6E3CE";
			}else if(test==1){
				ctx.fillStyle="#6666FF";
			}else if(test==2){
				ctx.fillStyle="#6666AA";
			}else if(test==3){
				ctx.fillStyle="#D7A25E";
			}else if(test==4){
				ctx.fillStyle="#B78B51";
			}else{
				console.log("Error on cell:["+i+","+j+"]");
			}
			ctx.fillRect(i*w,j*h,w,h);
		}
	}	
}

function drawGrid(w,h,taille){
	ctx.strokeStyle="#000000";
	for(j = 0; j < taille; j++){
		for(i = 0; i < taille; i++){
			ctx.strokeRect(i*w,j*h,w,h);
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
function modify_cell(x, y, value){
	table[y][x]=value;
} 

//Makes one cell flowing to another
function flaw(Cell, Cell, value){
		
}

function reduce_cell(){
	
}


function login(){
	var login = $("#nickname-input").val();
	var pwd = $("#password-input").val();
	$.get("v1/userdb/auth/login?name="+login+"&mdp="+pwd, function(data, status){
		console.log(data.text);
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
		}
	});
}
