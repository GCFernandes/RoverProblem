import java.util.List;
import java.util.ArrayList;

/**
 * Class to represent a plateau in which rovers can be inserted and moved
 * @author GCFernandes
 *
 */
public class Plateau {
	private int maxX;
	private int maxY;
	private List<Rover> rovers;
	
	/**
	 * Plateau constructor
	 * @param maxX plateau maximum X dimension
	 * @param maxY plateau maximum Y dimension
	 */ 
	public Plateau (int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
		this.rovers = new ArrayList<Rover>();
	}
	
	/**
	 * Plateau conversion to String
	 * @returns String containing all the rovers
	 */
	@Override
	public String toString() {
		String allRovers = "";
		for (int i = 0; i < rovers.size(); i ++) {
			allRovers += rovers.get(i).toString();
		}
		return (allRovers);
	}
	
	/**
	 * This method returns the plateau's maximum X
	 * @return plateau's maximum X
	 */
	public int getSizeX() {
		return maxX;
	}
	
	/**
	 * This method returns the plateau's maximum Y
	 * @return plateau's maximum Y
	 */
	public int getSizeY() {
		return maxY;
	}
	
	/**
	 * Inserts a rover in this plateau
	 * @param rover rover to be added
	 * @return boolean indicating success
	 */
	public boolean insertRover (Rover rover) {
		rovers.add(rover);
		return true;
	}
	
	/**
	 * Moves the rover according to a series of instructions
	 * @param instructions String containing the instructions
	 * @param rover Rover to be moved
	 * @return boolean indicating success
	 * @throws RuntimeException on invalid instructions
	 * @throws InvalidPositionException Rover out of plateau after movement
	 * @throws CollisionException on Rover collision after movement
	 */
	public boolean moveRover (String instructions, Rover rover) throws RuntimeException, InvalidPositionException, CollisionException {
		for(int i = 0; i < instructions.length(); i ++) {
			switch(instructions.charAt(i)) {
				case 'L': rover.turn(Instruction.L);break;
				case 'R': rover.turn(Instruction.R);break;
				case 'M': rover.move();break;
				default: throw new RuntimeException("Invalid Instruction");
			}
			for(int j = 0; j < rovers.size(); j ++) {
				if (rover != rovers.get(j) && rover.getPosition().isEqual(rovers.get(j).getPosition())){
					throw new CollisionException(rover.getPosition(), rovers.get(j).getPosition(), this);
				}
			}
		}
		return true;
	}
}
