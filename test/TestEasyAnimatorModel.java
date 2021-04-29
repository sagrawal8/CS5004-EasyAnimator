import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.EasyAnimatorModel;

import cs5004.animator.model.Oval;

import static org.junit.Assert.assertEquals;

/** This class tests the EasyAnimatorModel class. */
public class TestEasyAnimatorModel {

  IShape rectangle1;
  IShape square1;
  IShape circle1;
  IShape oval1;
  EasyAnimatorModel newAnimation;

  @Before
  public void setUp() {
    rectangle1 = new Rectangle("R1", 0, 0, 4, 7, new Color(200, 100, 60), 10, 100);
    square1 = new Rectangle("S1", -3, -3, 4, 4, new Color(0, 100, 170), 30, 70);
    circle1 = new Oval("C1", 5, 5, 4, 4, new Color(40, 150, 60), 0, 100);
    oval1 = new Oval("O1", 2, 3, 7, 3, new Color(1, 15, 10), 50, 120);
    newAnimation = new EasyAnimatorModelImpl();
  }

  /** Test Constructor. */
  @Test
  public void testConstructor() {
    assertEquals(-1, newAnimation.getEndTime(), 0.0);
    assertEquals(0, newAnimation.getCanvasHeight());
    assertEquals(0, newAnimation.getCanvasWidth());
    assertEquals(0, newAnimation.getX());
    assertEquals(0, newAnimation.getY());
    assertEquals(0, newAnimation.getAnimations().size());
    assertEquals(0, newAnimation.getShapes().size());
  }

