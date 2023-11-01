package commonlabels;

public enum ImageOperations {
  Intensity("intensity-component"),
  Value("value-component"),
  Blur("blur"),
  Luma("luma-component"),
  Sharpen("sharpen"),
  Greyscale("greyscale"),
  Sepia("sepia"),
  Brightness("brighten"),
  VerticalFlip("vertical-flip"),
  HorizontalFlip("horizontal-flip"),
  MergeSingleChannelImages("rgb-combine"),
  SplitImage("rgb-split"),
  SplitImageByRedChannel("red-component"),
  SplitImageByGreenChannel("green-component"),
  SplitImageByBlueChannel("blue-component");

  private final String stringValue;

  /**
   * Constructs an ImageOperation object with the given string value.
   *
   * @param stringValue the string value of the image format
   */
  ImageOperations(String stringValue) {
    this.stringValue = stringValue;
  }

  @Override
  public String toString() {
    return stringValue;
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
}
