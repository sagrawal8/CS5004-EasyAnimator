package cs5004.animator.model;

/**
 * This is an ENUM that represents a type of shape. There are ShapeType fields in the child classes
 * of the IShape interface that get initialized to the values in this enum. This is so that we don't
 * have to use "instanceof" later when doing the view.
 */
public enum ShapeType {
  OVAL,
  RECTANGLE;
}
