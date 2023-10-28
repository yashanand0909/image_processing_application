package image;

import java.util.List;

/**
 * This class represents an image factory.
 */
public class ImageFactory {
  /**
   * Creates an image with the given height, width and channels.
   *
   * @param height      the height of the image
   * @param width       the width of the image
   * @param channelList the channels of the image
   * @return the image
   * @throws IllegalArgumentException if the number of channels is not 1 or 3
   */
  public static ImageInterface createImage(int height, int width, List<int[][]> channelList) {
    if (channelList.size() == 1) {
      return new GreyscaleImage(height, width, channelList);
    } else if (channelList.size() == 3) {
      return new RgbImage(height, width, channelList);
    } else {
      throw new IllegalArgumentException(channelList.size() + " number of channel is not supported");
    }
  }

}
