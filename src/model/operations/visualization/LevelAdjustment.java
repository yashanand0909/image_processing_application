package model.operations.visualization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import model.image.ImageFactory;
import model.image.ImageInterface;
import model.operations.operationinterfaces.SingleImageProcessorWithOffset;

/**
 * This class represents a level adjustment operation on an image.
 */
public class LevelAdjustment implements SingleImageProcessorWithOffset {
  /**
   * This method applies a level adjustment operation on the image.
   *
   * @param image    the image to be operated on
   * @param operator the operator to be applied
   * @return new image with level adjustment
   * @throws IllegalArgumentException if the operator is less than 3 and
   *                                  not in ascending order
   */
  @Override
  public ImageInterface apply(ImageInterface image, Object operator)
          throws IllegalArgumentException {
    List<Integer> levelAdjustmentParameters =
            Arrays.stream(operator.toString().trim().split("\\s+"))
                    .map(Integer::parseInt).collect(Collectors.toList());
    if (levelAdjustmentParameters.size() != 3) {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
    if (!(levelAdjustmentParameters.get(0) < levelAdjustmentParameters.get(1)
            && levelAdjustmentParameters.get(1) < levelAdjustmentParameters.get(2))) {
      throw new IllegalArgumentException("Invalid ordering of parameters");
    }
    return ImageFactory.createImage(createLevelAdjustment(image, levelAdjustmentParameters));
  }

  private List<int[][]> createLevelAdjustment(ImageInterface image,
                                              List<Integer> levelAdjustmentParameters) {
    int blackFactor = levelAdjustmentParameters.get(0);
    int middleFactor = levelAdjustmentParameters.get(1);
    int whiteFactor = levelAdjustmentParameters.get(2);
    float commonDivisorForAdjustmentParameter = blackFactor * blackFactor
            * (middleFactor - whiteFactor)
            - blackFactor * (middleFactor * middleFactor
            - whiteFactor * whiteFactor)
            + whiteFactor * middleFactor * middleFactor
            - middleFactor * whiteFactor * whiteFactor;
    float levelAdjustmentForLinearity = (-blackFactor * (128 - 255)
            + 128 * whiteFactor
            - 255 * middleFactor);
    float levelAdjustmentForQuadratic = blackFactor * blackFactor * (128 - 255)
            + 255 * middleFactor * middleFactor
            - 128 * whiteFactor * whiteFactor;
    float levelAdjustmentForConstant = blackFactor * blackFactor
            * (255 * middleFactor - 128 * whiteFactor)
            - blackFactor * (255 * middleFactor * middleFactor
            - 128 * whiteFactor * whiteFactor);
    int parameterForLinearity = (int) (levelAdjustmentForLinearity
            / commonDivisorForAdjustmentParameter);
    int parameterForQuadratic = (int) (levelAdjustmentForQuadratic
            / commonDivisorForAdjustmentParameter);
    int parameterForConstant = (int) (levelAdjustmentForConstant
            / commonDivisorForAdjustmentParameter);
    List<int[][]> channels = new ArrayList<>();
    for (int[][] channel : image.getChannel()) {
      channels.add(createLevelAdjustmenPerChannel(channel,
              parameterForLinearity, parameterForQuadratic,
              parameterForConstant));
    }
    return channels;
  }

  private int[][] createLevelAdjustmenPerChannel(int[][] channel,
                                                 int parameterForLinearity,
                                                 int parameterForQuadratic,
                                                 int parameterForConstant) {
    for (int i = 0; i < channel.length; i++) {
      for (int j = 0; j < channel[0].length; j++) {
        int pixel = channel[i][j];
        channel[i][j] = parameterForQuadratic * pixel * pixel
                + parameterForLinearity * pixel
                + parameterForConstant;
      }
    }
    return channel;
  }
}
