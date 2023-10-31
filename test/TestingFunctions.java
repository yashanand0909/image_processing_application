import commonlabels.ImageFormats;
import image.ImageInterface;
import imageio.CommonFormatsFileAdapter;
import java.io.IOException;
import java.util.List;
import operations.split.SplitImage;
import org.junit.Test;

public class TestingFunctions {

  @Test
  public void test() throws IOException {
    CommonFormatsFileAdapter commonFormatsFileAdapter = new CommonFormatsFileAdapter();
    ImageInterface image = commonFormatsFileAdapter.decodeImage("/Users/yashanand/Documents/PDP_assignment_4/manhattan-small.png");
    System.out.println("test done");
    SplitImage horizontalFlipOperation = new SplitImage();
    List<ImageInterface> flipedImage = horizontalFlipOperation.apply(image);
    for ( int i=0;i<flipedImage.size();i++)
      commonFormatsFileAdapter.encodeAndSaveImage("/Users/yashanand/Documents/PDP_assignment_4/manhattan-new-small-dark-"+i+".png",flipedImage.get(i),
        ImageFormats.PNG);
  }

}
