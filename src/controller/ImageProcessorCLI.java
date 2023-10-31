package controller;

import image.ImageInterface;
import imageio.IOFileFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ImageProcessorCLI {

  private final Map<String, ImageInterface> images;

  public ImageProcessorCLI() {
    images = new HashMap<>();
  }

  public void processCommands() {
    Scanner scanner = new Scanner(System.in);
    String input;

    label:
    while (true) {
      try {
        System.out.print("Enter a command: ");
        input = scanner.nextLine();
        String[] parts = input.split(" ");

        if (parts.length == 0) {
          throw new IllegalArgumentException("Invalid command. Try again.");
        }
        String command = parts[0];
        switch (command) {
          case "load":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid load command. Usage: load <image-name> <image-path>");
            } else {
              images.put(parts[2], IOFileFactory.decodeImage(parts[1]));
            }
            break;
          case "save":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid save command. Usage: save <image-name> <image-path>");
            } else {
              if (images.containsKey(parts[2])) {
                IOFileFactory.encodeAndSaveImage(parts[2], images.get(parts[2]));
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
              ImageInterface newImage = null;
              images.put(parts[3], newImage);
            }
            break;
          case "red-component":
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
              ImageInterface newImage = null;
              images.put(parts[2], newImage);
            }
            break;
          case "green-component":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid green-component command. Usage: green-component <image-name> <dest-image-name>");
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
              ImageInterface newImage = null;
              images.put(parts[2], newImage);
            }
            break;
          case "blue-component":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid blue-component command. Usage: blue-component <image-name> <dest-image-name>");
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
              ImageInterface newImage = null;
              images.put(parts[2], newImage);
            }
            break;
          case "value-component":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid value-component command. Usage: value-component <image-name> <dest-image-name>");
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
              ImageInterface newImage = null;
              images.put(parts[2], newImage);
            }
            break;
          case "luma-component":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid luma-component command. Usage: luma-component <image-name> <dest-image-name>");
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
              ImageInterface newImage = null;
              images.put(parts[2], newImage);
            }
            break;
          case "intensity-component":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid intensity-component command. Usage: intensity-component <image-name> <dest-image-name>");
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
              ImageInterface newImage = null;
              images.put(parts[2], newImage);
            }
            break;
          case "horizontal-flip":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid horizontal-flip command. Usage: horizontal-flip <image-name> <dest-image-name>");
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
              ImageInterface newImage = null;
              images.put(parts[2], newImage);
            }
            break;
          case "vertical-flip":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid vertical-flip command. Usage: vertical-flip <image-name> <dest-image-name>");
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
              ImageInterface newImage = null;
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
              ImageInterface newImage = null;
              images.put(parts[2], newImage);
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
            }
            break;
          case "blur":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid blur command. Usage: blur <image-name> <dest-image-name>");
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
              ImageInterface newImage = null;
              images.put(parts[2], newImage);
            }
            break;
          case "sharpen":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid sharpen command. Usage: sharpen <image-name> <dest-image-name>");
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
              ImageInterface newImage = null;
              images.put(parts[2], newImage);
            }
            break;
          case "sepia":
            if (parts.length != 3) {
              throw new IllegalArgumentException(
                  "Invalid sepia command. Usage: sepia <image-name> <dest-image-name>");
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
              ImageInterface newImage = null;
              images.put(parts[2], newImage);
            }
            break;
          case "exit":
            break label;
          default:
            throw new IllegalArgumentException(
                "Unknown command. Try again or type 'exit' to quit.");
        }
      } catch (Exception e) {
        // call view with exception;
      }
    }
    scanner.close();
  }


}
