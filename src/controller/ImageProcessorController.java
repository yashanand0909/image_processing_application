package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import logger.ViewLogger;
import model.imageprocessingmodel.ImageProcessorModelInterface;

/**
 * This class represents an image processing controller that handles user commands.
 */
public class ImageProcessorController implements ControllerInterface {

  private final Readable in;
  private final Appendable out;
  private final ViewLogger viewLogger;
  private final ImageProcessorModelInterface imageProcessorModel;

  /**
   * Constructs an ImageProcessorController with the specified dependencies.
   *
   * @param viewLogger          the logger for displaying messages
   * @param imageProcessorModel the model for processing image commands
   * @param in                  the input source (e.g., user input)
   * @param out                 the output destination (e.g., console or file)
   */
  public ImageProcessorController(ViewLogger viewLogger,
                                  ImageProcessorModelInterface imageProcessorModel,
                                  Readable in, Appendable out) {
    this.in = in;
    this.out = out;
    this.viewLogger = viewLogger;
    this.imageProcessorModel = imageProcessorModel;
  }

  /**
   * Starts the image processing controller, allowing users to enter and execute commands.
   */
  public void startImageProcessingController() {
    try {
      handleCommands();
    } catch (Exception e) {
      viewLogger.logException(e);
    }
  }

  /**
   * Handles user commands for image processing.
   *
   * @throws IOException if there is an issue with input or output
   */
  private void handleCommands() throws IOException {
    Scanner scanner = new Scanner(this.in);
    String input;

    while (true) {
      try {
        this.out.append("Enter a command:");
        input = scanner.nextLine();
        String[] parts = input.split(" ");

        if (parts.length == 1 && !parts[0].equals("exit")) {
          throw new IllegalArgumentException("Invalid command. Try again.");
        }
        if (parts[0].equals("exit")) {
          break;
        }
        if (parts[0].equals("run")) {
          handleScriptFile(parts);
        } else {
          imageProcessorModel.processCommands(parts);
        }
        viewLogger.logString("Command ran successfully \n");
      } catch (Exception e) {
        viewLogger.logException(e);
      }
    }
  }

  /**
   * Handles a script file containing image processing commands.
   *
   * @param commandInput the command input
   * @throws IOException if there is an issue with input or output
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
            try {
              String[] parts = line.split(" ");
              if (parts.length == 0) {
                throw new IllegalArgumentException("Invalid command in the script file.");
              }
              imageProcessorModel.processCommands(parts);
            } catch (Exception e) {
              viewLogger.logException(e);
            }
          }
        }
      } else {
        throw new IllegalArgumentException("Invalid command : " +
                "File does not exist with name " + commandInput[1]);
      }
    }
  }
}
