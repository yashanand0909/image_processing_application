package model.image;

import java.util.List;

/**
 * This class represents an image factory.
 */
public class ImageFactory {
  /**
   * Creates an image with the given height, width and channels.
   *
   * @param channelList the channels of the image
   * @return the image
   * @throws IllegalArgumentException if the number of channels is not 1 or 3
   */
  public static ImageInterface createImage( List<int[][]> channelList) {
    if (channelList.size() == 1) {
      return new GreyscaleImage(channelList.get(0));
    } else if (channelList.size() == 3) {
      return new RgbImage(channelList.get(0), channelList.get(1), channelList.get(2));
    } else {
      throw new IllegalArgumentException(channelList.size() + " number of channel is not supported");
    }
  }

}
