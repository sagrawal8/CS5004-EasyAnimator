package cs5004.animator.view;

import java.awt.event.ActionListener;

import cs5004.animator.model.EasyAnimatorModel;

/** This class represents a text based view of the animation. */
public class TextView implements IView {

  EasyAnimatorModel model;

  /**
   * Constructs a TextView object, by taking in a model.
   *
   * @param model The model we want to take in.
   */
  public TextView(EasyAnimatorModel model) {
    if (model == null) {
      throw new IllegalArgumentException("The model you passed in is null");
    }
    this.model = model;
  }

  /**
   * This method shows the view in a string format.
   *
   * @return A string representation of the model.
   */
  public String showView() {
    return this.model.toString();
  }

  /**
   * Getter method for the frame, however it is null in this case, as there is no frame for the
   * TextView.
   *
   * @return null.
   */
  @Override
  public SwingFrame getFrame() {
    throw new UnsupportedOperationException("TextView has no field of type SwingFrame");
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

  @Override
  public String getViewType() {
    return "text";
  }

  /**
   * Returns the model. This a getter method.
   *
   * @return the model.
   */
  public EasyAnimatorModel getModel() {
    return model;
  }
}
