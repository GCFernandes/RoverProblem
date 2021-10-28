/**
 * Exception indicating a collision between two positions
 * @author GCFernandes
 *
 */
public class CollisionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Position position1;
	private Position position2;
	private Plateau plateau;
	
	/**
	 * Collision constructor
	 * @param position1 first position
	 * @param position2 second position
	 * @param plateau plateau containing rovers with these positions
	 */
	public CollisionException(Position position1, Position position2, Plateau plateau) {
		super("Collision between positions");
		this.position1 = position2;
		this.position1 = position2;
		this.plateau = plateau;
	}
	
	/**
	 * This method returns the first position
	 * @return position1
	 */
	public Position getPosition1 () {
		return position1;
	}
	
	/**
	 * This method returns the second position
	 * @return position2
	 */
	public Position getPosition2 () {
		return position2;
	}
	
	/**
	 * This method returns the plateau
	 * @return plateau
	 */
	public Plateau getPlateau() {
		return plateau;
	}
}
