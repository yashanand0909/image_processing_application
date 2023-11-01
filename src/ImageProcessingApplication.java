import java.util.Scanner;

import controller.ImageProcessorCLI;

public class ImageProcessingApplication {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ImageProcessorCLI imageProcessorCLI = new ImageProcessorCLI();
    imageProcessorCLI.startImageProcessingController();
  }
}
