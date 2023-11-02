package model.ImageProcessingModel;

import java.io.IOException;
import model.image.ImageInterface;

public interface ImageProcessorModelInterface {

  void processCommands(String[] parts) throws IOException;

  ImageInterface getImage(String name);

}
