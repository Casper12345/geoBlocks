import static org.junit.Assert.*;

import java.util.Map;
import org.junit.Test;

public class CSVReaderTest {

  @Test
  public void readFile() throws Exception {
    CSVReader c = new CSVReader();
    Map<Integer, String> read = c.readFile("TestFile.csv");

    assertTrue(read.containsKey(4));
    assertFalse(read.containsKey(1));

  }

  @Test
  public void readFile2() throws Exception {
    CSVReader c = new CSVReader();
    Map<Integer, String> read = c.readFile("TestFile.csv");

    assertEquals(read.get(4), "Tom, 2010-10-10");


  }

  @Test(expected = CustomException.class)
  public void readFile3() throws Exception {
    CSVReader c = new CSVReader();
    c.readFile("TestdFile.txsdt");

  }

  @Test(expected = CustomException.class)
  public void readFile4() throws Exception {
    CSVReader c = new CSVReader();
    c.readFile("BrokenFile.csv");

  }

}