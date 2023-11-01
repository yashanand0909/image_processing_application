package controller;

import commonlabels.ImageOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import logger.ViewLogger;
import model.image.ImageInterface;
import model.imageio.IOFileFactory;
import model.operations.operationfactory.ImageProcessorFactory;

/**
 * This class represents the controller for the image processing application.
 */
public class ImageProcessorController {

  private final Map<String, ImageInterface> images;
  final Readable in;
  final Appendable out;

  /**
   * Constructs an ImageProcessorController object.
   *
   * @param in  the input source
   * @param out the output source
   */
  public ImageProcessorController(Readable in, Appendable out) {
    images = new HashMap<>();
    this.in = in;
    this.out = out;
  }

  /**
   * This method starts the image processing controller.
   */
  public void startImageProcessingController() {
    try {
      handleCommands();
    } catch (Exception e) {
      new ViewLogger(this.out).logException(e);
    }
  }

  /**
   * This method processes the commands.
   *
   * @param parts the command parts
   */
  public void processCommands(String[] parts) {
    try {
      if (parts.length == 0) {
        throw new IllegalArgumentException("Invalid command. Try again.");
      }
      String command = parts[0];
      switch (command) {
        case "load":
          if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Invalid load command. Usage: load <image-path> <image-name>");
          } else {
            images.put(parts[2], IOFileFactory.decodeImage(parts[1]));
          }
          break;
        case "save":
          if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Invalid save command. Usage: save <image-path> <image-name>");
          } else {
            if (images.containsKey(parts[2])) {
              IOFileFactory.encodeAndSaveImage(parts[1], images.get(parts[2]));
            }
          }
          break;
        case "brighten":
          if (parts.length != 4) {
            throw new IllegalArgumentException(
                    "Invalid save command. Usage: save <brightness-factor> <current-image-name> <new-image-name>");
          } else {
            if (!images.containsKey(parts[2])) {
              throw new IllegalArgumentException(
                      "Invalid request : No image exist with the name " + parts[2]);
            }
            if (images.containsKey(parts[3])) {
              throw new IllegalArgumentException(
                      "Invalid request : An Image exist with the name " + parts[3]);
            }
            // Add call to module factory
            List<ImageInterface> imageList = Collections.singletonList(images.get(parts[3]));
            ImageInterface newImage = ImageProcessorFactory.performOperation(imageList,
                    ImageOperations.fromString(parts[0]), parts[1]);
            images.put(parts[3], newImage);
          }
          break;
        case "red-component":
        case "green-component":
        case "blue-component":
        case "value-component":
        case "luma-component":
        case "intensity-component":
        case "horizontal-flip":
        case "vertical-flip":
        case "blur":
        case "sharpen":
        case "greyscale":
        case "sepia":
          if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Invalid green-component command. Usage: red-component <image-name> <dest-image-name>");
          } else {
            if (!images.containsKey(parts[1])) {
              throw new IllegalArgumentException(
                      "Invalid request : No image exist with the name " + parts[1]);
            }
            if (images.containsKey(parts[2])) {
              throw new IllegalArgumentException(
                      "Invalid request : An Image exist with the name " + parts[2]);
            }
            // Add call to module factory
            List<ImageInterface> imageList = Collections.singletonList(images.get(parts[1]));
            ImageInterface newImage = ImageProcessorFactory.performOperation(imageList,
                    ImageOperations.fromString(parts[0]), null);
            images.put(parts[2], newImage);
          }
          break;
        case "rgb-split":
          if (parts.length != 5) {
            throw new IllegalArgumentException(
                    "Invalid rgb-split command. Usage: rgb-split <image-name> <dest-image-name-red> <dest-image-name-green> <dest-image-name-blue>");
          } else {
            if (!images.containsKey(parts[1])) {
              throw new IllegalArgumentException(
                      "Invalid request : No image exist with the name " + parts[1]);
            }
            if (images.containsKey(parts[2])) {
              throw new IllegalArgumentException(
                      "Invalid request : An Image exist with the name " + parts[2]);
            }
            if (images.containsKey(parts[3])) {
              throw new IllegalArgumentException(
                      "Invalid request : An Image exist with the name " + parts[3]);
            }
            if (images.containsKey(parts[4])) {
              throw new IllegalArgumentException(
                      "Invalid request : An Image exist with the name " + parts[4]);
            }
            if (parts[2].equals(parts[3]) || parts[3].equals(parts[4]) || parts[4]
                    .equals(parts[2])) {
              throw new IllegalArgumentException(
                      "Invalid request : Red Blue and Green image name cannot be same");
            }
            // Add call to module factory
            for (int i = 0; i < 3; i++) {
              List<ImageInterface> imageList = Collections.singletonList(images.get(parts[1]));
              ImageInterface newImage = ImageProcessorFactory.performOperation(imageList,
                      ImageOperations.fromString(parts[0]), i);
              images.put(parts[i + 2], newImage);
            }
          }
          break;
        case "rgb-combine":
          if (parts.length != 5) {
            throw new IllegalArgumentException(
                    "Invalid rgb-combine command. Usage: rgb-combine <image-name> <red-image> <green-image> <blue-image>");
          } else {
            if (images.containsKey(parts[1])) {
              throw new IllegalArgumentException(
                      "Invalid request : An Image exist with the name " + parts[1]);
            }
            if (!images.containsKey(parts[2])) {
              throw new IllegalArgumentException(
                      "Invalid request : No image exist with the name " + parts[2]);
            }
            if (!images.containsKey(parts[3])) {
              throw new IllegalArgumentException(
                      "Invalid request : No image exist with the name " + parts[3]);
            }
            if (!images.containsKey(parts[4])) {
              throw new IllegalArgumentException(
                      "Invalid request : No image exist with the name " + parts[4]);
            }
            List<ImageInterface> imageList = new ArrayList<>();
            for (int i = 2; i < 5; i++) {
              imageList.add(images.get(parts[i]));
            }
            ImageInterface newImage = ImageProcessorFactory.performOperation(imageList,
                    ImageOperations.fromString(parts[0]), null);
            images.put(parts[1], newImage);
          }
          break;

        case "run": {
          handleScriptFile(parts);
          break;
        }
        default:
          throw new IllegalArgumentException(
                  "Unknown command. Try again or type 'exit' to quit.");
      }
    } catch (Exception e) {
      new ViewLogger(this.out).logException(e);
    }
  }

  /**
   * This method handles the commands.
   *
   * @throws IOException if the input is invalid
   */
  private void handleCommands() throws IOException {
    Scanner scanner = new Scanner(this.in);
    String input;

    while (true) {
      this.out.append("Enter a command: ");
      input = scanner.nextLine();
      String[] parts = input.split(" ");

      if (parts.length == 0) {
        throw new IllegalArgumentException("Invalid command. Try again.");
      }
      if (parts[0].equals("exit")) {
        break;
      }
      processCommands(parts);
    }
    System.exit(0);
  }

  /**
   * This method handles the script file.
   *
   * @param commandInput the command input
   * @throws IOException if the input is invalid
   */
  private void handleScriptFile(String[] commandInput) throws IOException {
    if (commandInput.length != 2) {
      throw new IllegalArgumentException(
              "Invalid run command. Usage: run <script-file-name>");
    } else {
      File scriptFile = new File(commandInput[1]);

      if (scriptFile.exists() && scriptFile.isFile()) {
        try (BufferedReader reader = new BufferedReader(new FileReader(scriptFile))) {
          String line;
          while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 0) {
              throw new IllegalArgumentException("Invalid command in the script file.");
            }
            processCommands(parts);
          }
        }
      }
    }
  }
}
