package model.operations.operatorutil;

public class OperatorUtil {

  public static int castOperatorToDouble(String operator) {
    int percentage;
    try {
      percentage = Integer.parseInt(operator);
    }
    catch (Exception e){
      throw new IllegalArgumentException("Percentage should be double value");
    }
    if (percentage < 0 || percentage > 100){
      throw new IllegalArgumentException("Percentage should be between 0 and 100");
    }
    return percentage;
  }

}
