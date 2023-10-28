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

  /**
   * Constructs a RGB image object with the given height, width and channels.
   *
   * @param red   the red channel of the image
   * @param green the green channel of the image
   * @param blue  the blue channel of the image
   * @throws IllegalArgumentException if the number of channels is not 3
   */
  public RgbImage(int[][] red, int[][] green, int[][] blue) throws IllegalArgumentException {
    if (red.length != green.length || red.length != blue.length
            || red[0].length != green[0].length
            || red[0].length != blue[0].length) {
      throw new IllegalArgumentException("Number of rows and columns must be the same");
    }
    this.channels = new ArrayList<>();
    this.height = red.length;
    this.width = red[0].length;
    this.channels.add(red);
    this.channels.add(green);
    this.channels.add(blue);
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
