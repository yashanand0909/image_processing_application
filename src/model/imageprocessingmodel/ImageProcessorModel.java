package model.imageprocessingmodel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.image.ImageInterface;
import model.imageio.IOFileFactory;
import model.operations.colorrepresentation.Intensity;
import model.operations.colorrepresentation.Value;
import model.operations.colortransformation.Greyscale;
import model.operations.colortransformation.Sepia;
import model.operations.filters.BlurFilter;
import model.operations.filters.SharpenFilter;
import model.operations.merge.MergeSingleChannelImages;
import model.operations.pixeloffset.BrightnessOperation;
import model.operations.pixeloffset.CompressionOperation;
import model.operations.rotation.HorizontalFlipOperation;
import model.operations.rotation.VerticalFlipOperation;
import model.operations.split.SplitImageOperation;
import model.operations.visualization.ColorCorrection;
import model.operations.visualization.HistogramVisualization;
import model.operations.visualization.LevelAdjustment;

/**
 * This class represents a factory for image operations.
 */
public class ImageProcessorModel implements
    ImageProcessorModelInterface {

  private final Map<String, ImageInterface> images;

  public ImageProcessorModel() {
    this.images = new HashMap<>();
  }


  @Override
  public void blurImage(String imageName, String destImageName, Object operator) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new BlurFilter().apply(images.get(imageName), operator);
    images.put(destImageName, newImage);
  }

  @Override
  public void sharpenImage(String imageName, String destImageName, Object operator) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new SharpenFilter().apply(images.get(imageName), operator);
    images.put(destImageName, newImage);
  }

  @Override
  public void horizontalFlipImage(String imageName, String destImageName) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new HorizontalFlipOperation().apply(images.get(imageName));
    images.put(destImageName, newImage);
  }

  @Override
  public void verticalFlipImage(String imageName, String destImageName) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new VerticalFlipOperation().apply(images.get(imageName));
    images.put(destImageName, newImage);
  }

  @Override
  public void greyScaleImage(String imageName, String destImageName, Object operator) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new BlurFilter().apply(images.get(imageName), operator);
    images.put(destImageName, newImage);
  }

  @Override
  public void lumaImage(String imageName, String destImageName, Object operator) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new Greyscale().apply(images.get(imageName), operator);
    images.put(destImageName, newImage);
  }

  @Override
  public void sepiaImage(String imageName, String destImageName, Object operator) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new Sepia().apply(images.get(imageName), operator);
    images.put(destImageName, newImage);
  }

  @Override
  public void splitImage(String imageName, String destImageName, Object operator) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new SplitImageOperation().apply(images.get(imageName), operator);
    images.put(destImageName, newImage);
  }

  @Override
  public void rgbSplitImage(String imageName, List<String> destImageNames) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(destImageNames);
    for (int i = 0; i < destImageNames.size(); i++) {
      ImageInterface newImage = new SplitImageOperation().apply(images.get(imageName), i);
      images.put(destImageNames.get(i), newImage);
    }
  }

  @Override
  public void brightenImage(String imageName, String destImageName, Object operator) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new BrightnessOperation().apply(images.get(imageName), operator);
    images.put(destImageName, newImage);
  }

  @Override
  public void CompressImage(String imageName, String destImageName, Object operator) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new CompressionOperation().apply(images.get(imageName), operator);
    images.put(destImageName, newImage);
  }

  @Override
  public void valueImage(String imageName, String destImageName) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new Value().apply(images.get(imageName));
    images.put(destImageName, newImage);
  }

  @Override
  public void intensityImage(String imageName, String destImageName) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new Intensity().apply(images.get(imageName));
    images.put(destImageName, newImage);
  }

  @Override
  public void mergeImage(List<String> imagesToMergeName, String destImageName) {
    checkImageNames(imagesToMergeName);
    checkDestinationImageNames(Collections.singletonList(destImageName));
    List<ImageInterface> imageToMerge = new ArrayList<>();
    imagesToMergeName.forEach(a -> imageToMerge.add(images.get(a)));
    ImageInterface newImage = new MergeSingleChannelImages().apply(imageToMerge);
    images.put(destImageName, newImage);
  }

  @Override
  public void histogramImage(String imageName, String destImageName) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new HistogramVisualization().apply(images.get(imageName));
    images.put(destImageName, newImage);
  }

  @Override
  public void levelAdjustImage(String imageName, String destImageName, Object operator) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new LevelAdjustment().apply(images.get(imageName), operator);
    images.put(destImageName, newImage);
  }

  @Override
  public void colorCorrectImage(String imageName, String destImageName, Object operator) {
    checkImageNames(Collections.singletonList(imageName));
    checkDestinationImageNames(Collections.singletonList(destImageName));
    ImageInterface newImage = new ColorCorrection().apply(images.get(imageName), operator);
    images.put(destImageName, newImage);
  }

  @Override
  public void loadImage(String imagePath, String destImageName) throws IOException {
    checkDestinationImageNames(Collections.singletonList(destImageName));
    images.put(destImageName, IOFileFactory.decodeImage(imagePath));
  }

  @Override
  public void saveImage(String imagePath, String imageName) throws IOException {
    checkImageNames(Collections.singletonList(imageName));
    IOFileFactory.encodeAndSaveImage(imagePath, images.get(imageName));
  }

  @Override
  public ImageInterface getImage(String imageName) {
    return this.images.get(imageName);
  }

  private void checkImageNames(List<String> imageNames) {
    for (String imageName : imageNames) {
      if (!images.containsKey(imageName)) {
        throw new IllegalArgumentException(
            "Invalid request : No image exist with the name " + imageName);
      }
    }
  }

  private void checkDestinationImageNames(List<String> imageNames) {
    for (String imageName : imageNames) {
      if (images.containsKey(imageName)) {
        throw new IllegalArgumentException(
            "Invalid request : An Image exist with the name " + imageName);
      }
    }
  }

}
