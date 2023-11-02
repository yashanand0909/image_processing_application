import controller.ImageProcessorController;

import java.io.InputStreamReader;

import logger.ViewLogger;
import model.imageprocessingmodel.ImageProcessorModel;

/**
 * This class represents the main class for the image processing application and runs this program.
 */
public class ImageProcessingApplication {
  /**
   * Main method for starting the application.
   *
   * @param args argument
   */
  public static void main(String[] args) {
    ImageProcessorController imageProcessorController =
            new ImageProcessorController(new ViewLogger(System.out),
                    new ImageProcessorModel(), new InputStreamReader(System.in), System.out);
    imageProcessorController.startImageProcessingController();
  }
}
