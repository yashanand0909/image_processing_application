package model.image;

import java.util.Collections;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImageFactoryTest {

  @Test
  public void testCreateImageWithValidChannels() {
    int[][] channel1 = {{255, 0}, {0, 255}};
    int[][] channel2 = {{0, 255}, {255, 0}};
    int[][] channel3 = {{0, 0}, {255, 255}};
    ImageInterface image = ImageFactory.createImage(List.of(channel1, channel2, channel3));

    assertNotNull(image);
    assertEquals(3, image.getChannel().size());
    assertEquals(2, image.getChannel().get(0).length);
    assertEquals(2, image.getChannel().get(0)[0].length);
  }

  @Test
  public void testCreateImageWithSingleChannel() {
    int[][] channel = {{255, 0}, {0, 255}};
    ImageInterface image = ImageFactory.createImage(Collections.singletonList(channel));

    assertNotNull(image);
    assertEquals(1, image.getChannel().size());
    assertEquals(2, image.getChannel().get(0).length);
    assertEquals(2, image.getChannel().get(0)[0].length);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateImageWithInvalidNumberOfChannels() {
    int[][] channel1 = {{255, 0}, {0, 255}};
    int[][] channel2 = {{0, 255}, {255, 0}};
    int[][] channel3 = {{0, 0}, {255, 255}};
    int[][] channel4 = {{128, 128}, {128, 128}};
    ImageFactory.createImage(List.of(channel1, channel2, channel3, channel4));
  }
}
