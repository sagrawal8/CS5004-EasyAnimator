package cs5004.animator.view;

import java.awt.BorderLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import cs5004.animator.model.IShape;

/**
 * This class creates the frame for the visual view. It extends the JFrame class, and has a
 * SwingPanel. It also has a private class that allows us to implement a scroll bar in both the
 * horizontal and vertical directions.
 */
public class SwingFrame extends JFrame {

  private SwingPanel panel;

  /**
   * Creates a SwingFrame object.
   *
   * @param x An integer to set the x-location of the frame.
   * @param y An integer to set the y-location of the frame.
   * @param width The width of the frame.
   * @param height The height of the frame.
   * @param listOfMutatedShapes The list of mutated shapes that we get from the model.
   */
  public SwingFrame(int x, int y, int width, int height, List<IShape> listOfMutatedShapes) {
    // Setting the title of our animation.
    super("Our Grand Animation");
    BorderLayout newBorder = new BorderLayout();
    this.setLayout(newBorder);
    this.panel = new SwingPanel(listOfMutatedShapes);
    this.add(this.panel, BorderLayout.CENTER);
    this.panel.setVisible(true);

    // Declaring a vertical scroll bar.
    JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL, 0, 100, -200, 200);

    /**
     * This class is an adjustment listener for the vertical scroll bar. It allows us to offset the
     * panel by a certain distance, based on how much we scroll in the vertical direction.
     */
    class VerticalAdjustmentListener implements AdjustmentListener {
      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        setPanelOffSetY(e.getValue());
      }
    }

    // Declaring the horizontal scroll bar.
    JScrollBar horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 100, -200, 200);

    /**
     * This class is an adjustment listener for the horizontal scroll bar. It allows us to offset
     * the panel by a certain distance, based on how much we scroll in the horizontal direction.
     */
    class HorizontalAdjustmentListener implements AdjustmentListener {
      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        setPanelOffSetX(e.getValue());
      }
    }

    // Here we are adding an adjustment Listener to the vertical and horizontal scroll
    // bars.
    verticalScrollBar.addAdjustmentListener(new VerticalAdjustmentListener());
    horizontalScrollBar.addAdjustmentListener(new HorizontalAdjustmentListener());

    // Adding the vertical and horizontal scroll bars to the frame.
    this.getContentPane().add(verticalScrollBar, BorderLayout.EAST);
    this.getContentPane().add(horizontalScrollBar, BorderLayout.SOUTH);

    // Setting the size of the frame.
    this.setSize(width, height);
    this.setLocation(x, y);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  /**
   * This method is a getter for the panel.
   *
   * @return The panel in the current frame.
   */
  public SwingPanel getPanel() {
    return this.panel;
  }

  /**
   * This method updates the current view by setting the panel to the new list of mutated shapes,
   * and then calls the repaint method.
   *
   * @param listOfMutatedShapes The list of mutated shapes that was returned by the
   *     getShapesAtTimeStamp, method in the model.
   */
  public void currentView(List<IShape> listOfMutatedShapes) {
    this.revalidate();
    this.panel.setModel((listOfMutatedShapes));
    this.repaint();
  }

  /**
   * This method sets the panel offset for the y direction.
   *
   * @param value The amount by which we want to offset the panel in the y direction.
   */
  private void setPanelOffSetY(int value) {
    this.panel.setPanelOffSetY(value);
  }

  /**
   * This method sets the panel offset for the x direction.
   *
   * @param value The amount by which we want the offset the panel in the x direction.
   */
  private void setPanelOffSetX(int value) {
    this.panel.setPanelOffSetX(value);
  }
}
