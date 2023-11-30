package controller;

import commonlabels.SupportedUIOperations;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    imageProcessorModel.histogramImage(destImageName,"histogram_"+destImageName);
    view.setHistogramImage(imageProcessorModel.getImage("histogram_"+destImageName));
    view.enableOperations();
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
  public void undoSplit(String imageName){
    ImageInterface image = imageProcessorModel.getImage(imageName);
    ImageInterface histogramImage = imageProcessorModel.getImage("histogram_"+imageName);
    view.setCurrentImage(image);
    view.setHistogramImage(histogramImage);
  }

  @Override
  public void executeOperationWithSplit(String imageName, String destImageName,
      String operationName, Object operator) {
    String splitDestinationName = destImageName + "_split";
    Map<String, Runnable> keyReleases = new HashMap<>();
    keyReleases.put(SupportedUIOperations.BLUR.toString(), () -> imageProcessorModel.blurImage(imageName, splitDestinationName,operator));
    keyReleases.put(SupportedUIOperations.SHARPEN.toString(), () -> imageProcessorModel.sharpenImage(imageName, splitDestinationName,operator));
    keyReleases.put(SupportedUIOperations.GREYSCALE.toString(), () -> imageProcessorModel.greyScaleImage(imageName, splitDestinationName,operator));
    keyReleases.put(SupportedUIOperations.SEPIA.toString(), () -> imageProcessorModel.sepiaImage(imageName, splitDestinationName,operator));
    keyReleases.put(SupportedUIOperations.COLORCORRECTION.toString(), () -> imageProcessorModel.colorCorrectImage(imageName, splitDestinationName,operator));
    keyReleases.put(SupportedUIOperations.LEVELADJUST.toString(), () -> imageProcessorModel.levelAdjustImage(imageName, splitDestinationName,
        operator));
    keyReleases.put(SupportedUIOperations.REDCOMPONENT.toString(), () -> imageProcessorModel.splitImage(imageName,
        splitDestinationName, "0 "+operator.toString()));
    keyReleases.put(SupportedUIOperations.GREENCOMPONENT.toString(), () -> imageProcessorModel.splitImage(imageName,
        splitDestinationName, "1 "+operator.toString()));
    keyReleases.put(SupportedUIOperations.BLUECOMPONENT.toString(), () -> imageProcessorModel.splitImage(imageName,
        splitDestinationName, "2 "+operator.toString()));
    executeAndGetImages(splitDestinationName, operationName, keyReleases);
  }

  @Override
  public void executeOperation(String imageName, String destImageName,
                               String operationName, Object operator) {
    Map<String, Runnable> keyReleases = new HashMap<>();
    keyReleases.put(
        SupportedUIOperations.BLUR.toString(), () -> imageProcessorModel.blurImage(imageName, destImageName));
    keyReleases.put(SupportedUIOperations.SHARPEN.toString(), () -> imageProcessorModel.sharpenImage(imageName, destImageName));
    keyReleases.put(SupportedUIOperations.GREYSCALE.toString(), () -> imageProcessorModel.greyScaleImage(imageName, destImageName));
    keyReleases.put(SupportedUIOperations.SEPIA.toString(), () -> imageProcessorModel.sepiaImage(imageName, destImageName));
    keyReleases.put(SupportedUIOperations.COLORCORRECTION.toString(), () -> imageProcessorModel.colorCorrectImage(imageName, destImageName));
    keyReleases.put(SupportedUIOperations.LEVELADJUST.toString(), () -> imageProcessorModel.levelAdjustImage(imageName, destImageName,
            operator));
    keyReleases.put(SupportedUIOperations.REDCOMPONENT.toString(), () -> imageProcessorModel.splitImage(imageName,
            destImageName, 0));
    keyReleases.put(SupportedUIOperations.GREENCOMPONENT.toString(), () -> imageProcessorModel.splitImage(imageName,
            destImageName, 1));
    keyReleases.put(SupportedUIOperations.BLUECOMPONENT.toString(), () -> imageProcessorModel.splitImage(imageName,
            destImageName, 2));
    keyReleases.put(SupportedUIOperations.HORIZONTALFLIP.toString(), () -> imageProcessorModel.horizontalFlipImage(imageName, destImageName));
    keyReleases.put(SupportedUIOperations.VERTICALFLIP.toString(), () -> imageProcessorModel.verticalFlipImage(imageName, destImageName));
    keyReleases.put(SupportedUIOperations.COMPRESSION.toString(), () -> imageProcessorModel.compressImage(imageName, destImageName,
            operator));
    executeAndGetImages(destImageName, operationName, keyReleases);
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
