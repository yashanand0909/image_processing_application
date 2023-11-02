Image processing application using Command Line Interface

## Getting Started
Simply run the following program to get started:
```
ImageProcessingApplication - Contains the main method which calls the controller and initiates the application
``` Once application is running following commands are supported:
load <file-name> <img-var-name> --- loads the image from the file and assigns it to the variable
save <new-file-name> <img-var-name> --- saves the image to the file
blur <img-var-name> <new-img-var-name> --- blurs the image
sharpen <img-var-name> <new-img-var-name> --- sharpens the image
greyscale <img-var-name> <new-img-var-name> --- converts the image to greyscale
sepia <img-var-name> <new-img-var-name> --- converts the image to sepia
intensity-component <img-var-name> <new-img-var-name> --- color representation to find average intensity for each pixel among all channels
value-component <img-var-name> <new-img-var-name> --- color representation to find max value for each pixel among all channel
luma-component <img-var-name> <new-img-var-name> --- color representation to find weighted average of each pixel among all channels
red-component <img-var-name> <new-img-var-name> --- color representation to find red-component of an image
green-component <img-var-name> <new-img-var-name> --- color representation to find green-component of an image
blue-component <img-var-name> <new-img-var-name> --- color representation to find blue-component of an image
horizontal-flip <img-var-name> <new-img-var-name> --- flips the image horizontally
vertical-flip <img-var-name> <new-img-var-name> --- flips the image vertically
rgb-combine <new-img-var-name> <img-var-name1> <img-var-name2> <img-var-name3> --- combines three images into one
rgb-split <img-var-name> <new-img-var-name1> <new-img-var-name2> <new-img-var-name3> --- splits the image into three images

To load the plug and play script file use the following command:
```run script.txt

Image used for testing is provided in the resources folder and is named as open-source-image-original.png image is open source and can be freely used as per the license.

### Design
MVC design pattern is used to design the application. The model contains the image class which contains the image data and the methods to manipulate the image. The view contains the viewlogger class which is responsible for displaying the success and error messages. The controller contains the controller class which is responsible for taking the input from the user and calling the appropriate methods in the model and view.
