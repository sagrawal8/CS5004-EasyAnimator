package cs5004.animator.model;

import java.awt.Color;

/**
 * This class represents the Rectangle shape. It extends the class AbstractShape because a lot of
 * its methods and fields are common to all shapes.
 */
public class Rectangle extends AbstractShape {

  /**
   * This constructor initializes the Rectangle object. It passes the arguments to the super()
   * method to be initialized by the constructor in the AbstractShape class.
   *
   * @param name The name of each shape.
   * @param x The x-coordinate of the corner of the Rectangle.
   * @param y The y-coordinate of the corner of the Rectangle.
   * @param length The length of the shape.
   * @param width The width of the shape.
   * @param color The color r,g,b represented by the Color class.
   * @param timeAppear The time at which the shape appears in the animation.
   * @param timeDisappear The time at which the shape lasts till in the animation.
   */
  public Rectangle(
      String name,
      double x,
      double y,
      double length,
      double width,
      Color color,
      double timeAppear,
      double timeDisappear) {
    super(name, x, y, length, width, color, timeAppear, timeDisappear);
    this.type = ShapeType.RECTANGLE;
  }

  /**
   * This is a second constructor that just takes in the shape's name.
   *
   * @param name The name of the shape.
   */
  public Rectangle(String name) {
    super(name);
    this.type = ShapeType.RECTANGLE;
  }

  /**
   * This is a getter method. It gives us the area of the current Rectangle after calculating it in
   * this method.
   *
   * @return It returns the area of the Rectangle object.
   */
  @Override
  public double getArea() {
    return this.length * this.width;
  }

  /**
   * This is a getter method. It gives us the perimeter of the current Rectangle after calculating
   * it in this method.
   *
   * @return It returns the perimeter of the Rectangle object.
   */
  @Override
  public double getPerimeter() {
    return 2 * this.length + 2 * this.width;
  }

  /**
   * This is a copy method which copies the current shape at its current state. It gives us a new
   * Rectangle with the same fields used to initialize the original object.
   *
   * @return It returns a new instance of the Rectangle object.
   */
  @Override
  public IShape cloneShape() {
    return new Rectangle(
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
    return this.type;
  }

  /**
   * This method gives us a string representation of the Rectangle object. It describes the fields
   * of the object.
   *
   * @return It returns a String representation of Rectangle.
   */
  @Override
  public String toString() {
    String shapeDescription;
    shapeDescription =
        "\nName: "
            + this.name
            + "\nType: "
            + "rectangle"
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
