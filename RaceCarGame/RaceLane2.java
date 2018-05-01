import java.util.Scanner;

public class RaceLane2 {
	// ------------------------------------------------------------------------------------------------------------------
	// Assignment 5 Part 2
	// Written By: Giselle Martel 26352936
	// for COMP 248 Section Q - Fall 2017
	// ------------------------------------------------------------------------------------------------------------------

	/*
	 * The goal of this program is to execute a car race. The number of cars that
	 * will participate and the number of laps is determined by the user. The user
	 * will also name each car object and define its maximum speed. Using several
	 * methods in the Car class, and also some static methods in the RaceLane2
	 * class, the program will accelerate all the cars until they reach the finish
	 * line or until they experience a collision. When a collision occurs, the
	 * crashed cars will be removed from the race and the remaining cars will be
	 * stopped before restarting the race from the same position. The cars that are
	 * involved in a collision will be displayed to the user. At the end of the
	 * race, the winners will be displayed to the user (up to 3). The moment all
	 * cars crash, or there are 3 winners, or there is a collision and at least one
	 * winner, then the race will end.
	 */

	public static boolean theEndingCrash(Car[] carArray) { // create a method that will detect when all cars have
		// crashed.
		int numCrashed = 0; // set number of crashed cars initially to zero.
		for (int j = 0; j < carArray.length; j++) { // check every car to see if it has crashed
			if (carArray[j].hasItCrashed())
				numCrashed++; // if a car has crashed, increment numCrashed by 1.
		}
		return (numCrashed == carArray.length); // if the number of crashed cars is equal to the total number of cars
		// then end race.
	}

	public static void crashDetector(Car[] carArray) {
		boolean display = false; // initialize boolean display to control what will be displayed.
		// initialize variables for the number of crashed cars after a collision and
		// total crashed cars in a race.
		int numCrashed = 0, totalCrashed = 0;
		for (int x = 0; x < carArray.length; x++) { // use for loop to evaluate each car in race.
			for (int y = 0; y < carArray.length; y++) { // create for loop to evaluate a second car.
				// if 2 unique cars are at the same location and both have not crashed.
				if (x != y && carArray[x].getLocation() == carArray[y].getLocation() && !carArray[y].hasItCrashed()) {
					display = true;
					for (int z = 0; z < carArray.length; z++) { // create loop for third car
						// if 3 unique cars are at the same location a none of them have crashed yet the
						// a collision will occur.
						if (y != z && z != x && carArray[y].getLocation() == carArray[z].getLocation()
								&& !carArray[z].hasItCrashed()) {
							carArray[x].crash(); // call crash method to the 3 cars that have crashed.
							carArray[y].crash();
							carArray[z].crash();
							numCrashed = 3; // number of crashed cars = 3
							totalCrashed += 3; // the total amount of crashed cars increases by 3 after every
							// collision.
							for (int w = 0; w < carArray.length; w++) { // Stop all the cars in the race after crash
								// occurs.
								carArray[w].stop();
							}

						}
					}
				}
			}

		}
		if (numCrashed == 3) { // if 3 cars have cars have crashed, display to the user.
			System.out.println("\n* * * * *The following cars have crashed!!* * * * *");
			for (int i = 0; i < carArray.length; i++) { // loop that checks if each car has crashed.
				if (carArray[i].hasItCrashed()) // if a car has crashed, display to user.
					System.out.println(carArray[i].toString());
			}
			System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * *\n");
			System.out.println("Number of crashed cars: " + totalCrashed);
		}
		if (!display) { // if no crash has occurred in the previous iteration then display 0 cars have
			// crashed.
			System.out.println("Number of crashed cars: 0");
		}
	}

	// create method which detects when each car has surpassed one lap.
	public static boolean oneLap(Car[] carArray) {
		boolean overOneLap = false; // initialize boolean variable to track when a car has completed one lap.
		// use for loop to check if position of each car is over one lap (100).
		for (int i = 0; i < carArray.length; i++) {
			if (carArray[i].getLocation() > 100)
				overOneLap = true;
		}
		return overOneLap;
	}

	// create method that will track the number of remaining cars in a race after a
	// collision occurs.
	public static int remainingCars(Car[] carArray, int laps, int numCars) {
		// initially the number of cars remaining is equal to the number of cars until a
		// crash occurs.
		int numRemainingCars = numCars;
		for (int i = 0; i < carArray.length; i++) {
			if (carArray[i].hasItCrashed() && carArray[i].getLocation() < laps * 100) {
				numRemainingCars--; // if a car has crashed then decrement the number of remaining cars.
			}
		}
		return numRemainingCars;
	}

	// Use method to determine if race has finished.
	public static boolean finishedRace(Car[] carArray, int laps) {

		int numCrashed = 0; // initialize number of crashed cars to zero before verifying.
		for (int i = 0; i < carArray.length; i++) { // verify how many cars have crashed.
			if (carArray[i].hasItCrashed())
				numCrashed++; // increment number of crashed cars by if a car has crashed.
		}
		int counter = 0; // initialize the number of cars that have completed the race to 0.
		for (int i = 0; i < carArray.length; i++) {
			// if a car's position is greater than the total race length (laps*100), then it
			// has completed the race.
			if (carArray[i].getLocation() >= (100 * laps) && !carArray[i].hasItCrashed()) {
				counter++; // increment the counter if a car has completed race.
			}
		}
		/*
		 * if all cars have crashed or all cars have completed the race, or there are 3
		 * winners, or a crash occurs and there are less then 3 winners, return true to
		 * end race.
		 */
		return (numCrashed == carArray.length || counter == 3 || (counter < 3 && counter >= 1 && numCrashed == 3));
	}

