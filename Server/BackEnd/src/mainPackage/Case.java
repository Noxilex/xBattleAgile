package mainPackage;

public class Case {

	private Coord coord;
	private int owner;
	private boolean[] pipes;
	private int level;
	private int fieldType;

	public Case(Coord coord) {
		this.coord = coord;
		owner = 0;
		pipes = new boolean[]{false, false, false, false, false, false, false, false};
		level = 0;		
	}
	
	
	
}
