package mainPackage;

import utilities.Case;

public class GameMecanics {

	private Case[][] map;
	private final int DLEFT =4; 
	private final int DBOT = 2;
	private final int DTOP = 8;
	private final int DRIGHT = 6;

	public GameMecanics() {}

	public Map refreshMap(Map originMap) {

		originMap.setCaseMap(calculateEverything(originMap.getCaseMap()));
//		System.out.println("{}"+originMap.getCaseMap()[5][5].getLevel()+"{}\n");
		return originMap;
	}

	private Case[][] calculateEverything(Case[][] cMap) {
		this.map = cMap;
		levelManager();
		return map;
	}

	private void levelManager() {
		int tmp = 0;
		for (int x=0; x<map.length; x++) {
			for (int y=0; y<map[0].length; y++) {
				Case c 		= map[x][y];
				Case left = new Case();
				Case right =  new Case();
				Case up = new Case ();
				Case down = new Case();
				if (isAllowed(c, c.getPipes(), x, y)) {
					if ( x != 0) {
						left = map[x-1][y];
					} if ( x != map.length-1) {
						right = map[x+1][y];
					} if (y != 0) {
						up = map[x][y-1];
					} if (y != map[0].length-1)  { 
						down = map[x][y+1];
					}
				
					tmp = (int)c.getLevel()/5;
					boolean done = false;
				
					//c.setLevel(tmp);
					switch (c.getPipes()) {
						case 1:
							if (isAllowed(c, DLEFT, x, y)) {
								left.setLevel(left.getLevel()+tmp/2);
								left.setOwner(c.getOwner());
								done = true;
								System.out.println("Left Pipe activated. "+tmp/2+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}

							if (isAllowed(c, DBOT, x, y)) {
								down.setLevel(down.getLevel()+tmp/2);
								down.setOwner(c.getOwner());
								done = true;
								System.out.println("Down Pipe activated. "+tmp/2+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}
							if (done) {
								c.setLevel(c.getLevel()-tmp);
							}
						break;
						case 2:
							if (isAllowed(c, DBOT, x, y)) {
								down.setLevel(down.getLevel()+tmp);
								down.setOwner(c.getOwner());
								done = true;
								System.out.println("Down Pipe activated. "+tmp+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}
							if (done) {
								c.setLevel(c.getLevel()-tmp);
							}
						break;
						case 3:
							if (isAllowed(c, DBOT, x, y)) {
								down.setLevel(down.getLevel()+tmp/2);
								down.setOwner(c.getOwner());
								done = true;
								System.out.println("Down Pipe activated. "+tmp/2+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}
							if (isAllowed(c, DRIGHT, x, y)) {							
								right.setLevel(right.getLevel()+tmp/2);						
								right.setOwner(c.getOwner());
								done = true;
								System.out.println("Right Pipe activated. "+tmp/2+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}
							if (done) {
								c.setLevel(c.getLevel()-tmp);
							}
						break;
						case 4:
							if (isAllowed(c, DLEFT, x, y)) {
								left.setLevel(left.getLevel()+tmp);
								left.setOwner(c.getOwner());
								done = true;
								System.out.println("Left Pipe activated. "+tmp+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}
							if (done) {
								c.setLevel(c.getLevel()-tmp);
							}
						break;
						case 5:
							//neutral pipe
						break;
						case 6:
							if (isAllowed(c, DRIGHT, x, y)) {
								right.setLevel(right.getLevel()+tmp);						
								right.setOwner(c.getOwner());
								done = true;
								System.out.println("Right Pipe activated. "+tmp+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}
							if (done) {
								c.setLevel(c.getLevel()-tmp);
							}
						break;
						case 7:
							if (isAllowed(c, DLEFT, x, y)) {
								left.setLevel(left.getLevel()+tmp/2);
								left.setOwner(c.getOwner());
								done = true;
								System.out.println("Left Pipe activated. "+tmp/2+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}								
							if (isAllowed(c, DTOP, x, y)) {
								up.setLevel(up.getLevel()+tmp/2);
								up.setOwner(c.getOwner());
								done = true;
								System.out.println("Up Pipe activated. "+tmp/2+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}
							if (done) {
								c.setLevel(c.getLevel()-tmp);
							}
						break;
						case 8:
							if (isAllowed(c, DTOP, x, y)) {
								up.setLevel(up.getLevel()+tmp);
								up.setOwner(c.getOwner());
								done = true;
								System.out.println("Up Pipe activated. "+tmp+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}
							if (done) {
								c.setLevel(c.getLevel()-tmp);
							}
						break;
						case 9:
							if (isAllowed(c, DTOP, x, y)) {
								up.setLevel(up.getLevel()+tmp/2);
								up.setOwner(c.getOwner());
								done = true;
								System.out.println("Up Pipe activated. "+tmp/2+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}
							if (isAllowed(c, DRIGHT, x, y)) {
								right.setLevel(right.getLevel()+tmp/2);						
								right.setOwner(c.getOwner());
								done = true;
								System.out.println("Right Pipe activated. "+tmp+" transfered from "+c.coord.toString()+"[lvl:"+c.getLevel()+"]");
							}
							if (done) {
								c.setLevel(c.getLevel()-tmp);
							}
						break;
						default:
							System.out.println("Direction Bug");
						break;												
					}	
				}

			}
		}
	}

	private boolean isAllowed(Case c, int direction, int x, int y) {
		if (getBorderType(c, x, y).contains("left") && direction==4) {
			return false;
		}
		if (getBorderType(c, x, y).contains("bot") && direction==2) {
			return false; 
		}
		if (getBorderType(c, x, y).contains("right") && direction==6) {
			return false;
		}
		if (getBorderType(c, x, y).contains("top") && direction==8) {
			return false;
		}
		return true;
	}

	private String getBorderType(Case c, int x, int y) {
		String borderType = "";
		if (c.coord.x()==0) {
			borderType+="left";
		} else if (c.coord.x()==map.length-1) {
			borderType+="right";
		}
		if (c.coord.y()==0) {
			borderType+="top";
		} else if (c.coord.y()==map[0].length-1) {
			borderType+="bot";
		}
		return borderType;
	}
}