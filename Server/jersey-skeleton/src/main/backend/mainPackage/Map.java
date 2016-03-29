package mainPackage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


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
	private Case[][] emptyMap;
	private final int PLAIN=0;
 	private final int SAND=1;
 	private final int HILL1=2;
 	private final int HILL2=3;
 	private final int HILL3=4;
 	private final int WATER1=5;
 	private final int WATER2=6;
 	private final int WATER3=7;
	
	public Map() {
		this.activeTexturePack = "Default";
		this.intMap = generateNewMap(30, 20);
		this.caseMap = generateCaseMap(intMap);
	//	this.caseMap = new Case[10][10];
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
		for (int y=0; y<lenY; y+=2) {
			for (int x=0; x<lenX; x+=2) {
				int random = rand.nextInt(10);
				if (random==8) {
					random=0;
				} else if (random==9) {
					random=1;
				}
				printField(iMap, random, x, y);
			}
		}
		return iMap;
	}
/**
* @return the type of the field.
* 0 = PLAIN ; 1 = SAND 
* 2 = hill 1; 3 = hill 2; 4 = hill 3
* 5 = WATER1; 6 = WATER2; 7 = WATER3
*/
	private int[][] printField(int[][] iMap, int fieldId, int x, int y) {
		if (fieldId<=HILL1 || fieldId==WATER1) {
			for (int cptY=y-2; cptY<y+2; cptY++) {
				for (int cptX=x-2; cptX<x+2; cptX++) {
					if (setOnMap(cptX, cptY, iMap)) {
						iMap[cptX][cptY]=fieldId;					
					}
				}
			}
		} else {
			int defaut=rand.nextInt(2);
			int border;
			int miborder;
			int center;
			if (fieldId==HILL2) {
				border=defaut;
				miborder=HILL1;
				center=HILL2;
			} else if (fieldId==HILL3) {
				border=HILL1;
				miborder=HILL2;
				center=HILL3;
			} else if (fieldId==WATER2) {
				border=defaut;
				miborder=WATER1;
				center=WATER2;	
			} else if (fieldId==WATER3) {
				border=WATER1;
				miborder=WATER2;
				center=WATER3;
			} else {
				border=defaut;
				miborder=border;
				center=miborder;
			}
			for (int cptY=y-2; cptY<y+2; cptY++) {
				for (int cptX=x-2; cptX<x+2; cptX++) {
					if (setOnMap(cptX, cptY, iMap)) {
						if (cptX==x-2 || cptY==y-2 || cptX==x+2 || cptY==y+2) {
							iMap[cptX][cptY]=border;
						}
						if (cptX==x-1 || cptY==y-1 || cptX==x+1 || cptY==y+1) {
							iMap[cptX][cptY]=miborder;
						}
						if (cptX==x-1 || cptX==x || cptY==y || cptY==y+1) {
						iMap[cptX][cptY]=center;	
						}
					}
				}
			}
		}
		return iMap;
	}
	/**
	* @param x
	* @param y
	* @param intMap
	* @return if the (x,y) cell of the map is editable
	*/
	boolean setOnMap(int x, int y, int[][] intMap) {
		if (x<0 || x>=intMap.length || y<0 || y>=intMap[0].length) {
			return false;
		}
		return true;
	}


	/**
	 * @param intMap
	 * @return the Case type array version of the Map (translated from the intMap version
	 */
	private Case[][] generateCaseMap(int[][] intMap) {
		Case[][] cMap = new Case[intMap.length][intMap[0].length];
		for (int y=0; y<intMap[0].length; y++) {
			for (int x=0; x<intMap.length; x++) {
				cMap[x][y] = new Case(new Coord(x, y), intMap[x][y]);
				if (rand.nextInt(10)==5) {
					cMap[x][y].setPump(true);
				}
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
		for (int y=0; y<map[0].length; y++) {
			System.out.print("|");
			for (int x=0; x<map.length; x++) {
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


