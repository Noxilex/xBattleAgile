package mainPackage;

import java.io.File;
import java.util.ArrayList;

import utilities.Case;

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
	 * GAME MECANIC FUNCTION
	 * 
	 * Calculate the number of case owned
	 */
	public int calculateNumberCaseOwned(Map map) {
		
		Case[][] c = map.getCaseMap();

		for (int x=0; x<c.length; x++) {
			for (int y=0; y<c[0].length; y++) {
				
			}
		}
	}
	
	
	/**
	 * @param nbr
	 * Set the number of cases owned by the player
	 */
	public void setNbrCaseOwned(int nbr) {
		this.nbrCaseOwned = nbr;
		System.out.println("Set "+this.nbrCaseOwned+" cases owned for the player.");
	}
	
	/**
	 * @return Number of cases owned by the player
	 */
	public int getNbrCaseOwned() {
		System.out.println(pseudo+" possess "+this.nbrCaseOwned+" cases.");
		return this.nbrCaseOwned;
	}
	
	/**
	 * @return the Path to the Players textures pack folder
	 */
	public String getResourcePath() {
		System.out.println("The player resource path is "+this.rscPath);
		return rscPath;
	}
	
	/**
	 * @return the Skin image name
	 */
	public String getSkinImg() {
		System.out.println("The Skin image name is "+this.skinImg);
		return this.skinImg;
	}
	
	/**
	 * @param skinImg
	 * Set the new name of the Skin image
	 */
	public void setSkinImg(String skinImg) {
		this.skinImg = skinImg;
		System.out.println("The skin image is "+this.skinImg);
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
