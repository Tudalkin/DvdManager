package dvdproject;
import java.util.*;

/**
 * 	Program to display and modify a simple DVD collection
 */

public class DVDManager {

	public static void main(String[] args) {
		
	    DVDCollection dl = new DVDCollection();
	    DVDGUI dlInterface = new DVDGUI(dl);  // Create GUI interface

	    // Prompt the user to enter the file name and load it via the processFile method
	    dlInterface.processFile();

	    // Start processing commands via the GUI
	    dlInterface.processCommands();
	}

}
