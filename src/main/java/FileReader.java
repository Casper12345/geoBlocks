import java.util.Map;

/**
 * Interface for FileReader. May be implemented by any type of file Reader that returns a
 * Map<Integer, String>
 */
public interface FileReader {

  /**
   * Method for reading file.
   *
   * @param fileName fileName
   * @return Map<Integer, String>
   * @throws CustomException Custom Exception.
   */
  Map<Integer, String> readFile(String fileName) throws CustomException;

}
