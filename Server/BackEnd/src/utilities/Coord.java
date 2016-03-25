package utilities;

/**
 * @author cailliea
 *	Class managing coordinates
 *
 */
public class Coord {

	private int x;
	private int y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coord() {}
	
	/**
	 * @return the coordinates x
	 */
	public int x() {
		return this.x;
	}
	
	/**
	 * @return the coordinates y
	 */
	public int y() {
		return this.y;
	}
	
	/**
	 * Set the coordinates x
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Set the coordinates y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * @return A string displaying the coordinates 
	 */
	public String toString() {
		return "("+x+";"+y+")";
	}
	
}
