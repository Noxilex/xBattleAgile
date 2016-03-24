package mainPackage;

/**
 * @author cailliea
 * Class about Cases.
 * Storing the coordinates, the ownerId, the active pipes, the level of the liquid
 * and the field-type number
 *	
 */
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

	/**
	 * @return the type of the field.
	 * 0 = plain ; 1 = sand 
	 * 2 = hill 1; 3 = hill 2; 4 = hill 3
	 * 5 = water1; 6 = water2; 7 = water3
	 */
	public int getFieldType() {
		return this.fieldType;
	}

	/** 
	 * @param fieldTypeId
	 * Set the field type via his new id
	 */
	public void setFieldType(int fieldTypeId) {
		this.fieldType = fieldTypeId;
	}
	
	/**
	 * @param change
	 * Change the level of the player liquid via operation.
	 */
	public void changeLevel(int change) {
		this.level+=change;
	}
	
	/**
	 * @param lvl
	 * Set the player liquid level of the case.
	 */
	public void setLevel(int lvl) {
		this.level = lvl;
	}
	
	/**
	 * @return get the player liquid level. 0-100
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * @return The number of active directions
	 */
	public int getNumberOfActivePipes() {
		int actives = 0;
		for (int i=0; i<pipes.length; i++) {
			if (pipes[i]) {actives++;}
		}
		return actives;
	}
	
	/**
	 * @return The Array of the different direction of the pipe.
	 * In clock order, starting at noon. 
	 */
	public boolean[] getPipes() {
		return this.pipes;
	}

	/**
	 * @param pipes 
	 * Set a new direction-array of pipes
	 */
	public void setPipes(boolean[] pipes) {
		this.pipes = pipes;
	}
	
	/**
	 * @param direction
	 * @return Check if the pipe toward the specified direction is active
	 */
	public boolean getDirectionOfPipe(int direction) {
		return this.pipes[direction];
	}
	
	/**
	 * @return The Coord coordinates of the case
	 */
	public Coord getCoord() {
		return this.coord;
	}

	/**
	 * @param coord
	 * Set coord at the Case
	 */
	public void setCoord(Coord coord) {
		this.coord = coord;
	}
	
	/**
	 * @return The X value of the coordinates of the case
	 */
	public int getX() {
		return this.coord.x();
	}
	
	/**
	 * @return The Y value of the coordinates of the case
	 */
	public int getY() {
		return this.coord.y();
	}	
	
	/**
	 * @return the owner id. 0 = NONE
	 */
	public int getOwner() {
		return this.owner;
	}

	/**
	 * @param ownerId
	 * Set the id of the owner of the case. 0 = NONE
	 */
	public void setOwner(int ownerId) {
		this.owner = ownerId;
	}
	
	
	
}
