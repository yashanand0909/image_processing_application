package model.operations.split;

import static model.operations.operatorutil.OperatorUtil.castOperatorToDouble;

import java.util.List;
import model.image.ImageInterface;
import model.operations.operationinterfaces.MultipleToSingleImageProcessorWithOffset;

public class PartialImageOperation implements MultipleToSingleImageProcessorWithOffset {

  @Override
  public ImageInterface apply(List<ImageInterface> images, Object operator)
      throws IllegalArgumentException {
    int percentage = castOperatorToDouble((String) operator);
    ImageInterface orignalImage = images.get(0);
    ImageInterface newImage = images.get(1);
    int height = newImage.getHeight();
    int width = newImage.getWidth();
    int perWidth = width * percentage/100;
    for (int w=0;w<newImage.getChannel().size();w++){
      int[][] origChannel= orignalImage.getChannel().get(w);
      int[][] newChannel= newImage.getChannel().get(w);
      for (int i=0;i<height;i++){
        if (width - perWidth >= 0)
          System.arraycopy(origChannel[i], perWidth, newChannel[i], perWidth, width - perWidth);
      }
    }
    return newImage;
  }
}
