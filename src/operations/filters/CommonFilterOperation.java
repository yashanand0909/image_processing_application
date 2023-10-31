package operations.filters;


import image.ImageFactory;
import image.ImageInterface;
import operations.operationinterfaces.SingleImageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a common filter operation that implements the FilterOperation interface.
 * It contains a method that applies a filter to an image.
 */
public abstract class CommonFilterOperation implements SingleImageProcessor {

  /**
   * This method applies a filter to an image.
   *
   * @param image the image to be filtered
   * @return the filtered image
   * @throws IllegalArgumentException if the kernel is larger than the image
   */
  @Override
  public ImageInterface apply(ImageInterface image) throws IllegalArgumentException {
    double[][] kernel = getFilter();
    int height = image.getHeight();
    int width = image.getWidth();
    if (kernel.length > height || kernel[0].length > width) {
      throw new IllegalArgumentException("Kernel must be smaller than image");
    }
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

  /**
   * This method returns the filter to be applied to the image.
   *
   * @return the filter to be applied to the image
   */
  public abstract double[][] getFilter();
}
