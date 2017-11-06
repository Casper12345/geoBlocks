import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Main Entry point for Application.
 */
public class Main {

  private Map<Integer, String> readValues;
  private FileReader fileReader;

  /**
   * Constructor can be autowired for DI.
   */
  public Main() {
    this.fileReader = new CSVReader();
  }

  /**
   * Main method for application.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    Main m = new Main();

    try {
      m.commandLineInputHandler(args);
    } catch (CustomException e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * Method for handling commandLine arguments.
   *
   * @param args command line arguments. Throws Custom Exception
   */
  private void commandLineInputHandler(String[] args) throws CustomException {

    if (args.length < 3) {
      System.out.println("Please include arguments: height weight filename");
    } else {
      Object[] toReturn = new Object[3];
      try {
        toReturn[0] = Integer.parseInt(args[0]);
        toReturn[1] = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        throw new CustomException("Argument is not an integer");
      }
      toReturn[2] = args[2];
      try {
        applicationLogic(toReturn);
      } catch (CustomException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Method controlling application logic.
   *
   * @param args commandLine arguments.
   */
  private void applicationLogic(Object[] args) throws CustomException {

    try {
      readValues = fileReader.readFile((String) args[2]);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new CustomException("Problem with file", e);
    }

    int width = (Integer) args[0];
    int height = (Integer) args[1];

    List<Integer> geosToCreate = readValues.entrySet().stream()
        .map(Entry::getKey)
        .collect(Collectors.toList());

    int highest = geosToCreate.stream()
        .collect(Collectors.summarizingInt(Integer::intValue))
        .getMax();

    if (highest > width * height) {
      throw new CustomException("Size of grid is too small for Geos in file");
    }

    GeoClusters geoClusters = new GeoClusters(width);

    HashMap<Integer, HashSet<Integer>> neighborMap =
        geoClusters.clusterCounter(geosToCreate);

    List<Integer> listOfGeosOccupied;

    try {
      listOfGeosOccupied =
          new ArrayList<>(neighborMap.entrySet().stream()
              .max(Comparator.comparingInt(a -> a.getValue().size()))
              .orElse(null).getValue());

    } catch (NullPointerException e) {
      throw new CustomException("File is empty or broken", e);
    }

    Collections.sort(listOfGeosOccupied);
    printResult(listOfGeosOccupied);

  }

  /**
   * Method for Printing to Console.
   *
   * @param listOfGeosOccupied sorted list of occupied geos.
   */
  private void printResult(List<Integer> listOfGeosOccupied) {

    System.out.println("The Geos in the largest cluster of occupied Geos for this GeoBlock are:");
    for (Object index : listOfGeosOccupied) {
      System.out.println(index + ", " + readValues.get(index));
    }

  }

}
