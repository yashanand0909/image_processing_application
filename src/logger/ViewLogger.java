package logger;

import java.io.IOException;

public class ViewLogger {

  private final Appendable out;

  public ViewLogger(Appendable out) {
    this.out = out;
  }

  public void logException(Exception e) {
    try {
      this.out.append(e.getMessage());
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

  public void LogString(String s){
    try {
      this.out.append(s);
    } catch (IOException ex) {
      System.out.println(ex.getMessage());
    }
  }

}
