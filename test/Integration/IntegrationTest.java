package Integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.ImageProcessorController;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import logger.ViewLogger;
import model.ImageProcessingModel.ImageProcessorModel;
import model.image.CommonImage;
import model.image.ImageInterface;
import model.imageio.IOFileFactory;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {

  private final String imagePath = "test_image.jpg";
  ImageProcessorController imageProcessorController;
  private StringWriter out;
  private ViewLogger logger;

  @Before
  public void setUp() throws IOException {
    out = new StringWriter();
    logger = new ViewLogger(out);
    int[][] redChannel = new int[][]{{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] greenChannel = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] blueChannel = new int[][]{{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};
    ImageInterface imageInterface = new CommonImage.ImageBuilder().addChannel(redChannel)
        .addChannel(greenChannel)
        .addChannel(blueChannel)
        .build();
    IOFileFactory.encodeAndSaveImage
        (imagePath
            , imageInterface);
  }

  @Test
  public void testBrightenFlow() throws IOException {
    String newImagePath = "test_image_bright.jpg";
    int[][] newChannelAfterIncreaseBrightnessRed = {{245, 245, 245}, {245, 245, 245},
        {245, 245, 245}};
    int[][] newChannelAfterIncreaseBrightnessGreen = {{0, 0, 0}, {0, 0, 0}, {0, 76, 0}};
    int[][] newChannelAfterIncreaseBrightnessBlue = {{244, 244, 230}, {230, 0, 80},
        {73, 245, 34}};
    List<int[][]> newChannleList = Arrays
        .asList(newChannelAfterIncreaseBrightnessRed, newChannelAfterIncreaseBrightnessGreen,
            newChannelAfterIncreaseBrightnessBlue);
    String loadCommand = "load " + imagePath + " test\n";
    String brightenCommand = "brighten -10 test test_bright\n";
    String saveCommand = "save " + newImagePath + " test_bright\n";
    imageProcessorController = new ImageProcessorController(logger, new ImageProcessorModel(),
        new StringReader(loadCommand + brightenCommand + saveCommand + "exit"), out);
    imageProcessorController.startImageProcessingController();
    assertTrue(out.toString().contains("Command ran successfully"));
    ImageInterface image = IOFileFactory.decodeImage(newImagePath);
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        for (int w = 0; w < image.getWidth(); w++) {
          assertEquals(image.getChannel().get(i)[j][w], newChannleList.get(i)[j][w]);
        }
      }
    }
    cleanupImages(Arrays.asList(imagePath, newImagePath));
  }

  @Test
  public void testRedComponentFlow() throws IOException {
    // Define your expected image and other necessary variables
    String newImagePath = "test_image_red_component.jpg";
    int[][] newChannelAfterIncreaseBrightnessRed = {{255, 255, 255}, {255, 255, 255},
        {255, 255, 255}};
    int[][] newChannelAfterIncreaseBrightnessGreen = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    int[][] newChannelAfterIncreaseBrightnessBlue = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    List<int[][]> newChannleList = Arrays
        .asList(newChannelAfterIncreaseBrightnessRed, newChannelAfterIncreaseBrightnessGreen,
            newChannelAfterIncreaseBrightnessBlue);
    String loadCommand = "load " + imagePath + " test\n";
    String redComponentCommand = "red-component test test_red_component\n";
    String saveCommand = "save " + newImagePath + " test_red_component\n";
    imageProcessorController = new ImageProcessorController(logger, new ImageProcessorModel(),
        new StringReader(loadCommand + redComponentCommand + saveCommand + "exit"), out);
    imageProcessorController.startImageProcessingController();
    assertTrue(out.toString().contains("Command ran successfully"));
    ImageInterface image = IOFileFactory.decodeImage(newImagePath);
    assertImageEquals(newChannleList, image);
    cleanupImages(Arrays.asList(imagePath, newImagePath));
  }

  @Test
  public void testGreenComponentFlow() throws IOException {
    String newImagePath = "test_image_green_component.jpg";
    int[][] newChannelAfterIncreaseBrightnessRed = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    int[][] newChannelAfterIncreaseBrightnessGreen = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] newChannelAfterIncreaseBrightnessBlue = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    List<int[][]> newChannleList = Arrays
        .asList(newChannelAfterIncreaseBrightnessRed, newChannelAfterIncreaseBrightnessGreen,
            newChannelAfterIncreaseBrightnessBlue);
    String loadCommand = "load " + imagePath + " test\n";
    String greenComponentCommand = "green-component test test_green_component\n";
    String saveCommand = "save " + newImagePath + " test_green_component\n";
    imageProcessorController = new ImageProcessorController(logger, new ImageProcessorModel(),
        new StringReader(loadCommand + greenComponentCommand + saveCommand + "exit"), out);
    imageProcessorController.startImageProcessingController();
    assertTrue(out.toString().contains("Command ran successfully"));
    ImageInterface image = IOFileFactory.decodeImage(newImagePath);
    assertImageEquals(newChannleList, image);
    cleanupImages(Arrays.asList(imagePath, newImagePath));
  }

  @Test
  public void testBlueComponentFlow() throws IOException {
    String newImagePath = "test_image_blue_component.jpg";
    int[][] newChannelAfterIncreaseBrightnessRed = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    int[][] newChannelAfterIncreaseBrightnessGreen = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    int[][] newChannelAfterIncreaseBrightnessBlue = {{254, 254, 240}, {240, 0, 90}, {83, 255, 44}};
    List<int[][]> newChannleList = Arrays
        .asList(newChannelAfterIncreaseBrightnessRed, newChannelAfterIncreaseBrightnessGreen,
            newChannelAfterIncreaseBrightnessBlue);
    String loadCommand = "load " + imagePath + " test\n";
    String greenComponentCommand = "blue-component test test_blue_component\n";
    String saveCommand = "save " + newImagePath + " test_blue_component\n";
    imageProcessorController = new ImageProcessorController(logger, new ImageProcessorModel(),
        new StringReader(loadCommand + greenComponentCommand + saveCommand + "exit"), out);
    imageProcessorController.startImageProcessingController();
    assertTrue(out.toString().contains("Command ran successfully"));
    ImageInterface image = IOFileFactory.decodeImage(newImagePath);
    assertImageEquals(newChannleList, image);
    cleanupImages(Arrays.asList(imagePath, newImagePath));
  }

  @Test
  public void testHorizontalFlipFlow() throws IOException {
    String newImagePath = "test_image_horizontal_flip.jpg";
    int[][] newChannelAfterHorizontalRotationRed = {{255, 255, 255}, {255, 255, 255},
        {255, 255, 255}};
    int[][] newChannelAfterHorizontalRotationGreen = {{0, 0, 0}, {0, 0, 0}, {0, 86, 0}};
    int[][] newChannelAfterHorizontalRotationBlue = {{240, 254, 254}, {90, 0, 240},
        {44, 255, 83}};
    List<int[][]> newChannleList = Arrays
        .asList(newChannelAfterHorizontalRotationRed, newChannelAfterHorizontalRotationGreen,
            newChannelAfterHorizontalRotationBlue);
    String loadCommand = "load " + imagePath + " test\n";
    String horizontalFlipCommand = "horizontal-flip test test_horizontal_flip\n";
    String saveCommand = "save " + newImagePath + " test_horizontal_flip\n";
    imageProcessorController = new ImageProcessorController(logger, new ImageProcessorModel(),
        new StringReader(loadCommand + horizontalFlipCommand + saveCommand + "exit"), out);
    imageProcessorController.startImageProcessingController();
    assertTrue(out.toString().contains("Command ran successfully"));
    ImageInterface image = IOFileFactory.decodeImage(newImagePath);
    assertImageEquals(newChannleList, image);
    cleanupImages(Arrays.asList(imagePath, newImagePath));
  }

  @Test
  public void testVerticalFlipFlow() throws IOException {
    String newImagePath = "test_image_vertical_flip.jpg";
    int[][] newChannelAfterVerticalRotationRed = {{255, 255, 255}, {255, 255, 255},
        {255, 255, 255}};
    int[][] newChannelAfterVerticalRotationGreen = {{0, 86, 0}, {0, 0, 0}, {0, 0, 0}};
    int[][] newChannelAfterVerticalRotationBlue = {{83, 255, 44}, {240, 0, 90}, {254, 254, 240}};
    List<int[][]> newChannleList = Arrays
        .asList(newChannelAfterVerticalRotationRed, newChannelAfterVerticalRotationGreen,
            newChannelAfterVerticalRotationBlue);
    String loadCommand = "load " + imagePath + " test\n";
    String verticalFlipCommand = "vertical-flip test test_vertical_flip\n";
    String saveCommand = "save " + newImagePath + " test_vertical_flip\n";
    imageProcessorController = new ImageProcessorController(logger, new ImageProcessorModel(),
        new StringReader(loadCommand + verticalFlipCommand + saveCommand + "exit"), out);
    imageProcessorController.startImageProcessingController();
    assertTrue(out.toString().contains("Command ran successfully"));
    ImageInterface image = IOFileFactory.decodeImage(newImagePath);
    assertImageEquals(newChannleList, image);
    cleanupImages(Arrays.asList(imagePath, newImagePath));
  }

  @Test
  public void testBlurFlow() throws IOException {
    // Define your expected image and other necessary variables
    String newImagePath = "test_image_blur.jpg";
    int[][] newChannelAfterBlurRed = {{140, 186, 140}, {186, 247, 186}, {140, 186, 140}};
    int[][] newChannelAfterBlurGreen = {{0, 0, 0}, {5, 10, 5}, {10, 21, 10}};
    int[][] newChannelAfterBlurBlue = {{124, 144, 102}, {131, 140, 87}, {81, 98, 53}};
    List<int[][]> newChannleList = Arrays
        .asList(newChannelAfterBlurRed, newChannelAfterBlurGreen,
            newChannelAfterBlurBlue);
    String loadCommand = "load " + imagePath + " test\n";
    String blurCommand = "blur test test_blur\n";
    String saveCommand = "save " + newImagePath + " test_blur\n";
    imageProcessorController = new ImageProcessorController(logger, new ImageProcessorModel(), new StringReader(loadCommand + blurCommand + saveCommand + "exit"), out);
    imageProcessorController.startImageProcessingController();
    assertTrue(out.toString().contains("Command ran successfully"));
    ImageInterface image = IOFileFactory.decodeImage(newImagePath);
    assertImageEquals(newChannleList, image);
    cleanupImages(Arrays.asList(imagePath, newImagePath));
  }

  @Test
  public void testSharpenFlow() throws IOException {
    String newImagePath = "test_image_sharpen.jpg";
    int[][] newChannelAfterSharpenRed = {{255, 255, 255}, {255, 255, 255}, {255, 255, 255}};
    int[][] newChannelAfterSharpenGreen = {{0, 0, 0}, {21, 21, 21}, {21, 86, 21}};
    int[][] newChannelAfterSharpenBlue = {{255, 255, 215}, {255, 255, 215}, {97, 255, 0}};
    List<int[][]> newChannleList = Arrays
        .asList(newChannelAfterSharpenRed, newChannelAfterSharpenGreen,
            newChannelAfterSharpenBlue);
    String loadCommand = "load " + imagePath + " test\n";
    String sharpenCommand = "sharpen test test_sharpen\n";
    String saveCommand = "save " + newImagePath + " test_sharpen\n";
    imageProcessorController = new ImageProcessorController(logger, new ImageProcessorModel(), new StringReader(loadCommand + sharpenCommand + saveCommand + "exit"), out);
    imageProcessorController.startImageProcessingController();
    assertTrue(out.toString().contains("Command ran successfully"));
    ImageInterface image = IOFileFactory.decodeImage(newImagePath);
    assertImageEquals(newChannleList, image);
    cleanupImages(Arrays.asList(imagePath, newImagePath));
  }

  @Test
  public void testSepiaFlow() throws IOException {
    String newImagePath = "test_image_sepia.jpg";
    int[][] newChannelAfterSepiaRed = {{148, 148, 145}, {145, 100, 117}, {115, 214, 108}};
    int[][] newChannelAfterSepiaGreen = {{131, 131, 129}, {129, 88, 104}, {102, 190, 96}};
    int[][] newChannelAfterSepiaBlue = {{102, 102, 100}, {100, 69, 81}, {80, 148, 75}};
    List<int[][]> newChannleList = Arrays
        .asList(newChannelAfterSepiaRed, newChannelAfterSepiaGreen,
            newChannelAfterSepiaBlue);
    String loadCommand = "load " + imagePath + " test\n";
    String sepiaCommand = "sepia test test_sepia\n";
    String saveCommand = "save " + newImagePath + " test_sepia\n";
    imageProcessorController = new ImageProcessorController(logger, new ImageProcessorModel(), new StringReader(loadCommand + sepiaCommand + saveCommand + "exit"), out);
    imageProcessorController.startImageProcessingController();
    assertTrue(out.toString().contains("Command ran successfully"));
    ImageInterface image = IOFileFactory.decodeImage(newImagePath);
    assertImageEquals(newChannleList, image);
    cleanupImages(Arrays.asList(imagePath, newImagePath));
  }



  private void assertImageEquals(List<int[][]> expectedChannel, ImageInterface image) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        for (int w = 0; w < image.getWidth(); w++) {
          assertEquals(image.getChannel().get(i)[j][w], expectedChannel.get(i)[j][w]);
        }
      }
    }
  }

  private void cleanupImages(List<String> paths) {
    for (String path : paths) {
      File file = new File(path);
      file.delete();
    }
  }


}
