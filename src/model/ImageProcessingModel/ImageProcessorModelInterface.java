package model.ImageProcessingModel;

import java.io.IOException;

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

}
