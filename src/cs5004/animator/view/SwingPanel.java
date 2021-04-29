package cs5004.animator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import cs5004.animator.model.IShape;
import cs5004.animator.model.ShapeType;

/**
 * This class represents the panel for the visual view. It has an ability to set the new List of
 * shapes to draw on the canvas, an ability to paint the canvas/panel, and also sets the offset for
 * the x and the y directions.
 */
public class SwingPanel extends JPanel {

  private List<IShape> mutatedShapes;
  int offSetX;
  int offSetY;

  /**
   * Constructs a new panel object.
   *
   * @param initialShapeStates The initial list of shapes we want to draw on our panel.
   */
  public SwingPanel(List<IShape> initialShapeStates) {
    super(true);
    this.mutatedShapes = initialShapeStates;

    setBackground(Color.WHITE);
    setBorder(new LineBorder(Color.black, 3));

    setVisible(true);

  }

  /**
   * A setter method to set the new mutated shapes as we receive them from the model.
   *
   * @param mutatedShapes A list of new mutated shapes.
   */
  public void setModel(List<IShape> mutatedShapes) {
    this.mutatedShapes = mutatedShapes;
  }

  /**
   * This method draws the new shapes on the panel using the Graphics class.
   *
   * @param g the Graphics we want to draw the new mutated shapes with.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    if (this.mutatedShapes == null) {
      return;
    }

    // Looping over the mutated shapes.
    for (IShape shape : this.mutatedShapes) {
      if (shape.getShapeType() == ShapeType.RECTANGLE) {
        g2.setColor(shape.getColor());
        g2.draw(new Rectangle2D.Double(this.offSetX + shape.getX(),
                this.offSetY + shape.getY(), shape.getWidth(), shape.getLength()));
        g2.fill(new Rectangle2D.Double(this.offSetX + shape.getX(),
                this.offSetY + shape.getY(), shape.getWidth(), shape.getLength()));
      }

      if (shape.getShapeType() == ShapeType.OVAL) {
        g2.setColor(shape.getColor());
        g2.draw(new Ellipse2D.Double(this.offSetX + shape.getX(),
                this.offSetY + shape.getY(), shape.getWidth(), shape.getLength()));
        g2.fill(new Ellipse2D.Double(this.offSetX + shape.getX(),
                this.offSetY + shape.getY(), shape.getWidth(), shape.getLength()));
      }
    }
  }

  /**
   * This is a setter method to offset the panel in the y direction.
   *
   * @param value The value by which we want to offset the panel in the y direction.
   */
  public void setPanelOffSetY(int value) {
    this.offSetY = value;

  }

  /**
   * This is a setter method to offset the panel in the x direction.
   *
   * @param value The value by which we wnt to offset the panel in the x direction.
   */
  public void setPanelOffSetX(int value) {
    this.offSetX = value;

  }
}
