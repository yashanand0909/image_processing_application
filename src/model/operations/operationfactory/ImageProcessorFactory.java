package model.operations.operationfactory;

import java.util.List;

import commonlabels.ImageOperations;
import model.image.ImageInterface;
import model.operations.colorrepresentation.Value;
import model.operations.rotation.HorizontalFlipOperation;
import model.operations.colorrepresentation.Intensity;
import model.operations.colortransformation.Greyscale;
import model.operations.colortransformation.Sepia;
import model.operations.filters.BlurFilter;
import model.operations.filters.SharpenFilter;
import model.operations.merge.MergeSingleChannelImages;
import model.operations.pixeloffset.BrightnessOperation;
import model.operations.rotation.VerticalFlipOperation;
import model.operations.split.SplitImageOperation;

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
    if (images.isEmpty()) {
      throw new IllegalArgumentException("Images cannot be empty");
    }

    switch (operation) {
      case Blur:
        return new BlurFilter().apply(images.get(0));
      case Sharpen:
        return new SharpenFilter().apply(images.get(0));
      case HorizontalFlip:
        return new HorizontalFlipOperation().apply(images.get(0));
      case VerticalFlip:
        return new VerticalFlipOperation().apply(images.get(0));
      case Greyscale:
      case Luma:
        return new Greyscale().apply(images.get(0));
      case Sepia:
        return new Sepia().apply(images.get(0));
      case SplitImage:
        return new SplitImageOperation().apply(images.get(0), operator);
      case Brightness:
        return new BrightnessOperation().apply(images.get(0), operator);
      case Intensity:
        return new Intensity().apply(images.get(0));
      case Value:
        return new Value().apply(images.get(0));
      case MergeSingleChannelImages:
        return new MergeSingleChannelImages().apply(images);
      default:
        throw new IllegalArgumentException("Invalid operation");
    }
  }
}
