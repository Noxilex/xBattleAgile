package mainPackage;

import java.io.File;
import java.util.ArrayList;

/**
 * @author cailliea
 * Class managing all the player's data
 */
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
		if (skinImg.isEmpty()) {
			this.skinImg="basic"+p;
		} else {
			this.skinImg = skinImg;
		}

	}
	
	/**
	 * @param pseudo
	 * Change the current pseudo to new pseudo
	 */
	public void setPseudo(String pseudo) {		
		this.pseudo = pseudo;
		System.out.println("The new player's nickname is "+pseudo);
	}
	
	/**
	 * @return the nickname of the player
	 */
	public String getPseudo() {
		System.out.println("The player's nickname is "+this.pseudo);
		return this.pseudo;
	}
	
	
}
