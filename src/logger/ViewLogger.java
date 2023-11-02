package logger;

import java.io.IOException;

/**
 * This class represents the logger for the view.
 */
public class ViewLogger {

  private final Appendable out;

  /**
   * Constructs a ViewLogger object.
   *
   * @param out the output source
   */
  public ViewLogger(Appendable out) {
    this.out = out;
  }

  /**
   * This method logs the exception.
   *
   * @param e the exception
   */
  public void logException(Exception e) {
    try {
      this.out.append(e.getMessage());
    } catch (IOException ex) {
      System.out.println(ex.getMessage() + "\n");
    }
  }

  /**
   * This method logs the String.
   *
   * @param s the String
   */
  public void logString(String s) {
    try {
      this.out.append(s);
    } catch (IOException ex) {
      System.out.println(ex.getMessage() + "\n");
    }
  }

}
