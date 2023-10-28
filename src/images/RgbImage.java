package images;

import java.util.List;
import operations.Operation;

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
  public ImageInterface applyOperation(Operation operation, Object operator) {
    return operation.apply(this,operator);
  }
}
