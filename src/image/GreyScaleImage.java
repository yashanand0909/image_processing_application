package image;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a grey scale image. A grey scale image is an image with only one channel.
 */
public class GreyScaleImage implements ImageInterface {
  private final int height;
  private final int width;
  private final List<int[][]> channels;

  /**
   * Constructs a grey scale image object with the given height, width and channels.
   *
   * @param height   the height of the image
   * @param width    the width of the image
   * @param channels the channels of the image
   * @throws IllegalArgumentException if the number of channels is not 1
   */
  public GreyScaleImage(int height, int width, List<int[][]> channels) throws IllegalArgumentException {
    if (channels.size() != 1) {
      throw new IllegalArgumentException("Number of channels must be 1");
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
