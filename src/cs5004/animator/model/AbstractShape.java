package cs5004.animator.model;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * This class implements the IShape interface. Furthermore, this is an abstract class so it doesn't
 * have to implement all the methods outlined in the interface, however, whatever method that is not
 * implemented, has to be implemented the child classes of the abstract class.
 */
public abstract class AbstractShape implements IShape {

  protected String name;
  protected Point2D pos;
  protected double length;
  protected double width;
  protected Color color;
  protected double timeAppear;
  protected double timeDisappear;
  protected ShapeType type;
  protected boolean flagDeclared;

  /**
   * This constructor initializes the objects of any class that extends this abstract class. The
   * child classes call the super() method in order to initialize their objects. Since a lot of the
   * methods are common among diff objects that implement the IShape interface, the common methods
   * and the fields are abstracted out in this Abstract class.
   *
   * @param name The name of each shape.
   * @param x The x-coordinate position of the shape.
   * @param y The y-cooridnate position of the shape.
   * @param length The length of the shape.
   * @param width The width of the shape.
   * @param color The color r,g,b represented by the Color class.
   * @param timeAppear The time at which the shape appears in the animation.
   * @param timeDisappear The time at which the shape lasts till in the animation.
   * @throws IllegalArgumentException We throw this exception if the length or width is less than 0,
   *     when the timeAppear or Disappear is less than 0, when timeDisappear is less than
   *     timeAppear, when null is passed in for the color.
   */
  public AbstractShape(
      String name,
      double x,
      double y,
      double length,
      double width,
      Color color,
      double timeAppear,
      double timeDisappear)
      throws IllegalArgumentException {

    if (name == null) {
      throw new IllegalArgumentException("Name is null");
    }

    if (length < 0 || width < 0) {
      throw new IllegalArgumentException("Length or width is negative.");
    }

    if (timeAppear < 0 || timeDisappear < 0) {
      throw new IllegalArgumentException(
          "timeAppear or timeDisappear is negative. They both" + " have to be the positive.");
    }

    if (timeAppear > timeDisappear) {
      throw new IllegalArgumentException(
          "Time appear > Time disappear. Has to be the other way" + " around.");
    }

    if (color == null) {
      throw new IllegalArgumentException("Can't have a null object");
    }

    this.name = name;
    this.pos = new Point2D.Double(x, y);
    this.length = length;
    this.width = width;
    this.color = color;
    this.timeAppear = timeAppear;
    this.timeDisappear = timeDisappear;

    // The flag field let's us determine the initial state of the Shape object.
    this.flagDeclared = false;
  }

  /**
   * This is a second constructor for the AbstractShape. It only takes in the name of the object.
   *
   * @param name the name of the shape.
   */
  public AbstractShape(String name) {
    this.name = name;
    // The flag field let's us determine the initial state of the Shape object.
    this.flagDeclared = false;
  }

  /**
   * This is a getter method. This method gives us the name associated with each shape. Each shape
   * is given a name, and is identified by their name.
   *
   * @return It returns the name of each shape. Its data type is type String
   */
  @Override
  public String getName() {
    if (name == null) {
      throw new IllegalArgumentException("Name is null");
    }
    return this.name;
  }

  /**
   * This a getter method. It gives us the calculated area for the type of shape that implements
   * this method. The implementation of this method is left to the specific child classes that
   * extend this abstract class because the the formula of the area might change depending on
   * different shapes.
   *
   * @return It returns the calculated area of the shape. Its data type is type double.
   */
  @Override
  public abstract double getArea();

  /**
   * This a getter method. It gives us the calculated perimeter for type of shape that implements
   * this method. The implementation of this method is left to the specific child classes that
   * extends this abstract class because the the formula of the perimeter might change depending on
   * different shapes.
   *
   * @return It returns the calculated area of the shape. Its data type is type double.
   */
  @Override
  public abstract double getPerimeter();

  /**
   * This is a getter method. It gives us the x-coordinate of our shape. If the shape is of type
   * Rectangle, it's the x-coordinate of the corner of the shape. If the shape is of type Oval, it's
   * the the x-coordinate of the center of the shape.
   *
   * @return It returns the x-coordinate of our shape. Its data type is type double.
   */
  @Override
  public double getX() {
    return pos.getX();
  }

