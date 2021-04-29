package cs5004.animator.controller;

import java.io.IOException;
import cs5004.animator.model.EasyAnimatorModel;
import cs5004.animator.view.IView;

/**
 * This interface represents the behaviors that the controller should be capable of. The controller
 * should be given the view and model, and then control should be relinquished to the controller.
 */
public interface IController {

  /**
   * This method relinquishes control to the controller and produces the correct view based on the
   * input from the EasyAnimator class.
   *
   * @throws IOException throws a checked exception if we have a compile-time error.
   */
  void playAnimation() throws IOException;

  /**
   * This method is a getter for the view.
   *
   * @return the view that was given to the controller.
   */
  IView getView();

  /**
   * This method is a getter for the model.
   *
   * @return the model that was given to the controller.
   */
  EasyAnimatorModel getModel();

  /**
   * This method is a getter to retrieve the current tick.
   *
   * @return the current tick.
   */
  double getTick();
}
