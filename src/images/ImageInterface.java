package images;

import java.util.List;
import operations.Operation;

public interface ImageInterface {
  public List<int[][]> getChannel();

  public ImageInterface applyOperation(Operation operation, Object operator);

}
