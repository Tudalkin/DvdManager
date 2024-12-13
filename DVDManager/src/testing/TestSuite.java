package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    DVDTest.class,
    DVDCollectionTest.class
})
public class TestSuite {
}
