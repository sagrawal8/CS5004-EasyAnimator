import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import cs5004.animator.model.AnimationChanges;
import cs5004.animator.model.EasyAnimatorModel;
import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.model.IShape;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ShapeType;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;

import static org.junit.Assert.assertEquals;

/**
 * This class is a test class for the builder that is responsible for populating the model with our
 * IShape objects and their corresponding transformations.
 */
public class TestBuilder {

  IShape rectangle1;
  IShape square1;
  IShape circle1;
  IShape oval1;
  EasyAnimatorModel newAnimation;
  EasyAnimatorModel emptyAnimation;
  AnimationBuilder<EasyAnimatorModel> builder;

  /**
   * This is a setup method. This method gets run everytime a a test method of this class runs its
   * test. It basically "resets" everything and starts from the beginning.
   */
  @Before
  public void setUp() {
    rectangle1 = new Rectangle("R1", 0, 0, 4, 7, new Color(200, 100, 60), 10, 100);
    square1 = new Rectangle("S1", -3, -3, 4, 4, new Color(0, 100, 170), 30, 70);
    circle1 = new Oval("C1", 5, 5, 4, 4, new Color(40, 150, 60), 0, 100);
    oval1 = new Oval("O1", 2, 3, 7, 3, new Color(1, 15, 10), 50, 120);
    newAnimation = new EasyAnimatorModelImpl();
    emptyAnimation = new EasyAnimatorModelImpl();

    builder = new EasyAnimatorModelImpl.BobTheBuilder(newAnimation);

    // Readable
    // String filePath = new File("").getAbsolutePath();
    // Readable readable = new FileReader(filePath + "/" + "toh-8-Test.txt");
    // Parse File
    // AnimationReader.parseFile(readable, builder);
  }

  /**
   * This method tests the constructor of the model. It shows that the data structures are
   * initialized and empty.
   */
  @Test
  public void validModelConstructor() {

    assertEquals("Shapes:\n", newAnimation.toString());
    assertEquals("Shapes:\n", emptyAnimation.toString());
  }

  /**
   * This method tests the build() method of the builder. The build method is responsible for
   * initializing all the transformations that our IShape objects go through.
   *
   * @throws FileNotFoundException This is a checked exception that needs to be thrown if a file is
   *     not found.
   */
  @Test
  public void testBuild() throws FileNotFoundException {

    assertEquals("[]", newAnimation.getShapes().toString());

    String filePath = new File("").getAbsolutePath();
    Readable readable = new FileReader(filePath + "/" + "toh-8-Test.txt");
    AnimationReader.parseFile(readable, builder);

    assertEquals(
        "[\n"
            + "Name: disk1\n"
            + "Type: rectangle\n"
            + "Min corner: (190.0,161.0), Width: 20.0, Height: 11.0, Color: (113.0,87.0,151.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=111.0\n"
            + ", \n"
            + "Name: disk2\n"
            + "Type: rectangle\n"
            + "Min corner: (483.0,238.0), Width: 32.0, Height: 11.0, Color: (35.0,173.0,73.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=207.0\n"
            + ", \n"
            + "Name: disk3\n"
            + "Type: rectangle\n"
            + "Min corner: (477.0,50.0), Width: 45.0, Height: 11.0, Color: (9.0,67.0,130.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=409.0\n"
            + ", \n"
            + "Name: disk4\n"
            + "Type: rectangle\n"
            + "Min corner: (170.0,50.0), Width: 58.0, Height: 11.0, Color: (96.0,198.0,208.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=281.0\n"
            + "]",
        newAnimation.getShapes().toString());
  }

  /**
   * This method tests the build() method of the builder. The build method is responsible for
   * initializing all the transformations that our IShape objects go through.
   *
   * @throws FileNotFoundException This is a checked exception that needs to be thrown if a file is
   *     not found.
   */
  @Test
  public void testBuild2() throws FileNotFoundException {

    String filePath = new File("").getAbsolutePath();
    Readable newReadable = new FileReader(filePath + "/" + "buildingsTest.txt");
    AnimationReader.parseFile(newReadable, builder);

    assertEquals(
        "[\n"
            + "Name: background\n"
            + "Type: rectangle\n"
            + "Min corner: (0.0,0.0), Width: 800.0, Height: 800.0, Color: (33.0,94.0,248.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=50.0\n"
            + ", \n"
            + "Name: B0\n"
            + "Type: rectangle\n"
            + "Min corner: (80.0,424.0), Width: 100.0, Height: 326.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=200.0\n"
            + ", \n"
            + "Name: B1\n"
            + "Type: rectangle\n"
            + "Min corner: (260.0,365.0), Width: 100.0, Height: 385.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=200.0\n"
            + ", \n"
            + "Name: window351\n"
            + "Type: rectangle\n"
            + "Min corner: (680.0,-550.0), Width: 20.0, Height: 20.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=20.0\n"
            + ", \n"
            + "Name: window360\n"
            + "Type: rectangle\n"
            + "Min corner: (640.0,-510.0), Width: 20.0, Height: 20.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=20.0\n"
            + ", \n"
            + "Name: moon\n"
            + "Type: oval\n"
            + "Min corner: (200.0,200.0), Width: 100.0, Height: 100.0, Color: (229.0,229.0,255.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=200.0\n"
            + ", \n"
            + "Name: eclipse\n"
            + "Type: oval\n"
            + "Min corner: (400.0,0.0), Width: 100.0, Height: 100.0, Color: (33.0,94.0,248.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=50.0\n"
            + ", \n"
            + "Name: star0\n"
            + "Type: oval\n"
            + "Min corner: (223.0,66.0), Width: 6.0, Height: 6.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=108.0\n"
            + "Disappears at t=200.0\n"
            + ", \n"
            + "Name: star1\n"
            + "Type: oval\n"
            + "Min corner: (585.0,211.0), Width: 6.0, Height: 6.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=120.0\n"
            + "Disappears at t=200.0\n"
            + ", \n"
            + "Name: star2\n"
            + "Type: oval\n"
            + "Min corner: (489.0,77.0), Width: 6.0, Height: 6.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=110.0\n"
            + "Disappears at t=200.0\n"
            + "]",
        newAnimation.getShapes().toString());
  }

