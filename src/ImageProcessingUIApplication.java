import java.io.IOException;

import controller.ControllerInterface;
import controller.ImageProcessorControllerV2;
import logger.JFrameView;
import logger.JViewInterface;
import model.imageprocessingmodel.ImageProcessorModel;
import model.imageprocessingmodel.ImageProcessorModelInterface;

public class ImageProcessingUIApplication {
  public static void main(String[] args) throws IOException {
    ImageProcessorModelInterface imageProcessorModel = new ImageProcessorModel();
    JViewInterface view = new JFrameView("Image Processing Application");
    ControllerInterface controller = new ImageProcessorControllerV2(view, imageProcessorModel);
  }
}
