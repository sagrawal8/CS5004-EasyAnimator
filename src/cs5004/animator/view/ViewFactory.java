package cs5004.animator.view;

import cs5004.animator.model.EasyAnimatorModel;

/**
 * This class is a ViewFactory that decides what actions to take based on the view type that is
 * passed in from the EasyAnimator class.
 */
public class ViewFactory {

  /**
   * This method gets the view and generates the required view based on that.
   *
   * @param model The model with the populated data structures.
   * @param viewType The view type we get from the EasyAnimator class.
   * @param outputFile The name of the output file we want.
   * @param speed The speed of the animation.
   */
  public static IView getView(
      EasyAnimatorModel model, String viewType, String outputFile, double speed) {

    IView view = null;
    try {
      if (viewType.equals("svg") && (outputFile == null || outputFile.isEmpty())) {
        view = new SvgView(model, speed);
      } else if (viewType.equalsIgnoreCase("svg")) {
        view = new SvgView(model, speed, outputFile);
      } else if (viewType.equalsIgnoreCase("text")) {
        view = new TextView(model);
      } else if (viewType.equalsIgnoreCase("visual")) {
        view = new VisualView(model);
      } else if (viewType.equalsIgnoreCase("playback")) {
        view = new CompositeView(model);
      }
    } catch (Exception e) {
      System.out.println("There's something wrong");
    }
    return view;
  }
}
