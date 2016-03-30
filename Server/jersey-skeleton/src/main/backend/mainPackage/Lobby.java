package mainPackage;

import java.util.ArrayList;

public class Lobby {
	
	private String test;

	private ArrayList<Player> listPlayers = new ArrayList<Player>();

	public Lobby() {
	}
	
	public String getTest() {
		return test;
	}
	
	public void setTest(String test) {
		this.test = test;
	}

	public ArrayList<Player> addPlayer(Player p) {
		if (listPlayers.size() < 8)
			listPlayers.add(p);
		return listPlayers;
	}

	public ArrayList<Player> getListPlayer() {
		return listPlayers;
	}
	
	public void setListPlayers(ArrayList<Player> listPlayers) {
		this.listPlayers = listPlayers;
	}

	public ArrayList<Player> removePlayerFromList(Player p) {
		listPlayers.remove(p);
		return listPlayers;
	}

}