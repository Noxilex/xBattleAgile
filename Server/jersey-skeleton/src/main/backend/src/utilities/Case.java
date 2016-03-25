package utilities;

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
	private int pipes;
	private int level;
	private int fieldType;

	public Case(Coord coord, int fieldType) {
		this.coord = coord;
		this.owner = 0;
		this.pipes = 0;
		this.fieldType = fieldType;
		switch (fieldType){
		case 5:
			this.level = 25;
			break;
		case 6:
			this.level = 50;
			break;
		case 7:
			this.level = 100;
			break;
		default:
			this.level = 0;
			break;
		}
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
		return actives;
	}
	
	/**
	 * @return The Array of the different direction of the pipe.
	 * In clock order, starting at noon. 
	 */
	public int getPipes() {
		return this.pipes;
	}

	/**
	 * @param pipes 
	 * Set a new direction-array of pipes
	 */
	public void setPipes(int pipes) {
		this.pipes = pipes;
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
	
	/*---------------------------------------LOG FUNCTIONS-------------------------*/
	
	/**
	 * LOG FUNCTION
	 * @param fieldTypeId
	 * @return A readable string to check what is the fieldTypeId
	 */
	private String logGetReadableFieldType(int fieldTypeId) {
		String s;
		switch (fieldTypeId) {
		case 0:
			s = "Plain";
			break;

		case 1:
			s = "Sand";
			break;
			
		case 2:
			s = "Hill 1";
			break;
			
		case 3:
			s = "Hill 2";
			break;
			
		case 4:
			s = "Hill 3";
			break;
			
		case 5:
			s = "Water 1";
			break;
			
		case 6:
			s = "Water 2";
			break;
			
		case 7:
			s = "Water 3";
			break;
		default:
			s = "#ERROR#";
			break;
		}
		System.out.println("Translated fieldType id"+fieldTypeId);
		return s;
	}
	
	/**
	 * LOG FUNCTION
	 * @param direction
	 * @return A readable string of the specified direction
	 */
	private String logGetReadableDirection(int direction) {
		String s;
		switch (direction) {
		case 0:
			s = "North";
			break;
		
		case 1:
			s = "North East";
			break;
		
		case 2:
			s = "East";
			break;
			
		case 3:
			s = "South East";
			break;
		
		case 4:
			s = "South";
			break;
			
		case 5:
			s = "South West";
			break;
			
		case 6:
			s = "West";
			break;
			
		case 7:
			s = "North West";
			break;
			
		default:
			s = "#ERROR#";
			break;
		}
		return s;
	}

	
}
