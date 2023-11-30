package logger;

import controller.UIFeatures;
import java.util.List;
import model.image.ImageInterface;

public interface JViewInterface {
  void addFeatures(UIFeatures f);
  void setCurrentImage(ImageInterface img);
  void setHistogramImage(ImageInterface histogramImage);
  void enableOperations();
  void displayErrorPopup(String message);
}
