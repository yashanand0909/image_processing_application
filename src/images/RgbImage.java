package images;

import java.util.List;

public class RgbImage implements ImageInterface{
  private final int height;
  private final int width;
  private final List<int[][]> channels;

  public RgbImage(int height, int width, List<int[][]> channels) {
    this.height = height;
    this.width = width;
    this.channels = channels;
  }

  @Override
  public List<int[][]> getChannel() {
    return channels;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public int getWidth() {
    return 0;
  }

}
