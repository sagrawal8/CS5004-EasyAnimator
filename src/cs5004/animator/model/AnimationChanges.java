package cs5004.animator.model;

/**
 * This interface defines the methods that change the state of an IShape object. This interface will
 * be implemented by class that represent the changes. For example MoveShape class implements this
 * interface and changes the state of the IShape object and mutates its (x,y) position during a
 * given time.
 */
public interface AnimationChanges<T> {

  /**
   * This method is responsible changing the state of the IShape object. What state of the object
   * changes is dependent upon the class that implements this method that implements the particular
   * changes. It's a type of mutator method since it's mutating the state of an object.
   *
   * @param time The time stamp at which we want to see the reflected changes.
   */
  IShape executeChange(double time);

  /**
   * This method gives us the string representation of the changes made to the IShape object's
   * state. The classes that implement this method, implement how they want the changes to be
   * reflected in the String.
   *
   * @param key The name of the IShape object.
   * @return It returns a string that describes what changes are made to the object in animation.
   */
  String stringForm(String key);

  /**
   * This method shows the start time for a mutation to the object.
   *
   * @return Start time of mutation.
   */
  double getStart();

  /**
   * This method shows the end time for a mutation to the object.
   *
   * @return End time of mutation.
   */
  double getEnd();

  /** This is a getter method to retrieve the transformation ENUM. */
  Transformations getTransformation();

  /**
   * This method does the tweening for the model. It modifies the state of the shape at each tick.
   *
   * @param time The time at which we want the shape to be modified.
   */
  void tweening(double time);

  /**
   * This is a getter method to retrieve the old state of the shape.
   *
   * @return The old state of the shape.
   */
  T getOld();

  /**
   * This is a getter method to retrieve the new state of the shape.
   *
   * @return The new state of the shape.
   */
  T getNew();
}
