package operations;

import image.ImageInterface;

public interface Operation {
  public ImageInterface apply(ImageInterface image, Object operator);

}
