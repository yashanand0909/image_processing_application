package model.image;

import commonlabels.ImageOperations;
import model.ImageProcessingModel.ImageProcessorModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the operations on an image.
 */
public class OperationsTest {

  ImageProcessorModel imageProcessorModel;

  @Before
  public void setup() {
    imageProcessorModel = new ImageProcessorModel();
  }

  @Test
  public void testIntensity() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    int[][] blueChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};

    int[][] newChannelAfterIntensity = {{170, 170, 170}, {170, 170, 170}, {170, 170, 170}};
    ImageInterface imageAfterIntensity = ImageFactory.createImage(
            Collections.singletonList(newChannelAfterIntensity));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.INTENSITY, null);
    assertEqualImages(imageAfterIntensity, newImage);

  }

  @Test
  public void testValue() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] blueChannel = {{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};

    int[][] newChannelAfterValue = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            Collections.singletonList(newChannelAfterValue));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.VALUE, null);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test
  public void testGreyscale() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] blueChannel = {{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};

    int[][] newChannelAfterGreyscale = {{72, 72, 71}, {71, 54, 60}, {60, 134, 57}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterGreyscale, newChannelAfterGreyscale, newChannelAfterGreyscale));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.GREYSCALE, null);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGreyscale() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel));
    imageProcessorModel.performOperation(List.of(image),
            ImageOperations.GREYSCALE, null);
  }

  @Test
  public void testLuma() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] blueChannel = {{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};

    int[][] newChannelAfterGreyscale = {{72, 72, 71}, {71, 54, 60}, {60, 134, 57}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterGreyscale, newChannelAfterGreyscale, newChannelAfterGreyscale));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.LUMA, null);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLuma() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel));
    imageProcessorModel.performOperation(List.of(image),
            ImageOperations.LUMA, null);
  }

  @Test
  public void testBlurFilter() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] blueChannel = {{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};

    int[][] newChannelAfterBlurRed = {{140, 186, 140}, {186, 247, 186}, {140, 186, 140}};
    int[][] newChannelAfterBlurGreen = {{0, 0, 0}, {5, 10, 5}, {10, 21, 10}};
    int[][] newChannelAfterBlurBlue = {{124, 144, 102}, {131, 140, 87}, {81, 98, 53}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterBlurRed, newChannelAfterBlurGreen, newChannelAfterBlurBlue));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.BLUR, null);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test
  public void testSharpenFilter() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] blueChannel = {{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};

    int[][] newChannelAfterSharpenRed = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] newChannelAfterSharpenGreen = {{0, 0, 0}, {21, 21, 21}, {21, 86, 21}};
    int[][] newChannelAfterSharpenBlue = {{255, 255, 215}, {255, 255, 215}, {97, 255, 0}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterSharpenRed, newChannelAfterSharpenGreen,
                    newChannelAfterSharpenBlue));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.SHARPEN, null);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test
  public void testIncreaseBrightness() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] blueChannel = {{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};
    int[][] newChannelAfterIncreaseBrightnessRed = {{255, 255, 255}, {255, 255, 255},
            {255, 255, 255}};
    int[][] newChannelAfterIncreaseBrightnessGreen = {{10, 10, 10}, {10, 10, 10}, {10, 96, 10}};
    int[][] newChannelAfterIncreaseBrightnessBlue = {{255, 255, 250}, {250, 10, 100},
            {93, 255, 54}};
    ImageInterface imageAfterValue = ImageFactory
            .createImage(List.of(newChannelAfterIncreaseBrightnessRed,
                    newChannelAfterIncreaseBrightnessGreen, newChannelAfterIncreaseBrightnessBlue));
    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));
    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.BRIGHTNESS, "10");
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test
  public void testDecreaseBrightness() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] blueChannel = {{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};
    int[][] newChannelAfterIncreaseBrightnessRed = {{245, 245, 245}, {245, 245, 245},
            {245, 245, 245}};
    int[][] newChannelAfterIncreaseBrightnessGreen = {{0, 0, 0}, {0, 0, 0}, {0, 76, 0}};
    int[][] newChannelAfterIncreaseBrightnessBlue = {{244, 244, 230}, {230, 0, 80},
            {73, 245, 34}};
    ImageInterface imageAfterValue = ImageFactory
            .createImage(List.of(newChannelAfterIncreaseBrightnessRed,
                    newChannelAfterIncreaseBrightnessGreen, newChannelAfterIncreaseBrightnessBlue));
    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));
    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.BRIGHTNESS, "-10");
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test
  public void testSepia() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] blueChannel = {{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};

    int[][] newChannelAfterSepiaRed = {{148, 148, 145}, {145, 100, 117}, {115, 214, 108}};
    int[][] newChannelAfterSepiaGreen = {{131, 131, 129}, {129, 88, 104}, {102, 190, 96}};
    int[][] newChannelAfterSepiaBlue = {{102, 102, 100}, {100, 69, 81}, {80, 148, 75}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterSepiaRed, newChannelAfterSepiaGreen, newChannelAfterSepiaBlue));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.SEPIA, null);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test
  public void testSepiaClamp() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] blueChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};

    int[][] newChannelAfterSepiaRed = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] newChannelAfterSepiaGreen = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] newChannelAfterSepiaBlue = {{238, 238, 238}, {238, 238, 238}, {238, 238, 238}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterSepiaRed, newChannelAfterSepiaGreen, newChannelAfterSepiaBlue));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.SEPIA, null);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test
  public void testVerticalRotation() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] blueChannel = {{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};

    int[][] newChannelAfterVerticalRotationRed = {{255, 255, 255}, {255, 255, 255},
            {255, 255, 255}};
    int[][] newChannelAfterVerticalRotationGreen = {{0, 86, 0}, {0, 0, 0}, {0, 0, 0}};
    int[][] newChannelAfterVerticalRotationBlue = {{83, 255, 44}, {240, 0, 90}, {254, 254, 240}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterVerticalRotationRed, newChannelAfterVerticalRotationGreen,
                    newChannelAfterVerticalRotationBlue));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.VERTICAL_FLIP, null);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test
  public void testHorizontalRotation() {
    int[][] redChannel = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] blueChannel = {{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};

    int[][] newChannelAfterHorizontalRotationRed = {{255, 255, 255}, {255, 255, 255},
            {255, 255, 255}};
    int[][] newChannelAfterHorizontalRotationGreen = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] newChannelAfterHorizontalRotationBlue = {{240, 254, 254}, {90, 0, 240},
            {44, 255, 83}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterHorizontalRotationRed, newChannelAfterHorizontalRotationGreen,
                    newChannelAfterHorizontalRotationBlue));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.HORIZONTAL_FLIP, null);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test
  public void testSplitOperationRed() {
    int[][] redChannel = {{255, 0}, {0, 255}};
    int[][] greenChannel = {{0, 255}, {255, 0}};
    int[][] blueChannel = {{0, 0}, {255, 255}};

    int[][] newChannelAfterSplitRed = {{255, 0}, {0, 255}};
    int[][] newChannelAfterSplitGreen = {{0, 0}, {0, 0}};
    int[][] newChannelAfterSplitBlue = {{0, 0}, {0, 0}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterSplitRed, newChannelAfterSplitGreen, newChannelAfterSplitBlue));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.SPLIT_IMAGE, 0);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSplit() {
    int[][] redChannel = {{255, 0}, {0, 255}};

    ImageInterface image = ImageFactory.createImage(Collections.singletonList(redChannel));
    imageProcessorModel.performOperation(List.of(image),
            ImageOperations.SPLIT_IMAGE, 0);
  }

  @Test
  public void testSplitOperationGreen() {
    int[][] redChannel = {{255, 0}, {0, 255}};
    int[][] greenChannel = {{0, 255}, {255, 0}};
    int[][] blueChannel = {{0, 0}, {255, 255}};

    int[][] newChannelAfterSplitRed = {{0, 0}, {0, 0}};
    int[][] newChannelAfterSplitGreen = {{0, 255}, {255, 0}};
    int[][] newChannelAfterSplitBlue = {{0, 0}, {0, 0}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterSplitRed, newChannelAfterSplitGreen, newChannelAfterSplitBlue));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.SPLIT_IMAGE, 1);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test
  public void testSplitOperationBlue() {
    int[][] redChannel = {{255, 0}, {0, 255}};
    int[][] greenChannel = {{0, 255}, {255, 0}};
    int[][] blueChannel = {{0, 0}, {255, 255}};

    int[][] newChannelAfterSplitRed = {{0, 0}, {0, 0}};
    int[][] newChannelAfterSplitGreen = {{0, 0}, {0, 0}};
    int[][] newChannelAfterSplitBlue = {{0, 0}, {255, 255}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterSplitRed, newChannelAfterSplitGreen, newChannelAfterSplitBlue));

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
            ImageOperations.SPLIT_IMAGE, 2);
    assertEqualImages(imageAfterValue, newImage);
  }

  @Test
  public void testSplitOperationAll() {
    int[][] redChannel = {{255, 0}, {0, 255}};
    int[][] greenChannel = {{0, 255}, {255, 0}};
    int[][] blueChannel = {{0, 0}, {255, 255}};
    List<ImageInterface> newChannels = new ArrayList<>();

    int[][] newChannelAfterSplitRedForRed = {{255, 0}, {0, 255}};
    int[][] newChannelAfterSplitGreenForRed = {{0, 0}, {0, 0}};
    int[][] newChannelAfterSplitBlueForRed = {{0, 0}, {0, 0}};
    ImageInterface imageAfterValue1 = ImageFactory.createImage(
            List.of(newChannelAfterSplitRedForRed, newChannelAfterSplitGreenForRed,
                    newChannelAfterSplitBlueForRed));
    newChannels.add(imageAfterValue1);

    int[][] newChannelAfterSplitRedForGreen = {{0, 0}, {0, 0}};
    int[][] newChannelAfterSplitGreenForGreen = {{0, 255}, {255, 0}};
    int[][] newChannelAfterSplitBlueForGreen = {{0, 0}, {0, 0}};
    ImageInterface imageAfterValue2 = ImageFactory.createImage(
            List.of(newChannelAfterSplitRedForGreen, newChannelAfterSplitGreenForGreen,
                    newChannelAfterSplitBlueForGreen));
    newChannels.add(imageAfterValue2);

    int[][] newChannelAfterSplitRedForBlue = {{0, 0}, {0, 0}};
    int[][] newChannelAfterSplitGreenForBlue = {{0, 0}, {0, 0}};
    int[][] newChannelAfterSplitBlueForBlue = {{0, 0}, {255, 255}};
    ImageInterface imageAfterValue3 = ImageFactory.createImage(
            List.of(newChannelAfterSplitRedForBlue, newChannelAfterSplitGreenForBlue,
                    newChannelAfterSplitBlueForBlue));
    newChannels.add(imageAfterValue3);

    ImageInterface image = ImageFactory.createImage(List.of(redChannel, greenChannel, blueChannel));

    for (int i = 0; i < 3; i++) {
      ImageInterface newImage = imageProcessorModel.performOperation(List.of(image),
              ImageOperations.SPLIT_IMAGE, i);
      assertEqualImages(newChannels.get(i), newImage);
    }
  }

  @Test
  public void testMergeOperation() {
    int[][] redChannel1 = {{255, 0}, {0, 255}};
    int[][] greenChannel1 = {{255, 0}, {0, 255}};
    int[][] blueChannel1 = {{255, 0}, {0, 255}};
    ImageInterface imageAfterValue1 = ImageFactory.createImage(
            List.of(redChannel1, greenChannel1,
                    blueChannel1));

    int[][] redChannel2 = {{0, 255}, {255, 0}};
    int[][] greenChannel2 = {{0, 255}, {255, 0}};
    int[][] blueChannel2 = {{0, 255}, {255, 0}};
    ImageInterface imageAfterValue2 = ImageFactory.createImage(
            List.of(redChannel2, greenChannel2,
                    blueChannel2));

    int[][] redChannel3 = {{0, 0}, {255, 255}};
    int[][] greenChannel3 = {{0, 0}, {255, 255}};
    int[][] blueChannel3 = {{0, 0}, {255, 255}};
    ImageInterface imageAfterValue3 = ImageFactory.createImage(
            List.of(redChannel3, greenChannel3,
                    blueChannel3));

    int[][] newChannelAfterValueRed = {{255, 0}, {0, 255}};
    int[][] newChannelAfterValueGreen = {{0, 255}, {255, 0}};
    int[][] newChannelAfterValueBlue = {{0, 0}, {255, 255}};
    ImageInterface imageAfterValue = ImageFactory.createImage(
            List.of(newChannelAfterValueRed, newChannelAfterValueGreen,
                    newChannelAfterValueBlue));

    ImageInterface newImage = imageProcessorModel.performOperation(List.of(imageAfterValue1,
                    imageAfterValue2, imageAfterValue3),
            ImageOperations.MERGE_SINGLE_CHANNEL_IMAGES, null);
    assertEqualImages(imageAfterValue, newImage);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMergeWithMoreChannels() {
    int[][] redChannel1 = {{255, 0}, {0, 255}};
    int[][] greenChannel1 = {{255, 0}, {0, 255}};
    int[][] blueChannel1 = {{255, 0}, {0, 255}};
    ImageInterface imageAfterValue1 = ImageFactory.createImage(
            List.of(redChannel1, greenChannel1,
                    blueChannel1));

    int[][] redChannel2 = {{0, 255}, {255, 0}};
    int[][] greenChannel2 = {{0, 255}, {255, 0}};
    int[][] blueChannel2 = {{0, 255}, {255, 0}};
    ImageInterface imageAfterValue2 = ImageFactory.createImage(
            List.of(redChannel2, greenChannel2,
                    blueChannel2));

    imageProcessorModel.performOperation(List.of(imageAfterValue1,
                    imageAfterValue2),
            ImageOperations.MERGE_SINGLE_CHANNEL_IMAGES, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnequalHeightForMerge() {
    int[][] redChannel1 = {{255, 0}, {0, 255}, {0, 255}};
    int[][] greenChannel1 = {{255, 0}, {0, 255}, {0, 255}};
    int[][] blueChannel1 = {{255, 0}, {0, 255}, {0, 255}};
    ImageInterface imageAfterValue1 = ImageFactory.createImage(
            List.of(redChannel1, greenChannel1,
                    blueChannel1));

    int[][] redChannel2 = {{0, 255}, {255, 0}};
    int[][] greenChannel2 = {{0, 255}, {255, 0}};
    int[][] blueChannel2 = {{0, 255}, {255, 0}};
    ImageInterface imageAfterValue2 = ImageFactory.createImage(
            List.of(redChannel2, greenChannel2,
                    blueChannel2));

    imageProcessorModel.performOperation(List.of(imageAfterValue1,
                    imageAfterValue2),
            ImageOperations.MERGE_SINGLE_CHANNEL_IMAGES, null);
  }

  private void assertEqualImages(ImageInterface imageAfterIntensity, ImageInterface newImage) {
    assertEquals(imageAfterIntensity.getHeight(), newImage.getHeight());
    assertEquals(imageAfterIntensity.getWidth(), newImage.getWidth());
    assertEquals(imageAfterIntensity.getChannel().size(), newImage.getChannel().size());
    for (int i = 0; i < imageAfterIntensity.getChannel().size(); i++) {
      for (int j = 0; j < imageAfterIntensity.getChannel().get(i).length; j++) {
        for (int k = 0; k < imageAfterIntensity.getChannel().get(i)[j].length; k++) {
          assertEquals(imageAfterIntensity.getChannel().get(i)[j][k],
                  newImage.getChannel().get(i)[j][k]);
        }
      }
    }
  }
}
