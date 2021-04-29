package cs5004.animator.model;

import java.awt.Color;

/**
 * This class represents the Oval shape. It extends the class AbstractShape because a lot of its
 * methods and fields are common to all shapes.
 */
public class Oval extends AbstractShape {

  /**
   * This constructor initializes the Oval object. It passes the arguments to the super() method to
   * be initialized by the constructor in the AbstractShape class.
   *
   * @param name The name of each shape.
   * @param x The x-coordinate of the center of the Oval.
   * @param y The y-coordinate of the center of the Oval.
   * @param xRadius The length of the shape.
   * @param yRadius The width of the shape.
   * @param color The color r,g,b represented by the Color class.
   * @param timeAppear The time at which the shape appears in the animation.
   * @param timeDisappear The time at which the shape lasts till in the animation.
   */
  public Oval(
      String name,
      double x,
      double y,
      double xRadius,
      double yRadius,
      Color color,
      double timeAppear,
      double timeDisappear) {
    super(name, x, y, xRadius, yRadius, color, timeAppear, timeDisappear);
    this.type = ShapeType.OVAL;
  }

  /**
   * This is a second constructor that just takes in the shape's name.
   *
   * @param name The name of the shape.
   */
  public Oval(String name) {
    super(name);
    this.type = ShapeType.OVAL;
  }

  /**
   * This is a getter method. It gives us the area of the current Oval after calculating it in this
   * method.
   *
   * @return It returns the area of the Oval object.
   */
  @Override
  public double getArea() {
    return Math.PI * this.length * this.width;
    // return 1.0;
  }

  /**
   * This is a getter method. It gives us the perimeter of the current Oval after calculating it in
   * this method.
   *
   * @return It returns the perimeter of the Oval object.
   */
  @Override
  public double getPerimeter() {
    double stuffInSquareRoot = (Math.pow(this.length, 2) + Math.pow(this.length, 2)) / 2;
    double perimeter = 2 * Math.PI * Math.sqrt(stuffInSquareRoot);
    return perimeter;
  }

  /**
   * This is a copy method which copies the current shape at its current state. It gives us a new
   * Oval with the same fields used to initialize the original object.
   *
   * @return It returns a new instance of the Oval object.
   */
  @Override
  public IShape cloneShape() {
    return new Oval(
        this.name,
        this.pos.getX(),
        this.pos.getY(),
        this.length,
        this.width,
        this.color,
        this.timeAppear,
        this.timeDisappear);
  }

  @Override
  public ShapeType getShapeType() {
    return type;
  }

  /**
   * This method gives us a string representation of the Oval object. It describes the fields of the
   * object.
   *
   * @return It returns a String representation of Oval.
   */
  @Override
  public String toString() {
    String shapeDescription;
    shapeDescription =
        "\nName: "
            + this.name
            + "\nType: "
            + "oval"
            + "\nMin corner: "
            + "("
            + this.pos.getX()
            + ","
            + this.pos.getY()
            + ")"
            + ", "
            + "Width: "
            + this.width
            + ", "
            + "Height: "
            + this.length
            + ", "
            + "Color: "
            + "("
            + (float) this.color.getRed()
            + ","
            + (float) this.color.getGreen()
            + ","
            + (float) this.color.getBlue()
            + ")"
            + "\nAppears at t="
            + this.timeAppear
            + "\nDisappears at t="
            + this.timeDisappear
            + "\n";

    return shapeDescription;
  }
}
