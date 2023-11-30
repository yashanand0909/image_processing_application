package controller;

import commonlabels.SupportedUIOperations;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import logger.JViewInterface;
import model.image.ImageInterface;
import model.imageprocessingmodel.ImageProcessorModelInterface;

public class ImageProcessorControllerV2 implements ControllerInterface,
        UIFeatures {

  private final JViewInterface view;
  private final ImageProcessorModelInterface imageProcessorModel;
  private String originalImageName;

  public ImageProcessorControllerV2(JViewInterface view,
                                    ImageProcessorModelInterface imageProcessorModel) {
    this.view = view;
    this.imageProcessorModel = imageProcessorModel;
    view.addFeatures(this);
  }

  @Override
  public void startImageProcessingController() {

  }

  @Override
  public void loadImage(String imagePath, String destImageName) {
    try {
      if (!Objects.nonNull(imageProcessorModel.getImage(destImageName))) {
        imageProcessorModel.loadImage(imagePath, destImageName);
        imageProcessorModel.histogramImage(destImageName, "histogram_" + destImageName);
      }
      view.setCurrentImage(imageProcessorModel.getImage(destImageName));
      originalImageName = destImageName;
      view.setHistogramImage(imageProcessorModel.getImage("histogram_" + destImageName));
      view.enableOperations();
    } catch (Exception e){
      view.displayErrorPopup(e.getMessage());
    }
  }

  @Override
  public void saveImage(String imagePath) {
    try {
      imageProcessorModel.saveImage(imagePath, originalImageName);
    } catch (Exception e){
      view.displayErrorPopup(e.getMessage());
    }
  }


  @Override
  public void loadHistogram(String imageName, String destImageName) {
    try {
      imageProcessorModel.histogramImage(imageName, destImageName);
      view.setHistogramImage(imageProcessorModel.getImage(destImageName));
      view.enableOperations();
    } catch (Exception e){
      view.displayErrorPopup(e.getMessage());
    }
  }

  @Override
  public void undoSplit(){
    try {
      ImageInterface image = imageProcessorModel.getImage(originalImageName);
      ImageInterface histogramImage = imageProcessorModel.getImage("histogram_" + originalImageName);
      view.setCurrentImage(image);
      view.setHistogramImage(histogramImage);
    } catch (Exception e){
      view.displayErrorPopup(e.getMessage());
    }
  }

  @Override
  public void executeOperationWithSplit(String operationName, Object operator) {
    try {
      String destImageName = originalImageName+operationName;
      String splitDestinationName = destImageName + "_split" + operator.toString();
      if (Objects.nonNull(imageProcessorModel.getImage(splitDestinationName))) {
        view.setCurrentImage(imageProcessorModel.getImage(splitDestinationName));
        view.setHistogramImage(imageProcessorModel.getImage("histogram_" + splitDestinationName));
        return;
      }
      Map<String, Runnable> keyReleases = new HashMap<>();
      keyReleases.put(SupportedUIOperations.BLUR.toString(),
          () -> imageProcessorModel.blurImage(originalImageName, splitDestinationName, operator));
      keyReleases.put(SupportedUIOperations.SHARPEN.toString(),
          () -> imageProcessorModel.sharpenImage(originalImageName, splitDestinationName, operator));
      keyReleases.put(SupportedUIOperations.GREYSCALE.toString(),
          () -> imageProcessorModel.greyScaleImage(originalImageName, splitDestinationName, operator));
      keyReleases.put(SupportedUIOperations.SEPIA.toString(),
          () -> imageProcessorModel.sepiaImage(originalImageName, splitDestinationName, operator));
      keyReleases.put(SupportedUIOperations.COLORCORRECTION.toString(),
          () -> imageProcessorModel.colorCorrectImage(originalImageName, splitDestinationName, operator));
      keyReleases.put(SupportedUIOperations.LEVELADJUST.toString(),
          () -> imageProcessorModel.levelAdjustImage(originalImageName, splitDestinationName,
              operator));
      keyReleases.put(SupportedUIOperations.REDCOMPONENT.toString(),
          () -> imageProcessorModel.splitImage(originalImageName,
              splitDestinationName, "0 " + operator));
      keyReleases.put(SupportedUIOperations.GREENCOMPONENT.toString(),
          () -> imageProcessorModel.splitImage(originalImageName,
              splitDestinationName, "1 " + operator));
      keyReleases.put(SupportedUIOperations.BLUECOMPONENT.toString(),
          () -> imageProcessorModel.splitImage(originalImageName,
              splitDestinationName, "2 " + operator));
      executeAndGetImages(splitDestinationName, operationName, keyReleases);
    }catch (Exception e){
      view.displayErrorPopup(e.getMessage());
    }
  }

  @Override
  public void executeOperation(String operationName, Object operator) {
    try {
      String destImageName = originalImageName+operationName;
      if (Objects.nonNull(imageProcessorModel.getImage(destImageName))) {
        view.setCurrentImage(imageProcessorModel.getImage(destImageName));
        view.setHistogramImage(imageProcessorModel.getImage("histogram_" + destImageName));
        return;
      }
      Map<String, Runnable> keyReleases = new HashMap<>();
      keyReleases.put(
          SupportedUIOperations.BLUR.toString(),
          () -> imageProcessorModel.blurImage(originalImageName, destImageName));
      keyReleases.put(SupportedUIOperations.SHARPEN.toString(),
          () -> imageProcessorModel.sharpenImage(originalImageName, destImageName));
      keyReleases.put(SupportedUIOperations.GREYSCALE.toString(),
          () -> imageProcessorModel.greyScaleImage(originalImageName, destImageName));
      keyReleases.put(SupportedUIOperations.SEPIA.toString(),
          () -> imageProcessorModel.sepiaImage(originalImageName, destImageName));
      keyReleases.put(SupportedUIOperations.COLORCORRECTION.toString(),
          () -> imageProcessorModel.colorCorrectImage(originalImageName, destImageName));
      keyReleases.put(SupportedUIOperations.LEVELADJUST.toString(),
          () -> imageProcessorModel.levelAdjustImage(originalImageName, destImageName,
              operator));
      keyReleases.put(SupportedUIOperations.REDCOMPONENT.toString(),
          () -> imageProcessorModel.splitImage(originalImageName,
              destImageName, 0));
      keyReleases.put(SupportedUIOperations.GREENCOMPONENT.toString(),
          () -> imageProcessorModel.splitImage(originalImageName,
              destImageName, 1));
      keyReleases.put(SupportedUIOperations.BLUECOMPONENT.toString(),
          () -> imageProcessorModel.splitImage(originalImageName,
              destImageName, 2));
      keyReleases.put(SupportedUIOperations.HORIZONTALFLIP.toString(),
          () -> imageProcessorModel.horizontalFlipImage(originalImageName, destImageName));
      keyReleases.put(SupportedUIOperations.VERTICALFLIP.toString(),
          () -> imageProcessorModel.verticalFlipImage(originalImageName, destImageName));
      keyReleases.put(SupportedUIOperations.COMPRESSION.toString(),
          () -> imageProcessorModel.compressImage(originalImageName, destImageName,
              operator));
      executeAndGetImages(destImageName, operationName, keyReleases);
      originalImageName = destImageName;
    } catch (Exception e){
      view.displayErrorPopup(e.getMessage());
    }
  }

  private void executeAndGetImages(String destImageName, String operationName,
      Map<String, Runnable> keyReleases) {
    keyReleases.get(operationName).run();
    ImageInterface curImage = imageProcessorModel.getImage(destImageName);
    String histogramDestinationName = "histogram_" + destImageName;
    imageProcessorModel.histogramImage(destImageName,histogramDestinationName);
    ImageInterface imageHistogram = imageProcessorModel.getImage(histogramDestinationName);
    view.setCurrentImage(curImage);
    view.setHistogramImage(imageHistogram);
  }
}
