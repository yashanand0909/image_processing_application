package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import logger.ViewLogger;
import model.image.ImageInterface;
import model.imageprocessingmodel.ImageProcessorModel;
import model.imageprocessingmodel.ImageProcessorModelInterface;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the ImageProcessorController class.
 */
public class ImageProcessorControllerTest {

  private ImageProcessorController controller;
  private StringWriter out;
  private ImageProcessorModel model;
  private ViewLogger logger;

  @Before
  public void setUp() {
    out = new StringWriter();
    logger = new ViewLogger(out);
    model = new ImageProcessorModel();
  }

  @Test
  public void testHandleValidCommands() {
    StringBuilder s = new StringBuilder();
    MockModel modelMock = new MockModel(s);
    controller = new ImageProcessorController(logger, modelMock,
        new StringReader("load path/to/image.jpg image1 \nexit"), out);
    controller.startImageProcessingController();
    assertEquals("loadpath/to/image.jpgimage1", s.toString());
    assertTrue(out.toString().contains("Command ran successfully"));
  }

  @Test
  public void testHandleExitCommand() {
    controller = new ImageProcessorController(logger, model, new StringReader("exit"), out);
    controller.startImageProcessingController();
    assertEquals("Enter a command:", out.toString());
  }

  @Test
  public void testExitCommand() {
    ImageProcessorController controller = new ImageProcessorController(logger, model,
        new StringReader("exit"), out);
    controller.startImageProcessingController();
    assertEquals("Enter a command:", out.toString());
  }

  @Test
  public void testHandleInvalidCommands() {
    controller = new ImageProcessorController(logger, model, new StringReader("\nexit"), out);
    controller.startImageProcessingController();
    assertTrue(out.toString().contains("Invalid command. Try again."));
  }

  @Test
  public void testHandleMultipleCommands() {
    String scriptContent = "load path/to/image.jpg image1\n"
        + "brighten 10 image1 image2\n"
        + "exit\n";
    StringBuilder s = new StringBuilder();
    MockModel modelMock = new MockModel(s);
    controller = new ImageProcessorController(logger, modelMock, new StringReader(scriptContent),
        out);
    controller.startImageProcessingController();
    assertEquals("loadpath/to/image.jpgimage1brighten10image1image2", s.toString());
  }

  @Test
  public void testHandleScriptCommands() throws IOException {
    String scriptContent = "load path/to/image.jpg image1\n"
        + "brighten 10 image1 image2\n";
    File tempFile = createTempScriptFile(scriptContent);
    assert tempFile != null;
    String filePath = tempFile.getAbsolutePath();
    String runString = "run " + filePath + "\nexit";
    StringBuilder s = new StringBuilder();
    MockModel modelMock = new MockModel(s);
    controller = new ImageProcessorController(logger, modelMock, new StringReader(runString), out);
    controller.startImageProcessingController();
    assertTrue(s.toString().contains("loadpath/to/image.jpgimage1brighten10image1image2"));
  }

  @Test
  public void testInvalidScriptFile() {
    String scriptContent = "invalid_command\nexit";
    File tempFile = createTempScriptFile(scriptContent);
    assert tempFile != null;
    String filePath = tempFile.getAbsolutePath();
    ImageProcessorController controller = new ImageProcessorController(logger, model,
        new StringReader("run " + filePath + "\nexit"), out);
    controller.startImageProcessingController();
    assertTrue(out.toString().contains("Unknown command. Try again or type 'exit' to quit."));
  }

  @Test
  public void testHandleScriptAndCliCommands() throws IOException {
    String scriptContent = "load path/to/image.jpg image1\n"
        + "brighten 10 image1 image2\n";
    File tempFile = File.createTempFile("temp", ".txt");
    String filePath = tempFile.getAbsolutePath();
    PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
    writer.println(scriptContent);
    writer.close();
    String runString = "run " + filePath + "\nbrighten 10 image1 image3" + "\nexit";
    StringBuilder s = new StringBuilder();
    MockModel modelMock = new MockModel(s);
    controller = new ImageProcessorController(logger, modelMock, new StringReader(runString), out);
    controller.startImageProcessingController();
    assertTrue(s.toString()
        .contains("loadpath/to/image.jpgimage1brighten10image1image2brighten10image1image3"));
  }

  private File createTempScriptFile(String scriptContent) {
    try {
      File tempFile = File.createTempFile("temp", ".txt");
      PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
      writer.println(scriptContent);
      writer.close();
      return tempFile;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  static class MockModel implements ImageProcessorModelInterface {

    private final StringBuilder log;

    public MockModel(StringBuilder log) {
      this.log = log;
    }

    @Override
    public void processCommands(String[] parts) throws IOException {
      for (String s : parts) {
        log.append(s);
      }
    }

    @Override
    public ImageInterface getImage(String imageName) {
      return null;
    }
  }

}
