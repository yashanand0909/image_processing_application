package model.imageprocessingmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests valid and invalid calls to its public function
 */
public class ImageProcessorModelTest {

  private ImageProcessorModel imageProcessorModel;

  @Before
  public void setUp() {
    imageProcessorModel = new ImageProcessorModel();
  }

  @Test
  public void testInvalidLoadCommand() {
    try {
      imageProcessorModel.processCommands(new String[]{"load", "invalid_image_path.jpg"});
    } catch (IllegalArgumentException | IOException e) {
      assertEquals("Invalid load command. Usage: load <image-path> <image-name>", e.getMessage());
    }
  }

  @Test
  public void testInvalidSaveCommand() {
    try {
      imageProcessorModel.processCommands(new String[]{"save", "output_image.jpg"});
    } catch (IllegalArgumentException | IOException e) {
      assertEquals("Invalid save command. Usage: save <image-path> <image-name>", e.getMessage());
    }
  }

  @Test
  public void testInvalidCommand() {
    try {
      imageProcessorModel.processCommands(new String[]{"invalid_command"});
    } catch (IllegalArgumentException | IOException e) {
      assertEquals("Unknown command. Try again or type 'exit' to quit.", e.getMessage());
    }
  }

  @Test
  public void testNonExistentSourceImage() {
    String newImageName = "brightened_image";
    try {
      imageProcessorModel
          .processCommands(new String[]{"brighten", "1.5", "non_existent_image", newImageName});
    } catch (IllegalArgumentException | IOException e) {
      assertEquals("Invalid request : No image exist with the name non_existent_image",
          e.getMessage());
    }
    assertNull(imageProcessorModel.getImage(newImageName));
  }


}