  /**
   * This method tests an invalid file name to make sure that a FileNotFound exception is thrown.
   *
   * @throws FileNotFoundException This is a checked exception that needs to be thrown if a file is
   *     * not found.
   */
  @Test(expected = FileNotFoundException.class)
  public void testInvalidBuild() throws FileNotFoundException {
    String filePath = new File("").getAbsolutePath();
    Readable newReadable = new FileReader(filePath + "/" + "gibberish.txt");
    AnimationReader.parseFile(newReadable, builder);
  }

  /**
   * This method tests if a NullPointerException is thrown when a null object is passed in for the
   * builder.
   *
   * @throws FileNotFoundException This is a checked exception that needs to be thrown if a file is
   *     * not found.
   */
  @Test(expected = NullPointerException.class)
  public void testInvalidBuild2() throws FileNotFoundException {
    String filePath = new File("").getAbsolutePath();
    Readable newReadable = new FileReader(filePath + "/" + "buildings.txt");
    AnimationReader.parseFile(newReadable, null);
  }

  /**
   * This method tests if a NullPointerException is thrown when a null object is passed in for the
   * reader.
   *
   * @throws FileNotFoundException This is a checked exception that needs to be thrown if a file is
   *     * not found.
   */
  @Test(expected = NullPointerException.class)
  public void testInvalidBuild3() throws FileNotFoundException {
    String filePath = new File("").getAbsolutePath();
    Readable newReadable = new FileReader(filePath + "/" + "buildings.txt");
    AnimationReader.parseFile(null, builder);
  }

  /**
   * This method tests the setBounds() method of the builder. The build method is responsible for
   * being able to change the dimensions of our canvas.
   *
   * @throws FileNotFoundException This is a checked exception that needs to be thrown if a file is
   *     not found.
   */
  @Test
  public void testSetBounds() throws FileNotFoundException {
    String filePath = new File("").getAbsolutePath();
    Readable newReadable = new FileReader(filePath + "/" + "buildingsTest.txt");
    AnimationReader.parseFile(newReadable, builder);

    assertEquals(800, newAnimation.getCanvasHeight());
    assertEquals(800, newAnimation.getCanvasWidth());
    assertEquals(0, newAnimation.getX());
    assertEquals(0, newAnimation.getY());

    builder.setBounds(25, 25, 25, 25);

    assertEquals(25, newAnimation.getCanvasHeight());
    assertEquals(25, newAnimation.getCanvasWidth());
    assertEquals(25, newAnimation.getX());
    assertEquals(25, newAnimation.getY());
  }

  /**
   * This method tests the declareShape() method of the builder. The declareShape method initializes
   * the IShape object and "Declares" the shape that will be used eventually for the animation.
   *
   * @throws FileNotFoundException This is a checked exception that needs to be thrown if a file is
   *     not found.
   */
  @Test
  public void testDeclareShape() {

    assertEquals("[]", newAnimation.getShapes().toString());
    builder.declareShape("FirstRectangle", "Rectangle");
    builder.declareShape("SecondOval", "ellipse");

    IShape newRect = newAnimation.getShapes().get(0);
    IShape newOval = newAnimation.getShapes().get(1);
    assertEquals(ShapeType.RECTANGLE, newRect.getShapeType());
    assertEquals(ShapeType.OVAL, newOval.getShapeType());
  }

  @Test
  public void testAddMotion() {

    builder.declareShape("FirstRectangle", "Rectangle");
    builder.declareShape("SecondOval", "ellipse");

    builder.addMotion("FirstRectangle", 1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 25, 19, 34, 11, 78, 100);
    List<AnimationChanges> shapes = newAnimation.getAnimations().get("FirstRectangle");

    AnimationChanges moveShape = shapes.get(0);

    assertEquals(
        "Shape FirstRectangle moves from (2.0,3.0) to (20.0,25.0) from time t=1 to t=9\n",
        moveShape.stringForm("FirstRectangle"));
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
}