  /**
   * This is a getter method. It gives us the y-coordinate of our shape. If the shape is of type
   * Rectangle, it's the y-coordinate of the corner of the shape. If the shape is of type Oval, it's
   * the the y-coordinate of the center of the shape.
   *
   * @return It returns the y-coordinate of our shape. Its data type is type double.
   */
  @Override
  public double getY() {
    return pos.getY();
  }

  /**
   * This is a getter method. It gives us the length of our shape. If the shape is of type
   * Rectangle, it's the length side of our Rectangle. If the shape is of type Oval, it's the the
   * x-radius of the shape.
   *
   * @return It returns the length associated with our shape. Its data type is type double.
   */
  @Override
  public double getLength() {
    return this.length;
  }

  /**
   * This is a getter method. It gives us the width of our shape. If the shape is of type Rectangle,
   * it's the width side of our Rectangle. If the shape is of type Oval, it's the the y-radius of
   * the shape.
   *
   * @return It returns the width associated with our shape. Its data type is type double.
   */
  @Override
  public double getWidth() {
    return this.width;
  }

  /**
   * This is a getter method. It gives us the color of our shape. The color is implemented using
   * Java's Color class which takes in the r, g, b. values respectively.
   *
   * @return It returns the r, g, b values of our shape which are stored as a Color object.
   */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * This is a getter method. This method gives us the time at which our shape first appears in our
   * animation.
   *
   * @return It returns a unit-less time at which our shape first appears in our animation.
   */
  @Override
  public double getTimeAppear() {
    return this.timeAppear;
  }

  /**
   * This is a getter method. This method gives us the time at which our shape disappears in our
   * animation. That means that the the shape lasts on the animation until timeDisAppear.
   *
   * @return It returns a unit-less time at which our shape first appears in our animation.
   */
  @Override
  public double getTimeDisappear() {
    return this.timeDisappear;
  }

  /**
   * This is a setter method and is also called a mutator method. As the name suggests, it mutates
   * the fields, x and y, which are the x and y coordinates of each shape. If the shape is of type
   * Rectangle, the (x,y) is the corner of the shape. If the shape is of type Oval, the (x,y) is of
   * the center of the Oval object.
   *
   * @param x It's the new x-coordinate of the shape object.
   * @param y It's the new y-coordinate of the shape object.
   */
  @Override
  public void setNewXY(double x, double y) {
    pos.setLocation(x, y);
  }

  /**
   * This is a setter method and is also called a mutator method. As the name suggests, it mutates
   * the r, g, b value of each shape. The color of each shape is implemented by the Color object.
   * Therefore, the passed in Color(r,g,b) object is used to update the color field of our shape.
   *
   * @param color It's the new Color(r,g,b) of the shape object.
   */
  @Override
  public void setNewColor(Color color) {
    this.color = color;
  }

  /**
   * This is a setter method and is also called a mutator method. As the name suggests, it mutates
   * the size of our IShape object by the factor passed into the method. It changes the length field
   * of the shape and multiplies by the factor newLength and changes the width field of the shape
   * and multiplies by the factor newWidth.
   *
   * @param scaleFactor The factor by which we increase or decrease the size of our shape.
   */
  @Override
  public void setScale(double scaleFactor) {

    if (scaleFactor < 0) {
      throw new IllegalArgumentException("Can't scale to a negative number");
    }
    this.length *= scaleFactor;
    this.width *= scaleFactor;
  }

  @Override
  public abstract String toString();

  @Override
  public abstract IShape cloneShape();

  @Override
  public void setDimensions(double newLength, double newWidth) throws IllegalArgumentException {
    if (newLength < 0 || newWidth < 0) {
      throw new IllegalArgumentException(
          "Your new length or new width must be strictly greater than 0.");
    }
    this.length = newLength;
    this.width = newWidth;
  }

  @Override
  public void setTimeDisappear(double timeDisappear) {
    this.timeDisappear = timeDisappear;
  }

  @Override
  public boolean getFlag() {
    return this.flagDeclared;
  }

  @Override
  public void setFlag() {
    this.flagDeclared = !this.flagDeclared;
  }
}
