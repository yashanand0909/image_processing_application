package model.imageio;

import java.io.IOException;

import commonlabels.ImageFormats;
import model.image.ImageInterface;

/**
 * This interface helps in IO operations for different file formats.
 */
public interface IOFileByFormat {
  /**
   * This method encodes and saves the image in the given filename.
   *
   * @param filename the filename to save the image
   * @param image    the image to be saved
   * @throws IOException if the file cannot be saved
   */
  public void encodeAndSaveImage(String filename, ImageInterface image, ImageFormats format) throws IOException;

  /**
   * This method decodes the image from the given filename.
   *
   * @param filename the filename to read the image
   * @return the image read from the file
   * @throws IOException if the file cannot be read
   */
  public ImageInterface decodeImage(String filename) throws IOException;
}
