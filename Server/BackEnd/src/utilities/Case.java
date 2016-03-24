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
	private boolean[] pipes;
	private int level;
	private int fieldType;

	public Case(Coord coord, int fieldType) {
		this.coord = coord;
		this.owner = 0;
		this.pipes = new boolean[]{false, false, false, false, false, false, false, false};
		this.level = 0;		
		this.fieldType = fieldType;
	}
	
	

	/**
	 * @return the type of the field.
	 * 0 = plain ; 1 = sand 
	 * 2 = hill 1; 3 = hill 2; 4 = hill 3
	 * 5 = water1; 6 = water2; 7 = water3
	 */
	public int getFieldType() {
		System.out.println("The field type at "+coord.toString()+" is "+logGetReadableFieldType(this.fieldType));
		return this.fieldType;
	}

	/** 
	 * @param fieldTypeId
	 * Set the field type via his new id
	 */
	public void setFieldType(int fieldTypeId) {
		this.fieldType = fieldTypeId;
		System.out.println("Field type id is now "+fieldTypeId);
	}
	
	/**
	 * @param change
	 * Change the level of the player liquid via operation.
	 */
	public void changeLevel(int change) {
		System.out.println(change+" has been add to the liquid level.");
		this.level+=change;
	}
	
	/**
	 * @param lvl
	 * Set the player liquid level of the case.
	 */
	public void setLevel(int lvl) {
		this.level = lvl;
		System.out.println("Set the liquid level at "+lvl);
	}
	
	/**
	 * @return get the player liquid level. 0-100
	 */
	public int getLevel() {
		System.out.println("Liquid level at "+coord.toString());
		return this.level;
	}
	
	/**
	 * @return The number of active directions
	 */
	public int getNumberOfActivePipes() {
		int actives = 0;
		String s = "[";
		for (int i=0; i<pipes.length; i++) {
			if (pipes[i]) {
				actives++;
				s+="active";
			} else {
				s+="inactive";
			}
		}
		s+="]";
		System.out.println("Directions pipes are : "+s);
		return actives;
	}
	
	/**
	 * @return The Array of the different direction of the pipe.
	 * In clock order, starting at noon. 
	 */
	public boolean[] getPipes() {
		System.out.println("Having "+logPipes(this.pipes));
		return this.pipes;
	}

	/**
	 * @param pipes 
	 * Set a new direction-array of pipes
	 */
	public void setPipes(boolean[] pipes) {
		this.pipes = pipes;
		System.out.println("Set new directions as : "+logPipes(pipes));
	}

	/**
	 * @param direction
	 * @return Check if the pipe toward the specified direction is active
	 */
	public boolean getDirectionOfPipe(int direction) {
		String s;
		if (this.pipes[direction]) {s="active";}
		else {s="inactive";}
		
		System.out.println("The "+logGetReadableDirection(direction)+" is "+s);
		return this.pipes[direction];
	}
	

	/**
	 * @return The Coord coordinates of the case
	 */
	public Coord getCoord() {
		System.out.println("Coord : "+this.coord.toString());
		return this.coord;
	}

	/**
	 * @param coord
	 * Set coord at the Case
	 */
	public void setCoord(Coord coord) {
		this.coord = coord;
		System.out.println("Set new coord as "+this.coord.toString());
	}
	
	/**
	 * @return The X value of the coordinates of the case
	 */
	public int getX() {
		System.out.println("x="+this.coord.x());
		return this.coord.x();
	}
	
	/**
	 * @return The Y value of the coordinates of the case
	 */
	public int getY() {
		System.out.println("y="+this.coord.y());
		return this.coord.y();
	}	
	
	/**
	 * @return the owner id. 0 = NONE
	 */
	public int getOwner() {
		System.out.println("The id owner of this case is "+this.owner);
		return this.owner;
	}

	/**
	 * @param ownerId
	 * Set the id of the owner of the case. 0 = NONE
	 */
	public void setOwner(int ownerId) {		
		this.owner = ownerId;
		System.out.println("The new owner id is "+ownerId);
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
	
	/**
	 *  LOG FUNCTION
	 * @param pipes
	 * @return
	 */
	private String logPipes(boolean[] pipes) {
		String s = "[";
		for (int i=0; i<pipes.length; i++) {
			if (pipes[i]) {s+="true";} 
			else {s+="false";}
			
			if (i<pipes.length-1) {s+=",";}
		}
		return s+"]";
	}
	
	
}
