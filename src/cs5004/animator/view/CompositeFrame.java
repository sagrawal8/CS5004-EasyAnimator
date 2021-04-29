package cs5004.animator.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;

import cs5004.animator.model.IShape;

/**
 * This class represents the frame that is part of the CompositeView class. It contains buttons to
 * start, pause, restart, loop, resume, speed up and slow down the animation. It also adds a menu
 * bar, and in that menu bar, we have the option to save the current animation as a text file or svg
 * file, load a new animation, exit the program, add a new shape and remove a shape.
 */
public class CompositeFrame extends JFrame {
  private JButton start;
  private JButton pause;
  private JButton resume;
  private JButton restart;
  private JButton loop;
  private JButton speedUp;
  private JButton slowDown;
  private SwingPanel animationPanel;
  private JPanel addShapePanel;
  private JTextField textSpeed;
  private JMenuItem loadNew;
  private JMenuItem addShape;
  private JMenuItem removeShape;
  private JMenuItem text;
  private JMenuItem svg;

  private JTextField addName;
  private JTextField addType;
  private JTextField addX;
  private JTextField addY;
  private JTextField addHeight;
  private JTextField addWidth;
  private JTextField addRed;
  private JTextField addGreen;
  private JTextField addBlue;
  private JTextField addAppear;
  private JTextField addDisappear;

  /**
   * This method constructs a new composite frame. It shows the GUI that we are trying to create. It
   * shows the buttons, the menu bar and the animation we are trying to display.
   *
   * @param x the frame's x-position.
   * @param y the frame's y-position.
   * @param width the width of the frame.
   * @param height the height of the frame.
   * @param listOfMutatedShapes the original states of all the shapes at the beginning of the
   *     animation.
   */
  public CompositeFrame(int x, int y, int width, int height, List<IShape> listOfMutatedShapes) {
    super("Our Grand Animation");
    // this ensures that the Java Swing window closes when we click on the red X at top left of
    // window
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    BorderLayout newBorder = new BorderLayout();
    this.setLayout(newBorder);
    this.setLocation(x, y);
    // this sets the size of the frame to 1000 x 1000
    this.setSize(width, height);

    // we are initializing these buttons here
    start = new JButton("Start");
    pause = new JButton("Pause");
    resume = new JButton("Resume");
    restart = new JButton("Restart");
    loop = new JButton("Loop: Off");
    speedUp = new JButton("Speed Up");
    slowDown = new JButton("Slow Down");
    textSpeed = new JTextField();
    textSpeed.setEditable(false);
    // this is a JPanel that takes care of the animation of shapes.
    this.animationPanel = new SwingPanel(listOfMutatedShapes);
    this.add(this.animationPanel, BorderLayout.CENTER);

    // Declaring a vertical scroll bar.
    JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL, 0, 100, -200, 200);

