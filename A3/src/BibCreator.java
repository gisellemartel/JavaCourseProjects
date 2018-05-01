
// -----------------------------------------------------
// COMP 249 Assignment 3
// Written by: Giselle Martel ID# 26352936
// -----------------------------------------------------

import java.io.*; //import classes of java.io library to allow creation, reading, and writing of text files.
import java.util.Scanner; //import the Scanner class to allow files to be read, and to also facilitate user input from keyboard.
import java.util.regex.*; //import  classes of java.util.regex library to be able to use methods of Patterna and Matcher class.
import fileinvalid.FileInvalidException; //import FileInvalidException class.

/**
 * <p>
 * Giselle Martel ID# 26352936<br>
 * COMP249 Winter 2018 Assignment # 3<br>
 * Due Date: March 19, 2018
 * </p>
 * <p>
 * class BibCreator contains the main method, and several static methods which
 * form a program that can read .bib files, and create three JSON files for each
 * .bib file. (One each in ACM, NJ3, and IEEE format). The program works by
 * first attempting to open 10 input files within a try-catch block. If this
 * fails, the program will terminate. After opening 10 input files, the program
 * will then create 30 corresponding output files within another try-catch, to
 * be stored in an array of File Objects and also to be stored in an array of
 * PrintWriter objects (methods of this class will make it possible to write
 * information on these files). If the FileNotFoundExeption exception is thrown
 * (cannot create output files), the program will terminate at this point.
 * </p>
 * <p>
 * Once the program completes the files creation, a static method
 * processFilesForValidation will validate all the input files to ensure they
 * are not empty or invalid, then it will write the information from the valid
 * input files onto to the 3 corresponding output files (ACM, IEEE, and NJ). If
 * any file is found to be invalid, then the program will delete the3 output
 * files associated with the file which were created before. Once all files are
 * successfully written, the program will prompt the user to type in the output
 * file that they wish to review. If the file they entered does not exist, the
 * user will get one more chance to make a choice. A second failed attempt will
 * result in the program terminating. Once the user has made a valid selection,
 * the program will read the file using the BufferedReader class and then
 * display on the console the contents of the requested file. The program will
 * then display a goodbye message and terminate immediately
 * </p>
 *
 * @author Giselle Martel
 *
 */
public class BibCreator {

	/**
	 * this method will create and return an array of output File objects, using a
	 * for loop to write a unique name for each file.
	 *
	 * @param NUM_OUT
	 *            is the maximum number of output files the program can create, and
	 *            will be used to define the length of the array of File objects.
	 * @return out which is an array of File objects containing all the output files
	 *         to be written.
	 */
	public static File[] createOutputFiles(int NUM_OUT) {
		File[] out = new File[NUM_OUT];
		String IEEEname, ACMname, NJ3name;

		for (int i = 0; i < 10; i++) {
			IEEEname = "IEEE" + (i + 1) + ".json";
			out[i] = new File(IEEEname);
			ACMname = "ACM" + (i + 1) + ".json";
			out[i + 10] = new File(ACMname);
			NJ3name = "NJ" + (i + 1) + ".json";
			out[i + 20] = new File(NJ3name);
		}
		return out;
	}

	/**
	 * This method when called will close all open input files stored in an array of
	 * Scanner objects using a for-loop to iterate to each object within a try-catch
	 * block. If one of objects is null, NullPointException will be thrown by the
	 * method close() and caught in catch block.
	 *
	 * @param arrayIn
	 *            is an array of Scanner objects where input files of the program to
	 *            be read are stored.
	 */
	public static void closeInputFiles(Scanner[] arrayIn) {
		try {
			for (int i = 0; i < arrayIn.length; i++) {
				arrayIn[i].close();
			}
		} catch (NullPointerException e) {
		}
	}

	/**
	 * This method is called when invalid input file is detected. It will close all
	 * corresponding PrintWriter objects and delete all corresponding file objects.
	 *
	 * @param out
	 *            is an array containing File objects for output files.
	 * @param arrayOut
	 *            is an array containing PrintWriter objects
	 */
	public static void wipeOutputFiles(File[] out, PrintWriter[] arrayOut) {
		for (int i = 0; i < out.length; i++) {
			System.out.println("Problem creating the file " + out[i].getName());
			arrayOut[i].close();
			if (out[i].exists())
				out[i].delete();
		}
	}

