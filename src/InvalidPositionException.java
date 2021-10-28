/**
 * Exception indicating an a invalid position
 * @author GCFernandes
 *
 */
public class InvalidPositionException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private Position position;
	private Plateau plateau;
	
	/**
	 * Invalid position constructor
	 * @param position the invalid position
	 * @param plateau plateau of the position
	 */
	public InvalidPositionException(Position position, Plateau plateau) {
		super("Invalid position on plateau");
		this.position = position;
		this.plateau = plateau;
	}
	
	/**
	 * This method returns the position
	 * @return position
	 */
	public Position getPosition () {
		return position;
	}
	
	/**
	 * This method returns the plateau
	 * @return plateau
	 */
	public Plateau getPlateau() {
		return plateau;
	}
}