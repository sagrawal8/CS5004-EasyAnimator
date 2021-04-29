package cs5004.animator.view;

import java.awt.event.ActionListener;

/**
 * This interface represents a type of view. There are 3 types of views we want to implement for the
 * project: A TextView, SVGView and a VisualView.
 */
public interface IView {

  /**
   * A getter method to return the type of animation we are working with.
   *
   * @return A String representation of the type of view we are working with.
   */
  String getViewType();

  /**
   * This method shows the view to the user.
   *
   * @return A string representation of the model.
   */
  String showView();

  /**
   * This method is a getter method to return the frame for the visual view. For the text and the
   * SvgView, it returns NULL.
   *
   * @return the SwingFrame, so that the current view can be called.
   */
  SwingFrame getFrame();

  /**
   * This is a getter method to retrieve the composite frame.
   *
   * @return The composite frame.
   */
  CompositeFrame getCompositeFrame();

  /**
   * This is a getter method to retrieve the output file name.
   *
   * @return The output file name.
   */
  String getOutputFile();

  /**
   * This method adds an action listener object to the start button.
   *
   * @param listenForStart the start action listener.
   */
  void addStartListener(ActionListener listenForStart);

  /**
   * This method adds an action listener object to the pause button.
   *
   * @param listenForPause the pause action listener.
   */
  void addPauseListener(ActionListener listenForPause);

  /**
   * This method adds an action listener object to the restart button.
   *
   * @param listenForRestart the restart action listener.
   */
  void addRestartListener(ActionListener listenForRestart);

  /**
   * This method adds an action listener object to the speed up button.
   *
   * @param listenForSpeedUp the speed up action listener.
   */
  void addSpeedUpListener(ActionListener listenForSpeedUp);

  /**
   * This method adds an action listener object to the slow down button.
   *
   * @param listenForSlowDown the slow down action listener.
   */
  void addSlowDownListener(ActionListener listenForSlowDown);

  /**
   * This method adds an action listener object to the loop button.
   *
   * @param listenForLoop the loop action listener.
   */
  void addLoopListener(ActionListener listenForLoop);

  /**
   * This method adds an action listener object to the resume button.
   *
   * @param listenForResume the resume action listener.
   */
  void addResumeListener(ActionListener listenForResume);

  /**
   * This method adds an action listener object to the LoadNewFile button.
   *
   * @param listenForNewFile the new file action listener.
   */
  void addMenuItemListener(ActionListener listenForNewFile);

  /**
   * This method adds an action listener object to the Add-Shape button.
   *
   * @param listenForAddShape the add shape action listener.
   */
  void addShapeListener(ActionListener listenForAddShape);

  /**
   * This method adds an action listener object to the Remove-Shape button.
   *
   * @param removeShapeListener the remove shape action listener.
   */
  void addRemoveShapeListener(ActionListener removeShapeListener);

  /**
   * This method adds an action listener object to the "save as txt" button.
   *
   * @param saveTextListener the "save-as-text-file" action listener.
   */
  void addSaveTextListener(ActionListener saveTextListener);

  /**
   * This method adds an action listener object to the "save as SVG" button.
   *
   * @param saveSvgListener the "save-as-SVG-file" action listener.
   */
  void addSaveSvgListener(ActionListener saveSvgListener);
}
