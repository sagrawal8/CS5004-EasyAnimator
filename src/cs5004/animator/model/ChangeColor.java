package cs5004.animator.model;

import java.awt.Color;

/**
 * This class represents the changes to the IShape object's color. It basically changes the IShape
 * object from its current Color (r,g,b) to the new color that is passed in to initialize the
 * ChangeColor object.
 */
public class ChangeColor implements AnimationChanges<Color> {

  private Color newColor;
  private Color oldColor;
  private double start;
  private double end;
  private Transformations transformation;
  private Color colorAtTime;
  private IShape shape;

  /**
   * This constructor initializes the ChangeColor object. It takes in the new Color (r,g,b) as a
   * Color object, the IShape shape, and the start and end time that signify the duration of the
   * color change.
   *
   * @param newColor The new Color (r,g,b) of our IShape object.
   * @param shape The IShape object that we want to change the color of
   * @param start The start time at which we want the change in color to start.
   * @param end The end time at which we want the change in color to end.
   * @throws IllegalArgumentException If the Color passed into the constructor is null.
   */
  public ChangeColor(Color newColor, IShape shape, double start, double end)
      throws IllegalArgumentException {

    if (newColor == null) {
      throw new IllegalArgumentException("Can't pass in null for the positions");
    }

    this.newColor = newColor;
    this.oldColor = shape.getColor();
    this.start = start;
    this.end = end;
    this.transformation = Transformations.CHANGE_COLOR;
    this.shape = shape;
  }

  /**
   * This method implements the mutation to the color of the IShape object. It changes the current
   * color (r,g,b) of the IShape object to the new color (r,g,b).
   *
   * @param time The time stamp at which we want to see the reflected changes.
   */
  @Override
  public IShape executeChange(double time) {
    tweening(time);
    shape.setNewColor(colorAtTime);
    return shape;
  }

  /**
   * This method gives us the text representation of how the color of IShape object changed. It
   * identifies the IShape object that changed color by its name, its former color, and its new
   * color and the duration in which it changed its color.
   *
   * @param key The name of the IShape object.
   * @return It returns a text representation of the color chnage of our IShape object.
   */
  @Override
  public String stringForm(String key) {
    String str;
    str =
        "Shape "
            + key
            + " changes color from ("
            + (float) oldColor.getRed()
            + ","
            + (float) oldColor.getGreen()
            + ","
            + (float) oldColor.getBlue()
            + ") to ("
            + (float) newColor.getRed()
            + ","
            + (float) newColor.getGreen()
            + ","
            + (float) newColor.getBlue()
            + ") from t="
            + String.format("%.0f", start)
            + " to t="
            + String.format("%.0f\n", end);
    return str;
  }

  @Override
  public double getStart() {
    return start;
  }

  @Override
  public double getEnd() {
    return this.end;
  }

  @Override
  public Transformations getTransformation() {
    return this.transformation;
  }

  @Override
  public void tweening(double time) {
    int r =
        (int)
            (oldColor.getRed() * ((end - time) / (end - start))
                + newColor.getRed() * ((time - start) / (end - start)));
    int g =
        (int)
            (oldColor.getGreen() * ((end - time) / (end - start))
                + newColor.getGreen() * ((time - start) / (end - start)));
    int b =
        (int)
            (oldColor.getBlue() * ((end - time) / (end - start))
                + newColor.getBlue() * ((time - start) / (end - start)));
    colorAtTime = new Color(r, g, b);
  }

  @Override
  public Color getOld() {
    return oldColor;
  }

  @Override
  public Color getNew() {
    return newColor;
  }
}
