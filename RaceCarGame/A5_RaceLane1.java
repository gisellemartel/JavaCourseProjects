import java.util.Scanner;

public class A5_RaceLane1 {
	// ------------------------------------------------------------------------------------------------------------------
	// Assignment 5 Part 1
	// Written By: Giselle Martel 26352936
	// for COMP 248 Section Q - Fall 2017
	// ------------------------------------------------------------------------------------------------------------------
	public static void main(String s[]) {
		/*
		 * This program uses a car class and a driver class with a main method, in order
		 * to race 2 cars. First the program will prompt the user to enter information
		 * about each car (model, maximum speed, initial position). Using this data, the
		 * program will call methods from the car class using a series of if statements
		 * and while loops in order to race each car from their respective initial
		 * position and at their respective maximum speeds. If one car starts to the
		 * right of the the other car, once it moves to the left of the car (collides
		 * with it), the the program will display to the user that a crash has occurred.
		 * If cars end up precisely at the same position as the other car, then the
		 * program will also display that a collision has occurred. If the cars both
		 * start at the same initial position, then the moment the method which makes
		 * the cars start moving is called, a collision will occur.
		 */
		
		// display welcome message.
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------");
		System.out.println("Welcome to Giselle's Racing Game!!");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------"
						+ "\n");
		

		Scanner sc = new Scanner(System.in); // create Scanner object to allow user input.
		int location1 = 0, maxSpeed1 = 0, location2 = 0, maxSpeed2 = 0; // create integer variables initialized to 0.
		Car input = new Car(); // create object for input validation method in Car class.

		System.out.println("Please enter the model of the first car"); // prompt user for model name of first car
		String model1 = sc.nextLine();
		System.out.println("Please enter the location of the first car"); // prompt user for location of car 1.
		location1 = input.validateInput(); // call validateInput() method from car class to validate input.
		System.out.println("Please enter the speed of the first car");// prompt user for speed of car 1.
		maxSpeed1 = input.validateInput(); // call validateInput() method from car class to validate input.

		Car myCar1 = new Car(model1, location1, maxSpeed1); // create class object for car 1 and set parameters to user
															// input.

		System.out.println("Please enter the model of the second car"); // prompt user for model name of car 2
		String model2 = sc.next();
		System.out.println("Please enter the location of the second car"); // prompt user for location of car 2.
		location2 = input.validateInput(); // call validateInput() method from car class to validate input.
		System.out.println("Please enter the speed of the second car"); // prompt user for speed of car 1.
		maxSpeed2 = input.validateInput(); // call validateInput() method from car class to validate input.

		Car myCar2 = new Car(model2, location2, maxSpeed2); // create class object for car 2 and set parameters to user
															// input.

		System.out.println(myCar1.toString()); // call toString() method and print to display car 1 attributes.
		System.out.println(myCar2.toString()); // call toString() method and print to display car 2 attributes.

		if (location1 < location2) { // if car 2 is to the right of car 1, then turn it around
			myCar2.turnAround();
		} else { // if car 1 is to the right of car 2, then turn it around.
			myCar1.turnAround();
		}

		// display car details before beginning race.
		System.out.println(myCar1.toString() + "\n" + myCar2.toString());

		// if the 2 cars are at different locations, then they should continue to move
		// at maximum speed.
		if (myCar1.getLocation() != myCar2.getLocation()) {
			myCar1.go();
			System.out.println(myCar1.toString());
			myCar2.go();
			System.out.println(myCar2.toString());
		}

		if (myCar1.getLocation() > myCar2.getLocation()) { // if car 1 is to the right of car 2.
			// while car 1 is to the right of car 2, it should continue to change position
			// until it it has the same position as car 2 or is to the left of car 2.
			while (myCar1.getLocation() > myCar2.getLocation()) {
				myCar1.move();
				if (myCar1.getLocation() >= 0) { // Only print the location of the car if it is a positive number.
					System.out.println(myCar1.toString());
				}
				myCar2.move();
				if (myCar2.getLocation() >= 0) { // Only print the location of the car if it is a positive number.
					System.out.println(myCar2.toString());
				}

			}

		} else if (myCar1.getLocation() < myCar2.getLocation()) { // if car 1 is to the left of car 1.
			// while car 2 is to the right of car 1, it should continue to change position
			// until it it has the same position as car 1 or is to the left of car 1.
			while (myCar2.getLocation() > myCar1.getLocation()) {
				myCar1.move();
				if (myCar1.getLocation() >= 0) { // Only print the location of the car if it is a positive number.
					System.out.println(myCar1.toString());
				}
				myCar2.move();
				if (myCar2.getLocation() >= 0) { // Only print the location of the car if it is a positive number.
					System.out.println(myCar2.toString());
				}
			}
		}
		// when a car's position moves switches from right to left, or left to right, or when their
		// positions are the same, a collision happens. Display Boom!! to user to signify
		// collision has occurred.
		System.out.println("BOOM!!!!!");
		
		sc.close(); // close scanner object.
		
		// Display Goodbye message.
		System.out.println("\nThanks for playing! Goodbye!!");

	}
}
// End of Program.
