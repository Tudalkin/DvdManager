package dvdproject;
import javax.swing.*;
import java.io.File;

/**
 * This class is an implementation of DVDUserInterface that uses JOptionPane to
 * display the menu of command choices.
 */

public class DVDGUI implements DVDUserInterface {

	private DVDCollection dvdlist;

	public DVDGUI(DVDCollection dl) {
		dvdlist = dl;
	}

	public void processFile() {
		// Prompt for the file name using JOptionPane
		String filename = JOptionPane.showInputDialog("Enter the name of the data file to load:");

		// If the dialog was canceled or input is empty, return
		if (filename == null || filename.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "No file entered. Exiting.");
			System.exit(0); // Exit the program if no file is provided
		}

		File file = new File(filename);

		// Check if the file exists
		String message;
		if (file.exists()) {
			message = "File found: " + filename;
		} else {
			message = "File not found. A new file will be created.";
		}

		// Show the message and provide a "Continue" button
		JOptionPane.showMessageDialog(null, message, "File Status", JOptionPane.INFORMATION_MESSAGE);

		// Load data into the DVDCollection
		dvdlist.loadData(filename);

		// Debugging output
		System.out.println(message);
		System.out.println(dvdlist);
	}

	public void processCommands() {
		String[] commands = { "Modify DVD","Add DVD", "Remove DVD", "Get DVDs By Rating", "Get Total Running Time","Display All DVDs",
				"Exit and Save" };

		int choice;

		do {
			choice = JOptionPane.showOptionDialog(null, "Select a command\n", "DVD Collection",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, commands,
					commands[commands.length - 1]);

			switch (choice) {
			case 0:
				doModifyDVD();
				break;
			case 1:
				doAddDVD();
				break;
			case 2:
				doRemoveDVD();
				break;
			case 3:
				doGetDVDsByRating();
				break;
			case 4:
				doGetTotalRunningTime();
				break;
			case 5:
				doDisplay();
			case 6:
				doSave();
				break;
			default: // do nothing
			}

		} while (choice != commands.length - 1);
		System.exit(0);
	}

	private void doAddDVD() {
	    // Request the title
	    String title = JOptionPane.showInputDialog("Enter title");
	    if (title == null) {
	        return; // dialog was cancelled
	    }
	    title = title.toUpperCase();

	    // Check if the DVD already exists using getDVDByTitle
	    DVD existingDVD = dvdlist.getDVDByTitle(title);
	    if (existingDVD != null) {
	        JOptionPane.showMessageDialog(null, "DVD with title \"" + title + "\" already exists.");
	        return; // Cancel the addition if it exists
	    }

	    // Request the rating
	    String rating = JOptionPane.showInputDialog("Enter rating for " + title);
	    if (rating == null) {
	        return; // dialog was cancelled
	    }
	    rating = rating.toUpperCase();

	    // Request the running time
	    String time = JOptionPane.showInputDialog("Enter running time for " + title);
	    if (time == null) {
	        return; // dialog was cancelled
	    }
	    String image = JOptionPane.showInputDialog("Enter image number(1-3) for " + title);
	    if (image == null) {
	        return; // dialog was cancelled
	    }

	    // Add or modify the DVD (assuming the rating and time are valid)
	    dvdlist.addOrModifyDVD(title, rating, time, image);

	    // Display current collection to the console for debugging
	    System.out.println("Adding/Modifying: " + title + "," + rating + "," + time);
	    System.out.println(dvdlist);
	}


	private void doModifyDVD() {
		// Collect DVD titles
		String[] titles = new String[dvdlist.getnumdvds()];
		for (int i = 0; i < dvdlist.getnumdvds(); i++)
			titles[i] = dvdlist.getDVD(i).getTitle();

		// Show dropdown for selecting an existing DVD or adding a new one
		String selectedTitle = (String) JOptionPane.showInputDialog(null,
				"Select a DVD to modify or enter a new title:", "Add/Modify DVD", JOptionPane.QUESTION_MESSAGE, null,
				titles, titles[0] // Default selection
		);

		if (selectedTitle == null) {
			return; // dialog was cancelled
		}

		// Retrieve the selected DVD details
		DVD selectedDVD = dvdlist.getDVDByTitle(selectedTitle);
		String existingRating = selectedDVD.getRating();
		int existingRunningTime = selectedDVD.getRunningTime();
		int existingImage=selectedDVD.getImage();

		// Request the new rating, pre-filling with the existing rating
		String rating = JOptionPane.showInputDialog(null, "Enter rating for " + selectedTitle, existingRating);
		if (rating == null) {
			return; // dialog was cancelled
		}
		rating = rating.toUpperCase();

		// Request the new running time, pre-filling with the existing running time
		String timeString = JOptionPane.showInputDialog(null, "Enter running time for " + selectedTitle,
				existingRunningTime);
		if (timeString == null) {
			return; // dialog was cancelled
		}
		String imageString = JOptionPane.showInputDialog(null, "Enter image number(1-3)  for " + selectedTitle,
				existingImage);
		if (imageString == null) {
			return; // dialog was cancelled
		}

		// Add or modify the DVD (assuming the rating and time are valid)
		dvdlist.addOrModifyDVD(selectedTitle, rating, timeString,imageString);

		// Display current collection to the console for debugging
		System.out.println("Adding/Modifying: " + selectedTitle + "," + rating + "," + timeString);
		System.out.println(dvdlist);
	}

	private void doRemoveDVD() {
		// Collect DVD titles
		String[] titles = new String[dvdlist.getnumdvds()];
		for (int i = 0; i < dvdlist.getnumdvds(); i++) {
			titles[i] = dvdlist.getDVD(i).getTitle();
		}

		// Show dropdown for selecting a DVD to remove
		String selectedTitle = (String) JOptionPane.showInputDialog(null, "Select a DVD to remove:", "Remove DVD",
				JOptionPane.QUESTION_MESSAGE, null, titles, titles[0] // Default selection
		);

		if (selectedTitle == null) {
			return; // dialog was cancelled
		}

		// Remove the matching DVD
		dvdlist.removeDVD(selectedTitle);

		// Display current collection to the console for debugging
		System.out.println("Removing: " + selectedTitle);
		System.out.println(dvdlist);
	}

	private void doGetDVDsByRating() {

		// Request the rating
		String rating = JOptionPane.showInputDialog("Enter rating");
		if (rating == null) {
			return; // dialog was cancelled
		}
		rating = rating.toUpperCase();

		String message = "DVDs with rating " + dvdlist.getDVDsByRating(rating);
		JOptionPane.showMessageDialog(null, message, "Get DVDs By Rating", JOptionPane.INFORMATION_MESSAGE);

	}

	private void doGetTotalRunningTime() {

		int total = dvdlist.getTotalRunningTime();
		String message = "Total Running Time of DVDs: " + total;
		JOptionPane.showMessageDialog(null, message, "Get Total Running Time", JOptionPane.INFORMATION_MESSAGE);

	}
	private void doDisplay() {
	    // Initialize a string to collect DVD information
	    String displayMessage = "<html>"; // Use HTML to format the display
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout to arrange components vertically

	    // Iterate over the DVDs in the collection
	    for (int i = 0; i < dvdlist.getnumdvds(); i++) {
	        DVD dvd = dvdlist.getDVD(i);
	        String title = dvd.getTitle();
	        String rating = dvd.getRating();
	        int runningTime = dvd.getRunningTime();
	        int imageNumber = dvd.getImage(); 

	        // Load the image from the src/ directory
	        String imagePath = "src/" + imageNumber + ".png"; // Assuming images are named as 1.png, 2.png, etc.
	        ImageIcon imageIcon = new ImageIcon(imagePath);
	        JLabel imageLabel = new JLabel(imageIcon);

	        // Concatenate the DVD information to the displayMessage
	        System.out.println(title);
	        displayMessage += "Title: " + title +
	                          "<br>Rating: " + rating +
	                          "<br>Running Time: " + runningTime + " minutes<br><br>";

	        // Add the DVD info and image to the panel
	        panel.add(new JLabel("<html>" + displayMessage + "</html>"));
	        panel.add(imageLabel);
	        displayMessage = ""; // Reset for the next DVD
	    }

	    // Show the collected DVD information with images in a dialog box
	    JOptionPane.showMessageDialog(null, panel, "All DVDs", JOptionPane.INFORMATION_MESSAGE);
	}

	private void doSave() {

		dvdlist.save();

	}

}
