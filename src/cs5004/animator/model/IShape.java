package cs5004.animator.model;

import java.awt.Color;

/**
 * This interface defines the methods that are going to be implemented on different shapes. If a
 * class implements this interface, it is responsible for the implementations of the methods defined
 * here.
 */
public interface IShape {

  /**
   * This a getter method. It gives us the calculated area for the type of shape that implements
   * this method.
   *
   * @return It returns the calculated area of the shape. Its data type is type double.
   */
  double getArea();

  /**
   * This is a getter method. It gives us the calculated perimeter for the type of shape that
   * implements this method.
   *
   * @return It returns the calculated perimeter of the shape. Its data type is type double.
   */
  double getPerimeter();

  /**
   * This is a getter method. It gives us the x-coordinate of our shape. If the shape is of type
   * Rectangle, it's the x-coordinate of the corner of the shape. If the shape is of type Oval, it's
   * the the x-coordinate of the center of the shape.
   *
   * @return It returns the x-coordinate of our shape. Its data type is type double.
   */
  double getX();

  /**
   * This is a getter method. It gives us the y-coordinate of our shape. If the shape is of type
   * Rectangle, it's the y-coordinate of the corner of the shape. If the shape is of type Oval, it's
   * the the y-coordinate of the center of the shape.
   *
   * @return It returns the y-coordinate of our shape. Its data type is type double.
   */
  double getY();

  /**
   * This is a getter method. It gives us the length of our shape. If the shape is of type
   * Rectangle, it's the length side of our Rectangle. If the shape is of type Oval, it's the the
   * x-radius of the shape.
   *
   * @return It returns the length associated with our shape. Its data type is type double.
   */
  double getLength();

  /**
   * This is a getter method. It gives us the width of our shape. If the shape is of type Rectangle,
   * it's the width side of our Rectangle. If the shape is of type Oval, it's the the y-radius of
   * the shape.
   *
   * @return It returns the width associated with our shape. Its data type is type double.
   */
  double getWidth();

  /**
   * This is a getter method. It gives us the color of our shape. The color is implemented using
   * Java's Color class which takes in the r, g, b. values respectively.
   *
   * @return It returns the r, g, b values of our shape which are stored as a Color object.
   */
  Color getColor();

  /**
   * This is a getter method. This method gives us the time at which our shape first appears in our
   * animation.
   *
   * @return It returns a unit-less time at which our shape first appears in our animation.
   */
  double getTimeAppear();

  /**
   * This is a getter method. This method gives us the time at which our shape disappears in our
   * animation. That means that the the shape lasts on the animation until timeDisAppear.
   *
   * @return It returns a unit-less time at which our shape first appears in our animation.
   */
  double getTimeDisappear();

  /**
   * This is a getter method. This method gives us the name associated with each shape. Each shape
   * is given a name, and is identified by their name.
   *
   * @return It returns the name of each shape. Its data type is type String
   */
  String getName();

  /**
   * This is a setter method and is also called a mutator method. As the name suggests, it mutates
   * the fields, x and y, which are the x and y coordinates of each shape. If the shape is of type
   * Rectangle, the (x,y) is the corner of the shape. If the shape is of type Oval, the (x,y) is of
   * the center of the Oval object.
   *
   * @param x It's the new x-coordinate of the shape object.
   * @param y It's the new y-coordinate of the shape object.
   */
  void setNewXY(double x, double y);

  /**
   * This is a setter method and is also called a mutator method. As the name suggests, it mutates
   * the r, g, b value of each shape. The color of each shape is implemented by the Color object.
   * Therefore, the passed in Color(r,g,b) object is used to update the color field of our shape.
   *
   * @param color It's the new Color(r,g,b) of the shape object.
   */
  void setNewColor(Color color);

  /**
   * This is a setter method and is also called a mutator method. As the name suggests, it mutates
   * the size of our IShape object by the factor passed into the method. It changes the length field
   * of the shape and multiplies by the factor newLength and changes the width field of the shape
   * and multiplies by the factor newWidth.
   *
   * @param scaleFactor The factor by which we increase or decrease the size of our shape.
   */
  void setScale(double scaleFactor);

  /**
   * This is a copy method which copies the current shape at its current state. It gives us a new
   * object with the same fields used to initialize the original object.
   *
   * @return It returns a new instance of the IShape object.
   */
  IShape cloneShape();

  /**
   * This method changes the current length and width to the new length and the width. This method
   * mutates the original shape.
   *
   * @param newLength The new length of the shape.
   * @param newWidth The new width of the shape.
   */
  void setDimensions(double newLength, double newWidth);

  /**
   * This method retrieves the shape type.
   *
   * @return The shape type.
   */
  ShapeType getShapeType();

  /**
   * This is a setter method to set the time disappear of the shape. This method is only called in
   * the addMotion method in the builder.
   *
   * @param timeDisappear The new time we want the shape to disappear at.
   */
  void setTimeDisappear(double timeDisappear);

  /**
   * This method gets us the flag that tells us if the shape has already been declared as part of
   * the animation.
   *
   * @return Returns the boolean flag.
   */
  boolean getFlag();

  /**
   * This method lets us set the status of the flag that tells us if the shape has already been
   * declared as part of the animation.
   */
  void setFlag();
}
