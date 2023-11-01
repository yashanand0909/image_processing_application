package model.operations.colorrepresentation;

import java.util.List;

import model.image.ImageFactory;
import model.image.ImageInterface;
import model.operations.operationinterfaces.SingleImageProcessor;

/**
 * This class represents a color representation operation that can be applied to an image.
 */
public abstract class AbstractColorRepresentation implements SingleImageProcessor {
  /**
   * This method applies the color representation operation on the given image.
   *
   * @param image the image to be transformed
   * @return the transformed image
   * @throws IllegalArgumentException if the image is null or of single channel
   */
  @Override
  public ImageInterface apply(ImageInterface image) throws IllegalArgumentException {
    if (image == null || image.getHeight() == 0
            || image.getWidth() == 0 || image.getChannel().size() == 1) {
      throw new IllegalArgumentException("Image is not valid");
    }
    List<int[][]> imageChannel = image.getChannel();
    int height = image.getHeight();
    int width = image.getWidth();
    int[][] valueChannel = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        performOperation(image, valueChannel, i, j);
      }
    }
    imageChannel.add(valueChannel);
    return ImageFactory.createImage(imageChannel);
  }

  /**
   * This method performs the operation on the given image.
   *
   * @param image        the image to be performed on
   * @param valueChannel the value channel
   * @param i            the row index
   * @param j            the column index
   */
  public abstract void performOperation(ImageInterface image,
                                        int[][] valueChannel, int i, int j);
}
