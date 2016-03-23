var c = document.getElementById("gamepanel");
var ctx = c.getContext("2d");

ctx.fillStyle= "#000000";
ctx.fillRect= "";
ctx.fillStyle= "#FF0000";

var height = c.height;
var width = c.width;
var numC = 20;
var h = height/numC;
var w = width/numC;
var table = create2DArray(w, h);

//for each (var subT in table) {
//	for each (var v in subT) {
//		ctx.strokeRect();
//	}
//}

for(j = 0; j < numC; j++){
	for(i = 0; i < numC; i++){
		var test = table[j][i];
		if(test==0){
			ctx.strokeRect(i*w,j*h,w,h);
		}	
	}
}

function create2DArray(w, h){
	var t = new Array(h);
		for(i = 0; i < w; i++){
			t[i]=new Array(w).fill(0);
		}
	return t;
}

function drawGrid(){


}