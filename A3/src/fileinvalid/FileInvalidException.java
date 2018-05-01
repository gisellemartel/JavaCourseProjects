package fileinvalid;

import java.io.FileNotFoundException;

/**
 * class FileInvalidException is inherited from FileNotFoundExeption. Objects of
 * this class are created when the FileInvalidException is thrown when an
 * invalid input file is detected by methods of the BibCreator class.
 *
 * @author Giselle Martel
 *
 */

public class FileInvalidException extends FileNotFoundException {
	public FileInvalidException() {
		super("Error: Input file cannot be parsed due to missing information (i.e. month={}, title={}, etc.)");
	}

	public FileInvalidException(String s) {
		super(s);
	}
}
