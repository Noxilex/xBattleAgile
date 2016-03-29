package mainPackage;

import java.util.ArrayList;

public class Lobby {
	
	private ArrayList<Player> listPlayers = new ArrayList<Player>();

	public Lobby() {}

	public ArrayList<Player> addPlayer(Player p) {
		listPlayers.add(p);
		return listPlayers;
	}

	public ArrayList<Player> getListPlayer() {
		return listPlayers;
	}

	public ArrayList<Player> removePlayerFromList(Player p) {
		listPlayers.remove(p);
		return listPlayers;
	}

}