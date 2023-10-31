package logger;

import java.io.OutputStream;

public class ViewLogger {
  public static void logException(Exception e){
    System.out.println(e.getMessage());
  }

}
