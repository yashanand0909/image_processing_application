import controller.ImageProcessorController;
import java.io.InputStreamReader;

public class ImageProcessingApplication {
  public static void main(String[] args) {
    ImageProcessorController imageProcessorController = new ImageProcessorController(new InputStreamReader(System.in), System.out);
    imageProcessorController.startImageProcessingController();
  }
}
