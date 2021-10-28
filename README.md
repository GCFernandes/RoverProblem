# Rover-Problem
Rover mars problem implemented using Java

## How to run

To run this project compile every .java file in the src/ directory.

The example below shows how to do this using the command line on linux or windos PowerShell:

```
cd src
javac *.java
```

Then execute the RoverProblem file:

```
java RoverProblem
```

This will run the program in interactive mode, it will expect the inputs from the standard input from the terminal.

If you wish the program to run using inputs from a file, pass the file path and name as the first argument.

This example shows how to do this using the provided *validInput.txt* example file, located in the *examples* directory:

```
java RoverProblem example/validInput.txt
```

The input format should be the same as in the *validInput.txt* file:

```
5 5 
1 2 N 
LMLMLMLMM
3 3 E 
MMRMMRMRRM
```

The first indicate the plateau dimensions, then any number of rovers can be entered, one line indicating the starting position and heading, and one line indicating the sequence of instructions. The program stops reading the input and prints the output after receiving an empty line ou detecting an end of file.

There are other two example input files present in the *examples* folder: *collision.txt* which inputs a case where two rovers collide and *invalidPosition.txt* which inputs a case where a rover moves outside of the plateau.
## Implementation details

I considered that collisions between rovers should not be allowed to happen.

I implemented this problem representing the rovers as objects with x and y coordinates, and a plateau being a list of rovers. To check for a possible collision the program goes through the list of rovers in a plateau to check for equal coordinates. This solution is more efficient memory wise than representing the entire plateau as a matrix containing all possible positions, both occupied and not. On the other hand this solution has a worse performance time than the matrix solution to check for the collisions.