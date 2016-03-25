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
	
	public Player(){}
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
		return 0;
	}
	
	
	/**
	 * @param nbr
	 * Set the number of cases owned by the player
	 */
	public void setNbrCaseOwned(int nbr) {
		this.nbrCaseOwned = nbr;
	}
	
	/**
	 * @return Number of cases owned by the player
	 */
	public int getNbrCaseOwned() {
		return this.nbrCaseOwned;
	}
	
	/**
	 * @return the Path to the Players textures pack folder
	 */
	public String getResourcePath() {
		return rscPath;
	}
	
	/**
	 * @return the Skin image name
	 */
	public String getSkinImg() {
		return this.skinImg;
	}
	
	/**
	 * @param skinImg
	 * Set the new name of the Skin image
	 */
	public void setSkinImg(String skinImg) {
		this.skinImg = skinImg;
	}
	
	/**
	 * @param pseudo
	 * Change the current pseudo to new pseudo
	 */
	public void setPseudo(String pseudo) {				
		this.pseudo = pseudo;
	}
	
	/**
	 * @return the nickname of the player
	 */
	public String getPseudo() {
		return this.pseudo;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getRscPath() {
		return rscPath;
	}

	public void setRscPath(String rscPath) {
		this.rscPath = rscPath;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public ArrayList<Case> getOwnedCases() {
		return ownedCases;
	}

	public void setOwnedCases(ArrayList<Case> ownedCases) {
		this.ownedCases = ownedCases;
	}
	
	
	
	
}
