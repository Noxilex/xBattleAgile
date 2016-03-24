package mainPackage;

import java.io.File;
import java.util.ArrayList;

public class Player {

	private String p = File.pathSeparator;
	private String rscPath = ".."+p+".."+p+"Resources"+p+"Textures"+p+"Players"+p;
	private String pseudo;
	private int gameId;
	private String skinImg;
	private int nbrCaseOwned;
	private ArrayList<Case> ownedCases;
	
	public Player(String pseudo, int gameId, String skinImg) {
		this.pseudo = pseudo;
		this.gameId = gameId;
		this.skinImg = skinImg;
	}

}
