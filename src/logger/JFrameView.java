package logger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import commonlabels.SupportedUIOperations;
import controller.UIFeatures;
import model.image.ImageInterface;

public class JFrameView extends JFrame implements JViewInterface {
  private JPanel histogramPanel;

  private JPanel currentImagePanel;

  private JPanel operationsPanel;

  private JButton executeButton;

  private JPanel levelAdjustPanel;

  private JPanel compressImagePanel;

  private JPanel splitOperationPanel;

  private JButton applyButton;

  private JCheckBox tickBox;

  private JComboBox<String> operationsDropDown;

  private JButton loadImageButton;

  private JButton saveImageButton;

  private JTextField blackLevelTextBox;

  private JTextField whiteLevelTextBox;

  private JTextField midLevelTextBox;

  private JTextField compressImageTextBox;

  private JTextField splitOperationTextBox;


  public JFrameView(String caption) throws IOException {
    super(caption);
    setSize(1500, 1000);
    setMinimumSize(new Dimension(1200, 800));
    setLocation(20, 5);
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
    verticalContainerPanel.setPreferredSize(new Dimension(200, 200));
    addOperationsPanel(verticalContainerPanel);
    addTasksPanel(verticalContainerPanel);
    this.add(verticalContainerPanel, BorderLayout.SOUTH);
  }

  private void addOperationsPanel(JPanel verticalContainerPanel) {
    operationsPanel = new JPanel();
    operationsPanel.setLayout(new FlowLayout());
    operationsPanel.setBackground(Color.getColor("#F9EBD2"));
    operationsPanel.setPreferredSize(new Dimension(100, 90));
    addDropDown(operationsPanel);
    addExecuteButton(verticalContainerPanel);
    addTickBox(operationsPanel);
    addApplyButton(verticalContainerPanel);
    verticalContainerPanel.add(operationsPanel);

    compressImagePanel = new JPanel();
    compressImagePanel.setLayout(new FlowLayout());
    compressImagePanel.setBackground(Color.getColor("#F9EBD2"));
    compressImagePanel.setPreferredSize(new Dimension(100, 30));
    addCompressImageTextBox();

    splitOperationPanel = new JPanel();
    splitOperationPanel.setLayout(new FlowLayout());
    splitOperationPanel.setBackground(Color.getColor("#F9EBD2"));
    splitOperationPanel.setPreferredSize(new Dimension(100, 30));
    addSplitOperationTextBox();

    levelAdjustPanel = new JPanel();
    levelAdjustPanel.setLayout(new FlowLayout());
    levelAdjustPanel.setBackground(Color.getColor("#F9EBD2"));
    levelAdjustPanel.setPreferredSize(new Dimension(100, 30));
    addBMWLevelAdjustTextBox();
    verticalContainerPanel.add(levelAdjustPanel);
    verticalContainerPanel.add(compressImagePanel);
    verticalContainerPanel.add(splitOperationPanel);
  }

  private void addExecuteButton(JPanel verticalContainerPanel) {
    executeButton = new JButton("Execute");
    executeButton.setActionCommand("Execute");
    executeButton.setEnabled(false);
    operationsPanel.add(executeButton);
  }

  private void addApplyButton(JPanel verticalContainerPanel) {
    applyButton = new JButton("Apply");
    applyButton.setActionCommand("Apply");
    applyButton.setEnabled(false);
    operationsPanel.add(applyButton);
  }

  private void addTickBox(JPanel operationsPanel) {
    tickBox = new JCheckBox("Enable split mode");
    tickBox.setEnabled(false);
    tickBox.setBackground(Color.getColor("#F9EBD2"));
    operationsPanel.add(tickBox);
  }

  private void addDropDown(JPanel operationsPanel) {
    String[] operations = SupportedUIOperations.getSupportedUIOperations();
    operationsDropDown = new JComboBox<>(operations);
    operationsDropDown.setSelectedIndex(0);
    operationsDropDown.setEnabled(false);
    operationsPanel.add(operationsDropDown);
  }