	/**
	 * This static method is called when an invalid file is read in the method
	 * processFilesForValidation. When called it will close the PrintWriter object
	 * and delete the File object associated with the invalid input file, After this
	 * is completed, the method will throw the FileInvalidException which will be
	 * caught by the calling method processFilesForValidation.
	 *
	 * @param arrayOut
	 *            is an array containing PrintWriter objects
	 * @param out
	 *            is an array containing File objects for output files.
	 * @param in
	 *            is an array containing File objects for input files, which will
	 *            allow the getFile method to be used to indicate to user which
	 *            input file was invalid.
	 * @param index
	 *            is current index that the for-loop inside the calling method
	 *            processFilesForValidation is currently at. This will allow this
	 *            method to close and delete the correct corresponding output files
	 * @param type
	 *            is the type of attribute that had an empty field, which is used in
	 *            the parameter when FileInvalidException is thrown, to notify the
	 *            user which field was invalid.
	 * @throws FileInvalidException
	 *             has parameter stating which files was invalid and the field in
	 *             the file that was empty. It will be caught by the calling method,
	 *             processFilesForValidation.
	 */
	public static void deleteOutputFiles(PrintWriter[] arrayOut, File[] out, File[] in, int index, String type)
			throws FileInvalidException {

		arrayOut[index].close();
		out[index].delete();
		arrayOut[index + 10].close();
		out[index + 10].delete();
		arrayOut[index + 20].close();
		out[index + 20].delete();
		if (type != null) {
			throw new FileInvalidException("\nError: Detected Empty Field!\n\nProblem detected with input file "
					+ in[index].getName() + "\nField " + type
					+ " is empty. Processing stopped at this point. Other empty fields may be present as well!!\n\n");
		} else {
			throw new FileInvalidException("\nError: Detected Invalid File!! \n\nProblem detected with input file "
					+ in[index].getName()
					+ ". The file is either not in the proper format, is empty, or is missing information! Processing stopped at this point.\n\n");
		}
	}

	/**
	 * This method will write information onto the output files using the println
	 * method. Before writing information onto the file is attempted, the exists
	 * method of the File class will be used to ensure the file exists. The Pattern
	 * and Matcher classes will be used to correctly display the author for the ACM
	 * format.
	 *
	 * @param arrayOut
	 *            is an array of PrintWriter objects to be written
	 * @param values
	 *            is an array of String values containing the information to be
	 *            written
	 * @param index
	 *            is current index that the for-loop inside the calling method
	 *            processFilesForValidation is at. This will allow this method to
	 *            write the correct corresponding output files
	 * @param out
	 *            is an array of File objects containing the output files
	 *
	 * @param articleNum
	 *            is used to print the current article number for ACM format files.
	 */
	public static void printOutputFiles(PrintWriter[] arrayOut, String[] values, int index, File[] out,
			int articleNum) {
		// use regular expression with Pattern.compile in order to find matches matches
		// with the word "and" in the String containing the authors (in index 0 of
		// values).
		Matcher m = Pattern.compile("(?i)(and)").matcher(values[0]);
		String author = ""; // declare new empty String for author
		// use while loop with find method, which will return true when a match is found
		// in the String.
		int x = 0;
		while (m.find()) {
			x = m.start(); // when the first "and" is found, store the index in int x.
			author = values[0].substring(0, x) + "et al. "; // author will be equal to the first name to index before
															// the word "and" with "et al." added to the end.
			break; // break out of the loop as soon as first match is found.
		}
		// if "and" is not found in values[0], then only one author.
		if (x == 0) {
			author = values[0] + " ";
		}

		// print files formatted to IEEE format.
		if (out[index].exists()) {
			arrayOut[index].println((values[0].replaceAll(" and ", ", ") + ". \"" + values[1] + "\", " + "" + values[2]
					+ ", vol. " + values[4] + ", no. " + values[3] + ", p. " + values[5] + ", " + values[7] + " "
					+ values[8] + ".\n"));
		}
		// print files formatted to ACM format.
		if (out[index + 10].exists())
			arrayOut[index + 10].println("[" + (articleNum) + "] " + author + values[8] + ". " + values[1] + ". "
					+ values[2] + ". " + values[4] + ", " + values[3] + " (" + values[8] + "), " + values[5]
					+ ". DOI:https://doi.org/" + values[10] + ".\n");
		// print files formatted to NJ (nature journal) format.
		if (out[index + 20].exists())
			arrayOut[index + 20].println(values[0].replaceAll("and", "&") + ". " + values[1] + ". " + values[2] + ". "
					+ values[4] + ", " + values[5] + "(" + values[8] + ").\n");
	}

