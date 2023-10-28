package image;

import java.util.List;

public class ImageFactory {

  public static ImageInterface createImage(int height, int width, List<int[][]> channelList){
    if (channelList.size() == 1){
      return new  GreyScaleImage(height, width, channelList);
    }
    else if (channelList.size() == 3){
      return new RgbImage(height, width, channelList);
    }
    else {
      throw new IllegalArgumentException(channelList.size() + " number of channel is not supported");
    }
  }

}
