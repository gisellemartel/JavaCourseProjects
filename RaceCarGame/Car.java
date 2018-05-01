import java.util.Scanner;
// ------------------------------------------------------------------------------------------------------------------
// Assignment 5 Car Class
// Written By: Giselle Martel 26352936
// for COMP 248 Section Q - Fall 2017
// ------------------------------------------------------------------------------------------------------------------

public class Car {
	private String model; //create private variables to be used in Car class. 
	private int location;
	private int currentSpeed;
	private boolean movingForward;
	private int maxSpeed;
	private boolean hasCrashed=false; //create 2 new private variables to be used in Car class for Part 2. 
	private boolean winner = false;
	

	// create default constructor with variables initialized to 0.
	public Car() {
		location = 0;
		currentSpeed = 0;
		movingForward = false;
		maxSpeed = 0;
		model = "";
		winner = false;
	}
	// create another constructor that takes 3 inputs and assigns them to their
	// respective attributes.
	public Car(String m, int l, int s) {
		currentSpeed = 0;
		movingForward = true;
		model = m;
		maxSpeed = s;
		location = l;
		winner = false;
	}
	public Car(String m, int s) { //create new parameterized constructor for part 2. 
		model = m;
		maxSpeed = s;
		location = 0;
		movingForward = true;
		currentSpeed = 0;
		hasCrashed = false;
		winner = false;
	}
	//create 3 accessor methods for the car model, direction, and location. 
	public String getModel() { 
		return model;
	}

	public boolean getDirection() {
		return movingForward;
	}

	public int getLocation() {
		return location;
	}

	// create go() method to set the current speed of the car to its maximum speed.
	public int go() {
		currentSpeed = maxSpeed;
		return currentSpeed;
	}

	// create a method that makes the car stop moving (currentSpeed set to zero).
	public int stop() {
		currentSpeed = 0;
		return currentSpeed;

	}

	public boolean turnAround() { // create turnAround() method to change the direction the car is moving.
		if (!movingForward)
			movingForward = true;
		else
			movingForward = false;
		return movingForward;
	}

	public int move() { //create move() method which will change the car's location based on its current speed. 
		if (movingForward)
			location += currentSpeed;
		else
			location -= currentSpeed;
		return location;
	}

	public String toString() { // create toString() method to display attributes of car.
		String a, b;
		if (movingForward)
			a = "forward";
		else
			a = "backwards";
		if (currentSpeed == 0)
			b = ", not moving."; // when currentSpeed is zero, print "not moving"
		else
			b = (" and moving at " + currentSpeed + " speed."); // when currentSpeed is non-zero, display the speed.
		String x = (model + ": Located at " + location + ", facing " + a + b);
		return x;
	}
	//create accelerate class for Part 2, which increments the speed of the car by 1 until it reaches its maximum speed. 
	public int accelerate() { 
			if (currentSpeed < maxSpeed)
				currentSpeed++;
			return currentSpeed;
			
	}
	//create a brake() method which decrements the speed of the car by 1 until it stops. 
	public int brake() {
		if(currentSpeed > 0)
			currentSpeed --;
		return currentSpeed;
	}
	//create mutator method that will indicate that the a car has crashed for Part 2
	public void crash() {
		hasCrashed = true;
	}
	//create an accessor method to verify if a car has crashed. 
	public boolean hasItCrashed() {
		return hasCrashed;
	}
	//create mutator method for Part 2 which set the car as a winner. 
	public void Win() {
		winner = true;
	}
	//create accessor method for Part which will verify if a car is a winner or not. 
	public boolean hasWon() {
		return winner;
	}
	public int validateInput() { // create method that ensure user only enters a valid integer when prompted to.
		Scanner sc = new Scanner(System.in); //create Scanner object to allow user input. 
		int a; //create int value to be returned to main method. 
		while (!sc.hasNextInt()) { //while user does not enter a valid integer, re-prompt user. 
			System.out.println("Please enter a non negative integer value only.");
			sc.next();
		}
		a = sc.nextInt(); //set a equal to user input (once they have entered a valid integer). 
		while (a < 0) { //if the user enters a negative integer (we only want positive values), re-prompt user. 
			System.out.println("Please enter a non negative integer value only.");
			while (!sc.hasNextInt()) { //if user again enters a non-integer value, then re-prompt user. 
				System.out.println("Please enter a non negative integer value only.");
				sc.next();
			}
			a = sc.nextInt(); //set a equal to valid user input

		}
		return a; 

	}
	public int validateMaxSpeed() { // create method that ensure user only enters a valid, numerical maximum speed when prompted for Part 2. 
		Scanner sc = new Scanner(System.in); 
		int a;
		while (!sc.hasNextInt()) {
			System.out.println("Not a valid speed. Please re enter a value ranging from 2 to 7: ");
			sc.next();
		}
		a = sc.nextInt();
		while (a <2 || a > 7) {
			System.out.println("Not a valid speed. Please enter a value ranging from 2 to 7: ");
			while (!sc.hasNextInt()) {
				System.out.println("Not a valid speed. Please enter a value ranging from 2 to 7: ");
				sc.next();
			}
			a = sc.nextInt();

		}
		return a;
	
	}

	
}
