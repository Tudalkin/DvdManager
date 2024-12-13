package dvdproject;
import java.io.*;

public class DVDCollection {

	// Data fields
	
	/** The current number of DVDs in the array */
	private int numdvds;
	
	/** The array to contain the DVDs */
	private DVD[] dvdArray;
	
	/** The name of the data file that contains dvd data */
	private String sourceName;
	
	/** Boolean flag to indicate whether the DVD collection was
	    modified since it was last saved. */
	private boolean modified;
	
	/**
	 *  Constructs an empty directory as an array
	 *  with an initial capacity of 7. When we try to
	 *  insert into a full array, we will double the size of
	 *  the array first.
	 */
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[7];
	}
	
	public String toString() {
		String temp="";
		temp = "numdvds = " + numdvds +"\ndvdArray.length = 7\n";

		for (int i = 0; i < numdvds; i++) {
			temp+="dvdArray["+i+"] = "+dvdArray[i].getTitle()+"/"+dvdArray[i].getRating()+"/"+dvdArray[i].getRunningTime()+"\n";
		}
		return temp;
	}

	public void addOrModifyDVD(String title, String rating, String runningTime, String dvdImage) {
		//check rating & running time
		int time;
		int image;
		try {
	        time = Integer.parseInt(runningTime);
	        image=Integer.parseInt(dvdImage);
	    } catch (NumberFormatException e) {
	        return; // Invalid running time
	    }
	    if (rating == null || rating.isEmpty()) {
	        return; // Invalid rating
	    }
	    //find index position
	    int index= 0;
	    while(index < numdvds) {
	    	//if title exists
	    	if (dvdArray[index].getTitle().equalsIgnoreCase(title)) {
	    		dvdArray[index].setRating(rating);
	    		dvdArray[index].setRunningTime(time);
	    		dvdArray[index].setImage(image);
	    		return;
	    	}
	    	//if title doesn't exist
	    	if(dvdArray[index].getTitle().compareToIgnoreCase(title) > 0){
	    		break;
	    	}
	    	index++;

	    }
	    // Double the size of the array if full
	    if (numdvds == dvdArray.length) {
            resizeArray();//created new function
        }
	    //move all dvds back
	    for (int i = numdvds; i > index; i--) {
            dvdArray[i] = dvdArray[i - 1];
        }
	    //insert dvd
	    dvdArray[index] = new DVD(title, rating, time,image);
        numdvds++;
        modified = true;
	}
	
	public void removeDVD(String title) {
		  int index = -1;
		  for (int i = 0; i < numdvds; i++) {
		        if (dvdArray[i].getTitle().equals(title)) {
		            index = i;
		            break;
		        }
		    }
		    if (index != -1) {//cd found else not needed
		        for (int i = index; i < numdvds - 1; i++) {
		            dvdArray[i] = dvdArray[i + 1];
		        }
		        //modify data
		        numdvds--;
		        modified = true;
		    }

	}
	
	public String getDVDsByRating(String rating) {
		String temp = "";

	    // find cd 
	    for (int i = 0; i < numdvds; i++) {
	        if (dvdArray[i].getRating().equalsIgnoreCase(rating)) {
	            temp += dvdArray[i].toString() + "\n";
	        }
	    }

	    return temp;

	}

	public int getTotalRunningTime() {
		int temp = 0;

	    // count run time
	    for (int i = 0; i < numdvds; i++) {
	        temp += dvdArray[i].getRunningTime();
	    }

	    return temp;
	}

	
	public void loadData(String filename) {
		sourceName=filename;
		BufferedReader reader = null; 
	    try {
	        // Open the file
	        reader = new BufferedReader(new FileReader(filename));
	        String line;
	        
	        // Read line from the file
	        while ((line = reader.readLine()) != null) {
	            // Split the line
	            String[] dvdData = line.split(",");
	            
	            if (dvdData.length == 4) {
	                String title = dvdData[0].trim();
	                String rating = dvdData[1].trim();
	                String runningTime = dvdData[2].trim();
	                String image=dvdData[3].trim();
	                
	                //insert into the collection
	                addOrModifyDVD(title, rating, runningTime,image);
	            }
	        }
	    } catch (FileNotFoundException e) {
	        // If the file is not found, start with an empty collection
	        System.out.println("File not found. Starting with an empty collection.");
	        numdvds = 0;
	        dvdArray = new DVD[7];
	    } catch (IOException e) {
	        e.printStackTrace(); // Handle any other I/O exceptions
	    } finally {
	        try {
	            if (reader != null) {
	                reader.close(); // Close the file reader
	            }
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle exceptions when closing the reader
	        }
	    }
		
	}
	
	public void save() {
		   // Check if the sourceName is set
	    if (sourceName == null || sourceName.isEmpty()) {
	        System.out.println("No source file specified. Save operation aborted.");
	        return;
	    }

	    BufferedWriter writer = null;
	    
	    try {
	        // Open the file
	        writer = new BufferedWriter(new FileWriter(sourceName));

	        // add cds to file
	        for (int i = 0; i < numdvds; i++) {
	            writer.write(dvdArray[i].toString());
	            writer.newLine(); // Add a newline after each DVD
	        }
	        
	        // collection as not modified
	        modified = false;

	    } catch (IOException e) {
	        e.printStackTrace(); // Handle I/O exceptions
	    } finally {
	        try {
	            if (writer != null) {
	                writer.close(); // Close the file writer
	            }
	        } catch (IOException e) {
	            e.printStackTrace(); // Handle exceptions when closing the writer
	        }
	    }
	}
	// Additional private helper methods go here:
	private void resizeArray() {
	    DVD[] newArray = new DVD[dvdArray.length * 2];
	    for (int i = 0; i < dvdArray.length; i++) {
	        newArray[i] = dvdArray[i]; // Manually copying each element
	    }
	    dvdArray = newArray;
	}
	public DVD getDVD(int index) {
	    if (index < 0 || index >= numdvds) {
	        System.out.println("Invalid index.");
	        return null; // Return null if the index is out of bounds
	    }
	    return dvdArray[index]; // Return the DVD object at the given index
	}
	public int getnumdvds() {
	    return numdvds;
	}
	public DVD getDVDByTitle(String title) {
	    for (int i = 0; i < numdvds; i++) {
	        if (dvdArray[i].getTitle().equalsIgnoreCase(title)) {
	            return dvdArray[i]; // Return the matching DVD object
	        }
	    }
	    return null; // Return null if no matching DVD is found
	}



}
