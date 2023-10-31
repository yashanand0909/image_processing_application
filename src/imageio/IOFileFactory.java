package imageio;

import java.io.FileNotFoundException;
import java.io.IOException;

import commonlabels.ImageFormats;
import image.ImageInterface;

/**
 * This class is a factory for IOFileByFormat objects.
 */
public class IOFileFactory {

  /**
   * This method creates an IOFileByFormat object based on the file extension.
   *
   * @param filename the filename to read the image
   * @throws IOException if the file cannot be read
   */
  public static void encodeAndSaveImage(String filename, ImageInterface image) throws IOException,
          IllegalArgumentException {
    String fileExtension = getFileExtension(filename);
    if (fileExtension == null) {
      throw new IllegalArgumentException("Invalid file");
    }
    switch (fileExtension) {
      case "ppm":
        IOFileByFormat ppmFileAdapter = new PPMFileAdapter();
        ppmFileAdapter.encodeAndSaveImage(filename, image, ImageFormats.PPM);
        break;
      case "png":
      case "jpg":
      case "jpeg":
        IOFileByFormat commonFormatsFileAdapter = new CommonFormatsFileAdapter();
        commonFormatsFileAdapter.encodeAndSaveImage(filename, image, ImageFormats.PNG);
        break;
      default:
        throw new IllegalArgumentException("Invalid file extension");
    }
  }

  /**
   * This method decodes the image from the given filename.
   *
   * @param filename the filename to read the image
   * @return the image read from the file
   * @throws IOException if the file cannot be read
   */
  public static ImageInterface decodeImage(String filename) throws IOException,
          IllegalArgumentException {
    String fileExtension = getFileExtension(filename);
    if (fileExtension == null) {
      throw new IllegalArgumentException("Invalid file");
    }
    switch (fileExtension) {
      case "ppm":
        IOFileByFormat ppmFileAdapter = new PPMFileAdapter();
        return ppmFileAdapter.decodeImage(filename);
      case "png":
      case "jpg":
      case "jpeg":
        IOFileByFormat commonFormatsFileAdapter = new CommonFormatsFileAdapter();
        return commonFormatsFileAdapter.decodeImage(filename);
      default:
        throw new IllegalArgumentException("Invalid file extension");
    }
  }

  public static String getFileExtension(String filePath) {
    int lastDotIndex = filePath.lastIndexOf('.');
    if (lastDotIndex > 0) {
      return filePath.substring(lastDotIndex + 1);
    } else {
      return null;
    }
  }
}
