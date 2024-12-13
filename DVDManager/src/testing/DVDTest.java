package testing;

import org.junit.Test;
import static org.junit.Assert.*;
import dvdproject.DVD;

public class DVDTest {

    @Test
    public void testGetTitle() {
        DVD dvd = new DVD("Inception", "PG-13", 148, 1);
        assertEquals("Inception", dvd.getTitle());
    }

    @Test
    public void testSetTitle() {
        DVD dvd = new DVD("Inception", "PG-13", 148, 1);
        dvd.setTitle("Interstellar");
        assertEquals("Interstellar", dvd.getTitle());
    }

    @Test
    public void testGetRating() {
        DVD dvd = new DVD("Inception", "PG-13", 148, 1);
        assertEquals("PG-13", dvd.getRating());
    }

    @Test
    public void testSetRating() {
        DVD dvd = new DVD("Inception", "PG-13", 148, 1);
        dvd.setRating("R");
        assertEquals("R", dvd.getRating());
    }

    @Test
    public void testGetRunningTime() {
        DVD dvd = new DVD("Inception", "PG-13", 148, 1);
        assertEquals(148, dvd.getRunningTime());
    }

    @Test
    public void testSetRunningTime() {
        DVD dvd = new DVD("Inception", "PG-13", 148, 1);
        dvd.setRunningTime(180);
        assertEquals(180, dvd.getRunningTime());
    }

    @Test
    public void testSetImageValid() {
        DVD dvd = new DVD("Inception", "PG-13", 148, 1);
        dvd.setImage(2);
        assertEquals(2, dvd.getImage());
    }

    @Test
    public void testSetImageInvalid() {
        DVD dvd = new DVD("Inception", "PG-13", 148, 1);
        dvd.setImage(5);
        assertEquals(1, dvd.getImage());
    }

    @Test
    public void testToString() {
        DVD dvd = new DVD("Inception", "PG-13", 148, 1);
        assertEquals("Inception,PG-13,148,1", dvd.toString());
    }
}


