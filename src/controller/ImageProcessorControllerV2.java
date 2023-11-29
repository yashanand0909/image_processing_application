package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import logger.JViewInterface;
import logger.ViewLogger;
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
  }

  @Override

  public void startImageProcessingController() throws IOException {

  }

  @Override
  public void loadImage(String imagePath, String destImageName) {

  }

  @Override
  public void saveImage(String imagePath, String imageName) {

  }

  @Override
  public ImageInterface getImage(String imageName) {
    return null;
  }

  @Override
  public void histogramImage(String imageName, String destImageName) {

  }

  @Override
  public void executeOperation(String imageName, String destImageName,
                               String operationName, Object operator) {
    Map<String, Runnable> keyReleases = new HashMap<String, Runnable>();
    keyReleases.put("blur", () -> imageProcessorModel.blurImage(imageName, destImageName));
    keyReleases.put("sharpen", () -> imageProcessorModel.sharpenImage(imageName, destImageName));
    keyReleases.put("greyscale", () -> imageProcessorModel.greyScaleImage(imageName, destImageName));
    keyReleases.put("sepia", () -> imageProcessorModel.sepiaImage(imageName, destImageName));
    keyReleases.put("colorcorrect", () -> imageProcessorModel.colorCorrectImage(imageName, destImageName));
    keyReleases.put("leveladjust", () -> imageProcessorModel.levelAdjustImage(imageName, destImageName,
            operator));
    keyReleases.put("redcomponent", () -> imageProcessorModel.splitImage(imageName,
            destImageName, 0));
    keyReleases.put("greencomponent", () -> imageProcessorModel.splitImage(imageName,
            destImageName, 1));
    keyReleases.put("bluecomponent", () -> imageProcessorModel.splitImage(imageName,
            destImageName, 2));
    keyReleases.put("horizontalflip", () -> imageProcessorModel.horizontalFlipImage(imageName, destImageName));
    keyReleases.put("verticalflip", () -> imageProcessorModel.verticalFlipImage(imageName, destImageName));
    keyReleases.put("compress", () -> imageProcessorModel.compressImage(imageName, destImageName,
            operator));
    keyReleases.put("original", () -> imageProcessorModel.getImage(imageName));
  }
}