    /**
     * This class is an adjustment listener for the vertical scroll bar. It allows us to offset the
     * panel by a certain distance, based on how much we scroll in the vertical direction.
     */
    class VerticalAdjustmentListener implements AdjustmentListener {
      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        setPanelOffSetY(e.getValue());
      }
    }

    // Declaring the horizontal scroll bar.
    JScrollBar horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 100, -200, 200);

    /**
     * This class is an adjustment listener for the horizontal scroll bar. It allows us to offset
     * the panel by a certain distance, based on how much we scroll in the horizontal direction.
     */
    class HorizontalAdjustmentListener implements AdjustmentListener {
      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        setPanelOffSetX(e.getValue());
      }
    }

    // Here we are adding an adjustment Listener to the vertical and horizontal scroll
    // bars.
    verticalScrollBar.addAdjustmentListener(new VerticalAdjustmentListener());
    horizontalScrollBar.addAdjustmentListener(new HorizontalAdjustmentListener());

    // Adding the vertical and horizontal scroll bars to the frame.
    this.getContentPane().add(verticalScrollBar, BorderLayout.EAST);
    this.getContentPane().add(horizontalScrollBar, BorderLayout.SOUTH);

    // Add current speed to panel
    JPanel speedPanel = new JPanel();
    speedPanel.add(textSpeed);
    speedPanel.setVisible(true);

    // this panel is responsible for holding all the buttons
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.add(start);
    buttonsPanel.add(pause);
    buttonsPanel.add(resume);
    buttonsPanel.add(restart);
    buttonsPanel.add(loop);
    buttonsPanel.add(speedUp);
    buttonsPanel.add(slowDown);

    // Panel created that handles the option to add shapes to the animation
    JLabel shapeName = new JLabel("Shape Name:");
    JLabel shapeType = new JLabel("Shape Type:");
    JLabel shapeX = new JLabel("Shape x-coordinate:");
    JLabel shapeY = new JLabel("Shape y-coordinate:");
    JLabel shapeHeight = new JLabel("Shape Height:");
    JLabel shapeWidth = new JLabel("Shape Width:");
    JLabel shapeRed = new JLabel("Shape Color Red:");
    JLabel shapeGreen = new JLabel("Shape Color Green:");
    JLabel shapeBlue = new JLabel("Shape Color Blue:");
    JLabel shapeAppear = new JLabel("Shape Time Appear:");
    JLabel shapeDisappear = new JLabel("Shape Time Disappear:");

    // Creating new text fields so that if the user wants to add a shape,
    // they can write the required information of the shape.
    addShapePanel = new JPanel();
    addShapePanel.setLayout(new GridLayout(0, 2));
    addName = new JTextField(15);
    addType = new JTextField(15);
    addX = new JTextField(15);
    addY = new JTextField(15);
    addHeight = new JTextField(15);
    addWidth = new JTextField(15);
    addRed = new JTextField(15);
    addGreen = new JTextField(15);
    addBlue = new JTextField(15);
    addAppear = new JTextField(15);
    addDisappear = new JTextField(15);

    addShapePanel.add(shapeName);
    this.addShapePanel.add(addName);
    addShapePanel.add(shapeType);
    this.addShapePanel.add(addType);
    addShapePanel.add(shapeX);
    this.addShapePanel.add(addX);
    addShapePanel.add(shapeY);
    this.addShapePanel.add(addY);
    addShapePanel.add(shapeHeight);
    this.addShapePanel.add(addHeight);
    addShapePanel.add(shapeWidth);
    this.addShapePanel.add(addWidth);
    addShapePanel.add(shapeRed);
    this.addShapePanel.add(addRed);
    addShapePanel.add(shapeGreen);
    this.addShapePanel.add(addGreen);
    addShapePanel.add(shapeBlue);
    this.addShapePanel.add(addBlue);
    addShapePanel.add(shapeAppear);
    this.addShapePanel.add(addAppear);
    addShapePanel.add(shapeDisappear);
    this.addShapePanel.add(addDisappear);

    // initializing a menu bar
    JMenuBar menuBar = new JMenuBar();

    // adding the File option
    JMenu fileOption = new JMenu("File");
    fileOption.setMnemonic(KeyEvent.VK_A);
    fileOption
        .getAccessibleContext()
        .setAccessibleDescription("File Options: Save, Exit, Load new File");

    // submenu  "Save" in File Option
    fileOption.addSeparator();
    JMenu subMenuSave = new JMenu("Save File");
    subMenuSave.setMnemonic(KeyEvent.VK_S);

    // menu items for save file
    text = new JMenuItem("Text File");
    subMenuSave.add(text);

    svg = new JMenuItem("SVG File");
    subMenuSave.add(svg);

    // submenuItem  "Exit" in File Option
    JMenuItem exitItem = new JMenuItem(new AbstractAction("Exit Program") {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(NORMAL);
      }
    });

    // submenuItem  "Load New" in File Option
    loadNew = new JMenuItem("Load New File");
    loadNew.setMnemonic(KeyEvent.VK_S);

    // adding all the submenu items of File to the File option
    fileOption.add(subMenuSave);
    fileOption.add(loadNew);
    fileOption.add(exitItem);

    // adding the option to add shape and initializing its menu item
    JMenu addShapeOption = new JMenu("Add");
    addShapeOption.addSeparator();
    addShapeOption.setMnemonic(KeyEvent.VK_A);
    addShape = new JMenuItem("Add-Shape");
    addShape.setMnemonic(KeyEvent.VK_A);
    addShapeOption.add(addShape);

    // adding the option to remove shape and initializing its menu item
    JMenu removeShapeOption = new JMenu("Remove");
    removeShapeOption.setMnemonic(KeyEvent.VK_A);
    removeShapeOption.addSeparator();
    removeShape = new JMenuItem("Remove-Shape");
    removeShape.setMnemonic(KeyEvent.VK_A);
    removeShapeOption.add(removeShape);

    // Adding all the options to the menu bar and setting some space between them for formatting
    menuBar.add(fileOption);
    menuBar.add(Box.createHorizontalStrut(10));
    menuBar.add(addShapeOption);
    menuBar.add(Box.createHorizontalStrut(10));
    menuBar.add(removeShapeOption);

    // adding the menu bar to the frame
    this.setJMenuBar(menuBar);

    // Adding a new panel and packaging in the buttons panel and the speed panel
    // into that.
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
    topPanel.add(buttonsPanel);
    topPanel.add(speedPanel);
    this.getContentPane().add(topPanel, BorderLayout.NORTH);

    pack();
    this.setSize(width, height);
    this.setLocation(x, y);
    this.animationPanel.setVisible(true);
    buttonsPanel.setVisible(true);
    this.setVisible(true);
  }

  /**
   * A getter for the start button.
   *
   * @return the start button.
   */
  public JButton getStartButton() {
    return this.start;
  }

  /**
   * A getter for the pause button.
   *
   * @return the pause button.
   */
  public JButton getPauseButton() {
    return this.pause;
  }

  /**
   * A getter for the resume button.
   *
   * @return the resume button.
   */
  public JButton getResumeButton() {
    return this.resume;
  }

  /**
   * A getter for the restart button.
   *
   * @return the restart button.
   */
  public JButton getRestartButton() {
    return this.restart;
  }

  /**
   * A getter for the loop button.
   *
   * @return the loop button.
   */
  public JButton getLoopButton() {
    return this.loop;
  }

  /**
   * A getter for the "speed up" button.
   *
   * @return the speed-up button.
   */
  public JButton getSpeedUpButton() {
    return this.speedUp;
  }

  /**
   * A getter for the slow-down button.
   *
   * @return the slow-down button.
   */
  public JButton getSlowDownButton() {
    return this.slowDown;
  }

  /**
   * A getter for the JTextField that shows the current speed of the animation.
   *
   * @return the speed JTextField.
   */
  public JTextField getTextSpeed() {
    return textSpeed;
  }

  /**
   * A getter for the menu button that loads a new file.
   *
   * @return the menu button that loads in a new file.
   */
  public JMenuItem getNewLoadFile() {
    return this.loadNew;
  }

  /**
   * A getter for the panel that adds a shape.
   *
   * @return the panel that adds a new shape.
   */
  public JPanel getAddShapePanel() {
    return this.addShapePanel;
  }

  /**
   * A getter for the menu button that adds a shape.
   *
   * @return the menu button that adds a shape.
   */
  public JMenuItem getAddShapeOption() {
    return this.addShape;
  }

  /**
   * A getter for the menu button that removes a shape.
   *
   * @return the menu button that removes a shape.
   */
  public JMenuItem getRemoveShape() {
    return removeShape;
  }

  /**
   * A getter for the menu button that saves the current animation as a text file.
   *
   * @return the button that saves the current animation as a text file.
   */
  public JMenuItem getSaveText() {
    return text;
  }

  /**
   * A getter for the menu button that saves the current animation as a svg file.
   *
   * @return the button that saves the current animation as a svg file.
   */
  public JMenuItem getSaveSvg() {
    return svg;
  }

  /**
   * A getter for the addShape option that gets the name of the new shape.
   *
   * @return the JTextField that has the name of the new shape.
   */
  public String getShapeName() {
    return addName.getText();
  }

  /**
   * A getter for the addShape option that gets the type of the new shape.
   *
   * @return the JTextField that has the type of the new shape.
   */
  public String getShapeType() {
    return addType.getText();
  }

  /**
   * A getter for the addShape option that gets the x-coordinate of the new shape.
   *
   * @return the JTextField that has the x-coordinate of the new shape.
   */
  public double getShapeX() {
    return Double.parseDouble(addX.getText());
  }

  /**
   * A getter for the addShape option that gets the y-coordinate of the new shape.
   *
   * @return the JTextField that has the y-coordinate of the new shape.
   */
  public double getShapeY() {
    return Double.parseDouble(addY.getText());
  }

  /**
   * A getter for the addShape option that gets the height of the new shape.
   *
   * @return the JTextField that has the height of the new shape.
   */
  public double getShapeHeight() {
    return Double.parseDouble(addHeight.getText());
  }

  /**
   * A getter for the addShape option that gets the width of the new shape.
   *
   * @return the JTextField that has the width of the new shape.
   */
  public double getShapeWidth() {
    return Double.parseDouble(addWidth.getText());
  }

  /**
   * A getter for the addShape option that gets the red color of the new shape.
   *
   * @return the JTextField that has the red color of the new shape.
   */
  public int getShapeRed() {
    return Integer.parseInt(addRed.getText());
  }

  /**
   * A getter for the addShape option that gets the green color of the new shape.
   *
   * @return the JTextField that has the green color of the new shape.
   */
  public int getShapeGreen() {
    return Integer.parseInt(addGreen.getText());
  }

  /**
   * A getter for the addShape option that gets the blue color of the new shape.
   *
   * @return the JTextField that has the blue color of the new shape.
   */
  public int getShapeBlue() {
    return Integer.parseInt(addBlue.getText());
  }

  /**
   * A getter for the addShape option that gets the time the new shape appears.
   *
   * @return the JTextField that has the time that the new shape appears.
   */
  public double getShapeAppear() {
    return Double.parseDouble(addAppear.getText());
  }

  /**
   * A getter for the addShape option that gets the time the new shape disappears.
   *
   * @return the JTextField that has the time that the new shape disappears.
   */
  public double getShapeDisappear() {
    return Double.parseDouble(addDisappear.getText());
  }

  /**
   * This method is a getter for the panel.
   *
   * @return The panel in the current frame.
   */
  public SwingPanel getPanel() {
    return this.animationPanel;
  }

  /**
   * This method updates the current view by setting the panel to the new list of mutated shapes,
   * and then calls the repaint method.
   *
   * @param listOfMutatedShapes The list of mutated shapes that was returned by the
   *     getShapesAtTimeStamp, method in the model.
   */
  public void currentView(List<IShape> listOfMutatedShapes) {
    this.revalidate();
    this.animationPanel.setModel((listOfMutatedShapes));
    this.repaint();
  }

  /**
   * This method sets the panel offset for the y direction.
   *
   * @param value The amount by which we want to offset the panel in the y direction.
   */
  private void setPanelOffSetY(int value) {
    this.animationPanel.setPanelOffSetY(value);
  }

  /**
   * This method sets the panel offset for the x direction.
   *
   * @param value The amount by which we want the offset the panel in the x direction.
   */
  private void setPanelOffSetX(int value) {
    this.animationPanel.setPanelOffSetX(value);
  }
}
