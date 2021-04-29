package cs5004.animator.model;

import java.awt.geom.Point2D;

/**
 * This class represents the changes to the IShape object's size. It basically resizes the IShape
 * object from its current length and width to the new length and width that is passed in to
 * initialize the ScaleShape object.
 */
public class ScaleShape implements AnimationChanges<Point2D> {
  private double oldLength;
  private double oldWidth;
  private double newLength;
  private double newWidth;
  private double widthAtTime;
  private double heightAtTime;
  private double start;
  private double end;
  private final Transformations transformation;
  IShape shape;

  /**
   * This constructor initializes the ScaleShape object. It takes in the new length and width the
   * IShape shape, and the start and end time that signify the duration of the change in size.
   *
   * @param newLength new Length.
   * @param newWidth new Width.
   * @param shape The IShape object that we want to change the size of
   * @param start The start time at which we want the movement to start.
   * @param end The end time at which we want the movement to end.
   */
  public ScaleShape(double newLength, double newWidth, IShape shape, double start, double end) {

    this.oldLength = shape.getLength();
    this.oldWidth = shape.getWidth();
    this.newLength = newLength;
    this.newWidth = newWidth;
    this.start = start;
    this.end = end;
    this.transformation = Transformations.SCALE_SHAPE;
    this.shape = shape;
  }

  /**
   * This method implements the mutation to the size of the IShape object. It changes the current
   * length and width of the IShape object to the new length and width.
   *
   * @param time The time stamp at which we want to see the reflected changes.
   */
  @Override
  public IShape executeChange(double time) {
    tweening(time);
    shape.setDimensions(heightAtTime, widthAtTime);
    return shape;
  }

  /**
   * This method gives us the text representation of how the size of IShape changed. It identifies
   * the IShape object whose size we are changing by its name, its former size, and its new size.
   *
   * @param key The name of the IShape object.
   * @return It returns a text representation of the change in size of our IShape object.
   */
  @Override
  public String stringForm(String key) {
    String str;
    str =
        "Shape "
            + key
            + " scales from Width: "
            + oldLength
            + ", Height: "
            + oldWidth
            + " to Width: ";
    str += newLength + ", Height: " + newWidth;
    str += " from t=" + String.format("%.0f", start) + " to t=" + String.format("%.0f\n", end);

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
    this.widthAtTime =
        oldLength * ((end - time) / (end - start)) + newLength * ((time - start) / (end - start));
    this.heightAtTime =
        oldWidth * ((end - time) / (end - start)) + newWidth * ((time - start) / (end - start));
  }

  @Override
  public Point2D getOld() {
    return new Point2D.Double(oldLength, oldWidth);
  }

  @Override
  public Point2D getNew() {
    return new Point2D.Double(newLength, newWidth);
  }
}