  private void addTasksPanel(JPanel verticalContainerPanel) {
    JPanel tasksPanel = new JPanel(new FlowLayout());
    tasksPanel.setBackground(Color.WHITE);
    tasksPanel.setPreferredSize(new Dimension(100, 50));
    verticalContainerPanel.add(tasksPanel);

    loadImageButton = new JButton("Load Image");
    loadImageButton.setActionCommand("Load Image");
    tasksPanel.add(loadImageButton);

    saveImageButton = new JButton("Save Image");
    saveImageButton.setActionCommand("Save Image");
    saveImageButton.setEnabled(false);
    tasksPanel.add(saveImageButton);
  }

  private void addCompressImageTextBox() {
    if (compressImagePanel.getComponentCount() > 0) {
      return;
    }
    JLabel compressImageLabel = new JLabel("Compress Image");
    compressImageTextBox = new JTextField(10);
    compressImagePanel.add(compressImageLabel);
    compressImagePanel.add(compressImageTextBox);
    compressImagePanel.setVisible(false);
    operationsPanel.add(compressImagePanel);

    compressImagePanel.setVisible(false);

    getRootPane().revalidate();
    getRootPane().repaint();
  }

  private void addSplitOperationTextBox() {
    if (splitOperationPanel.getComponentCount() > 0) {
      return;
    }
    JLabel splitOperationLabel = new JLabel("Split Operation");
    splitOperationTextBox = new JTextField(10);
    splitOperationPanel.add(splitOperationLabel);
    splitOperationPanel.add(splitOperationTextBox);
    splitOperationPanel.setVisible(false);
    operationsPanel.add(splitOperationPanel);

    splitOperationPanel.setVisible(false);

    getRootPane().revalidate();
    getRootPane().repaint();
  }

  private void setHorizontalContainerPanel() throws IOException {

    JPanel horizontalContainerPanel = new JPanel();
    horizontalContainerPanel.setLayout(new BorderLayout());

    setCurrentImagePanel(horizontalContainerPanel);
    setHistogramPanel(horizontalContainerPanel);

    this.add(horizontalContainerPanel, BorderLayout.NORTH);
  }

  private void setHistogramPanel(JPanel horizontalContainerPanel) {
    histogramPanel = new JPanel(new BorderLayout());
    histogramPanel.setPreferredSize(new Dimension(500, 500));
    histogramPanel.setBackground(Color.GRAY);
    horizontalContainerPanel.add(histogramPanel, BorderLayout.WEST);
  }

