package dvdproject;
public class DVD {

	// Fields:

	private String title; // Title of this DVD
	private String rating; // Rating of this DVD
	private int runningTime; // Running time of this DVD in minutes
	private int image; //Which image to display 1-3

	public DVD(String dvdTitle, String dvdRating, int dvdRunningTime, int dvdImage) {
		this.title = dvdTitle;
		this.rating = dvdRating;
		this.runningTime = dvdRunningTime;
		this.image=dvdImage;
	}

	public String getTitle() {
		return this.title;
	}

	public String getRating() {
		return this.rating;
	}

	public int getRunningTime() {
		return this.runningTime;
	}
	public int getImage() {
		return this.image;
	}
	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public void setRating(String newRating) {
		this.rating = newRating;
	}

	public void setRunningTime(int newRunningTime) {
		this.runningTime = newRunningTime;
	}
	public void setImage(int newImage) {
		if (newImage>=1&&newImage<=3) 
			this.image=newImage;
	}
	public String toString() {
		return title + "," + rating + "," + runningTime +","+image;
	}

}
