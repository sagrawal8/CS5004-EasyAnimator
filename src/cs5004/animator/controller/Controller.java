package cs5004.animator.controller;

import cs5004.animator.view.ViewFactory;
import java.awt.FileDialog;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.Timer;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

import cs5004.animator.model.EasyAnimatorModel;
import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;

/**
 * This class represents the Controller of the entire application. It takes in a view and a model
 * and based on that view and model and it creates the required output.
 */
public class Controller implements IController {

  private IView view;
  private EasyAnimatorModel model;
  private Timer timer;
  private double speed;
  private double tick;
  StartListener startButton;
  private boolean flag = false;
  static int svgCount = 0; // for saved output svg file (extra credit)
  static int textCount = 0; // for saved output txt file (extra credit)

  final JTextArea txtArea = new JTextArea(0, 0);

  /**
   * This is the constructor for our controller. It takes in a view and a model, and depending on
   * the type of view, we create the necessary output.
   *
   * @param view The view that has been generated using the EasyAnimator.
   * @param model The model that has been built using the builder in EasyAnimator.
   */
  public Controller(IView view, EasyAnimatorModel model) {
    // Only of the view is a playback type, we create the GUI. Otherwise,
    // we create a basic view.
    if (view.getViewType().equalsIgnoreCase("playback")) {
      this.view = view;
      this.model = model;
      this.speed = model.getAnimationSpeed();
      this.tick = 0;

      // Hooking up the timer to the start button listener.
      startButton = new StartListener();
      timer = new Timer((int) (1000 / this.speed), startButton);

      // Adding the start button to the start listener class
      this.view.addStartListener(startButton);

      ResumeListener resumeButton = new ResumeListener();
      this.view.addResumeListener(resumeButton);

      RestartListener restartButton = new RestartListener();
      this.view.addRestartListener(restartButton);

      PauseListener pauseButton = new PauseListener();
      this.view.addPauseListener(pauseButton);

      LoopListener loopButton = new LoopListener();
      this.view.addLoopListener(loopButton);

      SpeedUpListener speedUpButton = new SpeedUpListener();
      this.view.addSpeedUpListener(speedUpButton);

      SlowDownListener slowDownButton = new SlowDownListener();
      this.view.addSlowDownListener(slowDownButton);

      this.view.getCompositeFrame().getTextSpeed().setText("Current Speed: " + (int) speed + "x");

      OpenNewFile newFile = new OpenNewFile();
      this.view.addMenuItemListener(newFile);

      AddShapeOption addNewShape = new AddShapeOption();
      this.view.addShapeListener(addNewShape);

      RemoveShapeListener removeShapeListener = new RemoveShapeListener();
      this.view.addRemoveShapeListener(removeShapeListener);

      SaveTextListener saveTextListener = new SaveTextListener();
      this.view.addSaveTextListener(saveTextListener);

      SaveSvgListener saveSvgListener = new SaveSvgListener();
      this.view.addSaveSvgListener(saveSvgListener);

    } else {
      this.view = view;
      this.model = model;
      this.speed = model.getAnimationSpeed();
      this.tick = 0;
    }
  }

  /**
   * This class represents a listener object for the start button. When the user clicks the start
   * button, the following set of commands in the actionPerformed method will be executed.
   */
  private class StartListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      view.getCompositeFrame().getStartButton().setText("Start: Running");
      timer.start();
      List<IShape> mutatedShapes = model.getShapesAtTimeStamp(tick);
      view.getCompositeFrame().currentView(mutatedShapes);
      tick++;

