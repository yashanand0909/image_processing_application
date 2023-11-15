package commonlabels;

/**
 * This enum represents the input type.
 */
public enum ImageOperations {
  INTENSITY("intensity-component"),
  VALUE("value-component"),
  BLUR("blur"),
  LUMA("luma-component"),
  SHARPEN("sharpen"),
  GREYSCALE("greyscale"),
  SEPIA("sepia"),
  BRIGHTNESS("brighten"),
  VERTICAL_FLIP("vertical-flip"),
  HORIZONTAL_FLIP("horizontal-flip"),
  MERGE_SINGLE_CHANNEL_IMAGES("rgb-combine"),
  SPLIT_IMAGE("rgb-split"),
  SPLIT_IMAGE_BY_RED_CHANNEL("red-component"),
  SPLIT_IMAGE_BY_GREEN_CHANNEL("green-component"),
  SPLIT_IMAGE_BY_BLUE_CHANNEL("blue-component"),
  COMPRESSION("compression");

  private final String stringValue;

  /**
   * Constructs an ImageOperation object with the given string value.
   *
   * @param stringValue the string value of the image format
   */
  ImageOperations(String stringValue) {
    this.stringValue = stringValue;
  }

  /**
   * Returns the ImageOperation object corresponding to the given string value.
   *
   * @param text the string value of the image format
   * @return the ImageOperation object corresponding to the given string value
   */
  public static ImageOperations fromString(String text) {
    for (ImageOperations b : ImageOperations.values()) {
      if (b.stringValue.equalsIgnoreCase(text)) {
        return b;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return stringValue;
  }
}
