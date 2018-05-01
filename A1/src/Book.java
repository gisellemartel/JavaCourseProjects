import java.text.DecimalFormat; //import DecimalFormat to allow formatting of floating point numbers.
import java.util.Scanner; //import Scanner class to allow user input
// ------------------------------------------------------------------------------------------------------------------
// Assignment 1 Book Class
// Written By: Giselle Martel 26352936
// for COMP 249 Section PP - Winter 2018
// ------------------------------------------------------------------------------------------------------------------

/**
 * <p>COMP 249 section PP Assignment # 1</p><p> Due Date: January 31, 2018</p>
 *
 * <p>This Book Class contains several methods which allow us to manipulate book
 * objects stored in an array of objects. The class contains constructor,
 * accessor, mutator, equals, and toString methods, as well as several methods
 * that validate user input and static methods that perform searches through the
 * array of objects.</p>
 *
 * @author Giselle Martel ID# 26352936
 */
public class Book {

	// create attributes for book object (part 1)
	private String title;
	private String author;
	private long isbn;
	private double price;
	private static int instanceCounter = 0;

	private static Scanner sc = new Scanner(System.in); // create static Scanner object to be used by methods of class
	// (part 2)
	private static DecimalFormat df2 = new DecimalFormat(".00"); // create static DecimalFormat object to allow
	// formatting of decimal numbers to two places.

	/**
	 * default constructor of the Book class which is called when Book object is
	 * created without parameters and sets attributes to default values. (part 1)
	 */
	public Book() {
		title = "";
		author = "";
		isbn = 0;
		price = 0;
	}

	/**
	 * copy constructor for Book class that takes a Book object b and makes a copy
	 * (part 1)
	 *
	 * @param b
	 *            is a book object which will be used to make a copy
	 */
	public Book(Book b) {
		this.title = b.title;
		this.author = b.author;
		this.isbn = b.isbn;
		this.price = b.price;
	}

