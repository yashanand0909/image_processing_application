package model.imageprocessingmodel;

import commonlabels.ImageOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.image.ImageInterface;
import model.imageio.IOFileFactory;
import model.operations.colorrepresentation.Intensity;
import model.operations.colorrepresentation.Value;
import model.operations.colortransformation.Greyscale;
import model.operations.colortransformation.Sepia;
import model.operations.filters.BlurFilter;
import model.operations.filters.SharpenFilter;
import model.operations.merge.MergeSingleChannelImages;
import model.operations.pixeloffset.BrightnessOperation;
import model.operations.pixeloffset.CompressionOperation;
import model.operations.rotation.HorizontalFlipOperation;
import model.operations.rotation.VerticalFlipOperation;
import model.operations.split.SplitImageOperation;

/**
 * This class represents a factory for image operations.
 */
public class ImageProcessorModel implements
    model.imageprocessingmodel.ImageProcessorModelInterface {

  private final Map<String, ImageInterface> images;

  public ImageProcessorModel() {
    this.images = new HashMap<>();
  }

  /**
   * This method performs the given operation on the given image.
   *
   * @param images    the images to be processed
   * @param operation the operation to be performed
   * @param operator  the operator to be applied
   * @return new processed image
   * @throws IllegalArgumentException if the process not possible
   */
  private ImageInterface performOperation(List<ImageInterface> images,
                                         ImageOperations operation,
                                         Object operator) throws IllegalArgumentException {
    if (images.isEmpty()) {
      throw new IllegalArgumentException("Images cannot be empty");
    }

    switch (operation) {
      case BLUR:
        return new BlurFilter().apply(images.get(0));
      case SHARPEN:
        return new SharpenFilter().apply(images.get(0));
      case HORIZONTAL_FLIP:
        return new HorizontalFlipOperation().apply(images.get(0));
      case VERTICAL_FLIP:
        return new VerticalFlipOperation().apply(images.get(0));
      case GREYSCALE:
      case LUMA:
        return new Greyscale().apply(images.get(0));
      case SEPIA:
        return new Sepia().apply(images.get(0));
      case SPLIT_IMAGE:
      case SPLIT_IMAGE_BY_RED_CHANNEL:
      case SPLIT_IMAGE_BY_BLUE_CHANNEL:
      case SPLIT_IMAGE_BY_GREEN_CHANNEL:
        return new SplitImageOperation().apply(images.get(0), operator);
      case BRIGHTNESS:
        return new BrightnessOperation().apply(images.get(0), operator);
      case COMPRESSION:
        return new CompressionOperation().apply(images.get(0),operator);
      case INTENSITY:
        return new Intensity().apply(images.get(0));
      case VALUE:
        return new Value().apply(images.get(0));
      case MERGE_SINGLE_CHANNEL_IMAGES:
        return new MergeSingleChannelImages().apply(images);
      default:
        throw new IllegalArgumentException("Invalid operation");
    }
  }

  /**
   * This method processes the commands.
   *
   * @param parts the parts of the command
   * @throws IOException if the input is invalid
   */
  @Override
  public void processCommands(String[] parts) throws IOException {
    if (parts.length == 0) {
      throw new IllegalArgumentException("Invalid command. Try again.");
    }
    String command = parts[0];
    switch (command) {
      case "load":
        if (parts.length != 3) {
          throw new IllegalArgumentException(
                  "Invalid load command. Usage: load <image-path> <image-name>");
        } else {
          images.put(parts[2], IOFileFactory.decodeImage(parts[1]));
        }
        break;
      case "save":
        if (parts.length != 3) {
          throw new IllegalArgumentException(
                  "Invalid save command. Usage: save <image-path> <image-name>");
        } else {
          if (images.containsKey(parts[2])) {
            IOFileFactory.encodeAndSaveImage(parts[1], images.get(parts[2]));
          }
        }
        break;
      case "compression":
      case "brighten":
        if (parts.length != 4) {
          throw new IllegalArgumentException(
                  "Invalid save command. Usage: brighten <brightness-factor> " +
                          "<current-image-name> <new-image-name>");
        } else {
          if (!images.containsKey(parts[2])) {
            throw new IllegalArgumentException(
                    "Invalid request : No image exist with the name " + parts[2]);
          }
          if (images.containsKey(parts[3])) {
            throw new IllegalArgumentException(
                    "Invalid request : An Image exist with the name " + parts[3]);
          }
          // Add call to module factory
          List<ImageInterface> imageList = Collections.singletonList(images.get(parts[2]));
          ImageInterface newImage = performOperation(imageList,
                  ImageOperations.fromString(parts[0]), parts[1]);
          images.put(parts[3], newImage);
        }
        break;
      case "red-component":
        if (parts.length != 3) {
          throw new IllegalArgumentException(
              "Invalid red-component command. Usage: command <image-name> <dest-image-name>");
        } else {
          if (!images.containsKey(parts[1])) {
            throw new IllegalArgumentException(
                    "Invalid request : No image exist with the name " + parts[1]);
          }
          if (images.containsKey(parts[2])) {
            throw new IllegalArgumentException(
                    "Invalid request : An Image exist with the name " + parts[2]);
          }
          // Add call to module factory
          List<ImageInterface> imageList = Collections.singletonList(images.get(parts[1]));
          ImageInterface newImage = performOperation(imageList,
                  ImageOperations.fromString(parts[0]), 0);
          images.put(parts[2], newImage);
        }
        break;
      case "green-component":
        if (parts.length != 3) {
          throw new IllegalArgumentException(
                  "Invalid green-component command. Usage: command <image-name> <dest-image-name>");
        } else {
          if (!images.containsKey(parts[1])) {
            throw new IllegalArgumentException(
                    "Invalid request : No image exist with the name " + parts[1]);
          }
          if (images.containsKey(parts[2])) {
            throw new IllegalArgumentException(
                    "Invalid request : An Image exist with the name " + parts[2]);
          }
          // Add call to module factory
          List<ImageInterface> imageList = Collections.singletonList(images.get(parts[1]));
          ImageInterface newImage = performOperation(imageList,
                  ImageOperations.fromString(parts[0]), 1);
          images.put(parts[2], newImage);
        }
        break;
      case "blue-component":
        if (parts.length != 3) {
          throw new IllegalArgumentException(
              "Invalid red-component command. Usage: command <image-name> <dest-image-name>");
        } else {
          if (!images.containsKey(parts[1])) {
            throw new IllegalArgumentException(
                    "Invalid request : No image exist with the name " + parts[1]);
          }
          if (images.containsKey(parts[2])) {
            throw new IllegalArgumentException(
                    "Invalid request : An Image exist with the name " + parts[2]);
          }
          // Add call to module factory
          List<ImageInterface> imageList = Collections.singletonList(images.get(parts[1]));
          ImageInterface newImage = performOperation(imageList,
                  ImageOperations.fromString(parts[0]), 2);
          images.put(parts[2], newImage);
        }
        break;
      case "value-component":
      case "luma-component":
      case "intensity-component":
      case "horizontal-flip":
      case "vertical-flip":
      case "blur":
      case "sharpen":
      case "greyscale":
      case "sepia":
        if (parts.length != 3) {
          throw new IllegalArgumentException(
              "Invalid component command. Usage: command <image-name> <dest-image-name>");
        } else {
          if (!images.containsKey(parts[1])) {
            throw new IllegalArgumentException(
                    "Invalid request : No image exist with the name " + parts[1]);
          }
          if (images.containsKey(parts[2])) {
            throw new IllegalArgumentException(
                    "Invalid request : An Image exist with the name " + parts[2]);
          }
          // Add call to module factory
          List<ImageInterface> imageList = Collections.singletonList(images.get(parts[1]));
          ImageInterface newImage = performOperation(imageList,
                  ImageOperations.fromString(parts[0]), null);
          images.put(parts[2], newImage);
        }
        break;
      case "rgb-split":
        if (parts.length != 5) {
          throw new IllegalArgumentException(
                  "Invalid rgb-split command. Usage: rgb-split <image-name> " +
                          "<dest-image-name-red> <dest-image-name-green> <dest-image-name-blue>");
        } else {
          if (!images.containsKey(parts[1])) {
            throw new IllegalArgumentException(
                    "Invalid request : No image exist with the name " + parts[1]);
          }
          if (images.containsKey(parts[2])) {
            throw new IllegalArgumentException(
                    "Invalid request : An Image exist with the name " + parts[2]);
          }
          if (images.containsKey(parts[3])) {
            throw new IllegalArgumentException(
                    "Invalid request : An Image exist with the name " + parts[3]);
          }
          if (images.containsKey(parts[4])) {
            throw new IllegalArgumentException(
                    "Invalid request : An Image exist with the name " + parts[4]);
          }
          if (parts[2].equals(parts[3]) || parts[3].equals(parts[4]) || parts[4]
                  .equals(parts[2])) {
            throw new IllegalArgumentException(
                    "Invalid request : Red Blue and Green image name cannot be same");
          }
          // Add call to module factory
          for (int i = 0; i < 3; i++) {
            List<ImageInterface> imageList = Collections.singletonList(images.get(parts[1]));
            ImageInterface newImage = performOperation(imageList,
                    ImageOperations.fromString(parts[0]), i);
            images.put(parts[i + 2], newImage);
          }
        }
        break;
      case "rgb-combine":
        if (parts.length != 5) {
          throw new IllegalArgumentException(
                  "Invalid rgb-combine command. Usage: rgb-combine <image-name> " +
                          "<red-image> <green-image> <blue-image>");
        } else {
          if (images.containsKey(parts[1])) {
            throw new IllegalArgumentException(
                    "Invalid request : An Image exist with the name " + parts[1]);
          }
          if (!images.containsKey(parts[2])) {
            throw new IllegalArgumentException(
                    "Invalid request : No image exist with the name " + parts[2]);
          }
          if (!images.containsKey(parts[3])) {
            throw new IllegalArgumentException(
                    "Invalid request : No image exist with the name " + parts[3]);
          }
          if (!images.containsKey(parts[4])) {
            throw new IllegalArgumentException(
                    "Invalid request : No image exist with the name " + parts[4]);
          }
          List<ImageInterface> imageList = new ArrayList<>();
          for (int i = 2; i < 5; i++) {
            imageList.add(images.get(parts[i]));
          }
          ImageInterface newImage = performOperation(imageList,
                  ImageOperations.fromString(parts[0]), null);
          images.put(parts[1], newImage);
        }
        break;

      default:
        throw new IllegalArgumentException(
                "Unknown command. Try again or type 'exit' to quit.");
    }
  }

  @Override
  public ImageInterface getImage(String imageName) {
    return this.images.get(imageName);
  }

}
