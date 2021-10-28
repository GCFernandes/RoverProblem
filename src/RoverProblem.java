import java.util.Scanner;
import static java.lang.System.in;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Class to solve the mars rover problem
 * Provides a user interface using text input or file input
 * @author GCFernandes
 *
 */
public class RoverProblem {

	/**
	 * Main method, providing a loop for interaction with the user
	 * or getting the inputs from a file.
	 * The first argument passed when executing the program should be the input
	 * file name, if no argument is passed the program expects the inputs directly
	 * from the user 
	 * @param args optional input file name
	 */
	public static void main(String[] args) {
		Scanner scanner = null;
		boolean errorFlag = false;
		if(args.length != 0) {
				File file = new File(args[0]);
			try {
				scanner = new Scanner(file);
			}
			catch(FileNotFoundException e) {
				System.out.print("File not found");
				return;
			}
		}
		else {
			scanner = new Scanner(in);	
		}

		String data = scanner.nextLine();
		Plateau plateau = createPlateau(data);
		
	    while (scanner.hasNextLine()) {
	    	data = scanner.nextLine();
	    	if (data == ""){
	    		errorFlag = true;
	    		break;
	    	}
	    	
	    	Rover rover = createRover(data, plateau);
	    	if(rover == null) {
	    		errorFlag = true;
	    		break;
	    	}
	    	plateau.insertRover(rover);
	    	
	    	data = scanner.nextLine();
	    	
	    	boolean instructionsCompleted = sendInstructions(rover, plateau, data);
	    	if(!instructionsCompleted) {
	    		errorFlag = true;
	    		break;
	    	}
	    }
	    scanner.close();
    	
	    if(!errorFlag) {
	    	System.out.print(plateau);
	    }
	}
	
	/**
	 * Creates a Plateau from an input String representing the dimensionsg
	 * @param dimensions String representing the dimensions
	 * @return created Plateau
	 */
	private static Plateau createPlateau (String dimensions) {
		String[] splitted = dimensions.split(" ");
		int x = Integer.parseInt(splitted[0]);
		int y = Integer.parseInt(splitted[1]);
		return new Plateau(x, y);
	}
	
	/**
	 * Creates a Rover from an input String representing the rover coordinates and heading
	 * @param dimensions String coordinates and heading
	 * @param plateau plateau in which this rover is placed
	 * @return created rover or null
	 */
	private static Rover createRover (String roverInfo, Plateau plateau) {
		String splitted[] = null;
		int x = 0;
		int y = 0;
		Heading heading = Heading.N;
		try{
			splitted = roverInfo.split(" ");
			x = Integer.parseInt(splitted[0]);
			y = Integer.parseInt(splitted[1]);
			heading = parseHeading(splitted[2]);
		}
		catch(RuntimeException e){
			System.out.print("Invalid information for rover creation");
			return(null);
		}
		
		Rover rover = new Rover (new Position(x, y), heading, plateau);
		return(rover);
	}
	
	/**
	 * Sends a String of instructions to a rover
	 * @param rover Rover to send instructions to
	 * @param plateau Plateau containing the rover
	 * @param instructions String of instructions
	 * @return boolean indicating success
	 */
	private static boolean sendInstructions (Rover rover, Plateau plateau, String instructions) {
		instructions = instructions.replace("\n", "").replace("\r", "").replace(" ", "");
		try {
			plateau.moveRover(instructions, rover);
		}
		catch(InvalidPositionException e) {
			System.out.print("Invalid rover position detected:\n");
			System.out.print("Position: " + e.getPosition().toString() + "\nPlateau max position: X=" + e.getPlateau().getSizeX() + " Y=" + e.getPlateau().getSizeY());
			return false;
		}
		catch(CollisionException e) {
			System.out.print("Rover Collision detected:\n");
			System.out.print("Position 1: " + e.getPosition1().toString() + "\nPosition 2: " + e.getPosition2().toString() + "\nPlateau:\n" + e.getPlateau().toString());
			return false;
		}
		catch(RuntimeException e) {
			System.out.print("Invalid instructions detected");
			return false;
		}

		return true;
	}

	/**
	 * Converts a String to a Heading
	 * @param headingInfo String in the Heading format
	 * @return converted Heading or null 
	 */
	private static Heading parseHeading (String headingInfo) {
		Heading heading = null;
		switch(headingInfo) {
			case "N": heading = Heading.N; break;
			case "S": heading = Heading.S; break;
			case "E": heading = Heading.E; break;
			case "W": heading = Heading.W; break;
		}
		return heading;
	}
	
}
