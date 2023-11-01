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
   * @param channels   the list of channels of the image
   * @throws IllegalArgumentException if the number of channels is not 3
   */
  private CommonImage(List<int[][]> channels) throws IllegalArgumentException {
    if (channels.get(0).length != channels.get(1).length || channels.get(0).length != channels.get(2).length
            || channels.get(0)[0].length != channels.get(1)[0].length
            || channels.get(0)[0].length != channels.get(2)[0].length) {
      throw new IllegalArgumentException("Number of rows and columns must be the same");
    }
    this.channels = channels;
    this.height = channels.get(0).length;
    this.width = channels.get(0)[0].length;
  }

  public static class ImageBuilder{
    private List<int[][]> channles;

    public ImageBuilder() {
      this.channles = new ArrayList<>();
    }

    public void addChannel(int[][] channel){
      this.channles.add(channel);
    }

    public ImageInterface build(){
      return new CommonImage(channles);
    }

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
