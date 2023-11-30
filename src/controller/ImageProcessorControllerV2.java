package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import logger.JViewInterface;
import model.image.ImageInterface;
import model.imageprocessingmodel.ImageProcessorModelInterface;

public class ImageProcessorControllerV2 implements ControllerInterface,
        UIFeatures {

  private final JViewInterface view;
  private final ImageProcessorModelInterface imageProcessorModel;

  private String originalImageName;

  private String currentImageName;

  private String lastSavedImageName;

  private String currentOperation;

  public ImageProcessorControllerV2(JViewInterface view,
                                    ImageProcessorModelInterface imageProcessorModel) {
    this.view = view;
    this.imageProcessorModel = imageProcessorModel;
    view.addFeatures(this);
  }

  @Override

  public void startImageProcessingController() throws IOException {

  }

  @Override
  public void loadImage(String imagePath) throws IOException {
    String destImageName = "originalImage_" + getFileNameFromPath(imagePath);
    imageProcessorModel.loadImage(imagePath,
            destImageName);
    this.originalImageName = getFileNameFromPath(imagePath);
    this.currentImageName = destImageName;
    view.setCurrentImage(imageProcessorModel.getImage(destImageName));
  }

  @Override
  public void saveImage(String imagePath) throws IOException {
    imageProcessorModel.saveImage(imagePath, this.currentImageName);
    this.lastSavedImageName = this.currentImageName;
  }

  @Override
  public ImageInterface getImage(String imageName) {
    return null;
  }

  @Override
  public void loadHistogram() {
    String imageName = this.currentImageName;
    String destImageName = "histogramImage_" + originalImageName;
    imageProcessorModel.histogramImage(imageName, destImageName);
    view.setHistogramImage(imageProcessorModel.getImage(destImageName));
    view.enableOperations();
  }

  private String getFileNameFromPath(String filePath) {
    File file = new File(filePath);
    this.originalImageName = file.getName();
    return file.getName();
  }

  @Override
  public void executeOperation(String operation) {

  }
}
