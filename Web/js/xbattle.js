//Initializing
var c = document.getElementById("gamepanel");
c.addEventListener("mousedown", doMouseDown, false);	
var ctx = c.getContext("2d");

function doMouseDown(event) {
	canvas_x = event.pageX;
	canvas_y = event.pageY;
	var cell = get_cell(canvas_x, canvas_y);
	alert("X="+cell[0]+"Y="+cell[1]);
}

var height = c.height;
var width = c.width;
var numC = 20;
var h = height/numC;
var w = width/numC;
var table = create2DArray(w, h);

ctx.fillStyle= "#000000";
ctx.fillRect(0,0,width,height);

table[3][3]=1;
table[3][4]=2;
table[3][5]=3;
table[3][6]=4;
draw2DArray();
drawGrid(w,h,numC);

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
	for(j = 0; j < taille; j++){
		for(i = 0; i < taille; i++){
			ctx.strokeRect(i*w,j*h,w,h);
		}
	}
}

//Return the position of the table where the user clicked
//as a table of coordinates
function get_cell(abs_x, abs_y){
	return [Math.round(abs_x/numC), Math.round(abs_y/numC)];
}

//Modify the content of the cell at the coordinates x,y with 
//the desired value
function modify_cell(x, y, value){
	table[y][x]=value;
} 


function login(){
	var username = $("#nickname-input").val();
	var password = $("#password-input").val();
	
	//Serveur connection.
	swal("Logged", "Welcome back " + username + ".", "success");

	$("#group-auth").hide();
	$("#gamepanel").show();
}

function register(){
	var username = $("#nickname-input").val();
	var password = $("#password-input").val();

	if(Math.random() > 0.5){
		swal("Register failed", "The username \"" + username + "\" is already taken.", "error");
	}else{
		swal("Registered", "Thanks for registering \"" + username + "\".\nYou have been logged in.", "success");
		$("#group-auth").hide();
		$("#gamepanel").show();		
	}

}