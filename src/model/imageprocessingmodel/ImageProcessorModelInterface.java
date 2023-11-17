package model.imageprocessingmodel;

import java.io.IOException;
import java.util.List;
import model.image.ImageInterface;

/**
 * This interface represents an image processor model.
 */
public interface ImageProcessorModelInterface {

  void blurImage(String imageName, String destImageName, Object operator);

  void sharpenImage(String imageName, String destImageName, Object operator);

  void horizontalFlipImage(String imageName, String destImageName);

  void verticalFlipImage(String imageName, String destImageName);

  void greyScaleImage(String imageName, String destImageName, Object operator);

  void lumaImage(String imageName, String destImageName, Object operator);

  void sepiaImage(String imageName, String destImageName, Object operator);

  void splitImage(String imageName, String destImageName, Object operator);

  void rgbSplitImage(String imageName, List<String> destImageNames);

  void brightenImage(String imageName, String destImageName, Object operator);

  void CompressImage(String imageName, String destImageName, Object operator);

  void valueImage(String imageName, String destImageName);

  void intensityImage(String imageName, String destImageName);

  void mergeImage(List<String> imagesToMergeName, String destImageName);

  void histogramImage(String imageName, String destImageName);

  void levelAdjustImage(String imageName, String destImageName, Object operator);

  void colorCorrectImage(String imageName, String destImageName, Object operator);

  void loadImage(String imagePath, String imageName) throws IOException;

  void saveImage(String imagePath, String imageName) throws IOException;

  ImageInterface getImage(String imageName);

}
