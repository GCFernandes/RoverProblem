/**
 * Representation of a x and y position on a plateau
 * @author GCFernandes
 *
 */
public class Position {
	private int x;
	private int y;
	
	/**
	 * Position constructor method
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Position (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Method to compare if two positions are equal, positions are considered
	 * equal if they have the same coordinates 
	 * @param target position to compare
	 * @return boolean indicating if the positions are equal
	 */
	public boolean isEqual (Position target) {
		return x == target.x && y == target.y;
	}
	
	/**
	 * Method to move a position forward, relative to a heading
	 * @param plateau plateau containing a rover with this position
	 * @param heading heading of the rover containing this position
	 * @return boolean indicating success
	 * @throws InvalidPositionException on invalid position after movement
	 * @throws RuntimeException on invalid heading
	 */
	public boolean move (Plateau plateau, Heading heading) throws InvalidPositionException, RuntimeException{
		switch(heading) {
			case N: y++; break;
			case E: x++; break;
			case S: y--; break;
			case W: x--; break;
			default: throw new RuntimeException("Invalid heading");
		}
		if(y < 0 || x < 0 || x > plateau.getSizeX() || y > plateau.getSizeY()) {
			throw new InvalidPositionException(this, plateau);
		}
		return true;
	}

	/**
	 * Method to return string representation of a position
	 * @return String in this format: "X Y"
	 */
	@Override
	public String toString() {
		return Integer.toString(x) + " " + Integer.toString(y);
	}
}
