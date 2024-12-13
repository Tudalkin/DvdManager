package testing;

import org.junit.Test;
import static org.junit.Assert.*;

import dvdproject.DVDCollection;

public class DVDCollectionTest {

    @Test
    public void testAddOrModifyDVD() {
        DVDCollection collection = new DVDCollection();
        collection.addOrModifyDVD("Inception", "PG-13", "148", "1");
        assertEquals(1, collection.getnumdvds());
        assertEquals("Inception", collection.getDVD(0).getTitle());
    }

    @Test
    public void testRemoveDVD() {
        DVDCollection collection = new DVDCollection();
        collection.addOrModifyDVD("Inception", "PG-13", "148", "1");
        collection.removeDVD("Inception");
        assertEquals(0, collection.getnumdvds());
    }

    @Test
    public void testGetDVDsByRating() {
        DVDCollection collection = new DVDCollection();
        collection.addOrModifyDVD("Inception", "PG-13", "148", "1");
        collection.addOrModifyDVD("Interstellar", "PG-13", "169", "2");
        assertTrue(collection.getDVDsByRating("PG-13").contains("Inception"));
        assertTrue(collection.getDVDsByRating("PG-13").contains("Interstellar"));
    }

    @Test
    public void testGetTotalRunningTime() {
        DVDCollection collection = new DVDCollection();
        collection.addOrModifyDVD("Inception", "PG-13", "148", "1");
        collection.addOrModifyDVD("Interstellar", "PG-13", "169", "2");
        assertEquals(317, collection.getTotalRunningTime());
    }

    @Test
    public void testResizeArray() {
        DVDCollection collection = new DVDCollection();
        for (int i = 0; i < 8; i++) {
            collection.addOrModifyDVD("Movie " + i, "PG", "120", "1");
        }
        assertEquals(8, collection.getnumdvds());
    }

    @Test
    public void testLoadData() {
        DVDCollection collection = new DVDCollection();
        collection.loadData("src/dvddata.txt");
        assertEquals(3, collection.getnumdvds());
    }

    @Test
    public void testSave() {
        DVDCollection collection = new DVDCollection();
        collection.addOrModifyDVD("Inception", "PG-13", "148", "1");
        collection.save();
        // Verify the file contents manually or use mock file writing in advanced cases
    }
}



