import controller.ImageProcessorController;
import java.io.InputStreamReader;
import logger.ViewLogger;
import model.operations.ImageProcessingModel.ImageProcessorModel;

public class ImageProcessingApplication {
  public static void main(String[] args) {
    ImageProcessorController imageProcessorController = new ImageProcessorController(new ViewLogger(System.out),new ImageProcessorModel(),new InputStreamReader(System.in), System.out);
    imageProcessorController.startImageProcessingController();
  }
}
