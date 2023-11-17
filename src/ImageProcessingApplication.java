import controller.ImageProcessorController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

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
    if(args.length > 0 && args[0].equals("-file")){
      ImageProcessorController imageProcessorController =
              new ImageProcessorController(new ViewLogger(System.out),
                      new ImageProcessorModel(),
                      new InputStreamReader(new ByteArrayInputStream(("run " + args[1]).getBytes())),
                      System.out);
      imageProcessorController.startImageProcessingController();
      return;
    }
    ImageProcessorController imageProcessorController =
            new ImageProcessorController(new ViewLogger(System.out),
                    new ImageProcessorModel(), new InputStreamReader(System.in), System.out);
    imageProcessorController.startImageProcessingController();
  }
}
