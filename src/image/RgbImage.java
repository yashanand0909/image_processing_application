package image;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a RGB image. A RGB image is an image with three channels.
 */
public class RgbImage implements ImageInterface {
  private final int height;
  private final int width;
  private final List<int[][]> channels;

  /**
   * Constructs a RGB image object with the given height, width and channels.
   *
   * @param height   the height of the image
   * @param width    the width of the image
   * @param channels the channels of the image
   * @throws IllegalArgumentException if the number of channels is not 3
   */
  public RgbImage(int height, int width, List<int[][]> channels) throws IllegalArgumentException {
    if (channels.size() != 3) {
      throw new IllegalArgumentException("Number of channels must be 3");
    }
    this.height = height;
    this.width = width;
    this.channels = new ArrayList<>(channels);
  }

  @Override
  public List<int[][]> getChannel() {
    return new ArrayList<>(channels);
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

}
