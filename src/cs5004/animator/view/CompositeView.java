package cs5004.animator.view;

import java.awt.event.ActionListener;
import cs5004.animator.model.EasyAnimatorModel;

/**
 * This class represents a composite view. This is such that if a user type's in "playback" in their
 * command line arguments, an interactive GUI is generated. This class represents that interactive
 * GUI.
 */
public class CompositeView implements IView {

  private CompositeFrame frame;

  /**
   * Generates a composite view instance.
   *
   * @param modell The model whose data structures have been populated using the builder.
   */
  public CompositeView(EasyAnimatorModel modell) {
    EasyAnimatorModel model = modell;
    this.frame =
        new CompositeFrame(
            model.getX(),
            model.getY(),
            model.getCanvasWidth(),
            model.getCanvasHeight(),
            model.getShapes());
  }

  @Override
  public void addStartListener(ActionListener listenForStart) {
    this.frame.getStartButton().addActionListener(listenForStart);
  }

  @Override
  public void addPauseListener(ActionListener listenForPause) {
    this.frame.getPauseButton().addActionListener(listenForPause);
  }

  @Override
  public void addRestartListener(ActionListener listenForRestart) {
    this.frame.getRestartButton().addActionListener(listenForRestart);
  }

  @Override
  public void addSpeedUpListener(ActionListener listenForSpeedUp) {
    this.frame.getSpeedUpButton().addActionListener(listenForSpeedUp);
  }

  @Override
  public void addSlowDownListener(ActionListener listenForSlowDown) {
    this.frame.getSlowDownButton().addActionListener(listenForSlowDown);
  }

  @Override
  public void addLoopListener(ActionListener listenForLoop) {
    this.frame.getLoopButton().addActionListener(listenForLoop);
  }

  @Override
  public void addResumeListener(ActionListener listenForResume) {
    this.frame.getResumeButton().addActionListener(listenForResume);
  }

  @Override
  public void addMenuItemListener(ActionListener listenForNewFile) {
    this.frame.getNewLoadFile().addActionListener(listenForNewFile);
  }

  @Override
  public void addShapeListener(ActionListener listenForAddShape) {
    this.frame.getAddShapeOption().addActionListener(listenForAddShape);
  }

  @Override
  public void addRemoveShapeListener(ActionListener listenForRemoveShape) {
    this.frame.getRemoveShape().addActionListener(listenForRemoveShape);
  }

  @Override
  public void addSaveTextListener(ActionListener listenForSaveText) {
    this.frame.getSaveText().addActionListener(listenForSaveText);
  }

  @Override
  public void addSaveSvgListener(ActionListener listenForSaveSvg) {
    this.frame.getSaveSvg().addActionListener(listenForSaveSvg);
  }

  @Override
  public String getViewType() {
    return "playback";
  }

  @Override
  public String showView() {
    return null;
  }

  @Override
  public SwingFrame getFrame() {
    return null;
  }

  public CompositeFrame getCompositeFrame() {
    return this.frame;
  }

  @Override
  public String getOutputFile() {
    return null;
  }
}
