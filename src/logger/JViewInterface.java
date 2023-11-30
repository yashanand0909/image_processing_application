package logger;

import java.util.List;

import controller.UIFeatures;
import model.image.ImageInterface;

public interface JViewInterface {
  void addFeatures(UIFeatures f);
  void setCurrentImage(ImageInterface img);
  void setHistogramImage(ImageInterface histogramImage);
  void enableOperations();
  List getAdjustLevelInputs();
  void displayErrorPopup(String message);
}
