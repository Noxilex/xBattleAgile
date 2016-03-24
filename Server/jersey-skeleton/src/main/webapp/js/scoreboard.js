var myJSONObject = {"players": [
        {"name": "Henri", "score": 10},
        {"name": "Paul", "score": 20},
        {"name": "Claude", "score" : 15}
    ]
};
var players = myJSONObject.players;
var nbLigne = players.length;
var table = $('#scoreboard');
table.html('');

// génération des cellules de la table à l'image de l'exemple
for(i=0; i < nbLigne; i++){
	var row = $('<tr></tr>');
	var p = players[i];
	for (prop in p){
		var rawData = $('<td>'+p[prop]+'</td>');
		row.append(rawData);
	}
	table.append(row);
}