	/**
	 * This static method validates input files to see if they fit the JSON format.
	 * It also checks to see if any value field is empty. If the file is invalid,
	 * then the static method deleteOutputFiles will be called which will delete any
	 * output files associated with an invalid input file. If a file is valid, then
	 * this method will store values of each field (author, title, etc) in an array
	 * of Strings, which will be used to write to the corresponding output files.
	 * Once every field has been stored in the array, the static method
	 * printOutputFiles will be called to write the data onto the output files.
	 *
	 * @param arrayIn
	 *            is an array of Scanner objects, which will be used to read each
	 *            input file
	 * @param arrayOut
	 *            is an array of PrintWriter objects, which will be used to write
	 *            each output file
	 * @param in
	 *            is an array of File objects, which contains all the created input
	 *            files of the program.
	 * @param out
	 *            is an array of File objects, which contains all the created output
	 *            files of the program.
	 */
	public static void processFilesForValidation(Scanner[] arrayIn, PrintWriter[] arrayOut, File[] in, File[] out) {

		final int numValues = 11; // create constant for the max # of attributes an article has (author, title,
									// etc)
		/*
		 * create array which will store the value of each attribute for every file in
		 * order. Index 0: author, 1:title, 2:journal, 3:number, 4:volume, 5: pages, 6:
		 * keyword, 7: month, 8: year, 9: ISSN, 10: doi
		 */
		String[] values = new String[numValues];
		int ctr = 0; // create counter to track number of invalid files.
		System.out.println("\nProcessing files...\n");
		for (int i = 0; i < arrayIn.length; i++) {
			int articleNum = 1; // create counter to track article numbers (for ACM format)
			// enclose code block that will validate files within a try-catch.
			try {
				String line = null, type = null;
				// if the file is empty, it is invalid
				if (!arrayIn[i].hasNextLine()) {
					ctr++; // increment the counter each time invalid file is detected.
					deleteOutputFiles(arrayOut, out, in, i, type); // call the deleteOutputFiles method.
				}
				// if file hasNextLine() (we haven't reached the end of the file) read articles
				while (arrayIn[i].hasNextLine()) {

					line = arrayIn[i].nextLine(); // read first line of document.
					if (line.equals("@ARTICLE{")) {
						// if the file contains no information then its invalid.
						if (arrayIn[i].next().equals("}")) {
							ctr++; // increment the counter each time invalid file is detected.
							deleteOutputFiles(arrayOut, out, in, i, type); // call the deleteOutputFiles method.
						}
					}
					// As long as the line is not empty or we have not reached a new article, then
					// continue reading file line by line.
					else if (!line.equals("@ARTICLE{") || !line.equals("")) {
						/*
						 * use series of if-else statement to see if the line that was just read
						 * contains any pertinent information (author, title, etc)
						 */
						if (line.contains("author={")) {
							/*
							 * store the value of each information type in its respective array index, by
							 * splitting the line into substring starting from just after opening curly
							 * brace (exclusive) to the just before the closing curly brace and comma. For
							 * example, if the Scanner reads "author={J. Smith},", then "J. Smith" will be
							 * stored in the correct index of value[]
							 */
							values[0] = line.substring(line.indexOf("{") + 1, line.indexOf(",") - 1);
							/*
							 * use nested if statement to evaluate if no value is given in between the curly
							 * braces (this is an invalid file). In this case, the helper method
							 * deleteOutputFiles we be called which will close and delete the invalid output
							 * files, and then throw the FileInvalidException to be caught by the catch
							 * block below.
							 */
							if (values[0].equals("")) {
								type = "author"; // store the information type as a String, which will be sent as a
								// parameter in the deleteOutputFilesMethod
								ctr++; // increment the counter each time invalid file is detected.
								deleteOutputFiles(arrayOut, out, in, i, type); // call the deleteOutputFiles method.
							}
						} else if (line.contains("title={")) {
							values[1] = line.substring(line.indexOf("{") + 1, line.indexOf(",") - 1);
							if (values[1].equals("")) {
								type = "title";
								ctr++;
								deleteOutputFiles(arrayOut, out, in, i, type);
							}
						} else if (line.contains("journal={")) {
							values[2] = line.substring(line.indexOf("{") + 1, line.indexOf(",") - 1);
							if (values[2].equals("")) {
								type = "journal";
								ctr++;
								deleteOutputFiles(arrayOut, out, in, i, type);
							}
						} else if (line.contains("number={")) {
							values[3] = line.substring(line.indexOf("{") + 1, line.indexOf(",") - 1);
							if (values[3].equals("")) {
								type = "number";
								ctr++;
								deleteOutputFiles(arrayOut, out, in, i, type);
							}
						} else if (line.contains("volume={")) {
							values[4] = line.substring(line.indexOf("{") + 1, line.indexOf(",") - 1);
							if (values[4].equals("")) {
								type = "volume";
								ctr++;
								deleteOutputFiles(arrayOut, out, in, i, type);
							}
						} else if (line.contains("pages={")) {
							values[5] = line.substring(line.indexOf("{") + 1, line.indexOf(",") - 1);
							if (values[5].equals("")) {
								type = "pages";
								ctr++;
								deleteOutputFiles(arrayOut, out, in, i, type);
							}

						} else if (line.contains("keywords={")) {
							values[6] = line.substring(line.indexOf("{") + 1, line.indexOf(",") - 1);
							if (values[6].equals("")) {
								type = "keywords";
								ctr++;
								deleteOutputFiles(arrayOut, out, in, i, type);
							}
						} else if (line.contains("month={")) {
							values[7] = line.substring(line.indexOf("{") + 1, line.indexOf(",") - 1);
							if (values[7].equals("")) {
								type = "month";
								ctr++;
								deleteOutputFiles(arrayOut, out, in, i, type);
							}
						} else if (line.contains("year={")) {
							values[8] = line.substring(line.indexOf("{") + 1, line.indexOf(",") - 1);
							if (values[8].equals("")) {
								type = "year";
								ctr++;
								deleteOutputFiles(arrayOut, out, in, i, type);
							}
						} else if (line.contains("ISSN={")) {
							values[9] = line.substring(line.indexOf("{") + 1, line.indexOf(",") - 1);
							if (values[9].equals("")) {
								type = "ISSN";
								ctr++;
								deleteOutputFiles(arrayOut, out, in, i, type);
							}
						} else if (line.contains("doi={")) {
							values[10] = line.substring(line.indexOf("{") + 1, line.indexOf(",") - 1);
							if (values[10].equals("")) {
								type = "doi";
								ctr++;
								deleteOutputFiles(arrayOut, out, in, i, type);
							}
							// once the line equals "}", we have reached the end of the article, and can
							// call the method to print the output files.
						} else if (line.equals("}")) {
							printOutputFiles(arrayOut, values, i, out, articleNum);
							articleNum++;
						}

					}
				}

				// once file has been validated, read, and written, close() the PrintWriter
				// objects.
				arrayOut[i].close();
				arrayOut[i + 10].close();
				arrayOut[i + 20].close();
			} catch (FileInvalidException e) { // catch FileInvalidException when invalid file is detected
				System.out.print(e.getMessage()); // print exception message.
			}

		}
		// notify user once all files are written how many could not be processed.
		System.out.println("\nA total of " + ctr + " files were invalid, and could not be processed. All other "
				+ (arrayIn.length - ctr) + " \"valid\" files have been created.");
		closeInputFiles(arrayIn); // close input files once they are all validated/read.
	}

