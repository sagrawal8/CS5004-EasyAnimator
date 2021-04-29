import cs5004.animator.controller.Controller;
import cs5004.animator.controller.IController;
import cs5004.animator.model.EasyAnimatorModel;
import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;
import cs5004.animator.view.ViewFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** This class tests the controller. */
public class TestController {

  EasyAnimatorModel model;
  AnimationBuilder<EasyAnimatorModel> builder;
  String filePath;

  @Before
  public void init() {
    model = new EasyAnimatorModelImpl();
    builder = new EasyAnimatorModelImpl.BobTheBuilder(model);
    filePath = new File("").getAbsolutePath();
  }

  @Test
  public void testConstructor_1() throws IOException {
    Readable readable = new FileReader(filePath + "\\buildings.txt");
    AnimationReader.parseFile(readable, builder);
    model.setAnimationSpeed(1);
    IView ourView = ViewFactory.getView(model, "text", "", 1);
    IController controller = new Controller(ourView, model);
    assertEquals(model, controller.getModel());
    assertEquals(1, controller.getModel().getAnimationSpeed(), 0.01);
    assertEquals(0, controller.getTick(), 0.01);
  }

  @Test
  public void testConstructor_2() throws IOException {
    Readable readable = new FileReader(filePath + "\\buildings.txt");
    AnimationReader.parseFile(readable, builder);
    model.setAnimationSpeed(1);
    IView ourView = ViewFactory.getView(model, "playback", "", 1);
    IController controller = new Controller(ourView, model);
    assertEquals(model, controller.getModel());
    assertEquals(1, controller.getModel().getAnimationSpeed(), 0.01);
    assertEquals(0, controller.getTick(), 0.01);
  }

