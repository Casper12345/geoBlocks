/**
 * Custom exception for application.
 */
public class CustomException extends RuntimeException {

  /**
   * Constructor one for wrapping and rethrowing exception.
   *
   * @param message custom message
   * @param original original exception
   */
  public CustomException(String message, Throwable original) {
    super(message, original);
  }

  /**
   * Constructor for throwing custom exception.
   *
   * @param message custom message
   */
  public CustomException(String message) {
    super(message);
  }

}
