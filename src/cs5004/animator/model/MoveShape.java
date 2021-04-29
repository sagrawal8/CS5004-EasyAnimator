package cs5004.animator.model;

import java.awt.geom.Point2D;

/**
 * This class represents the changes to the IShape objects (x,y) position. It basically moves the
 * IShape object from its current position to the new position that is passed in to initialize the
 * MoveShape object.
 */
public class MoveShape implements AnimationChanges<Point2D> {

  private Point2D newPos;
  private Point2D oldPos;
  private double start;
  private double end;
  private final Transformations transformation;
  private Point2D posAtTime;
  IShape shape;

  /**
   * This constructor initializes the MoveShape object. It takes in the new positions (x,y) as a
   * Point2D object, the IShape shape, and the start and end time that signify the duration of the
   * movement.
   *
   * @param newPos The new position of the IShape object.
   * @param shape The IShape object that we are moving.
   * @param start The start time at which we want the movement to start.
   * @param end The end time at which we want the movement to end.
   * @throws IllegalArgumentException We throw this exception, when null is passed in instead of
   *     Point2D object.
   */
  public MoveShape(Point2D newPos, IShape shape, double start, double end) {

    if (newPos == null) {
      throw new IllegalArgumentException("Can't pass in null for the positions");
    }

    this.newPos = newPos;
    this.oldPos = new Point2D.Double(shape.getX(), shape.getY());
    this.start = start;
    this.end = end;
    this.transformation = Transformations.MOVE_SHAPE;
    this.shape = shape;
  }

  /**
   * This method implements the mutation to the positions of the IShape object. It changes the
   * current (x,y) position of the IShape object to the new (x,y) position. It moves the object.
   *
   * @param time The time at which we want to execute the change.
   */
  @Override
  public IShape executeChange(double time) {
    tweening(time);
    shape.setNewXY(posAtTime.getX(), posAtTime.getY());
    return shape;
  }

  /**
   * This method gives us the text representation of how the IShape object moved. It identifies the
   * IShape object that moved by its name, its former position, and its new positions and the
   * duration in which it moves.
   *
   * @param key The name of the IShape object.
   * @return It returns a text representation of the movement of our IShape object.
   */
  @Override
  public String stringForm(String key) {
    String str;
    str =
        "Shape "
            + key
            + " moves from "
            + "("
            + this.oldPos.getX()
            + ","
            + this.oldPos.getY()
            + ")"
            + " to "
            + "("
            + this.newPos.getX()
            + ","
            + this.newPos.getY()
            + ")"
            + " from time t="
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
    double x =
        oldPos.getX() * ((end - time) / (end - start))
            + newPos.getX() * ((time - start) / (end - start));
    double y =
        oldPos.getY() * ((end - time) / (end - start))
            + newPos.getY() * ((time - start) / (end - start));
    this.posAtTime = new Point2D.Double(x, y);
  }

  @Override
  public Point2D getOld() {
    return this.oldPos;
  }

  @Override
  public Point2D getNew() {
    return this.newPos;
  }
}
