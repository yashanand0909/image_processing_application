package operations.operationfactory;

import java.util.List;

import image.ImageInterface;

/**
 * This interface represents a single to multiple image processor.
 */
public interface SingleToMutipleImageProcessor {
  /**
   * This method applies the operation on the given image and returns multiple images.
   *
   * @param image the image to be processed
   * @return new processed images
   * @throws IllegalArgumentException if the process not possible
   */
  public List<ImageInterface> apply(ImageInterface image) throws IllegalArgumentException;
}
