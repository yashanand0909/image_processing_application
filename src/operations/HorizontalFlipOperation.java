package operations;

import image.ImageFactory;
import image.ImageInterface;
import java.util.ArrayList;
import java.util.List;

public class HorizontalFlipOperation implements Operation{

  public ImageInterface apply(ImageInterface image, Object operator) {
    int height = image.getHeight();
    int width = image.getWidth();
    List<int[][]> imageChannel = image.getChannel();
    List<int[][]> flippedChannel = new ArrayList<>();

    for (int[][] channel : imageChannel) {
      int[][] flippedPixels = new int[height][width];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          flippedPixels[i][j] = channel[i][width - 1 - j];
        }
      }

      flippedChannel.add(flippedPixels);
    }

    return ImageFactory.createImage(flippedChannel);
  }


}