  private JScrollPane getImageJLabel(BufferedImage bufferedImage) {
    JLabel imageLabel = new JLabel(new ImageIcon(bufferedImage));
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);
    return new JScrollPane(imageLabel);
  }

  private void setCurrentImagePanel(JPanel horizontalContainerPanel) throws IOException {
    currentImagePanel = new JPanel(new BorderLayout());
    currentImagePanel.setPreferredSize(new Dimension(800, 500));
    currentImagePanel.setBackground(Color.DARK_GRAY);
    horizontalContainerPanel.add(currentImagePanel, BorderLayout.CENTER);
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
  public void addFeatures(UIFeatures features) {
    loadImageButton.addActionListener(evt -> {
      JFileChooser jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
      jFileChooser.setFileFilter(new FileFilter() {
        public String getDescription() {
          return "Images file selected (*jpg), (*jpeg), (*png), (*ppm)";
        }

        public boolean accept(File file) {
          if (file.isDirectory()) {
            return true;
          } else {
            String filename = file.getName().toLowerCase();
            return filename.endsWith(".jpg")
                    || filename.endsWith(".jpeg")
                    || filename.endsWith(".png")
                    || filename.endsWith(".ppm");
          }
        }
      });
      int r = jFileChooser.showOpenDialog(null);
      jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      if (r == JFileChooser.APPROVE_OPTION) {
        String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
        String fileName = getFileNameFromPath(filePath);
        features.loadImage(filePath,
                  fileName);
      } else {
        System.out.println("No file selected");
      }
    });

    saveImageButton.addActionListener(evt -> {
      JFileChooser fileChooser = new JFileChooser();
      int userChoice = fileChooser.showSaveDialog(this);
      if (userChoice == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();
        try {
          features.saveImage(fileToSave.getAbsolutePath());
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    });

    operationsDropDown.addActionListener(evt -> {
      compressImagePanel.setVisible(false);
      levelAdjustPanel.setVisible(false);
      if(Objects.equals(operationsDropDown.getSelectedItem(), "Level Adjust")){
        levelAdjustPanel.setVisible(true);
      }
      else if (Objects.equals(operationsDropDown.getSelectedItem(), "Compress")){
        compressImagePanel.setVisible(true);
      }
    });

    executeButton.addActionListener(evt -> {
      String operationName = (String) operationsDropDown.getSelectedItem();
      String operator = null;
      assert operationName != null;
      if (operationName.equals(SupportedUIOperations.LEVELADJUST.toString())){
        operator = blackLevelTextBox.getText() + " " + midLevelTextBox.getText() + " " +whiteLevelTextBox.getText();
      }
      if (operationName.equals(SupportedUIOperations.COMPRESSION.toString())){
        operator = compressImageTextBox.getText();
      }
      if (features != null) {
        features.executeOperation( operationName,operator);
        tickBox.setSelected(false);
      }
    });

    applyButton.addActionListener(evt -> {
      String operationName = (String) operationsDropDown.getSelectedItem();
      String splitOperator = splitOperationTextBox.getText();
      String operator = "";
      assert operationName != null;
      if (operationName.equals(SupportedUIOperations.LEVELADJUST.toString())){
        operator = blackLevelTextBox.getText() + " " + midLevelTextBox.getText() + " " +whiteLevelTextBox.getText();
      }
      if (operationName.equals(SupportedUIOperations.COMPRESSION.toString())){
        operator = compressImageTextBox.getText();
      }
      if (features != null) {
        features.executeOperationWithSplit(operationName,  operator + (operator.length()>0?" "+splitOperator:splitOperator));
      }
    });

    tickBox.addItemListener(e -> {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        applyButton.setEnabled(true);
        splitOperationPanel.setVisible(true);
      } else {
        applyButton.setEnabled(false);
        features.undoSplit();
        splitOperationPanel.setVisible(false);
      }
    });

  }

  private void addBMWLevelAdjustTextBox() {
    if (levelAdjustPanel.getComponentCount() > 0) {
      return;
    }
    JLabel blackLevelLabel = new JLabel("Black Level");
    blackLevelTextBox = new JTextField(10);
    JLabel whiteLevelLabel = new JLabel("White Level");
    whiteLevelTextBox = new JTextField(10);
    JLabel midLevelLabel = new JLabel("Mid Level");
    midLevelTextBox = new JTextField(10);
    levelAdjustPanel.add(blackLevelLabel);
    levelAdjustPanel.add(blackLevelTextBox);
    levelAdjustPanel.add(midLevelLabel);
    levelAdjustPanel.add(midLevelTextBox);
    levelAdjustPanel.add(whiteLevelLabel);
    levelAdjustPanel.add(whiteLevelTextBox);

    levelAdjustPanel.setVisible(false);

    getRootPane().revalidate();
    getRootPane().repaint();
  }

  private String getFileNameFromPath(String filePath) {
    File file = new File(filePath);
    return file.getName();
  }

  @Override
  public void setCurrentImage(ImageInterface img) {
    BufferedImage bufferedImage = getBufferedImage(img);
    currentImagePanel.removeAll();
    currentImagePanel.add(getImageJLabel(bufferedImage));
    currentImagePanel.revalidate();
    currentImagePanel.repaint();
  }

  @Override
  public void setHistogramImage(ImageInterface histogramImage) {
    BufferedImage bufferedImage = getBufferedImage(histogramImage);
    histogramPanel.removeAll();
    histogramPanel.add(getImageJLabel(bufferedImage));
    histogramPanel.revalidate();
    histogramPanel.repaint();
  }

  @Override
  public void enableOperations() {
    operationsDropDown.setEnabled(true);
    tickBox.setEnabled(true);
    executeButton.setEnabled(true);
    saveImageButton.setEnabled(true);
  }

//  private void enableAdjustLevelInputs() {
//    levelAdjustPanel.setVisible(true);
//    getRootPane().revalidate();
//    getRootPane().repaint();
//  }

  @Override
  public void displayErrorPopup(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

}