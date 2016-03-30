package utilities;

/**
 * @author cailliea
 * Class about Cases.
 * Storing the coordinates, the ownerId, the active pipes, the level of the liquid
 * and the field-type number
 *	
 */
public class Case {

	public static Coord coord;
	private int owner;
	private int pipes;
	private int level;
	private int fieldType=1;
	private boolean pump;
	
	public Case(){
		level=0;
		fieldType=1;
	}

	public Case(Coord coord, int fieldType) {
		this.coord = coord;
		this.owner = 0;
		this.pipes = 5 ;
		this.fieldType = fieldType;
		/*
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
		}*/
	}

	public boolean getPump() {
		return this.pump;
	}	

	public void setPump(boolean p) {
		 this.pump = p;
	}

	/**
	 * @return the type of the field.
	 * 0 = PLAIN ; 1 = SAND 
	 * 2 = hill 1; 3 = hill 2; 4 = hill 3
	 * 5 = WATER1; 6 = WATER2; 7 = WATER3
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
		if (lvl>100) {
			lvl=100;
		}
		if (lvl<0) {
			lvl=0;
		}
		this.level = lvl;
	}
	
	/**
	 * @return get the player liquid level. 0-100
	 */
	public int getLevel() {
		return this.level;
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
			s = "PLAIN";
			break;

		case 1:
			s = "SAND";
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
	

	
	
}
