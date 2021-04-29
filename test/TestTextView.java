import java.io.File;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.EasyAnimatorModel;
import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.model.Rectangle;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;
import cs5004.animator.view.TextView;
import cs5004.animator.view.ViewFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** This class tests the TextView. */
public class TestTextView {

  String basePath = new File("").getAbsolutePath();
  EasyAnimatorModel myModel;
  AnimationBuilder<EasyAnimatorModel> builder;

  @Before
  public void init() {
    myModel = new EasyAnimatorModelImpl();
    builder = new EasyAnimatorModelImpl.BobTheBuilder(myModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor_NullModel() {
    new TextView(null);
  }

  @Test
  public void testValidConstructor() {
    EasyAnimatorModel model = new EasyAnimatorModelImpl();
    model.addShape((new Rectangle("R1", 4, 4, 5, 5, new Color(0, 3, 3), 5, 8)));
    TextView obj = new TextView(model);
    assertTrue(model.equals(obj.getModel()));
    assertEquals(model.getShapes().size(), 1);
    assertEquals("text", obj.getViewType());
  }

  @Test
  public void testShowView_Buildings() throws FileNotFoundException {

    Readable readable = new FileReader(basePath + "\\buildingsTest.txt");
    AnimationReader.parseFile(readable, builder);
    IView obj = new TextView(myModel);
    assertEquals(
        "Shapes:\n"
            + "Name: background\n"
            + "Type: rectangle\n"
            + "Min corner: (0.0,0.0), Width: 800.0, Height: 800.0, Color: (33.0,94.0,248.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=50.0\n"
            + "\n"
            + "Name: B0\n"
            + "Type: rectangle\n"
            + "Min corner: (80.0,424.0), Width: 100.0, Height: 326.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=200.0\n"
            + "\n"
            + "Name: B1\n"
            + "Type: rectangle\n"
            + "Min corner: (260.0,365.0), Width: 100.0, Height: 385.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=200.0\n"
            + "\n"
            + "Name: window351\n"
            + "Type: rectangle\n"
            + "Min corner: (680.0,-550.0), Width: 20.0, Height: 20.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=20.0\n"
            + "\n"
            + "Name: window360\n"
            + "Type: rectangle\n"
            + "Min corner: (640.0,-510.0), Width: 20.0, Height: 20.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=20.0\n"
            + "\n"
            + "Name: moon\n"
            + "Type: oval\n"
            + "Min corner: (200.0,200.0), Width: 100.0, Height: 100.0, Color: (229.0,229.0,255.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=200.0\n"
            + "\n"
            + "Name: eclipse\n"
            + "Type: oval\n"
            + "Min corner: (400.0,0.0), Width: 100.0, Height: 100.0, Color: (33.0,94.0,248.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=50.0\n"
            + "\n"
            + "Name: star0\n"
            + "Type: oval\n"
            + "Min corner: (223.0,66.0), Width: 6.0, Height: 6.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=108.0\n"
            + "Disappears at t=200.0\n"
            + "\n"
            + "Name: star1\n"
            + "Type: oval\n"
            + "Min corner: (585.0,211.0), Width: 6.0, Height: 6.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=120.0\n"
            + "Disappears at t=200.0\n"
            + "\n"
            + "Name: star2\n"
            + "Type: oval\n"
            + "Min corner: (489.0,77.0), Width: 6.0, Height: 6.0, Color: (255.0,255.0,255.0)\n"
            + "Appears at t=110.0\n"
            + "Disappears at t=200.0\n"
            + "\n"
            + "Shape moon moves from (200.0,200.0) to (200.0,200.0) from time t=1 to t=1\n"
            + "Shape moon moves from (200.0,200.0) to (200.0,200.0) from time t=1 to t=200\n"
            + "Shape background moves from (0.0,0.0) to (0.0,0.0) from time t=1 to t=1\n"
            + "Shape background moves from (0.0,0.0) to (0.0,0.0) from time t=1 to t=50\n"
            + "Shape window360 moves from (640.0,-510.0) to (640.0,-510.0) from time t=1 to t=1\n"
            + "Shape window360 moves from (640.0,-510.0) to (640.0,-510.0) from time t=1 to t=20\n"
            + "Shape B0 moves from (80.0,424.0) to (80.0,424.0) from time t=1 to t=1\n"
            + "Shape B0 moves from (80.0,424.0) to (80.0,424.0) from time t=1 to t=200\n"
            + "Shape B1 moves from (260.0,365.0) to (260.0,365.0) from time t=1 to t=1\n"
            + "Shape B1 moves from (260.0,365.0) to (260.0,365.0) from time t=1 to t=200\n"
            + "Shape window351 moves from (680.0,-550.0) to (680.0,-550.0) from time t=1 to t=1\n"
            + "Shape window351 moves from (680.0,-550.0) to (680.0,-550.0) from time t=1 to t=20\n"
            + "Shape eclipse moves from (400.0,0.0) to (400.0,0.0) from time t=1 to t=1\n"
            + "Shape eclipse moves from (400.0,0.0) to (400.0,0.0) from time t=1 to t=50\n"
            + "Shape star0 moves from (223.0,66.0) to (223.0,66.0) from time t=108 to t=108\n"
            + "Shape star0 moves from (223.0,66.0) to (223.0,66.0) from time t=108 to t=200\n"
            + "Shape star2 moves from (489.0,77.0) to (489.0,77.0) from time t=110 to t=110\n"
            + "Shape star2 moves from (489.0,77.0) to (489.0,77.0) from time t=110 to t=200\n"
            + "Shape star1 moves from (585.0,211.0) to (585.0,211.0) from time t=120 to t=120\n"
            + "Shape star1 moves from (585.0,211.0) to (585.0,211.0) from time t=120 to t=200\n",
        obj.showView());
  }

  @Test
  public void testShowView_Hanoi() throws FileNotFoundException {

    Readable readable = new FileReader(basePath + "\\hanoiCopy.txt");
    AnimationReader.parseFile(readable, builder);
    IView obj = new TextView(myModel);
    assertEquals(
        "Shapes:\n"
            + "Name: S0\n"
            + "Type: rectangle\n"
            + "Min corner: (100.0,75.0), Width: 20.0, Height: 15.0, Color: (255.0,0.0,0.0)\n"
            + "Appears at t=0.0\n"
            + "Disappears at t=15.0\n"
            + "\n"
            + "Name: S1\n"
            + "Type: rectangle\n"
            + "Min corner: (90.0,90.0), Width: 40.0, Height: 15.0, Color: (255.0,153.0,0.0)\n"
            + "Appears at t=0.0\n"
            + "Disappears at t=30.0\n"
            + "\n"
            + "Name: S2\n"
            + "Type: rectangle\n"
            + "Min corner: (80.0,105.0), Width: 60.0, Height: 15.0, Color: (204.0,255.0,0.0)\n"
            + "Appears at t=0.0\n"
            + "Disappears at t=120.0\n"
            + "\n"
            + "Name: S9\n"
            + "Type: rectangle\n"
            + "Min corner: (10.0,210.0), Width: 200.0, Height: 15.0, Color: (255.0,0.0,153.0)\n"
            + "Appears at t=0.0\n"
            + "Disappears at t=15330.0\n"
            + "\n"
            + "Shape S9 moves from (10.0,210.0) to (10.0,210.0) from time t=0 to t=0\n"
            + "Shape S9 moves from (10.0,210.0) to (10.0,210.0) from time t=0 to t=15330\n"
            + "Shape S0 moves from (100.0,75.0) to (100.0,75.0) from time t=0 to t=0\n"
            + "Shape S0 moves from (100.0,75.0) to (100.0,0.0) from time t=0 to t=15\n"
            + "Shape S0 moves from (100.0,75.0) to (100.0,0.0) from time t=0 to t=15\n"
            + "Shape S1 moves from (90.0,90.0) to (90.0,90.0) from time t=0 to t=0\n"
            + "Shape S1 moves from (90.0,90.0) to (90.0,90.0) from time t=0 to t=30\n"
            + "Shape S2 moves from (80.0,105.0) to (80.0,105.0) from time t=0 to t=0\n"
            + "Shape S2 moves from (80.0,105.0) to (80.0,105.0) from time t=0 to t=90\n"
            + "Shape S2 moves from (80.0,105.0) to (80.0,0.0) from time t=90 to t=105\n"
            + "Shape S2 moves from (80.0,105.0) to (80.0,0.0) from time t=90 to t=105\n"
            + "Shape S2 moves from (80.0,0.0) to (300.0,0.0) from time t=105 to t=120\n"
            + "Shape S2 changes color from (204.0,255.0,0.0) to (142.0,178.0,0.0) from t=105 "
            + "to t=120\n"
            + "Shape S2 moves from (80.0,0.0) to (300.0,0.0) from time t=105 to t=120\n",
        obj.showView());
  }

  @Test
  public void testShowView_toh8() throws FileNotFoundException {

    Readable readable = new FileReader(basePath + "\\toh-8-Test.txt");
    AnimationReader.parseFile(readable, builder);
    IView obj = new TextView(myModel);
    assertEquals(
        "Shapes:\n"
            + "Name: disk1\n"
            + "Type: rectangle\n"
            + "Min corner: (190.0,161.0), Width: 20.0, Height: 11.0, Color: (113.0,87.0,151.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=111.0\n"
            + "\n"
            + "Name: disk2\n"
            + "Type: rectangle\n"
            + "Min corner: (483.0,238.0), Width: 32.0, Height: 11.0, Color: (35.0,173.0,73.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=207.0\n"
            + "\n"
            + "Name: disk3\n"
            + "Type: rectangle\n"
            + "Min corner: (477.0,50.0), Width: 45.0, Height: 11.0, Color: (9.0,67.0,130.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=409.0\n"
            + "\n"
            + "Name: disk4\n"
            + "Type: rectangle\n"
            + "Min corner: (170.0,50.0), Width: 58.0, Height: 11.0, Color: (96.0,198.0,208.0)\n"
            + "Appears at t=1.0\n"
            + "Disappears at t=281.0\n"
            + "\n"
            + "Shape disk3 moves from (477.0,50.0) to (477.0,227.0) from time t=1 to t=1\n"
            + "Shape disk3 moves from (477.0,50.0) to (477.0,227.0) from time t=1 to t=1\n"
            + "Shape disk4 moves from (170.0,50.0) to (170.0,50.0) from time t=1 to t=1\n"
            + "Shape disk1 moves from (190.0,161.0) to (190.0,161.0) from time t=1 to t=1\n"
            + "Shape disk1 moves from (190.0,161.0) to (190.0,161.0) from time t=1 to t=25\n"
            + "Shape disk2 moves from (483.0,238.0) to (483.0,50.0) from time t=1 to t=1\n"
            + "Shape disk2 moves from (483.0,238.0) to (483.0,50.0) from time t=1 to t=1\n"
            + "Shape disk1 moves from (340.0,50.0) to (340.0,50.0) from time t=99 to t=100\n"
            + "Shape disk1 moves from (340.0,50.0) to (490.0,50.0) from time t=100 to t=110\n"
            + "Shape disk1 moves from (340.0,50.0) to (490.0,50.0) from time t=100 to t=110\n"
            + "Shape disk1 moves from (490.0,50.0) to (490.0,50.0) from time t=110 to t=111\n"
            + "Shape disk2 moves from (483.0,238.0) to (483.0,50.0) from time t=185 to t=195\n"
            + "Shape disk2 moves from (483.0,238.0) to (483.0,50.0) from time t=185 to t=195\n"
            + "Shape disk2 moves from (483.0,50.0) to (483.0,50.0) from time t=195 to t=196\n"
            + "Shape disk2 moves from (483.0,50.0) to (333.0,50.0) from time t=196 to t=206\n"
            + "Shape disk2 moves from (483.0,50.0) to (333.0,50.0) from time t=196 to t=206\n"
            + "Shape disk2 moves from (333.0,50.0) to (333.0,50.0) from time t=206 to t=207\n"
            + "Shape disk4 moves from (170.0,50.0) to (170.0,50.0) from time t=259 to t=260\n"
            + "Shape disk4 moves from (170.0,50.0) to (470.0,50.0) from time t=260 to t=270\n"
            + "Shape disk4 moves from (170.0,50.0) to (470.0,50.0) from time t=260 to t=270\n"
            + "Shape disk4 moves from (470.0,50.0) to (470.0,50.0) from time t=270 to t=271\n"
            + "Shape disk4 moves from (470.0,50.0) to (470.0,238.0) from time t=271 to t=281\n"
            + "Shape disk4 moves from (470.0,50.0) to (470.0,238.0) from time t=271 to t=281\n"
            + "Shape disk3 moves from (327.0,50.0) to (477.0,50.0) from time t=388 to t=398\n"
            + "Shape disk3 moves from (327.0,50.0) to (477.0,50.0) from time t=388 to t=398\n"
            + "Shape disk3 moves from (477.0,50.0) to (477.0,50.0) from time t=398 to t=399\n"
            + "Shape disk3 moves from (477.0,50.0) to (477.0,227.0) from time t=399 to t=409\n"
            + "Shape disk3 moves from (477.0,50.0) to (477.0,227.0) from time t=399 to t=409\n",
        obj.showView());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetFrame() {
    IView obj = new TextView(myModel);
    obj.getFrame();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetCompositeFrame() {
    IView obj = new TextView(myModel);
    obj.getCompositeFrame();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testGetOutputFile() {
    IView obj = ViewFactory.getView(myModel, "text", "hello.text", 5);
    obj.getOutputFile();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddStartListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addStartListener(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddPauseListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addPauseListener(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddRestartListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addRestartListener(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddSpeedUpListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addSpeedUpListener(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddSlowDownListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addSlowDownListener(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddLoopListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addLoopListener(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddResumeListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addResumeListener(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddMenuItemListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addMenuItemListener(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddRemoveShapeListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addRemoveShapeListener(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddSaveTextListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addSaveTextListener(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddSavetextListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addSaveSvgListener(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testAddShapeListener() {
    ViewFactory.getView(myModel, "text", "hello.text", 5).addShapeListener(null);
  }
}
