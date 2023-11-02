package controller;

import java.io.IOException;
import model.operations.ImageProcessingModel.ImageProcessorModelInterface;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.StringReader;
import java.io.StringWriter;
import logger.ViewLogger;
import model.operations.ImageProcessingModel.ImageProcessorModel;

public class ImageProcessorControllerTest {

  private ImageProcessorController controller;
  private StringWriter out;
  private ImageProcessorModel model;
  private ViewLogger logger;

   class MockModel implements ImageProcessorModelInterface {
    private StringBuilder log;

     public MockModel(StringBuilder log) {
       this.log = log;
     }

     @Override
    public void processCommands(String[] parts) throws IOException {
      for (String s : parts)
      log.append(s);
    }
  }

  @Before
  public void setUp() {
    out = new StringWriter();
    logger = new ViewLogger(out);
    model = new ImageProcessorModel(); // You can create a mock or stub for ImageProcessorModel

  }

  @Test
  public void testHandleValidCommands() {
     StringBuilder s = new StringBuilder();
    MockModel modelMock = new MockModel(s);
    controller = new ImageProcessorController(logger, modelMock, new StringReader("load path/to/image.jpg image1 \nexit"), out);
    controller.startImageProcessingController();
    assertEquals("load path/to/image.jpg image1", s.toString());
    assertTrue(out.toString().contains("Command ran successfully"));
  }

  @Test
  public void testHandleExitCommand() {
    controller = new ImageProcessorController(logger, model, new StringReader("load path/to/image.jpg image1 \nexit"), out);
    assertEquals("", out.toString());
    // Ensure that no output is generated for the exit command
  }

  @Test
  public void testHandleInvalidCommands() {
    controller = new ImageProcessorController(logger, model, new StringReader("load path/to/image.jpg image1 \nexit"), out);
    assertTrue(out.toString().contains("Invalid command. Try again."));

    controller = new ImageProcessorController(logger, model, new StringReader("load path/to/image.jpg image1 \nexit"), out);
    assertTrue(out.toString().contains("Unknown command. Try again"));

    controller = new ImageProcessorController(logger, model, new StringReader("load path/to/image.jpg image1 \nexit"), out);
    assertTrue(out.toString().contains("Invalid load command. Try again."));

    // Add more test cases to cover various invalid command scenarios
  }

  @Test
  public void testHandleScriptFile() {
    String scriptContent = "load path/to/image.jpg image1\n"
        + "brighten 10 image1 image2\n"
        + "exit\n";
    controller = new ImageProcessorController(logger, model, new StringReader("load path/to/image.jpg image1 \nexit"), out);

    assertTrue(out.toString().contains("Command ran successfully"));
    // Add assertions to validate the expected behavior of script execution
  }

  @Test
  public void testHandleInvalidScriptFile() {
    String invalidScript = "invalid-command\n";
    controller = new ImageProcessorController(logger, model, new StringReader("load path/to/image.jpg image1 \nexit"), out);

    assertTrue(out.toString().contains("Invalid command in the script file."));

    controller = new ImageProcessorController(logger, model, new StringReader("load path/to/image.jpg image1 \nexit"), out);
    assertTrue(out.toString().contains("Invalid run command. Usage: run <script-file-name>"));
  }

  // You can add more test cases for specific scenarios or edge cases as needed
}
