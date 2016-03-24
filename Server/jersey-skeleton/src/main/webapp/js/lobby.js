
var players=[];
var table = $('#lobby');

function addPlayer(pname){
	players.push(pname);
	update();
}

function update(){
	table.html("");
	for (p in players) {
  		table.append("<tr><td>"+players[p]+"</td></tr>");
	}
}
