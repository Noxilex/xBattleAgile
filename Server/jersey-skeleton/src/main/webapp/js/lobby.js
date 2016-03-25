
var players=[];
var table = $('#lobby');
var nbLigne = players.length;
update();

// génération des cellules de la table à l'image de l'exemple

function addPlayer(pname){
	players.push(pname);
	update();
}

function update(){
	table.html("");
	var header = $('<tr></tr>');
	header.append('<th>Nom</th>');
	table.append(header);
	for (p in players) {
  		table.append("<tr><td>"+players[p]+"</td></tr>");
	}
}

function removeUser(){
players.splice(players.indexOf("undefined"));
update();
}

function clearLobby(){
	players=[];
	update();
}
