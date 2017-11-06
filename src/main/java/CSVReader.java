import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for comma separated file reader. Implements FileReader and may be swapped out with other
 * type of fileReader that implements FileReader.
 */
public class CSVReader implements FileReader {

  /**
   * Method for reading comma separated files.
   *
   * @param fileName name of file
   * @return Map key of geo location and string of information.
   */
  public Map<Integer, String> readFile(String fileName) throws CustomException {

    List<String[]> parsed = null;

    try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

      parsed = stream.map(a -> a.split(", "))
          .collect(Collectors.toList());

    } catch (IOException e) {
      throw new CustomException("File name invalid of file missing", e);
    }

    Map<Integer, String> toReturn = new LinkedHashMap<>();

    for (String[] s : parsed) {
      try {
        toReturn.put(Integer.parseInt(s[0]), s[1] + ", " + s[2]);
      } catch (NumberFormatException e) {
        throw new CustomException("Problem with file", e);
      }
    }
    return toReturn;
  }
}
