package logger;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import commonlabels.SupportedUIOperations;
import model.image.ImageInterface;
import model.imageprocessingmodel.ImageProcessorModel;
import model.imageprocessingmodel.ImageProcessorModelInterface;

public class JFrameView extends JFrame implements JViewInterface {
  private JPanel histogramPanel;

  private JPanel currentImagePanel;

  private JPanel operationsPanel;

  private JPanel tasksPanel;

  private JButton executeButton;

  private JCheckBox tickBox;

  private JComboBox<String> operationsDropDown;

  private JButton loadImageButton;

  private JButton saveImageButton;

  public JFrameView(String caption) throws IOException {
    super(caption);
    setSize(1500, 1000);
    setMinimumSize(new Dimension(1200, 800));
    setLocation(20, 20);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    setHorizontalContainerPanel();
    setVerticalContainerPanel();

    pack();
    setVisible(true);
  }

  private void setVerticalContainerPanel() {
    JPanel verticalContainerPanel = new JPanel();
    verticalContainerPanel.setLayout(new BoxLayout(verticalContainerPanel,
            BoxLayout.Y_AXIS));
    addOperationsPanel(verticalContainerPanel);
    addTasksPanel(verticalContainerPanel);
    this.add(verticalContainerPanel, BorderLayout.SOUTH);
  }

  private void addOperationsPanel(JPanel verticalContainerPanel) {
    operationsPanel = new JPanel();
    operationsPanel.setLayout(new FlowLayout());
    operationsPanel.setBackground(Color.getColor("#F9EBD2"));
    operationsPanel.setPreferredSize(new Dimension(100, 200));

    addDropDown(operationsPanel);
    addExecuteButton(verticalContainerPanel);
    addTickBox(operationsPanel);
    verticalContainerPanel.add(operationsPanel);

  }

  private void addExecuteButton(JPanel verticalContainerPanel) {
    JButton executeButton = new JButton("Execute");
    executeButton.setActionCommand("Execute");
    operationsPanel.add(executeButton);
  }

  private void addTickBox(JPanel operationsPanel) {
    tickBox = new JCheckBox("Enable split mode");
    tickBox.setBackground(Color.getColor("#F9EBD2"));
    operationsPanel.add(tickBox);
  }

  private void addDropDown(JPanel operationsPanel) {
    String[] operations = SupportedUIOperations.getSupportedUIOperations();
    operationsDropDown = new JComboBox<>(operations);
    operationsDropDown.setSelectedIndex(0);
    operationsPanel.add(operationsDropDown);
  }

  private void addTasksPanel(JPanel verticalContainerPanel) {
    tasksPanel = new JPanel(new FlowLayout());
    tasksPanel.setBackground(Color.WHITE);
    tasksPanel.setPreferredSize(new Dimension(100, 50));
    verticalContainerPanel.add(tasksPanel);

    loadImageButton = new JButton("Load Image");
    loadImageButton.setActionCommand("Load Image");
    tasksPanel.add(loadImageButton);

    saveImageButton = new JButton("Save Image");
    saveImageButton.setActionCommand("Save Image");
    tasksPanel.add(saveImageButton);
  }

  private void setHorizontalContainerPanel() throws IOException {

    JPanel horizontalContainerPanel = new JPanel();
    horizontalContainerPanel.setLayout(new BorderLayout());

    ImageProcessorModelInterface imageProcessorModel = new ImageProcessorModel();
    setCurrentImagePanel(imageProcessorModel,
            horizontalContainerPanel);
    setHistogramPanel(imageProcessorModel, horizontalContainerPanel);

    this.add(horizontalContainerPanel, BorderLayout.NORTH);

  }

  private void setHistogramPanel(ImageProcessorModelInterface imageProcessorModel,
                                 JPanel horizontalContainerPanel) {
    histogramPanel = new JPanel(new BorderLayout());
    histogramPanel.setPreferredSize(new Dimension(500, 500));
    histogramPanel.setBackground(Color.GRAY);
    imageProcessorModel.histogramImage("loadedImage", "histogramImage");
    JLabel imageLabel = getImageJLabel(imageProcessorModel, "histogramImage");
    histogramPanel.add(imageLabel, BorderLayout.CENTER);
    horizontalContainerPanel.add(histogramPanel, BorderLayout.WEST);
  }

  private JLabel getImageJLabel(ImageProcessorModelInterface imageProcessorModel,
                                String histogramImage) {
    ImageInterface img = imageProcessorModel.getImage(histogramImage);
    BufferedImage bufferedImage = getBufferedImage(img);
    JLabel imageLabel = new JLabel(new ImageIcon(bufferedImage));
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);
    return imageLabel;
  }

  private void setCurrentImagePanel(ImageProcessorModelInterface imageProcessorModel,
                                    JPanel horizontalContainerPanel) throws IOException {
    currentImagePanel = new JPanel(new BorderLayout());
    currentImagePanel.setPreferredSize(new Dimension(800, 500));
    currentImagePanel.setBackground(Color.DARK_GRAY);
    imageProcessorModel.loadImage("res/images/open-source-levels.png",
            "loadedImage");
    JLabel imageLabel = getImageJLabel(imageProcessorModel, "loadedImage");
    currentImagePanel.add(imageLabel);
    horizontalContainerPanel.add(getjScrollPane(), BorderLayout.CENTER);
  }

  private JScrollPane getjScrollPane() {
    JScrollPane scrollPane = new JScrollPane(currentImagePanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    return scrollPane;
  }

  private BufferedImage getBufferedImage(ImageInterface img) {
    BufferedImage bufferedImage = new BufferedImage(img.getWidth(), img.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    List<int[][]> channels = img.getChannel();
    int[][] redChannel = channels.get(0);
    int[][] greenChannel = channels.get(1);
    int[][] blueChannel = channels.get(2);

    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        int red = redChannel[i][j];
        int green = greenChannel[i][j];
        int blue = blueChannel[i][j];
        int rgb = (red << 16) | (green << 8) | blue;
        bufferedImage.setRGB(j, i, rgb);
      }
    }
    return bufferedImage;
  }

  @Override
  public void addFeatures(ImageProcessorModelInterface features) {
    /*JButton loadImageButton = (JButton) tasksPanel.getComponent(0);
    loadImageButton.addActionListener(evt -> features.loadImage();)
    JButton saveImageButton = (JButton) tasksPanel.getComponent(1);
    saveImageButton.addActionListener(evt -> features.saveImage("res/images/open-source-levels.png",
            "savedImage"));
    JButton executeButton = (JButton) operationsPanel.getComponent(2);
    executeButton.addActionListener(evt -> features.executeOperation("loadedImage",
            "processedImage", "Split", 0));*/
  }
}
