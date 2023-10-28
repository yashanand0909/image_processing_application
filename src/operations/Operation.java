package operations;

import images.ImageInterface;

public interface Operation {
  public ImageInterface apply(ImageInterface image, Object operator);

}
