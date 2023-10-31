package operations.operationfactory;

import image.ImageInterface;

/**
 * This interface represents a single image processor.
 */
public interface SingleImageProcessor {
  /**
   * This method applies the operation on the given image and returns the processed image.
   *
   * @param image the image to be processed
   * @return new processed image
   * @throws IllegalArgumentException if the process not possible
   */
  public ImageInterface apply(ImageInterface image) throws IllegalArgumentException;
}