	// create method to determine whether or not car has won.
	public static Car[] whoWon(Car[] carArray, int laps) {
		int counter = 0; // initialize the number of cars that have completed the race to 0.
		for (int i = 0; i < carArray.length; i++) {
			// if a car's position is greater than the total race length (laps*100), then it
			// has completed the race.
			if (carArray[i].getLocation() >= (100 * laps) && !carArray[i].hasItCrashed() && counter < 3
					&& !carArray[i].hasWon()) {
				carArray[i].Win();
				counter++; // increment the counter if a car has completed race.
			}
		}

		return carArray;
	}

	// ***************************************MAIN
	// METHOD*************************************************************
	public static void main(String[] args) {
		Scanner keyIn = new Scanner(System.in); // create Scanner object to allow user input.
		Car input = new Car(); // create object for user input validation methods in Car class.
		// create variables for number of laps and the number of participating cars.
		int laps, numCars;

		System.out.println("Enter the number of cars: "); // ask user to specify the number of cars.
		numCars = input.validateInput(); // call validateInput() method to ensure user enters valid positive integer for
		// the number of cars.
		while (numCars < 2) { // we need a minimum of 2 cars to have a race.
			System.out.println("Enter the number of cars: ");
			numCars = input.validateInput();
		}
		System.out.println("How many laps : "); // ask user to specify number of laps.
		laps = input.validateInput(); // call validateInput() method to ensure user enters valid positive integer for
		// the number of laps.
		while (laps < 1) { // we need a minimum of 1 lap to have a race.
			System.out.println("How many laps: ");
			laps = input.validateInput();
		}
		int raceLength = 100 * laps;
		Car[] carArray = new Car[numCars]; // create new array object for car attributes.

		int maxSpeed;
		String model;
		// use for loop to allow to user to enter model and max speed for each car.
		for (int i = 0; i < carArray.length; i++) {
			System.out.println("Please enter the model of car " + (i + 1));
			model = keyIn.next();
			System.out.println("Please enter the max speed of car " + (i + 1));
			maxSpeed = input.validateMaxSpeed();
			carArray[i] = new Car(model, maxSpeed);
		}
		// Indicate to user that race is beginning.
		System.out.println(
				"* * * * * * * * * * * * * * * * * * * * * * * * *\nStart Your Engines.... Ready! Set! GO!!!!!!!!!!!!!\n* * * * * * * * * * * * * * * * * * * * * * * * *");

		int crashCount = 0; // initialize variable to keep track of how many times the crashDetector method
		// is used.

		do {
			for (int i = 0; i < numCars; i++) { // evaluate each car in race.
				if (crashCount > 0) {

					// if a car has not crashed then accelerate it.
					if (!carArray[i].hasItCrashed()) {

						carArray[i].accelerate();
						// if a car has not crashed and has not completed the race, then call the move
						// method to it.
						if (carArray[i].getLocation() < raceLength) {
							carArray[i].move();
							// if the car has not crashed but completed the race then call the stop method
							// to it.
						} else {
							carArray[i].stop();
						}
					}
					System.out.println(carArray[i].toString()); // print status of car.
				} else { // if a car is still in the first lap, then call accelerate and move methods to
					// it.
					carArray[i].accelerate();
					carArray[i].move();
					System.out.println(carArray[i].toString()); // display the status of each car.
				}
			}

			// if a car has exceeded one lap then execute the crash detector algorithm using
			// the crashDetector method.
			if (oneLap(carArray)) {
				crashDetector(carArray);
				crashCount++; // increment crashCount each time a crash detection is used.
				// if a car has not yet finished the race, then call remainingCars method to
				// print the number of remaining cars in race periodically.
				if (!finishedRace(carArray, laps)) {
					System.out.println("Number of current cars in race " + remainingCars(carArray, laps, numCars));
				} else
					System.out.println("Number of current cars in race: 0"); // when the race is over, display this.
			}
			if (theEndingCrash(carArray)) { // if all cars have crashed, then the race is over.
				// display all cars have crashed and terminate program.
				System.out.println(
						"\n* * * * * * * * * * * * * * * * * * * * * * * * *\nAll cars have crashed!!! GAME OVER!!!!!"
								+ "\nThanks for playing! Goodbye!!");
				System.exit(0);
			}
			whoWon(carArray, laps); // call whoWon method to determine who the winners are.

		} while (!finishedRace(carArray, laps));

		// display winners.
		System.out.println(
				"\n* * * * * * * * * * * * * * * * * * * * * * * * *\nThe race is over!!....and the champions are:\n");
		for (int i = 0; i < carArray.length; i++) { // loop goes through each car to see if they have not crashed
			if (carArray[i].hasWon()) {
				System.out.println(carArray[i].toString()); // prints out the car and its status.
			}
		}

		// Display Goodbye message.
		System.out.println("\nThanks for playing! Goodbye!!");
	}
}