package controller;

import java.io.IOException;

import model.image.ImageInterface;

/**
 * This interface represents the features of the UI supported by controller.
 */
public interface UIFeatures {

  void loadImage(String imagePath, String destImageName);

  void saveImage(String imagePath);

  void loadHistogram(String imageName, String destImageName);

  public void undoSplit();

  public void executeOperationWithSplit(String operationName, Object operator);

  void executeOperation(String operationName, Object operator);

  boolean checkIfImageIsSaved();

  /*void horizontalFlipImage(String imageName, String destImageName);

  void verticalFlipImage(String imageName, String destImageName);*/

}
