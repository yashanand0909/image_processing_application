package model.operations.colortransformation;

import static model.operations.operatorutil.OperatorUtil.castOperatorToDouble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.image.ImageFactory;
import model.image.ImageInterface;
import model.operations.operationinterfaces.SingleImageProcessor;
import model.operations.operationinterfaces.SingleImageProcessorWithOffset;

/**
 * This interface represents a color transformation operation on an image.
 */
public abstract class CommonColorTransformOperation implements SingleImageProcessorWithOffset,
    SingleImageProcessor {

  /**
   * This method applies the color transformation operation on the given image.
   *
   * @param image    the image to be transformed
   * @param operator the operator object for the filter
   * @return the transformed image
   * @throws IllegalArgumentException if the image doesn't have 3 channels
   */
  @Override
  public ImageInterface apply(ImageInterface image, Object operator)
      throws IllegalArgumentException {
    return getImage(image, operator);
  }

  /**
   * This method applies the color transformation operation on the given image.
   *
   * @param image the image to be transformed
   * @return the transformed image
   * @throws IllegalArgumentException if the image doesn't have 3 channels
   */
  @Override
  public ImageInterface apply(ImageInterface image) {
    String fullImageOperator = "100";
    return getImage(image, fullImageOperator);
  }

  private ImageInterface getImage(ImageInterface image, Object operator) {
    int percentage = castOperatorToDouble((String) operator);
    double[][] coffeicient = getTransformCoefficient();
    int height = image.getHeight();
    int width = image.getWidth();
    int perWidth = width * percentage / 100;
    List<int[][]> imageChannel = image.getChannel();
    if (imageChannel.size() != 3) {
      throw new IllegalArgumentException("Image must have 3 channels");
    }
    int[][] newRedChannel = new int[height][width];
    int[][] newGreenChannel = new int[height][width];
    int[][] newBlueChannel = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = imageChannel.get(0)[i][j];
        int g = imageChannel.get(1)[i][j];
        int b = imageChannel.get(2)[i][j];

        int newRed = j >= perWidth ? r : (int) (coffeicient[0][0] * r + coffeicient[0][1] * g
            + coffeicient[0][2] * b);
        int newGreen = j >= perWidth ? g : (int) (coffeicient[1][0] * r + coffeicient[1][1] * g
            + coffeicient[1][2] * b);
        int newBlue = j >= perWidth ? b : (int) (coffeicient[2][0] * r + coffeicient[2][1] * g
            + coffeicient[2][2] * b);

        newRedChannel[i][j] = Math.min(newRed, 255);
        newGreenChannel[i][j] = Math.min(newGreen, 255);
        newBlueChannel[i][j] = Math.min(newBlue, 255);
      }
    }
    List<int[][]> newImageChannelList = new ArrayList<>(Arrays.asList(newRedChannel,
        newGreenChannel, newBlueChannel));
    return ImageFactory.createImage(newImageChannelList);
  }


  /**
   * Transform coefficient is returned by the method.
   *
   * @return the transform coefficient
   */
  public abstract double[][] getTransformCoefficient();
}