      if (tick == model.getEndTime() && !flag) {
        timer.stop();
      } else if (tick == model.getEndTime() && flag) {
        tick = 0;
        timer.restart();
        mutatedShapes = model.getShapesAtTimeStamp(tick);
        view.getCompositeFrame().currentView(mutatedShapes);
        tick++;
      }
    }
  }

  /**
   * This class represents a listener object for the pause button. When the user clicks the pause
   * button, the following set of commands in the actionPerformed method will be executed.
   */
  private class PauseListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      view.getCompositeFrame().getStartButton().setText("Start");
      view.getCompositeFrame().getPauseButton().setText("Pause: Paused");
      timer.stop();
    }
  }

  /**
   * This class represents a listener object for the resume button. When the user clicks the resume
   * button, the following set of commands in the actionPerformed method will be executed.
   */
  private class ResumeListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      view.getCompositeFrame().getStartButton().setText("Start: Running");
      view.getCompositeFrame().getPauseButton().setText("Pause");
      timer.start();
      List<IShape> mutatedShapes = model.getShapesAtTimeStamp(tick);
      view.getCompositeFrame().currentView(mutatedShapes);
      tick++;
    }
  }

  /**
   * This class represents a listener object for the restart button. When the user clicks the
   * restart button, the following set of commands in the actionPerformed method will be executed.
   */
  private class RestartListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      view.getCompositeFrame().getStartButton().setText("Start: Running");
      view.getCompositeFrame().getPauseButton().setText("Pause");
      tick = 0;
      timer.restart();
      speed = 1;
      timer.setDelay((int) (1000 / speed));
      List<IShape> mutatedShapes = model.getShapesAtTimeStamp(tick);
      view.getCompositeFrame().currentView(mutatedShapes);
      tick++;
      view.getCompositeFrame().getSpeedUpButton().setText("Speed Up");
      view.getCompositeFrame().getSlowDownButton().setText("Slow Down");
      view.getCompositeFrame().getTextSpeed().setText("Current Speed: " + speed);
    }
  }

  /**
   * This class represents a listener object for the loop button. When the user clicks the loop
   * button, the following set of commands in the actionPerformed method will be executed.
   */
  private class LoopListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (!flag) {
        flag = true;
        view.getCompositeFrame().getLoopButton().setText("Loop: On");
      } else if (flag) {
        flag = false;
        view.getCompositeFrame().getLoopButton().setText("Loop: Off");
      }
      System.out.println(flag);
    }
  }

  /**
   * This class represents a listener object for the speed up button. When the user clicks the speed
   * up button, the following set of commands in the actionPerformed method will be executed.
   */
  private class SpeedUpListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      speed = speed * 2;
      timer.setDelay((int) (1000 / speed));
      view.getCompositeFrame().getSpeedUpButton().setText("Speed Up: " + (int) speed * 2 + "x");
      view.getCompositeFrame().getSlowDownButton().setText("Slow Down");
      view.getCompositeFrame().getTextSpeed().setText("Current Speed: " + (int) speed);
    }
  }

  /**
   * This class represents a listener object for the slow down button. When the user clicks the slow
   * down button, the following set of commands in the actionPerformed method will be executed.
   */
  private class SlowDownListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      speed = speed / 2;
      // We don't want the animation to be TOO slow,
      // so if the speed goes below 1, we reset it back to 1.
      if (speed < 1) {
        speed = 1;
      }
      view.getCompositeFrame().getSpeedUpButton().setText("Speed Up");
      view.getCompositeFrame().getSlowDownButton().setText("Slow Down: " + (int) speed / 2 + "x");
      view.getCompositeFrame().getTextSpeed().setText("Current Speed: " + (int) speed);
      timer.setDelay((int) (1000 / speed));
    }
  }

  /**
   * This class represents a listener object for the "open file" button. When the user wants to open
   * a new animation file, the following set of commands in the actionPerformed method will be
   * executed.
   */
  private class OpenNewFile implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      FileDialog openFile =
          new FileDialog(view.getCompositeFrame(), "Select a .txt file", FileDialog.LOAD);
      openFile.show();
      if (openFile.getFile() != null) {
        String filename = openFile.getFile();
        EasyAnimatorModel model2 = new EasyAnimatorModelImpl();
        AnimationBuilder<EasyAnimatorModel> builder =
            new EasyAnimatorModelImpl.BobTheBuilder(model2);
        // Readable
        String filePath = new File("").getAbsolutePath();
        Readable readable = null;
        try {
          readable = new FileReader(filePath + "/" + filename);
        } catch (FileNotFoundException fileNotFoundException) {
          fileNotFoundException.printStackTrace();
        }
        // Parse File
        AnimationReader.parseFile(readable, builder);

        double speed2 = 1;
        model2.setAnimationSpeed(speed2);
        IView ourView = ViewFactory.getView(model2, "playback", "", speed2);
        new Controller(ourView, model2);
      }
    }
  }

  /**
   * This class represents a listener object for the "add shape" button. When the user wants to open
   * a new animation file, the following set of commands in the actionPerformed method will be
   * executed.
   */
  private class AddShapeOption implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      IShape newShape = null;
      try {
        JOptionPane.showConfirmDialog(
            null,
            view.getCompositeFrame().getAddShapePanel(),
            "Enter the following information to construct your new shape\n",
            2);
        // Flow of execution automatically pauses here until we click "ok" in the showConfirmDialog
        // pop-up. When we write the values we want, the JTextFields automatically get populated
        // with the correct values. That's why we can call the getter methods.
        if (view.getCompositeFrame().getShapeType().equalsIgnoreCase("rectangle")) {
          newShape =
              new Rectangle(
                  view.getCompositeFrame().getName(),
                  view.getCompositeFrame().getShapeX(),
                  view.getCompositeFrame().getShapeY(),
                  view.getCompositeFrame().getShapeHeight(),
                  view.getCompositeFrame().getShapeWidth(),
                  new Color(
                      view.getCompositeFrame().getShapeRed(),
                      view.getCompositeFrame().getShapeGreen(),
                      view.getCompositeFrame().getShapeBlue()),
                  view.getCompositeFrame().getShapeAppear(),
                  view.getCompositeFrame().getShapeDisappear());
        } else if (view.getCompositeFrame().getShapeType().equalsIgnoreCase("oval")) {
          newShape =
              new Oval(
                  view.getCompositeFrame().getName(),
                  view.getCompositeFrame().getShapeX(),
                  view.getCompositeFrame().getShapeY(),
                  view.getCompositeFrame().getShapeHeight(),
                  view.getCompositeFrame().getShapeWidth(),
                  new Color(
                      view.getCompositeFrame().getShapeRed(),
                      view.getCompositeFrame().getShapeGreen(),
                      view.getCompositeFrame().getShapeBlue()),
                  view.getCompositeFrame().getShapeAppear(),
                  view.getCompositeFrame().getShapeDisappear());
        }
      } catch (IllegalArgumentException exception) {
        JOptionPane.showMessageDialog(
            null,
            "Unable to create your shape, please retry.",
            "Invalid input in trying to add shape",
            JOptionPane.ERROR_MESSAGE);
      }
      try {
        model.addShape(newShape);
        model.moveShape(
            newShape,
            view.getCompositeFrame().getX(),
            view.getCompositeFrame().getY(),
            view.getCompositeFrame().getShapeAppear(),
            view.getCompositeFrame().getShapeDisappear());
      } catch (IllegalArgumentException iae) {
        System.out.print("");
      }
    }
  }

  /**
   * This class represents a listener object for the "Save shape" button. When the user wants to
   * save the current animation as an SVG file, they click this button and the following set of
   * commands in the actionPerformed method will be executed.
   */
  class SaveSvgListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String fileName = "Custom_Saved_SVG_" + svgCount + ".svg";
      svgCount++;
      IView ourView = ViewFactory.getView(model, "svg", fileName, speed);
      try {
        new Controller(ourView, model).playAnimation();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

  /**
   * This class represents a listener object for the "Save shape" button. When the user wants to
   * save the current animation as an text file, they click this button and the following set of
   * commands in the actionPerformed method will be executed.
   */
  class SaveTextListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String fileName = "Custom_Saved_Text_" + textCount + ".txt";
      textCount++;
      IView ourView = ViewFactory.getView(model, "text", "", speed);
      BufferedWriter writer;
      try {
        writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(ourView.showView());
        writer.close();
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

  /**
   * If a user wants to remove a shape, then this class's actionPerformed method is called upon and
   * executes those commands.
   */
  class RemoveShapeListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String result =
          (String)
              JOptionPane.showInputDialog(
                  view.getCompositeFrame(),
                  "Enter Name of Shape to Remove",
                  "Remove Shape",
                  JOptionPane.PLAIN_MESSAGE,
                  null,
                  null,
                  "");
      try {
        if ((result != null)) {
          model.removeShape(result);
        }
      } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(
            null, "Shape Not Found!", "User Input Error.", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  @Override
  public void playAnimation() throws IOException {
    if (view.getViewType().equalsIgnoreCase("text")) {
      playTextView();
    } else if (view.getViewType().equalsIgnoreCase("svg")) {
      playSVGView();
    } else if (view.getViewType().equalsIgnoreCase("visual")) {
      playVisualView();
    }
  }

  /**
   * This is a helper method that gets called upon by the go() method above in order to execute if
   * the user enters "text" as a command line argument.
   */
  private void playTextView() {
    System.out.println(view.showView());
  }

  /**
   * This is a helper method that gets called upon by the go() method above in order to execute if
   * the user enters "SVG" as a command line argument.
   *
   * @throws IOException If the user doesn't enter a correct command line argument.
   */
  private void playSVGView() throws IOException {
    if (this.view.getOutputFile().equalsIgnoreCase("")) {
      System.out.println(this.view.showView());
    } else {
      BufferedWriter writer = new BufferedWriter(new FileWriter(this.view.getOutputFile()));
      writer.write(this.view.showView());
      writer.close();
    }
  }

  /**
   * This is a helper method that gets called upon by the go() method above in order to execute if
   * the user enters "visual" as a command line argument.
   */
  private void playVisualView() {
    while (tick <= model.getEndTime()) {
      List<IShape> mutatedShapes = model.getShapesAtTimeStamp(tick);
      this.view.getFrame().currentView(mutatedShapes);
      // tick corresponds to frames per second
      tick++;
      try {
        Thread.sleep((long) ((long) 100 / speed));
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
    }
  }

  @Override
  public IView getView() {
    return this.view;
  }

  @Override
  public EasyAnimatorModel getModel() {
    return this.model;
  }

  @Override
  public double getTick() {
    return tick;
  }
}