  /** Testing the case in which the shape added is null. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddShape1() {
    newAnimation.addShape(null);
  }

  /**
   * Testing the case in which we're adding a shape with the same key as another shape in the
   * hashmap.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddShape2() {
    newAnimation.addShape(rectangle1);
    IShape rectangle2 = new Rectangle("R1", 0, 1, 3, 1, new Color(50, 100, 255), 20, 30);
    newAnimation.addShape(rectangle2);
  }

  /** Testing a valid addShape method. */
  @Test
  public void testAddShape() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    assertEquals(
        "Shapes:\n"
            + "Name: R1\n"
            + "Type: rectangle\n"
            + "Min corner: (0.0,0.0), Width: 7.0, Height: 4.0, Color: (200.0,100.0,60.0)\n"
            + "Appears at t=10.0\n"
            + "Disappears at t=100.0\n"
            + "\n"
            + "Name: S1\n"
            + "Type: rectangle\n"
            + "Min corner: (-3.0,-3.0), Width: 4.0, Height: 4.0, Color: (0.0,100.0,170.0)\n"
            + "Appears at t=30.0\n"
            + "Disappears at t=70.0\n"
            + "\n"
            + "Name: C1\n"
            + "Type: oval\n"
            + "Min corner: (5.0,5.0), Width: 4.0, Height: 4.0, Color: (40.0,150.0,60.0)\n"
            + "Appears at t=0.0\n"
            + "Disappears at t=100.0\n"
            + "\n"
            + "Name: O1\n"
            + "Type: oval\n"
            + "Min corner: (2.0,3.0), Width: 3.0, Height: 7.0, Color: (1.0,15.0,10.0)\n"
            + "Appears at t=50.0\n"
            + "Disappears at t=120.0\n\n",
        newAnimation.toString());
  }

  // ===================================== REMOVE-SHAPE TESTS ====================================
  /** Testing the case in which we try to remove a null shape. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRemoveShape1() {
    newAnimation.removeShape(null);
  }

  /** Testing the case in which we try to remove a shape that doesn't exist. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRemoveShape2() {
    newAnimation.removeShape(rectangle1.getName());
  }

  /** Testing a valid remove shape. */
  @Test
  public void testLegalRemoveShape1() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    newAnimation.removeShape(rectangle1.getName());
    assertEquals(
        "Shapes:\n"
            + "Name: S1\n"
            + "Type: rectangle\n"
            + "Min corner: (-3.0,-3.0), Width: 4.0, Height: 4.0, Color: (0.0,100.0,170.0)\n"
            + "Appears at t=30.0\n"
            + "Disappears at t=70.0\n"
            + "\n"
            + "Name: C1\n"
            + "Type: oval\n"
            + "Min corner: (5.0,5.0), Width: 4.0, Height: 4.0, Color: (40.0,150.0,60.0)\n"
            + "Appears at t=0.0\n"
            + "Disappears at t=100.0\n"
            + "\n"
            + "Name: O1\n"
            + "Type: oval\n"
            + "Min corner: (2.0,3.0), Width: 3.0, Height: 7.0, Color: (1.0,15.0,10.0)\n"
            + "Appears at t=50.0\n"
            + "Disappears at t=120.0\n"
            + "\n",
        newAnimation.toString());
  }

  // ===================================== MOVE-SHAPE TESTS ====================================
  /** Testing the case in which the shape passed in is null. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveShape1() {
    newAnimation.moveShape(null, 0, 0, 4, 5);
  }

  /**
   * Testing the case in which the start time is less than the shape's timeAppear, but the end time
   * is less than the shape's timeDisappear.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveShape2() {
    newAnimation.moveShape(rectangle1, 4, 5, 5, 50);
  }

  /**
   * Testing the case in which the start time is greater than the timeAppear but the end time is
   * greater than the shape's timeDisappear.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveShape3() {
    newAnimation.moveShape(square1, 0, 0, 35, 75);
  }

  /**
   * Testing the case in which the start time is less than the timeAppear for the shape AND the end
   * time is greater than the shape's timeDisappear.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveShape4() {
    newAnimation.moveShape(oval1, 0, 0, 40, 130);
  }

  /** Testing the case in which the start time is greater than the end time. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveShape5() {
    newAnimation.moveShape(circle1, 0, 0, 50, 40);
  }

  /** Testing the case in which there are conflicting motions. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveShape6() {
    newAnimation.addShape(circle1);
    newAnimation.moveShape(circle1, 0, 0, 50, 60);
    newAnimation.moveShape(circle1, 4, 4, 51, 52);
  }

  /** Testing a legal moveShape. */
  @Test
  public void testLegalMoveShape() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    assertEquals(0, newAnimation.getAnimations().get(rectangle1.getName()).size());
    newAnimation.moveShape(rectangle1, 4, 8, 30, 60);
    assertEquals(1, newAnimation.getAnimations().get(rectangle1.getName()).size());

    assertEquals(0, newAnimation.getAnimations().get(square1.getName()).size());
    newAnimation.moveShape(square1, 0, 0, 50, 60);
    assertEquals(1, newAnimation.getAnimations().get(square1.getName()).size());

    assertEquals(0, newAnimation.getAnimations().get(circle1.getName()).size());
    newAnimation.moveShape(circle1, 0, 0, 20, 90);
    assertEquals(1, newAnimation.getAnimations().get(circle1.getName()).size());

    assertEquals(0, newAnimation.getAnimations().get(oval1.getName()).size());
    newAnimation.moveShape(oval1, 2, 2, 51, 100);
    assertEquals(1, newAnimation.getAnimations().get(oval1.getName()).size());
  }

  // =================================== CHANGE-COLOR TESTS ====================================

  /** Testing an illegal ColorChange where the shape passed in is null. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColor1() {
    newAnimation.changeColor(null, 0, 1, 2, 30, 50);
  }

  /**
   * Testing an illegal Color Change where the start is less than the time appear but the end is
   * valid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColor2() {
    newAnimation.changeColor(rectangle1, 20, 30, 10, 5, 90);
  }

  /** Testing an illegal Color change when the start time is valid and the end time is invalid. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColor3() {
    newAnimation.changeColor(square1, 5, 50, 50, 35, 80);
  }

  /** Testing an illegal Color change when the start time is invalid AND the end time is invalid. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColor4() {
    newAnimation.changeColor(circle1, 2, 2, 2, -1, 110);
  }

  /** Testing the case where the start time is greater than the end time. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColors5() {
    newAnimation.changeColor(rectangle1, 155, 155, 155, 30, 15);
  }

  /** Testing negative RGB values. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColor6() {
    newAnimation.changeColor(square1, -20, -30, -40, 35, 40);
  }

  /** Testing RGB values greater than 255. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColor7() {
    newAnimation.changeColor(circle1, 256, 256, 256, 5, 6);
  }

  /** Testing the case in which there are conflicting change colors. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeColor8() {
    newAnimation.addShape(circle1);
    newAnimation.changeColor(circle1, 0, 0, 0, 50, 60);
    newAnimation.changeColor(circle1, 4, 4, 6, 51, 52);
  }

  /** Testing a valid changeColor. */
  @Test
  public void testLegalChangeColor() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);

    assertEquals(0, newAnimation.getAnimations().get(rectangle1.getName()).size());
    newAnimation.changeColor(rectangle1, 155, 155, 155, 30, 32);
    assertEquals(1, newAnimation.getAnimations().get(rectangle1.getName()).size());

    assertEquals(0, newAnimation.getAnimations().get(square1.getName()).size());
    newAnimation.changeColor(square1, 255, 255, 255, 50, 60);
    assertEquals(1, newAnimation.getAnimations().get(square1.getName()).size());

    assertEquals(0, newAnimation.getAnimations().get(circle1.getName()).size());
    newAnimation.changeColor(circle1, 0, 0, 0, 20, 50);
    assertEquals(1, newAnimation.getAnimations().get(circle1.getName()).size());

    assertEquals(0, newAnimation.getAnimations().get(oval1.getName()).size());
    newAnimation.changeColor(oval1, 2, 10, 15, 70, 90);
    assertEquals(1, newAnimation.getAnimations().get(oval1.getName()).size());
  }

  // ======================== SCALE-SHAPE TESTS ===============================
  /** This tests the case in which the shape passed into the second scaleShape method is null. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleShape1() {
    newAnimation.scaleShape(null, 1, 3, 30, 40);
  }

  /** Testing the case in which the new length is equal to 0. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleShape2() {
    newAnimation.scaleShape(rectangle1, 0, 4, 30, 45);
  }

  /** Testing the case in which the new length is negative. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleShape3() {
    newAnimation.scaleShape(square1, -1, 3, 35, 40);
  }

  /** Testing the case in which the new width is 0. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleShape4() {
    newAnimation.scaleShape(circle1, 3.45, 0, 4, 5);
  }

  /** Testing the case where the new width is negative. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleShape5() {
    newAnimation.scaleShape(oval1, 4.58, -2.1, 60, 61);
  }

  /** Testing the case where the start time is invalid. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleShape6() {
    newAnimation.scaleShape(rectangle1, 5, 5, 5, 15);
  }

  /** Testing the case where the end time is invalid. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleShape7() {
    newAnimation.scaleShape(square1, 3, 4, 35, 71);
  }

  /** Testing the case where the end is less than the start. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleShape8() {
    newAnimation.scaleShape(oval1, 2.3, 4.8, 10, 9);
  }

  /** Testing the case in which there are conflicting scale shape times. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveShape9() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    newAnimation.scaleShape(circle1, 34, 78, 50, 60);
    newAnimation.scaleShape(circle1, 4, 4, 51, 52);
  }

  /**
   * Testing the case in which the new length and new width are equal to the old length and old
   * width.
   */
  @Test
  public void testIllegalScaleShape10() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    newAnimation.scaleShape(rectangle1, 4, 7, 25, 35);
    assertEquals(4.0, rectangle1.getLength(), 0.001);
    assertEquals(7.0, rectangle1.getWidth(), 0.001);
  }

  /** Testing legal scale shapes. */
  @Test
  public void testLegalScaleShape1() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    // Scaling a rectangle to another rectangle
    assertEquals(0, newAnimation.getAnimations().get(rectangle1.getName()).size());
    newAnimation.scaleShape(rectangle1, 2, 1, 12, 23);
    assertEquals(1, newAnimation.getAnimations().get(rectangle1.getName()).size());

    // Scaling a rectangle to a square
    newAnimation.scaleShape(rectangle1, 3, 3, 23, 24);
    assertEquals(2, newAnimation.getAnimations().get(rectangle1.getName()).size());

    // Scaling a square to a rectangle
    assertEquals(0, newAnimation.getAnimations().get(square1.getName()).size());
    newAnimation.scaleShape(square1, 1.0, 4.5, 40, 45);
    assertEquals(1, newAnimation.getAnimations().get(square1.getName()).size());

    // Scaling a square to another square
    newAnimation.scaleShape(square1, 6, 6, 45, 50);
    assertEquals(2, newAnimation.getAnimations().get(square1.getName()).size());

    // Scaling a circle to another circle
    assertEquals(0, newAnimation.getAnimations().get(circle1.getName()).size());
    newAnimation.scaleShape(circle1, 5, 5, 20, 25);
    assertEquals(1, newAnimation.getAnimations().get(circle1.getName()).size());

    // Scaling a circle to an oval
    newAnimation.scaleShape(circle1, 4, 3, 15, 20);
    assertEquals(2, newAnimation.getAnimations().get(circle1.getName()).size());

    // Scaling an oval to a circle
    assertEquals(0, newAnimation.getAnimations().get(oval1.getName()).size());
    newAnimation.scaleShape(oval1, 3, 3, 55, 60);
    assertEquals(1, newAnimation.getAnimations().get(oval1.getName()).size());

    // Scaling an oval to another oval
    newAnimation.scaleShape(oval1, 2, 1, 60, 65);
    assertEquals(2, newAnimation.getAnimations().get(oval1.getName()).size());
  }

  /** Testing the toString method. */
  @Test
  public void testToString() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    assertEquals(
        "Shapes:\n"
            + "Name: R1\n"
            + "Type: rectangle\n"
            + "Min corner: (0.0,0.0), Width: 7.0, Height: 4.0, Color: (200.0,100.0,60.0)\n"
            + "Appears at t=10.0\n"
            + "Disappears at t=100.0\n"
            + "\n"
            + "Name: S1\n"
            + "Type: rectangle\n"
            + "Min corner: (-3.0,-3.0), Width: 4.0, Height: 4.0, Color: (0.0,100.0,170.0)\n"
            + "Appears at t=30.0\n"
            + "Disappears at t=70.0\n"
            + "\n"
            + "Name: C1\n"
            + "Type: oval\n"
            + "Min corner: (5.0,5.0), Width: 4.0, Height: 4.0, Color: (40.0,150.0,60.0)\n"
            + "Appears at t=0.0\n"
            + "Disappears at t=100.0\n"
            + "\n"
            + "Name: O1\n"
            + "Type: oval\n"
            + "Min corner: (2.0,3.0), Width: 3.0, Height: 7.0, Color: (1.0,15.0,10.0)\n"
            + "Appears at t=50.0\n"
            + "Disappears at t=120.0\n\n",
        newAnimation.toString());
  }

  // ======================== SHAPE-JOURNEY TESTS ===============================
  /** This tests the case in which the name is null. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeJourney1() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    newAnimation.scaleShape(rectangle1, 3, 3, 15, 20);
    newAnimation.changeColor(rectangle1, 155, 155, 155, 30, 32);
    newAnimation.moveShape(rectangle1, 4, 8, 30, 60);
    newAnimation.showShapeJourney(null);
  }

  /** This tests the case in which the name doesn't exist. */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalShapeJourney2() {
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    newAnimation.showShapeJourney(rectangle1.getName());
  }

  /** This tests a legal shapeJourney. */
  @Test
  public void testLegalShapeJourney1() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    newAnimation.scaleShape(rectangle1, 3, 3, 15, 20);
    newAnimation.changeColor(rectangle1, 155, 155, 155, 30, 32);
    newAnimation.moveShape(rectangle1, 4, 8, 30, 60);
    assertEquals(
        "\nName: R1\nType: rectangle\n"
            + "Min corner: (0.0,0.0), Width: 7.0, Height: 4.0, Color: (200.0,100.0,60.0)\n"
            + "Appears at t=10.0\n"
            + "Disappears at t=100.0\n"
            + "Shape R1 scales from Width: 4.0, Height: 7.0 to Width: 3.0, Height: 3.0 "
            + "from t=15 to t=20\n"
            + "Shape R1 changes color from (200.0,100.0,60.0) to (155.0,155.0,155.0) "
            + "from t=30 to t=32\n"
            + "Shape R1 moves from (0.0,0.0) to (4.0,8.0) from time t=30 to t=60\n",
        newAnimation.showShapeJourney(rectangle1.getName()));
  }

  /** This tests a legal shapeJourney. */
  @Test
  public void testLegalShapeJourney2() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    newAnimation.scaleShape(circle1, 5, 10, 14, 32);
    newAnimation.changeColor(circle1, 15, 155, 135, 33, 34);
    newAnimation.moveShape(circle1, 4, 8, 30, 60);
    assertEquals(
        "\nName: C1\n"
            + "Type: oval\n"
            + "Min corner: (5.0,5.0), Width: 4.0, Height: 4.0, Color: (40.0,150.0,60.0)\n"
            + "Appears at t=0.0\n"
            + "Disappears at t=100.0\n"
            + "Shape C1 scales from Width: 4.0, Height: 4.0 to Width: 5.0, Height: "
            + "10.0 from t=14 to t=32\n"
            + "Shape C1 changes color from (40.0,150.0,60.0) to (15.0,155.0,135.0) "
            + "from t=33 to t=34\n"
            + "Shape C1 moves from (5.0,5.0) to (4.0,8.0) from time t=30 to t=60\n",
        newAnimation.showShapeJourney(circle1.getName()));
  }

  /** This tests setting canvas dimensions. */
  @Test
  public void testSetCanvasDimensions() {
    newAnimation.setCanvasDimensions(5, 10, 34, 432);
    assertEquals(34, newAnimation.getCanvasHeight());
    assertEquals(432, newAnimation.getCanvasWidth());
    assertEquals(5, newAnimation.getX());
    assertEquals(10, newAnimation.getY());
  }

  /** This tests setting time disappear. */
  @Test
  public void testSetTimeDisappear() {
    newAnimation.setEndTime(5);
    assertEquals(5, newAnimation.getEndTime(), 0.01);
  }

  /** This tests getter method for Hashmap. */
  @Test
  public void testGetAnimations() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    assertEquals(4, newAnimation.getAnimations().size(), 0.0);
  }

  /** This tests getter method for ArrayList. */
  @Test
  public void testGetShapes() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(circle1);
    newAnimation.addShape(oval1);
    assertEquals(4, newAnimation.getShapes().size(), 0.0);
  }

  /** Tests getShapesAtTimeStamp(double time) method. No shapes present. */
  @Test
  public void testGetShapesAtTimeStamp_1() {
    assertEquals(0, newAnimation.getShapes().size());
    assertEquals(0, newAnimation.getShapesAtTimeStamp(2).size());
  }

  /**
   * Tests getShapesAtTimeStamp(double time) method. Shapes have been declared but no mutations are
   * present.
   */
  @Test
  public void testGetShapesAtTimeStamp_2() {
    newAnimation.addShape(rectangle1);
    newAnimation.addShape(square1);
    newAnimation.addShape(oval1);
    newAnimation.addShape(circle1);
    assertEquals(4, newAnimation.getShapes().size());
    assertEquals(0, newAnimation.getShapesAtTimeStamp(5).size());
  }

  /**
   * Tests getShapesAtTimeStamp(double time) method. Shapes have been declared and mutations are
   * present.
   */
  @Test
  public void testGetShapesAtTimeStamp_3() {
    //    rectangle1 = new Rectangle("R1", 0, 0, 4, 7, new Color(200, 100, 60), 10, 100);
    //    square1 = new Rectangle("S1", -3, -3, 4, 4, new Color(0, 100, 170), 30, 70);
    //    circle1 = new Oval("C1", 5, 5, 4, 4, new Color(40, 150, 60), 0, 100);
    //    oval1 = new Oval("O1", 2, 3, 7, 3, new Color(1, 15, 10), 50, 120);
    newAnimation.addShape(rectangle1);
    newAnimation.moveShape(rectangle1, 4, 4, 10, 11);
    newAnimation.moveShape(rectangle1, 4, 4, 15, 19);
    newAnimation.addShape(square1);
    newAnimation.moveShape(square1, 5, 6, 31, 35);
    newAnimation.moveShape(square1, 0, 0, 37, 45);
    newAnimation.moveShape(square1, -1, -3, 45, 65);
    newAnimation.addShape(circle1);
    newAnimation.moveShape(circle1, 4, 4, 2, 4);
    newAnimation.changeColor(circle1, 4, 4, 10, 1, 2);
    newAnimation.addShape(oval1);
    newAnimation.scaleShape(oval1, 4, 4, 62, 120);
    newAnimation.changeColor(oval1, 5, 4, 5, 119, 120);
    assertEquals(1, newAnimation.getShapesAtTimeStamp(1.5).size());
    assertEquals("C1", newAnimation.getShapesAtTimeStamp(1.5).get(0).getName());
    assertEquals(2, newAnimation.getShapesAtTimeStamp(2).size());
    assertEquals("C1", newAnimation.getShapesAtTimeStamp(2).get(0).getName());
    assertEquals("C1", newAnimation.getShapesAtTimeStamp(2).get(1).getName());
    assertEquals(1, newAnimation.getShapesAtTimeStamp(11).size());
    assertEquals("R1", newAnimation.getShapesAtTimeStamp(11).get(0).getName());
    assertEquals(1, newAnimation.getShapesAtTimeStamp(16).size());
    assertEquals("R1", newAnimation.getShapesAtTimeStamp(16).get(0).getName());
    assertEquals(2, newAnimation.getShapesAtTimeStamp(119).size());
    assertEquals("O1", newAnimation.getShapesAtTimeStamp(119).get(0).getName());
    assertEquals("O1", newAnimation.getShapesAtTimeStamp(119).get(1).getName());
  }
}
