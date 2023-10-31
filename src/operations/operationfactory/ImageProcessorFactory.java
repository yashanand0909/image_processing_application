package operations.operationfactory;

import java.util.List;

import commonlabels.ImageOperations;
import image.ImageInterface;
import operations.colorrepresentation.Intensity;
import operations.colorrepresentation.Value;
import operations.colortransformation.Greyscale;
import operations.colortransformation.Sepia;
import operations.filters.BlurFilter;
import operations.filters.SharpenFilter;
import operations.merge.MergeSingleChannelImages;
import operations.pixeloffset.BrightnessOperation;
import operations.rotation.HorizontalFlipOperation;
import operations.rotation.VerticalFlipOperation;
import operations.split.SplitImage;

/**
 * This class represents a factory for image operations.
 */
public class ImageProcessorFactory {
  /**
   * This method performs the given operation on the given image.
   *
   * @param images    the images to be processed
   * @param operation the operation to be performed
   * @param operator  the operator to be applied
   * @return new processed image
   * @throws IllegalArgumentException if the process not possible
   */
  public static ImageInterface performOperation(List<ImageInterface> images,
                                                ImageOperations operation,
                                                Object operator) throws IllegalArgumentException {
    if (!images.isEmpty()) {
      throw new IllegalArgumentException("Images cannot be empty");
    }
    if (ImageOperations.Blur.equals(operation)) {
      return new BlurFilter().apply(images.get(0));
    } else if (ImageOperations.Sharpen.equals(operation)) {
      return new SharpenFilter().apply(images.get(0));
    } else if (ImageOperations.HorizontalFlip.equals(operation)) {
      return new HorizontalFlipOperation().apply(images.get(0));
    } else if (ImageOperations.VerticalFlip.equals(operation)) {
      return new VerticalFlipOperation().apply(images.get(0));
    } else if (ImageOperations.Greyscale.equals(operation)) {
      return new Greyscale().apply(images.get(0));
    } else if (ImageOperations.Sepia.equals(operation)) {
      return new Sepia().apply(images.get(0));
    } else if (ImageOperations.Luma.equals(operation)) {
      return new Greyscale().apply(images.get(0));
    } else if (ImageOperations.SplitImage.equals(operation)) {
      return new SplitImage().apply(images.get(0), operator);
    } else if (ImageOperations.Brightness.equals(operation)) {
      return new BrightnessOperation().apply(images.get(0), operator);
    } else if (ImageOperations.Intensity.equals(operation)) {
      return new Intensity().apply(images.get(0));
    } else if (ImageOperations.Value.equals(operation)) {
      return new Value().apply(images.get(0));
    } else if (ImageOperations.MergeSingleChannelImages.equals(operation)) {
      return new MergeSingleChannelImages().apply(images);
    } else {
      throw new IllegalArgumentException("Invalid operation");
    }
  }
}
