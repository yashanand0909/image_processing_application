package model.operations.colortransformation;

import model.image.ImageFactory;
import model.image.ImageInterface;
import java.util.Arrays;
import model.operations.operationinterfaces.SingleImageProcessor;

import java.util.List;

/**
 * This interface represents a color transformation operation on an image.
 */
public abstract class CommonColorTransformOperation implements SingleImageProcessor {
  /**
   * This method applies the color transformation operation on the given image.
   *
   * @param image the image to be transformed
   * @return the transformed image
   * @throws IllegalArgumentException if the image doesn't have 3 channels
   */
  @Override
  public ImageInterface apply(ImageInterface image) throws IllegalArgumentException {
    double[][] coffeicient = getTransformCoefficient();
    int height = image.getHeight();
    int width = image.getWidth();
    List<int[][]> imageChannel = image.getChannel();
    if (imageChannel.size() != 3) {
      throw new IllegalArgumentException("Image must have 3 channels");
    }
    int[][] newRedChannel = new int[height][width];
    int[][] newGreenChannel = new int[height][width];
    int[][] newBlueChannel = new int[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int R = imageChannel.get(0)[i][j];
        int G = imageChannel.get(1)[i][j];
        int B = imageChannel.get(2)[i][j];

        int newRed = (int)(coffeicient[0][0] * R + coffeicient[0][1] * G
            + coffeicient[0][2] * B);
        int newGreen = (int)(coffeicient[1][0] * R + coffeicient[1][1] * G
            + coffeicient[1][2] * B);
        int newBlue = (int)(coffeicient[2][0] * R + coffeicient[2][1] * G
            + coffeicient[2][2] * B);

        newRedChannel[i][j] = Math.min(newRed, 255);
        newGreenChannel[i][j] = Math.min(newGreen, 255);
        newBlueChannel[i][j] = Math.min(newBlue, 255);
      }
    }
    List<int[][]> newImageChannelList = Arrays.asList(newRedChannel,newGreenChannel, newBlueChannel);
    return ImageFactory.createImage(newImageChannelList);
  }

  /**
   * This method returns the transform coefficient.
   *
   * @return the transform coefficient
   */
  public abstract double[][] getTransformCoefficient();
}
