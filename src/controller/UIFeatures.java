package controller;

import java.io.IOException;

import model.image.ImageInterface;

/**
 * This interface represents the features of the UI supported by controller.
 */
public interface UIFeatures {

  /*void blurImage(String imageName, String destImageName, Object operator);

  void sharpenImage(String imageName, String destImageName, Object operator);

  void greyScaleImage(String imageName, String destImageName, Object operator);

  void sepiaImage(String imageName, String destImageName, Object operator);

  void colorCorrectImage(String imageName, String destImageName, Object operator);

  void levelAdjustImage(String imageName, String destImageName, Object operator);

  void redComponentImage(String imageName, String destImageName);

  void greenComponentImage(String imageName, String destImageName);

  void blueComponentImage(String imageName, String destImageName);

  void compressImage(String imageName, String destImageName, Object operator);*/

  void loadImage(String imagePath) throws IOException;

  void saveImage(String imagePath) throws IOException;

  ImageInterface getImage(String imageName);

  void loadHistogram();

  void executeOperation(String operationName);

  /*void horizontalFlipImage(String imageName, String destImageName);

  void verticalFlipImage(String imageName, String destImageName);*/

}
