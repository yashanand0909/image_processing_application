package operations;

import image.ImageFactory;
import image.ImageInterface;
import java.util.ArrayList;
import java.util.List;

public class FilterOperation implements Operation{

  @Override
  public ImageInterface apply(ImageInterface image, Object operator) {
    int[][] kernel = (int[][]) operator;
    int height = image.getHeight();
    int width = image.getWidth();
    List<int[][]> imageChannel = image.getChannel();
    List<int[][]> filteredChannel = new ArrayList<>();

    for (int[][] channel : imageChannel) {
      int[][] filteredPixels = new int[height][width];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int sum = 0;
          int kernelSize = kernel.length;

          for (int k = 0; k < kernelSize; k++) {
            for (int l = 0; l < kernelSize; l++) {
              int rowIndex = i - kernelSize / 2 + k;
              int colIndex = j - kernelSize / 2 + l;

              if (rowIndex >= 0 && rowIndex < height && colIndex >= 0 && colIndex < width) {
                sum += kernel[k][l] * channel[rowIndex][colIndex];
              }
            }
          }

          filteredPixels[i][j] = sum;
        }
      }

      filteredChannel.add(filteredPixels);
    }

    return ImageFactory.createImage(filteredChannel);
  }
}
