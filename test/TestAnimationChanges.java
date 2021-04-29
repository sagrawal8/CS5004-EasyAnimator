import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.geom.Point2D;

import cs5004.animator.model.IShape;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.AnimationChanges;
import cs5004.animator.model.Oval;
import cs5004.animator.model.MoveShape;
import cs5004.animator.model.ChangeColor;
import cs5004.animator.model.ScaleShape;
import cs5004.animator.model.Transformations;

import static org.junit.Assert.assertEquals;

/** This class tests the AnimationChanges Interface. */
public class TestAnimationChanges {
  IShape shape1;
  IShape shape2;

  @Before
  public void init() {
    shape1 = new Rectangle("R1", 2.444, 5.32234, 10.12, 20, new Color(100, 200, 43), 2, 100);
    shape2 = new Oval("O1", 2, 5.1, 10.0, 20.003, new Color(100, 0, 43), 0, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveShape_NullNewPos() {
    new MoveShape(null, shape1, 5, 10);
  }

  @Test
  public void testValidMoveShapeConstructor_1() {
    AnimationChanges move = new MoveShape(new Point2D.Double(5.45, 10.42), shape1, 5, 10);
    assertEquals(
        "Shape "
            + "R1"
            + " moves from "
            + "("
            + 2.444
            + ","
            + 5.32234
            + ")"
            + " to "
            + "("
            + 5.45
            + ","
            + 10.42
            + ")"
            + " from time t="
            + 5
            + " to t="
            + 10
            + "\n",
        move.stringForm(shape1.getName()));
  }

  @Test
  public void testValidMoveShapeConstructor_2() {
    AnimationChanges move = new MoveShape(new Point2D.Double(5.00001, 3.003230), shape2, 5, 10);
    assertEquals(
        "Shape "
            + "O1"
            + " moves from "
            + "("
            + 2.0
            + ","
            + 5.1
            + ")"
            + " to "
            + "("
            + 5.00001
            + ","
            + 3.003230
            + ")"
            + " from time t="
            + 5
            + " to t="
            + 10
            + "\n",
        move.stringForm(shape2.getName()));
  }

  @Test
  public void testMoveExecuteChange_1() {
    AnimationChanges move = new MoveShape(new Point2D.Double(5, 6), shape1, 5, 10);
    move.executeChange(6);
    assertEquals(2.95, shape1.getX(), 0.01);
    assertEquals(5.45, shape1.getY(), 0.01);
    assertEquals(
        "Shape "
            + "R1"
            + " moves from "
            + "("
            + 2.444
            + ","
            + 5.32234
            + ")"
            + " to "
            + "("
            + 5.0
            + ","
            + 6.0
            + ")"
            + " from time t="
            + 5
            + " to t="
            + 10
            + "\n",
        move.stringForm(shape1.getName()));
  }

  @Test
  public void testMoveExecuteChange_2() {
    AnimationChanges move = new MoveShape(new Point2D.Double(0, 0), shape2, 5, 10);
    move.executeChange(10);
    assertEquals(0, shape2.getX(), 0.01);
    assertEquals(0, shape2.getY(), 0.01);
    assertEquals(
        "Shape "
            + "O1"
            + " moves from "
            + "("
            + 2.0
            + ","
            + 5.1
            + ")"
            + " to "
            + "("
            + 0.0
            + ","
            + 0.0
            + ")"
            + " from time t="
            + 5
            + " to t="
            + 10
            + "\n",
        move.stringForm(shape2.getName()));
  }

  @Test
  public void testGetStart_a() {
    assertEquals(5, new MoveShape(new Point2D.Double(0, 0), shape2, 5, 10).getStart(), 0.01);
  }

  @Test
  public void testGetEnd_a() {
    assertEquals(10, new MoveShape(new Point2D.Double(0, 0), shape2, 5, 10).getEnd(), 0.01);
  }

  @Test
  public void testGetTransformation_a() {
    assertEquals(
        Transformations.MOVE_SHAPE,
        new MoveShape(new Point2D.Double(0, 0), shape2, 5, 10).getTransformation());
  }

  @Test
  public void testGetOld_a() {
    MoveShape obj = new MoveShape(new Point2D.Double(0, 0), shape2, 5, 10);
    assertEquals(2, obj.getOld().getX(), 0.01);
    assertEquals(5.1, obj.getOld().getY(), 0.01);
  }

  @Test
  public void testGetNew_a() {
    MoveShape obj = new MoveShape(new Point2D.Double(0, 1), shape1, 5, 10);
    assertEquals(0, obj.getNew().getX(), 0.01);
    assertEquals(1, obj.getNew().getY(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidChangeColorConstructor_NullColor() {
    new ChangeColor(null, shape1, 5, 10);
  }

  @Test
  public void testValidChangeColorConstructor_1() {
    AnimationChanges changeColor = new ChangeColor(new Color(10, 20, 30), shape1, 5, 10);
    assertEquals(
        "Shape "
            + "R1"
            + " changes color from ("
            + 100.0
            + ","
            + 200.0
            + ","
            + 43.0
            + ") to ("
            + 10.0
            + ","
            + 20.0
            + ","
            + 30.0
            + ") from t="
            + 5
            + " to t="
            + 10
            + "\n",
        changeColor.stringForm(shape1.getName()));
  }

  @Test
  public void testValidChangeColorConstructor_2() {
    AnimationChanges changeColor = new ChangeColor(new Color(30, 200, 245), shape2, 5, 10);
    assertEquals(
        "Shape "
            + "O1"
            + " changes color from ("
            + 100.0
            + ","
            + 0.0
            + ","
            + 43.0
            + ") to ("
            + 30.0
            + ","
            + 200.0
            + ","
            + 245.0
            + ") from t="
            + 5
            + " to t="
            + 10
            + "\n",
        changeColor.stringForm(shape2.getName()));
  }

  @Test
  public void testChangeColorExecuteChange_1() {
    AnimationChanges changeColor = new ChangeColor(new Color(10, 20, 30), shape1, 5, 10);
    changeColor.executeChange(8);
    assertEquals(46.0, shape1.getColor().getRed(), 0.01);
    assertEquals(92.0, shape1.getColor().getGreen(), 0.01);
    assertEquals(35, shape1.getColor().getBlue(), 0.01);
    assertEquals(
        "Shape "
            + "R1"
            + " changes color from ("
            + 100.0
            + ","
            + 200.0
            + ","
            + 43.0
            + ") to ("
            + 10.0
            + ","
            + 20.0
            + ","
            + 30.0
            + ") from t="
            + 5
            + " to t="
            + 10
            + "\n",
        changeColor.stringForm(shape1.getName()));
  }

  @Test
  public void testChangeColorExecuteChange_2() {
    AnimationChanges changeColor = new ChangeColor(new Color(30, 20, 245), shape2, 5, 10);
    changeColor.executeChange(10);
    assertEquals(30.0, shape2.getColor().getRed(), 0.01);
    assertEquals(20.0, shape2.getColor().getGreen(), 0.01);
    assertEquals(245.0, shape2.getColor().getBlue(), 0.01);
    assertEquals(
        "Shape "
            + "O1"
            + " changes color from ("
            + 100.0
            + ","
            + 0.0
            + ","
            + 43.0
            + ") to ("
            + 30.0
            + ","
            + 20.0
            + ","
            + 245.0
            + ") from t="
            + 5
            + " to t="
            + 10
            + "\n",
        changeColor.stringForm(shape2.getName()));
  }

  @Test
  public void testGetStart_b() {
    assertEquals(5, new ChangeColor(new Color(30, 20, 245), shape2, 5, 10).getStart(), 0.01);
  }

  @Test
  public void testGetEnd_b() {
    assertEquals(10, new ChangeColor(new Color(30, 20, 245), shape2, 5, 10).getEnd(), 0.01);
  }

  @Test
  public void testGetTransformation_b() {
    assertEquals(
        Transformations.CHANGE_COLOR,
        new ChangeColor(new Color(30, 20, 245), shape2, 5, 10).getTransformation());
  }

  @Test
  public void testGetOld_b() {
    ChangeColor obj = new ChangeColor(new Color(255, 255, 255), shape2, 5, 10);
    assertEquals(100, obj.getOld().getRed(), 0.01);
    assertEquals(0, obj.getOld().getGreen(), 0.01);
    assertEquals(43, obj.getOld().getBlue(), 0.01);
  }

  @Test
  public void testGetNew_b() {
    ChangeColor obj = new ChangeColor(new Color(255, 254, 253), shape1, 5, 10);
    assertEquals(255, obj.getNew().getRed(), 0.01);
    assertEquals(254, obj.getNew().getGreen(), 0.01);
    assertEquals(253, obj.getNew().getBlue(), 0.01);
  }

  @Test
  public void testValidScaleShapeConstructor_1() {
    AnimationChanges scaleShape = new ScaleShape(1.32, 0.001, shape1, 3, 99);
    assertEquals(
        "Shape "
            + "R1"
            + " scales from Width: "
            + 10.12
            + ", Height: "
            + 20.0
            + " to Width: "
            + 1.32
            + ", Height: "
            + 0.001
            + " from t="
            + 3
            + " to t="
            + 99
            + "\n",
        scaleShape.stringForm(shape1.getName()));
  }

  @Test
  public void testValidScaleShapeConstructor_2() {
    AnimationChanges scaleShape = new ScaleShape(99.0, 3.002, shape2, 3, 49);
    assertEquals(
        "Shape "
            + "O1"
            + " scales from Width: "
            + 10.0
            + ", Height: "
            + 20.003
            + " to Width: "
            + 99.0
            + ", Height: "
            + 3.002
            + " from t="
            + 3
            + " to t="
            + 49
            + "\n",
        scaleShape.stringForm(shape2.getName()));
  }

  @Test
  public void testScaleShapeExecuteChange_1() {
    AnimationChanges scaleShape = new ScaleShape(1.32, 0.001, shape1, 3, 99);

    assertEquals(10.12, shape1.getLength(), 0.01);
    assertEquals(20, shape1.getWidth(), 0.001);
    scaleShape.executeChange(10);
    assertEquals(18.54, shape1.getLength(), 0.01);
    assertEquals(9.47, shape1.getWidth(), 0.01);
    assertEquals(
        "Shape "
            + "R1"
            + " scales from Width: "
            + 10.12
            + ", Height: "
            + 20.0
            + " to Width: "
            + 1.32
            + ", Height: "
            + 0.001
            + " from t="
            + 3
            + " to t="
            + 99
            + "\n",
        scaleShape.stringForm(shape1.getName()));
  }

  @Test
  public void testScaleShapeExecuteChange_2() {
    AnimationChanges scaleShape = new ScaleShape(99.0, 3.002, shape2, 3, 49);
    scaleShape.executeChange(27);
    assertEquals(11.13, shape2.getLength(), 0.01);
    assertEquals(56.43, shape2.getWidth(), 0.01);
    assertEquals(
        "Shape "
            + "R1"
            + " scales from Width: "
            + 10.0
            + ", Height: "
            + 20.003
            + " to Width: "
            + 99.0
            + ", Height: "
            + 3.002
            + " from t="
            + 3
            + " to t="
            + 49
            + "\n",
        scaleShape.stringForm(shape1.getName()));
  }

  @Test
  public void testGetStart_c() {
    assertEquals(3, new ScaleShape(99.0, 3.002, shape2, 3, 49).getStart(), 0.01);
  }

  @Test
  public void testGetEnd_c() {
    assertEquals(49, new ScaleShape(99.0, 3.002, shape2, 3, 49).getEnd(), 0.01);
  }

  @Test
  public void testGetTransformation_c() {
    assertEquals(
        Transformations.SCALE_SHAPE,
        new ScaleShape(99.0, 3.002, shape2, 3, 49).getTransformation());
  }
}
