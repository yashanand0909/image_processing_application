package operations;

import image.ImageFactory;
import image.ImageInterface;
import image.RgbImage;
import java.util.ArrayList;
import java.util.List;

public class BrightnessOperation implements Operation {
  int maxValue = 255;

  @Override
  public ImageInterface apply(ImageInterface image, Object operator) throws IllegalArgumentException{
    int height = image.getHeight();
    int width = image.getWidth();
    List<int[][]> imageChannel = image.getChannel();
    List<int[][]> newChannel = new ArrayList<>();
    Integer factor = (Integer) operator;
    for(int[][] channel: imageChannel){
      int[][] newPixels = new int[height][width];
      for (int i=0;i<height;i++){
        for(int j=0;j<width;j++){
          newPixels[i][j] = Math.min(channel[i][j] + factor,maxValue);
        }
      }
      newChannel.add(newPixels);
    }
    return ImageFactory.createImage(height, width, newChannel);
  }
}
