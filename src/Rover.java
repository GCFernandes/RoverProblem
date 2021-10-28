/**
 * Implementation of a rover, with position, heading and a plateau in which it is deployed.
 * @author GCFernandes
 */
public class Rover {
	private Position position;
	private Heading heading;
	private Plateau plateau;
	
	/**
	 * Rover constructor method
	*@param position rover position
	*@param heading rover heading
	*@param plateau rover plateau
	*/
	public Rover (Position position, Heading heading, Plateau plateau) {
		this.position = position;
		this.heading = heading;
		this.plateau = plateau;
	}
	
	/**
	 * Rover string representation
	 * @return rover position in string format: "X Y"
	 */
	@Override
	public String toString() {
		return (position.toString() + " " + heading + "\n");
	}
	
	/**
	 * This method returns the rover's current position
	 * @return rover's position
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * This method returns the rover's current heading
	 * @return rover's heading
	 */
	public Heading getHeading() {
		return heading;
	}
	
	/**
	 * Turns the rover, either left or right changing it's current heading
	 * @param direction direction of turning, can be R or L
	 * @return true if operation was successful
	 * @throws RuntimeException on invalid direction or heading
	 */
	public boolean turn(Instruction direction) throws RuntimeException{
		if(direction == Instruction.L) {
			switch(heading) {
				case N: heading = Heading.W; break;
				case W: heading = Heading.S; break;
				case S: heading = Heading.E; break;
				case E: heading = Heading.N; break;
				default: throw new RuntimeException("Invalid heading");
			}
		}
		else if (direction == Instruction.R) {
			switch(this.heading) {
				case N: heading = Heading.E; break;
				case E: heading = Heading.S; break;
				case S: heading = Heading.W; break;
				case W: heading = Heading.N; break;
				default: throw new RuntimeException("Invalid heading");
			}
		}
		else {
			throw new RuntimeException("Invalid turn instruction");
		}
		return true;
	}
	
	/**
	 * Moves the rover forward
	 * @return boolean indicating success
	 * @throws InvalidPositionException on invalid new position (out of plateau)
	 * @throws RuntimeException on invalid heading
	 */
	public boolean move() throws InvalidPositionException, RuntimeException {
		return position.move(plateau, heading);
	}
	
}
