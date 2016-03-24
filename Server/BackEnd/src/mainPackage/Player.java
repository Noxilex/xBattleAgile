package mainPackage;

import java.util.ArrayList;

public class Player {
	
	private String pseudo;
	private int gameId;
	private String skinImg;
	private int nbrCaseOwned;
	private ArrayList<Case> ownedCases;
	
	public Player(String pseudo, int gameId, String skinImg) {
		this.pseudo = pseudo;
		this.gameId = gameId;
		
	}

}
