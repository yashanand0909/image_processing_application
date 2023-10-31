package operations.operationinterfaces;

import java.util.List;

import image.ImageInterface;

/**
 * This interface represents a multiple to single image processor.
 */
public interface MultipleToSingleImageProcessor {
  /**
   * This method applies the operation on the given images and returns single image.
   *
   * @param images the images to be combined
   * @return new processed image
   * @throws IllegalArgumentException if the process not possible
   */
  public ImageInterface apply(List<ImageInterface> images) throws IllegalArgumentException;
}
