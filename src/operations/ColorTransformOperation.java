package operations;

import image.ImageFactory;
import image.ImageInterface;
import java.util.ArrayList;
import java.util.List;

public class ColorTransformOperation implements Operation {

  @Override
  public ImageInterface apply(ImageInterface image, Object operator) {
    double[] coffeicient = (double[]) operator;
    int height = image.getHeight();
    int width = image.getWidth();
    List<int[][]> imageChannel = image.getChannel();
    List<int[][]> grayscaleChannel = new ArrayList<>();
    int[][] grayscalePixels = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = imageChannel.get(0)[i][j];
        int g = imageChannel.get(1)[i][j];
        int b = imageChannel.get(2)[i][j];

        int grayValue = (int) (coffeicient[0] * r + coffeicient[1]* g + coffeicient[2] * b);

        grayscalePixels[i][j] = grayValue;
      }
    }

    grayscaleChannel.add(grayscalePixels);
    return ImageFactory.createImage(grayscaleChannel);
  }
}
