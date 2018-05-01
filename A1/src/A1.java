import java.util.Scanner; //import Scanner class to allow user input

//------------------------------------------------------------------------------------------------------------------
//Assignment 1 Driver Class
//Written By: Giselle Martel 26352936
//for COMP 249 Section PP - Winter 2018
//------------------------------------------------------------------------------------------------------------------
/**
 * <p>COMP 249 section PP Assignment # 1</p><p> Due Date: January 31, 2018 </p>
 *
 * <p>This driver class implements a program which manages inventory for a book
 * store. When the program begins, the user will be prompted to enter the
 * maximum amount of books their store can hold. The main user-interface will
 * then appear, which is principally based on a menu with 5 options, with each
 * option being associated with a condition of a switch (5 conditions for 5
 * options) statement. Option 1 asks the user to enter 4 attributes for each
 * book. These attributes are stored for each book in an array of objects of the
 * Book Class by using a for-loop. Option 2 allows the user to change attributes
 * of existing book objects by prompting the user for the book number and then
 * displaying a sub-menu where they can select which attribute they wish to
 * change. Option 3 allows the user to search for books by author name. This is
 * implemented by calling a method from the Book class (findBooksBy), which uses
 * a for-loop to search through each index of the inventory array to track any
 * book objects with a matching author attribute. Matching books will be
 * displayed to the user. Option 4 allows the user to search for books under a
 * price that they name. Similar to option 3, a method from the Book class will
 * be called which will use a for-loop to search for book object with desired
 * attributes. Matching book will be displayed to the user. Option 5 allows the
 * user to quit the program.</p>
 *
 * @author Giselle Martel ID# 26352936
 * @see Book Class
 */
