package operations.pixeloffset;

import image.ImageFactory;
import image.ImageInterface;
import operations.operationfactory.SingleImageProcessorWithOffset;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a simple offset operation of
 * brightening on image applied on every pixel.
 */
public class BrightnessOperation implements SingleImageProcessorWithOffset {
  int maxValue = 255;

  /**
   * @param image    the image to be operated on
   * @param operator the operator to be applied
   * @return new image with offset
   * @throws IllegalArgumentException if the operator is not integer
   */
  @Override
  public ImageInterface apply(ImageInterface image,
                              Object operator) throws IllegalArgumentException {
    if (!(operator instanceof Integer)) {
      throw new IllegalArgumentException("Operator must be an Integer");
    }
    int height = image.getHeight();
    int width = image.getWidth();
    List<int[][]> imageChannel = image.getChannel();
    List<int[][]> newChannel = new ArrayList<>();
    int factor = (Integer) operator;
    for (int[][] channel : imageChannel) {
      int[][] newPixels = new int[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          newPixels[i][j] = Math.min(channel[i][j] + factor, maxValue);
          newPixels[i][j] = Math.max(newPixels[i][j], 0);
        }
      }
      newChannel.add(newPixels);
    }
    return ImageFactory.createImage(newChannel);
  }
}
