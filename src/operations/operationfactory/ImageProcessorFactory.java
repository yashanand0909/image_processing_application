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

    return switch (operation) {
      case Blur -> new BlurFilter().apply(images.get(0));
      case Sharpen -> new SharpenFilter().apply(images.get(0));
      case HorizontalFlip -> new HorizontalFlipOperation().apply(images.get(0));
      case VerticalFlip -> new VerticalFlipOperation().apply(images.get(0));
      case Greyscale, Luma -> new Greyscale().apply(images.get(0));
      case Sepia -> new Sepia().apply(images.get(0));
      case SplitImage -> new SplitImage().apply(images.get(0), operator);
      case Brightness -> new BrightnessOperation().apply(images.get(0), operator);
      case Intensity -> new Intensity().apply(images.get(0));
      case Value -> new Value().apply(images.get(0));
      case MergeSingleChannelImages -> new MergeSingleChannelImages().apply(images);
      default -> throw new IllegalArgumentException("Invalid operation");
    };
  }
}