	/**
	 * This method will display output files created by the program which the user
	 * requests to review.
	 *
	 * @param b
	 *            is a BufferedReader object which is used to read the output files
	 * @param s
	 *            is a String which will store the contents read by the
	 *            BufferedReader
	 * @throws IOException
	 *             when there is an error reading the file. Will be caught in the
	 *             try-catch block in main method.
	 */
	public static void displayCreatedFiles(BufferedReader b, String s) throws IOException {
		System.out.println(
				"\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\nHere are the contents of the successfully created JSON file "
						+ s + ":\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");
		// Display contents of file using readLine() method
		String contentLine = b.readLine();
		while (contentLine != null) {
			System.out.println(contentLine);
			contentLine = b.readLine();
		}
	}

	public static void main(String[] args) {
		System.out.println(
				"* * * * * * * * * * * * * * * * * * * * * * * * * * * *\nWELCOME TO BIBCREATOR\n* * * * * * * * * * * * * * * * * * * * * * * * * * * *");

		Scanner[] arrayIn = null; // declare null array of Scanner objects for storing input files to be read
		File[] in = null; // declare null array of File objects for input files
		PrintWriter[] arrayOut = null; // declare null array of PrintWriter objects for storing output files to be
		// written
		File[] out = null; // declare null array of File objects for output files.
		final int NUM_IN = 10; // declare constants for the maximum number of input files and output files that
								// the program create.
		final int NUM_OUT = 30;
		String filename = ""; // declare empty String which will be used to temporarily store the name of
								// input files.

		// use try-catch block to open input files.
		Scanner sc;
		try {
			arrayIn = new Scanner[NUM_IN]; // instantiate array of Scanner objects and File objects.
			in = new File[NUM_IN];
			System.out.println("\nLoading input files... ");
			// use for loop to create new File objects to be stored in File array and
			// Scanner array.
			for (int i = 0; i < NUM_IN; i++) {
				filename = "Latex" + (i + 1) + ".bib"; // create filename
				File f = new File(filename); // create new file
				in[i] = f; // store in File array
				// create new Scanner object to read from file f
				sc = new Scanner(new FileInputStream(f));
				arrayIn[i] = sc; // store Scanner object in Scanner array

			}
			/*
			 * if FileNotFoundException is thrown, then notify user which files could not be
			 * opened, call method to close all opened input files, then terminate program.
			 */
		} catch (FileNotFoundException e) {
			System.out.println("Could not open input file " + filename
					+ " for reading. Please check if file exists! Program will terminate after closing any opened files.");
			closeInputFiles(arrayIn);
			System.out.println("Terminating program!!!");
			System.exit(0); // end program if this catch block executes.
		}

		// use try-catch block to open output files

		try {
			// call createOutputFiles method to create output files.
			out = createOutputFiles(NUM_OUT);
			arrayOut = new PrintWriter[NUM_OUT];
			PrintWriter pw;
			System.out.println("\nCreating output files... ");
			// use for loop to create PrintWriter object for each output file
			for (int i = 0; i < NUM_IN; i++) {
				pw = new PrintWriter(new FileOutputStream(out[i]));
				arrayOut[i] = pw;
				pw = new PrintWriter(new FileOutputStream(out[i + 10]));
				arrayOut[i + 10] = pw;
				pw = new PrintWriter(new FileOutputStream(out[i + 20]));
				arrayOut[i + 20] = pw;
			}
			/*
			 * if FileNotFoundException is thrown, then notify user which files could not be
			 * created, call method wipe/delete output files and method to close all open
			 * input files, then terminate program.
			 */
		} catch (FileNotFoundException e) {
			wipeOutputFiles(out, arrayOut);
			closeInputFiles(arrayIn);
			System.out.println("Terminating program!!!");
			System.exit(0); // end program if this catch block executes.
		}

		/*
		 * once all input files are successfully opened, and output files are
		 * successfully created, call processFilesForValidation method to read/write
		 * files.
		 */
		processFilesForValidation(arrayIn, arrayOut, in, out);

		/*
		 * if processFilesForValidation executes with no exceptions or errors, then use
		 * another try-catch block to allow the user to review written output files.
		 */
		Scanner input = new Scanner(System.in); // instantiate Scanner object to be used for user input.
		BufferedReader b = null; // declare null BufferedReader object.

		System.out.println("\n\nPlease enter the name of one of the files that you need to review: ");

		String s = input.next();
		input.nextLine();
		try {
			// instantiate BufferedReader object, which will contain FileReader object,
			// which will contain File object with user input as parameter.
			b = new BufferedReader(new FileReader(new File(s)));
			displayCreatedFiles(b, s); // call method to display user indicated file

		} catch (FileNotFoundException e1) {
			System.out.println("Could not open input file. File does not exist; possibly it could not be created!");
			// use nested try-catch to give the user one more chance to enter a file name.
			try {
				System.out.println(
						"However, you will be allowed another chance to enter the file name.\nPlease enter the name of one of the files that you need to review: ");
				s = input.next();
				input.nextLine();
				b = new BufferedReader(new FileReader(new File(s)));
				displayCreatedFiles(b, s); // call method to display user indicated file
			} catch (FileNotFoundException f1) {
				System.out.println("Sorry! I am unable to display your desired files! Program will exit!");
				System.exit(0);
			} catch (IOException f2) {
				System.out.println(f2.getMessage());
			}

		} catch (IOException e2) {
			System.out.println(e2.getMessage());
		}
		System.out.println(
				"* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  \nThanks for using BibCreator. Goodbye!!!\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		input.close(); // close input Scanner object before ending program.
		System.exit(0);
	}
}
