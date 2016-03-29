package mainPackage;

import utilities.Case;

public class GameMecanics {

	private Case[][] map;

	public GameMecanics() {}

	public Map refreshMap(Map originMap) {

		originMap.setCaseMap(calculateEverything(originMap.getCaseMap()));

		return originMap;
	}

	private Case[][] calculateEverything(Case[][] cMap) {
		this.map = cMap;
		levelManager();
		return map;
	}

	private void levelManager() {
		double tmp = 0;
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
				
					tmp = c.getLevel()*0.20;
				
				
					//c.setLevel(tmp);
					switch (c.getPipes()) {
						case 1:
							left.setLevel(left.getLevel()+tmp/2);
							left.setOwner(c.getOwner());

							down.setLevel(down.getLevel()+tmp/2);
							down.setOwner(c.getOwner());
							c.setLevel(tmp);
						break;
						case 2:
							down.setLevel(down.getLevel()+tmp);
							down.setOwner(c.getOwner());
							c.setLevel(tmp);
						break;
						case 3:
							down.setLevel(down.getLevel()+tmp/2);
							down.setOwner(c.getOwner());
							
							right.setLevel(right.getLevel()+tmp/2);						
							right.setOwner(c.getOwner());
							c.setLevel(tmp);
						break;
						case 4:
							left.setLevel(left.getLevel()+tmp);
							left.setOwner(c.getOwner());
							c.setLevel(tmp);
						break;
						case 5:
							//neutral pipe
						break;
						case 6:
							right.setLevel(right.getLevel()+tmp);						
							right.setOwner(c.getOwner());
							c.setLevel(tmp);
						break;
						case 7:
							left.setLevel(left.getLevel()+tmp/2);
							left.setOwner(c.getOwner());

							up.setLevel(up.getLevel()+tmp/2);
							up.setOwner(c.getOwner());
							c.setLevel(tmp);
						break;
						case 8:
							up.setLevel(up.getLevel()+tmp);
							up.setOwner(c.getOwner());
							c.setLevel(tmp);
						break;
						case 9:
							up.setLevel(up.getLevel()+tmp/2);
							up.setOwner(c.getOwner());

							right.setLevel(right.getLevel()+tmp/2);						
							right.setOwner(c.getOwner());
							c.setLevel(tmp);
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
		if (getBorderType(c, x, y).contains("left") && (direction==1 || direction==4 || direction==7)) {
			return false;
		}
		if (getBorderType(c, x, y).contains("bot") && (direction==1 || direction==2 || direction==3)) {
			return false; 
		}
		if (getBorderType(c, x, y).contains("right") && (direction==3 || direction==6 || direction==9)) {
			return false;
		}
		if (getBorderType(c, x, y).contains("top") && (direction==7 || direction==8 || direction==9)) {
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