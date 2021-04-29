package cs5004.animator.view;

import java.awt.event.ActionListener;

import cs5004.animator.model.EasyAnimatorModel;

/** This class represents a visual view. */
public class VisualView implements IView {

  SwingFrame frame;
  EasyAnimatorModel model;

  /**
   * This method constructs a visual view object.
   *
   * @param model The model from our builder.
   */
  public VisualView(EasyAnimatorModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model you passed in is null");
    }
    this.model = model;
    this.frame =
        new SwingFrame(
            model.getX(),
            model.getY(),
            model.getCanvasWidth(),
            model.getCanvasHeight(),
            model.getShapes());
  }

  @Override
  public String getViewType() {
    return "visual";
  }

  @Override
  public String showView() {
    throw new UnsupportedOperationException("Cannot show the view in text format");
  }

  /**
   * A getter method to retrieve the frame.
   *
   * @return The frame of the animation.
   */
  public SwingFrame getFrame() {
    return this.frame;
  }

  @Override
  public CompositeFrame getCompositeFrame() {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public String getOutputFile() {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addStartListener(ActionListener listenForStart) {

    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addPauseListener(ActionListener listenForPause) {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addRestartListener(ActionListener listenForRestart) {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addSpeedUpListener(ActionListener listenForSpeedUp) {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addSlowDownListener(ActionListener listenForSlowDown) {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addLoopListener(ActionListener listenForLoop) {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addResumeListener(ActionListener listenForResume) {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addMenuItemListener(ActionListener listenForNewFile) {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addShapeListener(ActionListener listenForAddShape) {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addRemoveShapeListener(ActionListener removeShapeListener) {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addSaveTextListener(ActionListener saveTextListener) {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  @Override
  public void addSaveSvgListener(ActionListener saveSvgListener) {
    throw new UnsupportedOperationException("This operation is not supported by this view");
  }

  /**
   * A getter method to retrieve the model.
   *
   * @return The model.
   */
  public EasyAnimatorModel getModel() {
    return this.model;
  }
}
