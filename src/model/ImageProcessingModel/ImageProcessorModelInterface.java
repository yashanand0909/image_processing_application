package model.ImageProcessingModel;

import java.io.IOException;

import model.image.ImageInterface;

/**
 * This interface represents an image processor model.
 */
public interface ImageProcessorModelInterface {
  /**
   * This method processes the commands.
   *
   * @param parts the commands to be processed
   * @throws IOException if the input is invalid
   */
  void processCommands(String[] parts) throws IOException;

  /**
   * This method returns the image with the given name.
   *
   * @param name of the image
   */
  ImageInterface getImage(String name);

}
