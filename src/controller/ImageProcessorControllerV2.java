package controller;

import java.io.IOException;
import logger.JViewInterface;
import model.image.ImageInterface;
import model.imageprocessingmodel.ImageProcessorModelInterface;

public class ImageProcessorControllerV2 implements ControllerInterface,
        UIFeatures {

  private final JViewInterface view;
  private final ImageProcessorModelInterface imageProcessorModel;

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
  public void loadImage(String imagePath, String destImageName) throws IOException {
    imageProcessorModel.loadImage(imagePath, destImageName);
    view.setCurrentImage(imageProcessorModel.getImage(destImageName));
  }

  @Override
  public void saveImage(String imagePath, String imageName) throws IOException {
    imageProcessorModel.saveImage(imagePath, imageName);
  }

  @Override
  public ImageInterface getImage(String imageName) {
    return null;
  }

  @Override
  public void loadHistogram(String imageName, String destImageName) {
    imageProcessorModel.histogramImage(imageName, destImageName);
    view.setHistogramImage(imageProcessorModel.getImage(destImageName));
    view.enableOperations();
  }

  @Override
  public void executeOperation(String imageName, String destImageName, String operationName, Object operator) {

  }
}
