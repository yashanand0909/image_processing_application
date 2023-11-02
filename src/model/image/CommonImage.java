package model.image;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a RGB image. A RGB image is an image with three channels.
 */
public class CommonImage implements ImageInterface {
  private final int height;
  private final int width;
  private final List<int[][]> channels;

  /**
   * Constructs a RGB image object with the given height, width and channels.
   *
   * @param channels the list of channels of the image
   * @throws IllegalArgumentException if the number of channels is not 3
   */
  private CommonImage(List<int[][]> channels) throws IllegalArgumentException {
    int height = channels.get(0).length;
    int width = channels.get(0)[0].length;
    for (int i = 1; i < channels.size(); i++) {
      if (channels.get(i).length != height || channels.get(i)[0].length != width) {
        throw new IllegalArgumentException("Number of rows and columns must be the same");
      }
    }
    this.channels = channels;
    this.height = channels.get(0).length;
    this.width = channels.get(0)[0].length;
  }

  /**
   * This method returns the builder of the image.
   */
  public static class ImageBuilder {
    private List<int[][]> channles;

    /**
     * Constructs a image builder object.
     */
    public ImageBuilder() {
      this.channles = new ArrayList<>();
    }

    /**
     * This method adds a channel to the image.
     *
     * @param channel the channel to be added
     */
    public void addChannel(int[][] channel) {
      this.channles.add(channel);
    }

    /**
     * This method builds the image.
     *
     * @return the image
     */
    public ImageInterface build() {
      return new CommonImage(channles);
    }

  }

  /**
   * This method returns the channels of the image.
   *
   * @return the channels of the image
   */
  @Override
  public List<int[][]> getChannel() {
    return new ArrayList<>(channels);
  }

  /**
   * This method returns the height of the image.
   *
   * @return the height of the image
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * This method returns the width of the image.
   *
   * @return the width of the image
   */
  @Override
  public int getWidth() {
    return width;
  }

}
