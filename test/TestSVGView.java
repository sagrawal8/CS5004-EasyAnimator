import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs5004.animator.model.EasyAnimatorModel;
import cs5004.animator.model.EasyAnimatorModelImpl;
import cs5004.animator.model.Rectangle;
import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.IView;
import cs5004.animator.view.SvgView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** This class tests the SVG View. */
public class TestSVGView {

  String filePath = new File("").getAbsolutePath();
  EasyAnimatorModel myModel;
  AnimationBuilder<EasyAnimatorModel> builder;

  @Before
  public void init() {
    myModel = new EasyAnimatorModelImpl();
    builder = new EasyAnimatorModelImpl.BobTheBuilder(myModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor_NullModel() {
    new SvgView(null, 2);
  }

  @Test
  public void testValidConstructor() {
    EasyAnimatorModel model = new EasyAnimatorModelImpl();
    model.addShape((new Rectangle("R1", 4, 4, 5, 5, new Color(0, 3, 3), 5, 8)));
    SvgView obj = new SvgView(model, 2);
    assertTrue(model.equals(obj.getModel()));
    assertEquals(model.getShapes().size(), 1);
    assertEquals(null, obj.getFrame());
    assertEquals("svg", obj.getViewType());
  }

  @Test
  public void testShowView_Buildings() throws FileNotFoundException {

    Readable readable = new FileReader(filePath + "/" + "buildingsTest.txt");
    AnimationReader.parseFile(readable, builder);
    IView obj = new SvgView(myModel, 2);
    assertEquals(
        "<svg width=\"800\" height=\"800\" viewBox=\"0 0 800 800\" version=\"1.1\"\n"
            + "\txmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"background\" x=\"0.0\" y=\"0.0\" width=\"800.0\" height=\"800.0\" "
            + "fill=\"rgb(33,94,248)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"2450.0ms\" "
            + "attributeName=\"x\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"2450.0ms\" "
            + "attributeName=\"y\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"B0\" x=\"80.0\" y=\"424.0\" width=\"100.0\" height=\"326.0\" "
            + "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" a"
            + "ttributeName=\"x\" from=\"80.0\" to=\"80.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"424.0\" to=\"424.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"9950.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"80.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"9950.0ms\" "
            + "attributeName=\"y\" from=\"424.0\" to=\"424.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"B1\" x=\"260.0\" y=\"365.0\" width=\"100.0\" height=\"385.0\" "
            + "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"260.0\" to=\"260.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"365.0\" to=\"365.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"9950.0ms\" "
            + "attributeName=\"x\" from=\"260.0\" to=\"260.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"9950.0ms\" "
            + "attributeName=\"y\" from=\"365.0\" to=\"365.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"window351\" x=\"680.0\" y=\"-550.0\" width=\"20.0\" "
            + "height=\"20.0\" fill=\"rgb(255,255,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"680.0\" to=\"680.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"-550.0\" to=\"-550.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"950.0ms\" "
            + "attributeName=\"x\" from=\"680.0\" to=\"680.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"950.0ms\" "
            + "attributeName=\"y\" from=\"-550.0\" to=\"-550.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"window360\" x=\"640.0\" y=\"-510.0\" width=\"20.0\" "
            + "height=\"20.0\" fill=\"rgb(255,255,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"640.0\" to=\"640.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"-510.0\" to=\"-510.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"950.0ms\" "
            + "attributeName=\"x\" from=\"640.0\" to=\"640.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"950.0ms\" "
            + "attributeName=\"y\" from=\"-510.0\" to=\"-510.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"moon\" cx=\"200.0\" cy=\"200.0\" rx=\"100.0\" ry=\"100.0\" "
            + "fill=\"rgb(229,229,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cx\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cy\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"9950.0ms\" "
            + "attributeName=\"cx\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"9950.0ms\" "
            + "attributeName=\"cy\" from=\"200.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "<ellipse id=\"eclipse\" cx=\"400.0\" cy=\"0.0\" rx=\"100.0\" "
            + "ry=\"100.0\" fill=\"rgb(33,94,248)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cx\" from=\"400.0\" to=\"400.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cy\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"2450.0ms\" "
            + "attributeName=\"cx\" from=\"400.0\" to=\"400.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"2450.0ms\" "
            + "attributeName=\"cy\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "<ellipse id=\"star0\" cx=\"223.0\" cy=\"66.0\" rx=\"6.0\" ry=\"6.0\" "
            + "fill=\"rgb(255,255,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"5400.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cx\" from=\"223.0\" to=\"223.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5400.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cy\" from=\"66.0\" to=\"66.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5400.0ms\" dur=\"4600.0ms\" "
            + "attributeName=\"cx\" from=\"223.0\" to=\"223.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5400.0ms\" dur=\"4600.0ms\" "
            + "attributeName=\"cy\" from=\"66.0\" to=\"66.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "<ellipse id=\"star1\" cx=\"585.0\" cy=\"211.0\" rx=\"6.0\" ry=\"6.0\" "
            + "fill=\"rgb(255,255,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cx\" from=\"585.0\" to=\"585.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cy\" from=\"211.0\" to=\"211.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"4000.0ms\" "
            + "attributeName=\"cx\" from=\"585.0\" to=\"585.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"6000.0ms\" dur=\"4000.0ms\" "
            + "attributeName=\"cy\" from=\"211.0\" to=\"211.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "<ellipse id=\"star2\" cx=\"489.0\" cy=\"77.0\" rx=\"6.0\" ry=\"6.0\" "
            + "fill=\"rgb(255,255,255)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cx\" from=\"489.0\" to=\"489.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"cy\" from=\"77.0\" to=\"77.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"4500.0ms\" "
            + "attributeName=\"cx\" from=\"489.0\" to=\"489.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"4500.0ms\" "
            + "attributeName=\"cy\" from=\"77.0\" to=\"77.0\" fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>",
        obj.showView());
  }

  @Test
  public void testShowView_Hanoi() throws FileNotFoundException {

    Readable readable = new FileReader(filePath + "/" + "hanoiCopy.txt");
    AnimationReader.parseFile(readable, builder);
    IView obj = new SvgView(myModel, 2);
    assertEquals(
        "<svg width=\"640\" height=\"225\" viewBox=\"10 0 640 225\" version=\"1.1\"\n"
            + "\txmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"S0\" x=\"100.0\" y=\"75.0\" width=\"20.0\" height=\"15.0\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"75.0\" to=\"75.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"750.0ms\" "
            + "attributeName=\"x\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"750.0ms\" "
            + "attributeName=\"y\" from=\"75.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"S1\" x=\"90.0\" y=\"90.0\" width=\"40.0\" height=\"15.0\" "
            + "fill=\"rgb(255,153,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"90.0\" to=\"90.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"90.0\" to=\"90.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"x\" from=\"90.0\" to=\"90.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"1500.0ms\" "
            + "attributeName=\"y\" from=\"90.0\" to=\"90.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"S2\" x=\"80.0\" y=\"105.0\" width=\"60.0\" height=\"15.0\" "
            + "fill=\"rgb(204,255,0)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"80.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"105.0\" to=\"105.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"4500.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"80.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"4500.0ms\" "
            + "attributeName=\"y\" from=\"105.0\" to=\"105.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"4500.0ms\" dur=\"750.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"80.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"4500.0ms\" dur=\"750.0ms\" "
            + "attributeName=\"y\" from=\"105.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5250.0ms\" dur=\"750.0ms\" "
            + "attributeName=\"x\" from=\"80.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5250.0ms\" dur=\"750.0ms\" "
            + "attributeName=\"y\" from=\"0.0\" to=\"0.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"S9\" x=\"10.0\" y=\"210.0\" width=\"200.0\" height=\"15.0\" "
            + "fill=\"rgb(255,0,153)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"10.0\" to=\"10.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"210.0\" to=\"210.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"766500.0ms\" "
            + "attributeName=\"x\" from=\"10.0\" to=\"10.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"766500.0ms\" "
            + "attributeName=\"y\" from=\"210.0\" to=\"210.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "</svg>",
        obj.showView());
  }

  @Test
  public void testShowView_toh8() throws FileNotFoundException {

    Readable readable = new FileReader(filePath + "/" + "toh-8-Test.txt");
    AnimationReader.parseFile(readable, builder);
    IView obj = new SvgView(myModel, 2);
    assertEquals(
        "<svg width=\"410\" height=\"199\" viewBox=\"145 50 410 199\" version=\"1.1\"\n"
            + "\txmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"disk1\" x=\"190.0\" y=\"161.0\" width=\"20.0\" height=\"11.0\" "
            + "fill=\"rgb(113,87,151)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"190.0\" to=\"190.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"161.0\" to=\"161.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"1200.0ms\" "
            + "attributeName=\"x\" from=\"190.0\" to=\"190.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"1200.0ms\" "
            + "attributeName=\"y\" from=\"161.0\" to=\"161.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"4950.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"x\" from=\"340.0\" to=\"340.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"4950.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"x\" from=\"340.0\" to=\"490.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"50.0ms\""
            + " attributeName=\"x\" from=\"490.0\" to=\"490.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"disk2\" x=\"483.0\" y=\"238.0\" width=\"32.0\" height=\"11.0\" "
            + "fill=\"rgb(35,173,73)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"483.0\" to=\"483.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"238.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9250.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"x\" from=\"483.0\" to=\"483.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9250.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"y\" from=\"238.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9750.0ms\" dur=\"50.0ms\" a"
            + "ttributeName=\"x\" from=\"483.0\" to=\"483.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9750.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9800.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"x\" from=\"483.0\" to=\"333.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"9800.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10300.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"x\" from=\"333.0\" to=\"333.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"10300.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"disk3\" x=\"477.0\" y=\"50.0\" width=\"45.0\" height=\"11.0\""
            + " fill=\"rgb(9,67,130)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"477.0\" to=\"477.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"227.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19400.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"x\" from=\"327.0\" to=\"477.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19400.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19900.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"x\" from=\"477.0\" to=\"477.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19900.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19950.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"x\" from=\"477.0\" to=\"477.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"19950.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"227.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<rect id=\"disk4\" x=\"170.0\" y=\"50.0\" width=\"58.0\" height=\"11.0\" "
            + "fill=\"rgb(96,198,208)\" visibility=\"visible\" >\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"x\" from=\"170.0\" to=\"170.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"50.0ms\" dur=\"0.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"12950.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"x\" from=\"170.0\" to=\"170.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"12950.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"13000.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"x\" from=\"170.0\" to=\"470.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"13000.0ms\" dur=\"500.0ms\""
            + " attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"13500.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"x\" from=\"470.0\" to=\"470.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"13500.0ms\" dur=\"50.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"13550.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"x\" from=\"470.0\" to=\"470.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"13550.0ms\" dur=\"500.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"238.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "</svg>",
        obj.showView());
  }
}
