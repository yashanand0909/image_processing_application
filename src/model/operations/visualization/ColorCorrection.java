package model.operations.visualization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.image.ImageFactory;
import model.image.ImageInterface;
import model.operations.merge.MergeSingleChannelImages;
import model.operations.operationinterfaces.SingleImageProcessor;
import model.operations.pixeloffset.BrightnessOperation;

/**
 * This class represents a color correction operation on an image.
 */
public class ColorCorrection implements SingleImageProcessor {
  /**
   * This method applies a color correction operation on the image.
   *
   * @param image the image to be operated on
   * @return new image with color correction
   */
  @Override
  public ImageInterface apply(ImageInterface image) {
    List<int[]> frequencyChannels = VisualizeImageUtil.calculateFrequencyPerChannel(image);
    List<Integer> peaks = new ArrayList<>();
    for (int x = 0; x < image.getChannel().size(); x++) {
      peaks.add(findPeak(frequencyChannels.get(x)));
    }
    int avg = (int) peaks.stream().mapToInt(Integer::intValue).average().getAsDouble();
    return colorCorrect(image, peaks, avg);
  }

  private ImageInterface colorCorrect(ImageInterface image, List<Integer> peaks, int avg) {
    List<ImageInterface> greyscaleImages = new ArrayList<>();
    for (int i = 0; i < image.getChannel().size(); i++) {
      int colorCorrection = avg - peaks.get(i);
      greyscaleImages.add(new BrightnessOperation()
              .apply(ImageFactory
                              .createImage(Collections.singletonList(image.getChannel().get(i)))
                      , colorCorrection));
    }
    return new MergeSingleChannelImages().apply(greyscaleImages);
  }

  private int findPeak(int[] normalizedFrequencyChannel) {
    int peak = 0;
    int peakIndex = Integer.MIN_VALUE;
    for (int i = 10; i < 245; i++) {
      if (normalizedFrequencyChannel[i] > peak) {
        peak = normalizedFrequencyChannel[i];
        peakIndex = i;
      }
    }
    return peakIndex;
  }
}
