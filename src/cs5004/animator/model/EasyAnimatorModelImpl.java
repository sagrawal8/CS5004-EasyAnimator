package cs5004.animator.model;

import cs5004.animator.util.AnimationBuilder;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is the Model of our animation. The model is responsible for implementing the logic
 * that updates the state of objects to eventually pass that information to the controller. Our
 * EasyAnimatorModel implements the logic to change the state of IShape objects to represent
 * animations on them. Each IShape object is stored in a list which maintains the shape's original
 * state at the time of creation. Furthermore, that shape is cloned and added to a hashmap in which
 * the keys are the String form of the shape's name, and the value is a list representing all the
 * modification/transformations that shape will go through during the course of the animation.
 */
public class EasyAnimatorModelImpl implements EasyAnimatorModel {

  private List<IShape> shapes;
  private HashMap<String, List<AnimationChanges>> animations;
  private int width;
  private int height;
  private double endTime;
  private int x;
  private int y;

  private double animationSpeed;

  /** Constructs a new animation model. */
  public EasyAnimatorModelImpl() {
    this.shapes = new ArrayList();
    this.animations = new HashMap();
    endTime = -1;
  }

  @Override
  public void addShape(IShape shape) throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("You can't pass a null shape");
    }

    if (animations.containsKey(shape.getName())) {
      throw new IllegalArgumentException("This key already exists");
    }
    this.shapes.add(shape);
    this.animations.put(shape.getName(), new ArrayList());
  }

  @Override
  public void removeShape(String shapeName) throws IllegalArgumentException {
    if (shapeName == null) {
      throw new IllegalArgumentException("You can't pass a null shape");
    }

    if (!animations.containsKey(shapeName)) {
      throw new IllegalArgumentException("This key doesn't exist.");
    }

    this.animations.remove(shapeName);
    shapes.removeIf(each -> each.getName().equalsIgnoreCase(shapeName));
  }

  @Override
  public void moveShape(IShape shape, int newX, int newY, double start, double end)
      throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("You can't pass a null shape");
    }

    if (start < shape.getTimeAppear() || end > shape.getTimeDisappear()) {
      throw new IllegalArgumentException(
          "Start time before shape appears or " + "end time after shape disappears.");
    }

    if (start > end) {
      throw new IllegalArgumentException("Start is greater than end.");
    }

    if (isConflictingChange(shape.getName(), start, end, Transformations.MOVE_SHAPE)) {
      throw new IllegalArgumentException(
          "You have an overlapping start or end time with" + " an existing change. Please change");
    }

    AnimationChanges moveShape = new MoveShape(new Point2D.Double(newX, newY), shape, start, end);
    this.animations.get(shape.getName()).add(moveShape);
  }

  @Override
  public void changeColor(IShape shape, int r, int g, int b, double start, double end)
      throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("You can't pass a null shape");
    }

    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("color values out of bounds");
    }

    if (start < shape.getTimeAppear() || end > shape.getTimeDisappear()) {
      throw new IllegalArgumentException(
          "Start time before shape appears or " + "end time after shape disappears.");
    }

    if (start >= end) {
      throw new IllegalArgumentException("Start is greater than end.");
    }

    if (isConflictingChange(shape.getName(), start, end, Transformations.CHANGE_COLOR)) {
      throw new IllegalArgumentException(
          "You have an overlapping start or end time with" + " an existing change. Please change");
    }

    AnimationChanges changeColor = new ChangeColor(new Color(r, g, b), shape, start, end);
    this.animations.get(shape.getName()).add(changeColor);
  }

  @Override
  public void scaleShape(IShape shape, double newLength, double newWidth, double start, double end)
      throws IllegalArgumentException {
    if (shape == null) {
      throw new IllegalArgumentException("You can't pass a null shape");
    }

    if (newLength <= 0 || newWidth <= 0) {
      throw new IllegalArgumentException("Length or width is negative or 0, cannot be negative.");
    }

    if (start < shape.getTimeAppear() || end > shape.getTimeDisappear()) {
      throw new IllegalArgumentException(
          "Start time before shape appears or " + "end time after shape disappears.");
    }

    if (start >= end) {
      throw new IllegalArgumentException("Start is greater than end.");
    }

    if (isConflictingChange(shape.getName(), start, end, Transformations.SCALE_SHAPE)) {
      throw new IllegalArgumentException(
          "You have an overlapping start or end time with" + " an existing change. Please change");
    }

    if (newLength == shape.getLength() && newWidth == shape.getWidth()) {
      return;
    }

    AnimationChanges scaledShape = new ScaleShape(newLength, newWidth, shape, start, end);
    this.animations.get(shape.getName()).add(scaledShape);
  }

  /**
   * This is a helper method to see if we made any kind of conflicting changes. For example, a shape
   * cannot move in opposite directions during the same time interval.
   *
   * @param key The name of the shape.
   * @param startTime The time the change starts.
   * @param endTime The time the change ends.
   * @param transformation The type of transformation.
   * @return true or false if we're making a conflicting change.
   */
  private boolean isConflictingChange(
      String key, double startTime, double endTime, Transformations transformation) {
    try {
      List<AnimationChanges> animationChangesList = this.animations.get(key);
      for (AnimationChanges animationChanges : animationChangesList) {
        if (animationChanges.getTransformation() == transformation
            && ((startTime > animationChanges.getStart()) && (startTime < animationChanges.getEnd())
                || (endTime > animationChanges.getStart())
                    && (endTime < animationChanges.getEnd()))) {
          return true;
        }
      }
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("HashMap is empty. Cannot iterate.");
    }
    return false;
  }

  @Override
  public String toString() {
    String str = "Shapes:";
    for (IShape each : this.shapes) {
      str += each.toString();
    }

    str += "\n";

    List<ArrayList<String>> list = new ArrayList<>();
    int count = 0;

    for (String each : this.animations.keySet()) {
      for (AnimationChanges obj : this.animations.get(each)) {
        list.add(new ArrayList<>());
        list.get(count).add(Double.toString(obj.getStart()));
        list.get(count).add(obj.stringForm(each));
        count++;
      }
    }

    list.sort(
        (o1, o2) -> Double.compare(Double.parseDouble(o1.get(0)), Double.parseDouble(o2.get(0))));

    for (ArrayList<String> subList : list) {
      str += subList.get(1);
    }
    return str;
  }

  @Override
  public HashMap<String, List<AnimationChanges>> getAnimations() {
    return this.animations;
  }

  @Override
  public List<IShape> getShapes() {
    return this.shapes;
  }

  @Override
  public List<IShape> getShapesAtTimeStamp(double time) {
    List<IShape> list = new ArrayList<>();
    for (IShape shape : this.shapes) {
      List<AnimationChanges> bucket = this.animations.get(shape.getName());
      for (AnimationChanges mutation : bucket) {
        if (time >= mutation.getStart() && time <= mutation.getEnd()) {
          list.add(mutation.executeChange(time));
        }
      }
    }
    return list;
  }

  @Override
  public String showShapeJourney(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name is null, can't be null");
    }
    String str = "";
    for (IShape shape : this.shapes) {
      if (shape.getName().equalsIgnoreCase(name)) {
        str += shape.toString();
        break;
      }
    }
    if (str.isEmpty()) {
      throw new IllegalArgumentException("The shape doesn't exist");
    }
    List<AnimationChanges> listOfChanges = this.animations.get(name);
    for (AnimationChanges each : listOfChanges) {
      str += each.stringForm(name);
    }
    return str;
  }

  @Override
  public void setCanvasDimensions(int x, int y, int height, int width) {
    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
  }

  @Override
  public int getCanvasHeight() {
    return this.height;
  }

  @Override
  public int getCanvasWidth() {
    return this.width;
  }

  @Override
  public double getEndTime() {
    return this.endTime;
  }

  @Override
  public void setEndTime(double endTime) {
    this.endTime = endTime;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public void setAnimationSpeed(double speed) {
    this.animationSpeed = speed;
  }

  @Override
  public double getAnimationSpeed() {
    return this.animationSpeed;
  }

  /** This class is the implementation for our Builder interface. */
  public static final class BobTheBuilder implements AnimationBuilder<EasyAnimatorModel> {

    private EasyAnimatorModel model;

    /**
     * Constructs a builder class.
     *
     * @param model The instance of the model we want to send in, so that the model's data
     *     structures can be populated.
     */
    public BobTheBuilder(EasyAnimatorModel model) {
      this.model = model;
    }

    @Override
    public EasyAnimatorModel build() {
      return this.model;
    }

    @Override
    public AnimationBuilder<EasyAnimatorModel> setBounds(int x, int y, int width, int height) {
      this.model.setCanvasDimensions(x, y, height, width);
      return this;
    }

    @Override
    public AnimationBuilder<EasyAnimatorModel> declareShape(String name, String type) {
      if (type.equalsIgnoreCase("rectangle")) {
        this.model.addShape(new Rectangle(name));
      } else if (type.equalsIgnoreCase("ellipse")) {
        this.model.addShape(new Oval(name));
      }
      return this;
    }

    @Override
    public AnimationBuilder<EasyAnimatorModel> addMotion(
        String name,
        int t1,
        int x1,
        int y1,
        int w1,
        int h1,
        int r1,
        int g1,
        int b1,
        int t2,
        int x2,
        int y2,
        int w2,
        int h2,
        int r2,
        int g2,
        int b2) {
      IShape newShape = null;
      double endTime = t2;
      if (endTime > model.getEndTime()) {
        model.setEndTime(endTime);
      }
      List<IShape> shapes = this.model.getShapes();

      for (int i = 0; i < this.model.getShapes().size(); i++) {

        if (shapes.get(i).getName().equalsIgnoreCase(name)
            && shapes.get(i).getShapeType() == ShapeType.RECTANGLE) {
          newShape = new Rectangle(name, x1, y1, h1, w1, new Color(r1, g1, b1), t1, t2);
          // If the t1 and t2 are equal to one another, then that means that's the first
          // time the shape appears.

          if ((t1 == t2)
              || (x1 == x2
                  && y1 == y2
                  && w1 == w2
                  && h1 == h2
                  && r1 == r2
                  && g1 == g2
                  && b1 == b2
                  && !shapes.get(i).getFlag())) {
            shapes.get(i).setFlag();
            newShape.setFlag();
            shapes.set(i, newShape);
          }

          // This statement will always be skipped over when the previous if-statement is met,
          // however every subsequent row of the animation text with the same name will have
          if (shapes.get(i).getTimeDisappear() < t2) {
            shapes.get(i).setTimeDisappear(t2);
          }
          break;
        } else if (shapes.get(i).getName().equalsIgnoreCase(name)
            && shapes.get(i).getShapeType() == ShapeType.OVAL) {

          newShape = new Oval(name, x1, y1, h1, w1, new Color(r1, g1, b1), t1, t2);

          if ((t1 == t2)
              || (x1 == x2
                  && y1 == y2
                  && w1 == w2
                  && h1 == h2
                  && r1 == r2
                  && g1 == g2
                  && b1 == b2
                  && !shapes.get(i).getFlag())) {
            shapes.get(i).setFlag();
            newShape.setFlag();
            shapes.set(i, newShape);
          }

          if (shapes.get(i).getTimeDisappear() < t2) {
            shapes.get(i).setTimeDisappear(t2);
          }
          break;
        }
      }
      // If there is actually some change
      if (Math.abs(x2 - x1) != 0 || Math.abs(y2 - y1) != 0) {
        this.model.moveShape(newShape, x2, y2, t1, t2);
      }
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        this.model.changeColor(newShape, r2, g2, b2, t1, t2);
      }
      if (h1 != h2 || w1 != w2) {
        this.model.scaleShape(newShape, w2, h2, t1, t2);
      } else {
        this.model.moveShape(newShape, x2, y2, t1, t2);
      }
      return this;
    }
  }
}
