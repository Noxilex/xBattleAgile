package mainPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import utilities.Case;
import utilities.Coord;

/**
 * @author cailliea
 * Class that generate a random Map.
 * Getter and setter of parameter like : the texture pack used for the map.
 * 
 */
public class Map {

	private String p = File.pathSeparator;
	private String rscPath = ".."+p+".."+p+"Resources"+p+"Textures"+p+"Maps"+p;
	private String activeTexturePack;
	private Random rand = new Random();
	private int[][] intMap;
	private Case[][] caseMap;
	
	public Map() {
		this.activeTexturePack = "Default";
		this.intMap = generateNewMap(30, 20);
		this.caseMap = generateCaseMap(intMap);
		logDisplayMap(caseMap);
	}
	
	/**
	 * @return The Case array version of the map
	 */
	public Case[][] getCaseMap() {
		return caseMap;
	}
	
	/**
	 * @param Coord c
	 * @return the integer in the case [c.x][c.y]
	 */
	public int getIdCase(Coord c) {
		return intMap[c.x()][c.y()];
	}
	/**
	 * @return The Currently active texture pack
	 */
	public String getActiveTexturePack() {
		return activeTexturePack;
	}
	
	/**
	 * Set the new name of the active texture pack
	 * @param texturePackName
	 */
	public void setActiveTexturePack(String texturePackName) {
		this.activeTexturePack = texturePackName;
	}
	
	/**
	 * 
	 * @param lenX
	 * @param lenY
	 * @return the fieldTypeId array version of the map 
	 */
	private int[][] generateNewMap(int lenX, int lenY) {
		int[][] iMap = new int[lenX][lenY];
		for (int x=0; x<lenX; x++) {
			for (int y=0; y<lenY; y++) {
				iMap[x][y] = rand.nextInt(8);
			}
		}
		return iMap;
	}

	/**
	 * @param intMap
	 * @return the Case type array version of the Map (translated from the intMap version
	 */
	private Case[][] generateCaseMap(int[][] intMap) {
		Case[][] cMap = new Case[intMap.length][intMap[0].length];
		for (int x=0; x<intMap.length; x++) {
			for (int y=0; y<intMap[0].length; y++) {
				cMap[x][y] = new Case(new Coord(x, y), intMap[x][y]);
			}
		}
		return cMap;
	}

	public void setIntMap(int[][] intMap) {
		this.intMap = intMap;
	}
	

	public void setCaseMap(Case[][] caseMap) {
		this.caseMap = caseMap;
	}


	/**
	 * LOG FUNCTION
	 * @param map
	 * Only for the debug. Display the map in character.
	 */
	private void logDisplayMap(Case[][] map){
		for (int x=0; x<map.length; x++) {
			System.out.print("|");
			for (int y=0; y<map[0].length; y++) {
				int t = map[x][y].getFieldType();
				String s;
				switch (t) {
					case 0 : s = "pl";
					break;
					case 1 : s = "sa";
					break;
					case 2 : s = "h1";
					break;
					case 3 : s = "h2";
					break;
					case 4 : s = "h3";
					break;
					case 5 : s = "w1";
					break;
					case 6 : s = "w2";
					break;
					case 7 : s = "w3";
					break;
					default: s = "##";
					break;
				}
				System.out.print(s+"|");
			}
			System.out.println();
		}
	}
	
}


