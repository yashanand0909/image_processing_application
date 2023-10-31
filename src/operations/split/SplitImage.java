package operations.split;

import java.util.ArrayList;
import java.util.List;

import image.ImageFactory;
import image.ImageInterface;
import operations.operationfactory.SingleToMutipleImageProcessor;

/**
 * This class represents a split image operation.
 */
public class SplitImage implements SingleToMutipleImageProcessor {

  /**
   * This method applies the split image operation on the given image.
   *
   * @param image the image to be split
   * @return the split image
   * @throws IllegalArgumentException if the image has 1 channel
   */
  @Override
  public List<ImageInterface> apply(ImageInterface image) throws IllegalArgumentException {

    List<int[][]> imageChannel = image.getChannel();
    if (imageChannel.size() == 1) {
      throw new IllegalArgumentException("Image must have more than 1 channel");
    }
    List<ImageInterface> newGreyscaleImage = new ArrayList<>();

    for (int[][] channel : imageChannel) {
      List<int[][]> channelList = new ArrayList<>();
      channelList.add(channel);
      newGreyscaleImage.add(ImageFactory.createImage(channelList));
    }
    return newGreyscaleImage;
  }
}