public class A1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // create object of Scanner class to allow user input.
		Book input = new Book(); // create new object of Book class which will be used to call methods of the
		// Book class that validate inputs.
		System.out.println(
				"*  *  *  *  *  *  *  *  *  *  *  *  *\nWelcome to Giselle's Book Emporium!!\n*  *  *  *  *  *  *  *  *  *  *  *  *\n");
		System.out.println("Please enter the maximum number of books for the store: ");
		int maxBooks = input.validateInput(); // create variable for the maximum amount of books for the store
		Book[] inventory = new Book[maxBooks]; // create array of objects inventory which will store Book objects.
		String title, author; // create variables for book attributes
		long isbn;
		double price;
		boolean restartMenu = false; // value of restartMenu will determine whether main menu reappears.
		int bookCounter = 0; // initialize variable to track number of created book objects.
		int lastBookIndex = 0; // initialize variable to track the the index of the last entered book.
		int iterationCounter = 0;// counter tracks # of times user repeats the password attempt process
		/*
		 * create do-while loop that will re-initialize the main menu in the program
		 * until the user indicates that they want to quit, or they are kicked out from
		 * making too many failed password (conditional on boolean value of restartMenu)
		 * attempts.
		 */
		do {
			// Display main menu and prompt user to select an option
			System.out.println(
					"\n------------------------\nMAIN MENU\n------------------------\nWhat do you want to do? \n"
							+ "1. Enter new books (password required) \n"
							+ "2. Change information of a book (password required) \n"
							+ "3. Display all books by a specific author \n"
							+ "4. Display all books under a certain price \n" + "5. Quit \n"
							+ "\nPlease enter your choice: ");
			int userChoice = input.validateInput(5); // call validateInput method to ensure valid input.
			int numBooks = 0; // create variable for the number of books the user wishes to create
			String response; // create String variable to store user response when prompted
			/*
			 * use a switch statement indicate program behavior based on user choice (5
			 * options). A break statement is used in every case in order to exit the switch
			 * statement once one condition has been evaluated.
			 */
			switch (userChoice) {
			// option 1 - user enters information for book object(s)
			case 1:
				// restartMenu equals boolean value returned by verifyPassword method.
				restartMenu = input.verifyPassword();
				// each time the a user enters an invalid password three times in a row (when
				// verifyPassword() returns a true boolean value), increment iteration counter.
				if (restartMenu == true)
					iterationCounter++;
				// as long as the user enters the correct password restartMenu will remain false
				// and the user will be prompted to enter book information.
				while (restartMenu == false) {
					// variable stores amount of remaining indices in the inventory array.
					int roomLeft = maxBooks - lastBookIndex;
					// if there is only room for one book the program will automatically make the
					// user enter its info.
					if (roomLeft == 1) {
						System.out.println("There is only space for one book.");
						numBooks = 1;
					} else if (roomLeft > 1) {
						numBooks = input.validateNumBooks(roomLeft);
						// if there is no more space in inventory than notify the user.
					} else if (roomLeft == 0) {
						System.out.println("The bookstore is full!! You cannot add any new books.");
					}
					// total amount of books is increased by number of books user enters info for.
					bookCounter += numBooks;
					/*
					 * use for-loop to allow user to enter info for each book to be stored in array
					 * inventory. The number of new books being entered plus the amount of
					 * previously entered books must not surpass the maximum amount of books
					 * allowed. if there is no more room in the store to add books, then the for
					 * loop will not be initialized.
					 */
					for (int i = lastBookIndex; i < numBooks + lastBookIndex; i++) {
						System.out.println("\nPlease enter the title of book " + (i + 1) + ": ");
						title = sc.next();
						title += sc.nextLine();
						System.out.println("Please enter the author of book " + (i + 1) + ": ");
						author = sc.nextLine();
						System.out.println("Please enter the ISBN of book " + (i + 1) + ": ");
						isbn = input.validateInput();
						System.out.println("Please enter the price of book " + (i + 1) + ": ");
						price = input.validatePrice();
						Book myBook = new Book(title, author, isbn, price); // create new book abject containing 4
																			// attributes (using parameterized
																			// constructor in Book class)
						inventory[i] = myBook; // assign book object to its respective index in the array of obj
												// inventory.
					}
					// the index of the last created book object will be incremented by the number
					// of newly created books
					lastBookIndex += numBooks;
					restartMenu = input.quitProgram(); // call the quitProgram method to see if user would like continue
														// using program.
				}
				break;
			// option 2 - user modifies information for existing book.
			case 2:
				// restartMenu will equal the boolean value returned by the verifyPassword
				// method.
				restartMenu = input.verifyPassword();
				// If the user enters the correct password, ask which book they would like to
				// change information for:
				while (restartMenu == false) {
					System.out.println("Please enter the book number that you would like to modify: ");
					int modBook = input.validateInput() - 1; // subtract one since java starts index from zero but books
					// will be counted from one
					/*
					 * if the book the user wishes to modify is greater or equal to the number of
					 * books created, then the book object does not exist.
					 */
					if (modBook >= bookCounter) {
						// ask user if they would like to try again if indicated book does not exist
						System.out.println(
								"The indicated book number does not exist in our records. Would you like to try again? (yes/no)");
						response = sc.next();
						// re-prompt user until they give valid response.
						while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
							System.out.println("Invalid Response! Would you like to try again? (yes/no)");
							response = sc.next();
						}
						if (response.equalsIgnoreCase("no"))
							restartMenu = true; // if user does not wish retry again main menu will re-initialize.
					} else {
						System.out.print("Book #: " + (modBook + 1) + "\n" + inventory[modBook] + "\n");
						// use do -while loop to re-initialize menu until user selects Quit option.
						do {
							// prompt user which parameters of the book they would like to modify.
							System.out.println(
									"\nWhat would you like to change? \n\t1. author \n\t2. title\n\t3. ISBN\n\t4. price\n\t5. Quit\nEnter your choice: ");
							userChoice = input.validateInput(5);
							// use switch statement to allow modification of book based on user choice.
							switch (userChoice) {
							// option 1 - change author
							case 1:
								System.out.println("Please enter the author of book " + (modBook + 1) + ": ");
								author = sc.next();
								// set author attribute of book object located at desired index to user input.
								inventory[modBook].setAuthor(author);
								// Display updated book to user.
								System.out.println("\nBook # " + modBook + 1 + "\n" + inventory[modBook]);
								break;
							// option 2 - change title
							case 2:
								System.out.println("Please enter the title of book " + (modBook + 1) + ": ");
								title = sc.next();
								title += sc.nextLine();
								inventory[modBook].setTitle(title);
								System.out.println("\nBook # " + modBook + 1 + "\n" + inventory[modBook]);
								break;
							// option 3 - change ISBN
							case 3:
								System.out.println("Please enter the ISBN of book " + (modBook + 1) + ": ");
								isbn = input.validateInput();
								inventory[modBook].setIsbn(isbn);
								System.out.println("\nBook # " + modBook + 1 + "\n" + inventory[modBook]);
								break;
							// option 4 - change price
							case 4:
								System.out.println("Please enter the price of book " + (modBook + 1) + ": ");
								price = input.validatePrice();
								inventory[modBook].setPrice(price);
								System.out.println("\nBook # " + modBook + 1 + "\n" + inventory[modBook]);
								break;
							// option 5 - quit to return to main menu.
							case 5:
								restartMenu = true; // set restartMenu to true to exit do-while loop and return to main
								// menu.
								break;
							}
						} while (restartMenu == false);
					}

				}

				break;
			case 3: // option 3 - Allow user to search for book based on author.
				// prompt user for author they wish to search for.
				if (bookCounter == 0) {
					System.out.println("There are no books in the store yet!!");
					restartMenu = true;
					break;
				}
				System.out.println("Enter author name: ");
				author = sc.next();
				author += sc.nextLine();
				// call static method findBooksBy to perform search to see if there are any
				// author matches in the inventory array
				Book.findBooksBy(author, inventory, bookCounter);
				// ask user if they would like to continue using the program
				restartMenu = input.quitProgram(); // call quitProgram method to prompt user if they would like to quit
				// program
				break;
			case 4: // option 4 - allows user to search for all books cheaper than indicated price
				if (bookCounter == 0) {
					System.out.println("There are no books in the store yet!!");
					restartMenu = true;
					break;
				}
				System.out.println("Please enter a price: ");
				price = input.validatePrice();
				Book.findCheaperThan(price, inventory, bookCounter);
				restartMenu = input.quitProgram(); // call quitProgram method to prompt user is they would like to quit
				// program
				break;
			// option 5 - when user wishes to exit program.
			case 5:
				System.out.println("\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *\nThank you for visiting Giselle's Book Emporium. Good Bye!!\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *");
				System.exit(0); // terminate program.
			}
			// when the user has repeated the process for option 1 four times, the program
			// will terminate
			if (iterationCounter >= 4) {
				System.out.println("\n\nProgram detected suspicious activities and will terminate immediately!!");
				System.exit(0); // terminate program.
			}
		} while (restartMenu == true); // once password is incorrectly entered 3 times,
		// or user completes task from main menu options, re-iteration of do-while loop
		// will occur.
		sc.close(); // close Scanner
	}
}
