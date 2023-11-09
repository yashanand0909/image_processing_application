package model.image;

import java.util.List;

/**
 * This interface represents an image.
 */
public interface ImageInterface {
  /**
   * Returns the channels of the image.
   *
   * @return the channels of the image
   */
  public List<int[][]> getChannel();

  /**
   * Returns the height of the image.
   *
   * @return the height of the image
   */
  public int getHeight();

  /**
   * Returns the width of the image.
   *
   * @return the width of the image
   */
  public int getWidth();

}
