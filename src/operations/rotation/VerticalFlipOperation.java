package operations.rotation;

import image.ImageFactory;
import image.ImageInterface;
import operations.operationfactory.SingleImageProcessor;


import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a vertical flip operation on an image.
 */
public class VerticalFlipOperation implements SingleImageProcessor {

  /**
   * This method applies the vertical flip operation on the given image.
   *
   * @param image the image to be flipped
   * @return the flipped image
   */
  public ImageInterface apply(ImageInterface image) {
    int height = image.getHeight();
    int width = image.getWidth();
    List<int[][]> imageChannel = image.getChannel();
    List<int[][]> flippedChannel = new ArrayList<>();

    for (int[][] channel : imageChannel) {
      int[][] flippedPixels = new int[height][width];

      for (int i = 0; i < height; i++) {
        int flippedRow = height - 1 - i;
        for (int j = 0; j < width; j++) {
          flippedPixels[i][j] = channel[flippedRow][j];
        }
      }

      flippedChannel.add(flippedPixels);
    }

    return ImageFactory.createImage(flippedChannel);
  }


}
