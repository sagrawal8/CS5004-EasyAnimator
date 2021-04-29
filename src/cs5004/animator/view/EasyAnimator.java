package cs5004.animator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.controller.Controller;
import cs5004.animator.controller.IController;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.model.EasyAnimatorModel;
import cs5004.animator.model.EasyAnimatorModelImpl;

import javax.swing.JOptionPane;

/**
 * This class is the "Driver" for our project. It reads in the command line arguments and populates
 * the data structure in our model (i.e. it mutates the model). We then pass that mutated model,
 * along with the view type, speed, the name of the output file name to the ViewFactory class, which
 * decides what type of view we want to display.
 */
public final class EasyAnimator {

  /**
   * This is the main method for the animation that takes in the command line arguments and
   * creates the appropriate model and view.
   *
   * @param args The command line arguments.
   * @throws IOException if there is a compilation error.
   */
  public static void main(String[] args) throws IOException {
    // our model
    EasyAnimatorModel myModel = new EasyAnimatorModelImpl();

    // variables for our command line arguments
    String inputFile = "";
    String outputFile = "";
    double speed = 0;
    String viewType = "";

    try {
      for (int i = 0; i < args.length; i += 2) {
        if (args[i].equalsIgnoreCase("-in")) {
          inputFile = args[i + 1];
        } else if (args[i].equalsIgnoreCase("-out")) {
          outputFile = args[i + 1];
        } else if (args[i].equalsIgnoreCase("-view")) {
          viewType = args[i + 1];
        } else if (args[i].equalsIgnoreCase("-speed")) {
          speed = Integer.parseInt(args[i + 1]);
        } else {
          throw new IllegalArgumentException("one of your arguments is not valid");
        }
      }

    } catch (IndexOutOfBoundsException e) {
      JOptionPane.showMessageDialog(
          null,
          "Command-Line argument error: Too many arguments " + "or too little arguments",
          "Argument Error",
          JOptionPane.ERROR_MESSAGE);

    } catch (NumberFormatException e2) {
      JOptionPane.showMessageDialog(
          null, "Speed is not an integer", "Data type error", JOptionPane.ERROR_MESSAGE);

    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          null,
          "Something isn't right-Check your commandline arguments",
          "Unknown Error",
          JOptionPane.ERROR_MESSAGE);
    }

    if (inputFile.equalsIgnoreCase("") || !inputFile.contains(".txt")) {
      JOptionPane.showMessageDialog(
          null, "InputFile is invalid", "Input File Error", JOptionPane.ERROR_MESSAGE);
    }

    if (speed == 0) {
      speed = 1;
    }

    try {
      // Builder
      AnimationBuilder<EasyAnimatorModel> builder =
          new EasyAnimatorModelImpl.BobTheBuilder(myModel);
      // Readable
      String filePath = new File("").getAbsolutePath();
      Readable readable = new FileReader(filePath + "/" + inputFile);
      // Parse File
      AnimationReader.parseFile(readable, builder);

    } catch (IllegalStateException e) {
      JOptionPane.showMessageDialog(
          null, e.getMessage(), "Invalid Read Error", JOptionPane.ERROR_MESSAGE);
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(
          null, "Input File is invalid", "Input File Error", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(
          null, e.getMessage(), "Invalid Build Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          null, e.getMessage(), "Invalid Build Error", JOptionPane.ERROR_MESSAGE);
    }

    myModel.setAnimationSpeed(1);

    // Delegating to our view factory.
    IView ourView = ViewFactory.getView(myModel, viewType, outputFile, speed);
    IController controller = new Controller(ourView, myModel);
    controller.playAnimation();
  }
}