	/**
	 * parameterized constructor for Book class that takes 4 parameters and sets
	 * each to their respective attribute for the book object. Increments static
	 * attribute instanceCounter each time a book object is created to track total
	 * number of book objects
	 *
	 * @param title
	 *            is an attribute of book object
	 * @param author
	 *            is an attribute of book object
	 * @param isbn
	 *            is an attribute of book object
	 * @param price
	 *            is an attribute of book object
	 */
	public Book(String title, String author, long isbn, double price) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.price = price;
		instanceCounter++;
	}

	/**
	 * acessor method for title attribute of book object (part 1)
	 *
	 * @return String title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * accessor method for author attribute of book object (part 1)
	 *
	 * @return String author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * accessor method for isbn attribute of book object (part 1)
	 *
	 * @return long isbn
	 */
	public long getIsbn() {
		return isbn;
	}

	/**
	 * accessor method for price attribute of book object (part 1)
	 *
	 * @return double price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * mutator method setTitle takes a String and points it to the same reference as
	 * the title attribute for the object(part 1).
	 *
	 * @param title
	 *            is a parameter set by the user and will be assigned to the title
	 *            attribute.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * mutator method setAuthor takes a String and points it to the same reference
	 * as the author attribute for the object(part 1).
	 *
	 *
	 * @param author
	 *            is a parameter set by the user and will be assigned to the author
	 *            attribute
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * mutator method setIsbn takes a long and points it to the same reference as
	 * the isbn attribute for the object (part 1).
	 *
	 * @param isbn
	 *            is a parameter set by the user and will be assigned to the isbn
	 *            attribute
	 */
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	/**
	 * mutator method setPrice takes a double and points it to the same reference as
	 * the price attribute for the object (part 1).
	 *
	 * @param price
	 *            is a parameter set by the user and will be assigned to the price
	 *            attribute
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * toString method
	 *
	 * @return a String displaying the attributes of the book object.
	 */
	public String toString() {
		return ("Title: " + title + "\n" + "Author: " + author + "\n" + "ISBN: " + isbn + "\n" + "Price: $"
				+ df2.format(price));
	}

	/**
	 * findNumberOfCreatedBooks is a static method
	 *
	 * @return the value of created objects using the static attribute
	 *         instanceCounter.
	 */
	public static int findNumberOfCreatedBooks() {
		return instanceCounter;
	}

	/**
	 * create equals method which takes Book object b as a parameter to determine if
	 * two book objects have the same price and same isbn
	 *
	 * @param b
	 *            is a book object which will be compared to another book object
	 * @return method will return true if both book objects have same price and isbn
	 */
	public boolean equals(Book b) {
		return (b.isbn == this.isbn && b.price == this.price);
	}

	/**
	 * VerifyPassword method takes user String input and verifies if it is a match
	 * to a predefined password using a while loop. If the user makes 3 unsuccessful
	 * password attempts, the method will return a boolean value which will prevent
	 * the user from continuing
	 *
	 * @return boolean passwordChecker as a true value when the user makes 3
	 *         unsuccessful password attempts.
	 */
	public boolean verifyPassword() {
		boolean passwordChecker = false; // initialize passwordChecker to false (will be set to true when max password
		// attempts have been made)
		// create counter that will track how many times a user enters incorrect
		// password.
		int passwordCounter = 0;
		final String password = "password"; // create final String which will store the correct password.
		System.out.println("Please enter your password");
		String userPassword = sc.next();
		// if the password is incorrect and less than 3 incorrect attempts have been
		// made then re-prompt user for password.
		while (!userPassword.equals(password) && passwordCounter <= 3) {
			if (passwordCounter < 2) {
				System.out.println("Invalid password! Please try again.");
				userPassword = sc.next();
				passwordCounter++; // each time an incorrect attempt is made passwordChecker is incremented
			} else if (passwordCounter == 2) {
				// once the user has made 3 incorrect attempts, increment passwordCounter to
				// initialize code in next else-if statement.
				passwordCounter++;
			}
			// once the user has made 3 incorrect attempts, passwordChecker is set to true
			// and returned.
			else if (passwordCounter == 3) {
				System.out.println("You have reached the maximum number of password attempts.");
				passwordChecker = true; // passwordChecker is set to true after 3 unsuccessful attempts
				passwordCounter++;
			}
		}
		return passwordChecker; // return boolean value of passwordChecker

	}

	/**
	 * this method ensures a user enters a valid integer, positive integer when
	 * prompted by using a while loop. The integer cannot exceed the value of the
	 * parameter max. When the user enters a double or String value, they will be
	 * re-prompted to enter until an integer value is entered.
	 *
	 * @return an int value that the user enters
	 * @param max
	 *            is a maximum value that cannot be exceeded by user input.
	 */
	public int validateInput(int max) {
		int input;
		while (!sc.hasNextInt()) {
			System.out.println("Please enter a value between 1 and " + max);
			sc.next();
		}
		input = sc.nextInt();
		while (input < 1 || input > max) {
			System.out.println("Please enter a value between 1 and " + max);
			while (!sc.hasNextInt()) {
				System.out.println("Please enter a value between 1 and " + max);
				sc.next();
			}
			input = sc.nextInt();
		}
		return input;

	}

	/**
	 * this overloaded method ensures a user enters a valid integer, positive
	 * integer when prompted by using a while loop. It does not take a paremter.
	 * When the user enters a double or String value, they will be reprompted to
	 * enter until an integer value is entered.
	 *
	 * @return an int value that the user enters
	 */
	public int validateInput() {
		int input;
		while (!sc.hasNextInt()) {
			System.out.println("Please re-enter a positive numeric value only");
			sc.next();
		}
		input = sc.nextInt();
		while (input < 1) {
			System.out.println("Please re-enter a positive numeric value only");
			while (!sc.hasNextInt()) {
				System.out.println("Please re-enter a positive numeric value only");
				sc.next();
			}
			input = sc.nextInt();
		}
		return input;

	}

	/**
	 * this method ensures a user enters a valid number of books by verifying that
	 * they do not enter more books than the max allowed (taken as parameter when
	 * method is called).
	 *
	 * @param max
	 *            is a value that cannot be exceeded by user input.
	 * @return a valid int value that the user enters
	 */
	public int validateNumBooks(int max) {
		int input;
		System.out.println(
				"How many books would you like to enter? There is space for " + (max) + " books in the store.");
		input = validateInput();
		while (input > max) {
			System.out.println("There is insufficient room in the store to enter that many books. Please retry.");
			input = validateInput();
		}
		return input;
	}

	/**
	 * This method prompts the user if they would like to quit the program, and will
	 * do so by first validating the input and then quitting the program immediately
	 * or returning a true boolean value
	 *
	 * @return true when the user does not wish to quit the program. This boolean
	 *         value will be used in the main method to re-initialize the main menu.
	 */
	public boolean quitProgram() {
		String response;
		System.out.println("\nWould you like to quit the program? Selecting no will return to the main menu (yes/no)");
		response = sc.next();
		// re-prompt user until they give valid response.
		while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
			System.out.println("Invalid response!\nWould you like to quit the program? (yes/no)");
			response = sc.next();
		}
		if (response.equalsIgnoreCase("yes")) { //
			System.out.println(
					"\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *\nThank you for visiting Giselle's Book Emporium. Good Bye!!\n*  *  "
							+ "*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *");
			System.exit(0); // terminate program.
			return false;
		} else {
			return true;
		}
	}

	/**
	 * this method ensures a user enters a valid positive floating point number when
	 * prompted for a price.
	 *
	 * @return a double price that the user enters.
	 */
	public double validatePrice() {
		double price;
		while (!sc.hasNextDouble()) { // makes sure user enters a valid floating point number for the price
			// of book.
			System.out.println("Must enter a valid price.");
			sc.next();
		}
		price = sc.nextDouble();
		while (price < 0) { // re-prompt user if they enter a negative number for the price
			System.out.println("Must enter a valid price.");
			price = sc.nextDouble();
		}
		return price;
	}

	/**
	 * static method which compares if a String entered by user matches the author
	 * attribute stored in each index of inventory array. When a match is found, the
	 * book object will be displayed to user. (part 2)
	 *
	 * @param author
	 *            is the String value of the author entered by the user, and will be
	 *            used to find any matches within the array of objects.
	 * @param inventory
	 *            is the array of book objects which we will search through in this
	 *            method
	 * @param bookCounter
	 *            is the total number of created book objects which we will need to
	 *            define the boundaries of the for-loop
	 */
	public static void findBooksBy(String author, Book[] inventory, int bookCounter) {
		int counter = 0; // initialize variable that will track how books have the same author
		System.out.println("The following books are written by " + author + ":");
		// loop through inventory array to see which indexes contain an author attribute
		// matching the
		// user input.
		for (int i = 0; i < bookCounter; i++) {
			if (author.equalsIgnoreCase(inventory[i].getAuthor())) {
				System.out.println("\n" + inventory[i]);
				counter++;
			}
			// if no book exists by indicated author, then display none.
		}
		// if books match the criteria then notify user.
		if (counter == 0)
			System.out.println("none.");
	}

	/**
	 * Static method which loops through each index of inventory array to see which
	 * book objects have a price lower than indicated by parameter (part 2)
	 *
	 * @param price
	 *            is the value of user input and will be used to compare
	 * @param inventory
	 *            is the array of book objects which we will search through in this
	 *            method
	 * @param bookCounter
	 *            is the total number of created book objects which we will need to
	 *            define the boundaries of the for-loop.
	 */
	public static void findCheaperThan(double price, Book[] inventory, int bookCounter) {
		int counter = 0; // initialize variable that will track how books are cheaper than indicated
		// price
		System.out.println("The following books are cheaper than $" + df2.format(price) + ":");
		// loop through inventory array to see which indexes contain book that have a
		// price attribute which is less than the user indicated price.
		for (int i = 0; i < bookCounter; i++) {
			// if the user value is greater than the price of a book stored in index i, then
			// display the book.
			if (price > inventory[i].getPrice()) {
				System.out.println("\n" + inventory[i]);
				counter++;
			}
		}
		// if books match the criteria then notify user.
		if (counter == 0)
			System.out.println("none.");
	}
}
