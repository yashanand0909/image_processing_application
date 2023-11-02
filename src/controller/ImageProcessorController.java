package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import logger.ViewLogger;
import model.ImageProcessingModel.ImageProcessorModelInterface;

/**
 * This class represents an image processor controller.
 */
public class ImageProcessorController implements ControllerInterface {

  final Readable in;
  final Appendable out;
  final ViewLogger viewLogger;
  final ImageProcessorModelInterface imageProcessorModel;

  /**
   * This constructor creates an object of the image processor controller.
   *
   * @param viewLogger          the view logger
   * @param imageProcessorModel the image processor model
   * @param in                  the readable input
   * @param out                 the appendable output
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
   * This method starts the image processing controller.
   */
  public void startImageProcessingController() {
    try {
      handleCommands();
    } catch (Exception e) {
      viewLogger.logException(e);
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
      try {
        this.out.append("Enter a command: \n");
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
        viewLogger.LogString("Command ran successfully \n");
      } catch (Exception e) {
        viewLogger.logException(e);
      }
    }
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
            imageProcessorModel.processCommands(parts);
          }
        }
      } else {
        throw new IllegalArgumentException("Invalid command : " +
                "File does not exist with name " + commandInput[1]);
      }
    }
  }
}