  @Test
  public void testGo_Text_1() throws IOException {
    Readable readable = new FileReader(filePath + "\\buildingsTest.txt");
    AnimationReader.parseFile(readable, builder);
    model.setAnimationSpeed(1);
    IView ourView = ViewFactory.getView(model, "text", "", 1);
    IController controller = new Controller(ourView, model);
    // Copy pasted output from the console for expected value.
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
        ourView.showView());
  }

  @Test
  public void testGo_Text_2() throws IOException {
    Readable readable = new FileReader(filePath + "\\hanoicopy.txt");
    AnimationReader.parseFile(readable, builder);
    model.setAnimationSpeed(1);
    IView ourView = ViewFactory.getView(model, "text", "", 1);
    IController controller = new Controller(ourView, model);
    // Copy pasted output from the console for expected value.
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
        ourView.showView());
  }

  @Test
  public void testGo_Text_3() throws IOException {
    Readable readable = new FileReader(filePath + "\\toh-8-Test.txt");
    AnimationReader.parseFile(readable, builder);
    model.setAnimationSpeed(1);
    IView ourView = ViewFactory.getView(model, "text", "", 1);
    IController controller = new Controller(ourView, model);
    // Copy pasted output from the console for expected value.
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
        ourView.showView());
  }

  @Test
  public void testGo_Svg_1() throws IOException {
    Readable readable = new FileReader(filePath + "\\buildingsTest.txt");
    AnimationReader.parseFile(readable, builder);
    model.setAnimationSpeed(1);
    IView ourView = ViewFactory.getView(model, "svg", "output1.svg", 1);
    IController controller = new Controller(ourView, model);
    // Copy pasted output from the console for expected value.
    assertEquals(
        "<svg width=\"800\" height=\"800\" viewBox=\"0 0 800 800\" version=\"1.1\"\n"
            + "\txmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"background\" x=\"0.0\" y=\"0.0\" width=\"800.0\" height=\"800.0\" "
            + "fill=\"rgb(33,94,248)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" attributeName=\"x\" "
            + "from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" attributeName=\"y\" "
            + "from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"4900.0ms\" "
            + "attributeName=\"x\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"4900.0ms\" "
            + "attributeName=\"y\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"B0\" x=\"80.0\" y=\"424.0\" width=\"100.0\" height=\"326.0\" "
            + "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"80.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"424.0\" to=\"424.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"19900.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"80.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"19900.0ms\" "
            + "attributeName=\"y\" from=\"424.0\" to=\"424.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"B1\" x=\"260.0\" y=\"365.0\" width=\"100.0\" height=\"385.0\" "
            + "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"260.0\" to=\"260.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"365.0\" to=\"365.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"19900.0ms\" "
            + "attributeName=\"x\" from=\"260.0\" to=\"260.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"19900.0ms\" "
            + "attributeName=\"y\" from=\"365.0\" to=\"365.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"window351\" x=\"680.0\" y=\"-550.0\" width=\"20.0\" "
            + "height=\"20.0\" fill=\"rgb(255,255,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"680.0\" to=\"680.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"-550.0\" to=\"-550.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"1900.0ms\" "
            + "attributeName=\"x\" from=\"680.0\" to=\"680.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"1900.0ms\" "
            + "attributeName=\"y\" from=\"-550.0\" to=\"-550.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"window360\" x=\"640.0\" y=\"-510.0\" width=\"20.0\" "
            + "height=\"20.0\" fill=\"rgb(255,255,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"640.0\" to=\"640.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"-510.0\" to=\"-510.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"1900.0ms\" "
            + "attributeName=\"x\" from=\"640.0\" to=\"640.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"1900.0ms\" "
            + "attributeName=\"y\" from=\"-510.0\" to=\"-510.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"moon\" cx=\"200.0\" cy=\"200.0\" rx=\"100.0\" ry=\"100.0\" "
            + "fill=\"rgb(229,229,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cx\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cy\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"19900.0ms\" "
            + "attributeName=\"cx\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"19900.0ms\" "
            + "attributeName=\"cy\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "<ellipse id=\"eclipse\" cx=\"400.0\" cy=\"0.0\" rx=\"100.0\" ry=\"100.0\" "
            + "fill=\"rgb(33,94,248)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cx\" from=\"400.0\" to=\"400.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cy\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"4900.0ms\" "
            + "attributeName=\"cx\" from=\"400.0\" to=\"400.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"4900.0ms\" "
            + "attributeName=\"cy\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "<ellipse id=\"star0\" cx=\"223.0\" cy=\"66.0\" rx=\"6.0\" ry=\"6.0\" "
            + "fill=\"rgb(255,255,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"10800.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cx\" from=\"223.0\" to=\"223.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10800.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cy\" from=\"66.0\" to=\"66.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10800.0ms\" dur=\"9200.0ms\" "
            + "attributeName=\"cx\" from=\"223.0\" to=\"223.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10800.0ms\" dur=\"9200.0ms\" "
            + "attributeName=\"cy\" from=\"66.0\" to=\"66.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "<ellipse id=\"star1\" cx=\"585.0\" cy=\"211.0\" rx=\"6.0\" ry=\"6.0\" "
            + "fill=\"rgb(255,255,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"12000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cx\" from=\"585.0\" to=\"585.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"12000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cy\" from=\"211.0\" to=\"211.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"12000.0ms\" dur=\"8000.0ms\" "
            + "attributeName=\"cx\" from=\"585.0\" to=\"585.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"12000.0ms\" dur=\"8000.0ms\" "
            + "attributeName=\"cy\" from=\"211.0\" to=\"211.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "<ellipse id=\"star2\" cx=\"489.0\" cy=\"77.0\" rx=\"6.0\" ry=\"6.0\" "
            + "fill=\"rgb(255,255,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cx\" from=\"489.0\" to=\"489.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cy\" from=\"77.0\" to=\"77.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"9000.0ms\" "
            + "attributeName=\"cx\" from=\"489.0\" to=\"489.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"9000.0ms\" "
            + "attributeName=\"cy\" from=\"77.0\" to=\"77.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>",
        ourView.showView());
  }

  @Test
  public void testGo_Svg_2() throws IOException {
    Readable readable = new FileReader(filePath + "\\hanoicopy.txt");
    AnimationReader.parseFile(readable, builder);
    model.setAnimationSpeed(1);
    IView ourView = ViewFactory.getView(model, "svg", "output2.svg", 1);
    IController controller = new Controller(ourView, model);
    // Copy pasted output from the console for expected value.
    assertEquals(
        "<svg width=\"640\" height=\"225\" viewBox=\"10 0 640 225\" version=\"1.1\"\n"
            + "\txmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"S0\" x=\"100.0\" y=\"75.0\" width=\"20.0\" height=\"15.0\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" attributeName=\"x\""
            + " from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" attributeName=\"y\""
            + " from=\"75.0\" to=\"75.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1500.0ms\" attributeName=\"x\" "
            + "from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1500.0ms\""
            + " attributeName=\"y\" from=\"75.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"x\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"y\" from=\"75.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"S1\" x=\"90.0\" y=\"90.0\" width=\"40.0\" height=\"15.0\" "
            + "fill=\"rgb(255,153,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"90.0\" to=\"90.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"90.0\" to=\"90.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"3000.0ms\""
            + " attributeName=\"x\" from=\"90.0\" to=\"90.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"3000.0ms\" "
            + "attributeName=\"y\" from=\"90.0\" to=\"90.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"S2\" x=\"80.0\" y=\"105.0\" width=\"60.0\" height=\"15.0\" "
            + "fill=\"rgb(204,255,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"80.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"105.0\" to=\"105.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"9000.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"80.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"9000.0ms\" "
            + "attributeName=\"y\" from=\"105.0\" to=\"105.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9000.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"80.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9000.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"y\" from=\"105.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9000.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"80.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9000.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"y\" from=\"105.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10500.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10500.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"y\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10500.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(204,255,0)\" to=\"rgb(142,178,0)\" "
            + "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10500.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10500.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"y\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"S9\" x=\"10.0\" y=\"210.0\" width=\"200.0\" height=\"15.0\" "
            + "fill=\"rgb(255,0,153)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"10.0\" to=\"10.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"210.0\" to=\"210.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1533000.0ms\" "
            + "attributeName=\"x\" from=\"10.0\" to=\"10.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1533000.0ms\" "
            + "attributeName=\"y\" from=\"210.0\" to=\"210.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "</svg>",
        ourView.showView());
  }

  @Test
  public void testGo_Svg_3() throws IOException {
    Readable readable = new FileReader(filePath + "\\toh-8-Test.txt");
    AnimationReader.parseFile(readable, builder);
    model.setAnimationSpeed(1);
    IView ourView = ViewFactory.getView(model, "svg", "output3.svg", 1);
    IController controller = new Controller(ourView, model);
    // Copy pasted output from the console for expected value.
    assertEquals(
        "<svg width=\"410\" height=\"199\" viewBox=\"145 50 410 199\" version=\"1.1\"\n"
            + "\txmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"disk1\" x=\"190.0\" y=\"161.0\" width=\"20.0\" height=\"11.0\" "
            + "fill=\"rgb(113,87,151)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"190.0\" to=\"190.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"161.0\" to=\"161.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"2400.0ms\" "
            + "attributeName=\"x\" from=\"190.0\" to=\"190.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"2400.0ms\" "
            + "attributeName=\"y\" from=\"161.0\" to=\"161.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9900.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"x\" from=\"340.0\" to=\"340.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9900.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"340.0\" to=\"490.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"340.0\" to=\"490.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"x\" from=\"490.0\" to=\"490.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"11000.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"disk2\" x=\"483.0\" y=\"238.0\" width=\"32.0\" height=\"11.0\" "
            + "fill=\"rgb(35,173,73)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"483.0\" to=\"483.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"238.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"483.0\" to=\"483.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"238.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"18500.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"483.0\" to=\"483.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"18500.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"238.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"18500.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"483.0\" to=\"483.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"18500.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"238.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19500.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"x\" from=\"483.0\" to=\"483.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19500.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19600.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"483.0\" to=\"333.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19600.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19600.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"483.0\" to=\"333.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19600.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"20600.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"x\" from=\"333.0\" to=\"333.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"20600.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"disk3\" x=\"477.0\" y=\"50.0\" width=\"45.0\" height=\"11.0\" "
            + "fill=\"rgb(9,67,130)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"477.0\" to=\"477.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"227.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"477.0\" to=\"477.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\""
            + " attributeName=\"y\" from=\"50.0\" to=\"227.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"38800.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"327.0\" to=\"477.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"38800.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"38800.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"327.0\" to=\"477.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"38800.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"39800.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"x\" from=\"477.0\" to=\"477.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"39800.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"39900.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"477.0\" to=\"477.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"39900.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"227.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"39900.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"477.0\" to=\"477.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"39900.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"227.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"disk4\" x=\"170.0\" y=\"50.0\" width=\"58.0\" height=\"11.0\" "
            + "fill=\"rgb(96,198,208)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"170.0\" to=\"170.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"100.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"25900.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"x\" from=\"170.0\" to=\"170.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"25900.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"26000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"170.0\" to=\"470.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"26000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"26000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"170.0\" to=\"470.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"26000.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"27000.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"x\" from=\"470.0\" to=\"470.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"27000.0ms\" dur=\"100.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"27100.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"470.0\" to=\"470.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"27100.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"238.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"27100.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"x\" from=\"470.0\" to=\"470.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"27100.0ms\" dur=\"1000.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"238.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "</svg>",
        ourView.showView());
  }
}
