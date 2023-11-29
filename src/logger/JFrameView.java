package logger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.FlowLayout;
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

  private JPanel levelAdjustPanel;

  private JButton executeButton;

  private JCheckBox tickBox;

  private JComboBox<String> operationsDropDown;

  private JButton loadImageButton;

  private JButton saveImageButton;

  private JTextField blackLevelTextBox;

  private JTextField whiteLevelTextBox;

  private JTextField midLevelTextBox;
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
    verticalContainerPanel.add(operationsPanel);

    levelAdjustPanel = new JPanel();
    levelAdjustPanel.setLayout(new FlowLayout());
    levelAdjustPanel.setBackground(Color.getColor("#F9EBD2"));
    levelAdjustPanel.setPreferredSize(new Dimension(100, 30));
    addBMWLevelAdjustTextBox();
    verticalContainerPanel.add(levelAdjustPanel);
  }

  private void addExecuteButton(JPanel verticalContainerPanel) {
    executeButton = new JButton("Execute");
    executeButton.setActionCommand("Execute");
    executeButton.setEnabled(false);
    operationsPanel.add(executeButton);
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
    tasksPanel.setPreferredSize(new Dimension(100, 20));
    verticalContainerPanel.add(tasksPanel);

    loadImageButton = new JButton("Load Image");
    loadImageButton.setActionCommand("Load Image");
    tasksPanel.add(loadImageButton);

    saveImageButton = new JButton("Save Image");
    saveImageButton.setActionCommand("Save Image");
    saveImageButton.setEnabled(false);
    tasksPanel.add(saveImageButton);
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
        try {
          String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
          features.loadImage(filePath);
          features.loadHistogram();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
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
      features.executeOperation(Objects.requireNonNull(operationsDropDown
              .getSelectedItem()).toString());
    });
  }

  private List getInputFromBMWLevelAdjustTextBox() {
    ArrayList<String> bmwInput = new ArrayList<>();
    bmwInput.add(blackLevelTextBox.getText());
    bmwInput.add(whiteLevelTextBox.getText());
    bmwInput.add(midLevelTextBox.getText());
    return bmwInput;
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
    levelAdjustPanel.add(whiteLevelLabel);
    levelAdjustPanel.add(whiteLevelTextBox);
    levelAdjustPanel.add(midLevelLabel);
    levelAdjustPanel.add(midLevelTextBox);

    levelAdjustPanel.setVisible(false);

    getRootPane().revalidate();
    getRootPane().repaint();
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

  @Override
  public List getAdjustLevelInputs() {
    levelAdjustPanel.setVisible(true);
    getRootPane().revalidate();
    getRootPane().repaint();
    return getInputFromBMWLevelAdjustTextBox();
  }

  @Override
  public void displayErrorPopup(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

}
