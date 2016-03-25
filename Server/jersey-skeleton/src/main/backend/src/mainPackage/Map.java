package src.mainPackage;

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
	private int plain=0;
	private int sand=1;
	private int hill1=2;
	private int hill2=3;
	private int hill3=4;
	private int water1=5;
	private int water2=6;
	private int water3=7;
	
	public Map() {
		this.activeTexturePack = "Default";
		this.intMap = generateNewMap(30, 20);
		this.caseMap = generateCaseMap(intMap);
		logDisplayMap(caseMap);
	}
	


	
	/**
	 * @return The integer array version of the map
	 */
	public int[][] getIntMap() {
		return intMap;
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
	 * @return the path to the Maps textures packs folder
	 */
	public String getResourcePath() {
		return rscPath;
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
		for (int x=0; x<lenX; x+=2) {
			for (int y=0; y<lenY; y+=2) {
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
	 * 0 = plain ; 1 = sand 
	 * 2 = hill 1; 3 = hill 2; 4 = hill 3
	 * 5 = water1; 6 = water2; 7 = water3
	 */
	private int[][] printField(int[][] iMap, int fieldId, int x, int y) {
		if (fieldId<=hill1 || fieldId==water1) {
			for (int cptX=x-2; cptX<x+2; cptX++) {
				for (int cptY=y-2; cptY<y+2; cptY++) {
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
			if (fieldId==hill2) {
				border=defaut;
				miborder=hill1;
				center=hill2;
			} else if (fieldId==hill3) {
				border=hill1;
				miborder=hill2;
				center=hill3;
			} else if (fieldId==water2) {
				border=defaut;
				miborder=water1;
				center=water2;
			} else if (fieldId==water3) {
				border=water1;
				miborder=water2;
				center=water3;
			} else {
				border=defaut;
				miborder=border;
				center=miborder;
			}

			for (int cptX=x-2; cptX<x+2; cptX++) {
				for (int cptY=y-2; cptY<y+2; cptY++) {
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
	private boolean setOnMap(int x, int y, int[][] intMap) {
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
		for (int x=0; x<intMap.length; x++) {
			for (int y=0; y<intMap[0].length; y++) {
				cMap[x][y] = new Case(new Coord(x, y), intMap[x][y]);
			}
		}
		return cMap;
	}
	
	
	/*---------------------------------------LOG FUNCTIONS-------------------------*/	
	
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


