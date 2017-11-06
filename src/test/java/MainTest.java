import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {

  /**
   * Application produces output with 10,000 connected geos in a 10,000 * 10,000 grid between 550
   * and 900 milliseconds
   */
  @Test(timeout = 1000)
  public void testBestTime() {
    String[] a = {"10000", "10000", "File2.csv"};
    Main.main(a);
  }


}