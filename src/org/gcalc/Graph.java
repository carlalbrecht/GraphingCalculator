package org.gcalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Graph extends JLabel implements ComponentListener, EquationListener {
    public static final Color[] lineColours = {
            new Color(231, 76, 60),
            new Color(26, 188, 156),
            new Color(241, 196, 15),
            new Color(211, 84, 0),
            new Color(39, 174, 96),
            new Color(41, 128, 185),
            new Color(255, 0, 255)
    };

    private int width, height;
    private BufferedImage img;
    private double scale = 1;
    private ArrayList<Equation> equations = new ArrayList<>();

    public Graph(int width, int height) {
        this.width = width;
        this.height = height;
        this.img =
                new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        this.setIcon(new ImageIcon(this.img));
        this.addComponentListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }

    public void componentResized(ComponentEvent e) {
        Dimension size = this.getSize();
        this.width = size.width;
        this.height = size.height;

        this.img = new BufferedImage(
                this.width, this.height, BufferedImage.TYPE_INT_RGB);
        this.setIcon(new ImageIcon(this.img));

        this.redraw();
    }

    // Unused component listeners
    public void componentHidden(ComponentEvent e) { }
    public void componentMoved(ComponentEvent e) { }
    public void componentShown(ComponentEvent e) { }

    /**
     * Signals that a new equation has been created. A reference to the new
     * equation is supplied.
     *
     * @param id The array index of the new equation
     * @param newEquation The newly added equation
     */
    public void equationAdded(int id, Equation newEquation) {

    }

    /**
     * Called when one or more equations have been removed.
     *
     * @param id The array index of the removed equation
     */
    public void equationRemoved(int id) {

    }

    /**
     * Called when a pre-existing equation has been modified. Note that the new
     * equation instance may be the same instance as the previous instance.
     * 
     * @param id The array index of the modified equation
     * @param e The equation object to replace the old one with
     */
    public void equationChanged(int id, Equation e) {

    }

    /**
     * Zooms in the graph a bit.
     */
    public void increaseScale() {
        this.setScale(this.getScale() * 1.5);
    }

    /**
     * Zooms out the graph a bit
     */
    public void decreaseScale() {
        this.setScale(this.getScale() / 1.5);
    }

    /**
     * Sets the zoom level of the graph. Automatically triggers a redraw of the
     * graph.
     *
     * @param scale Scale multiplier (i.e. 1 = no zoom)
     */
    public void setScale(double scale) {
        this.scale = scale;
        this.redraw();
    }

    /**
     * Returns the current scale multiplier in use by the graph.
     */
    public double getScale() {
        return this.scale;
    }

    /**
     * Called when the BufferedImage contents are stale and need updating, such
     * as when the window has been resized, or an equation has been added /
     * removed.
     */
    protected void redraw() {
        Graphics2D g = this.img.createGraphics();

        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, this.img.getWidth(), this.img.getHeight());

        drawGrid(g);

        this.repaint();
    }

    /**
     * Draws the x and y axes, and the grid lines for each integer along each
     * axis. Draws numbers for each perpendicular dashed line.
     */
    protected void drawGrid(Graphics2D g) {
        float[] dashPattern = new float[]{10 * (float) this.scale,
                                          5 * (float) this.scale};

        g.setColor(new Color(48, 48, 48));

        // Axis lines
        g.setStroke(new BasicStroke(2));
        g.draw(new Line2D.Double(0, this.img.getHeight() / 2,
                                 this.img.getWidth(), this.img.getHeight() / 2));
        g.draw(new Line2D.Double(this.img.getWidth() / 2, 0,
                                 this.img.getWidth() / 2, this.img.getHeight()));

        int imgWidth = this.img.getWidth(), imgHeight = this.img.getHeight();

        // X axis vertical lines & numbers
        g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10,
                                    dashPattern, (29 - (imgHeight % 30)) / 2.0f + 20));

        // Janky code to scale interval between perpendicular lines
        final int xNormInterval = 50;
        int xScaledInt = (int) Math.round(50 * this.scale);
        int xMultiplier = Math.max(Math.round((float) xNormInterval / (float) xScaledInt), 1);

        int xInterval = xMultiplier * xScaledInt;
        int xNumInterval = xMultiplier, xCurrent = 0;
        for (int x = imgWidth / 2 - xInterval; x >= 0; x -= xInterval) {
            g.draw(new Line2D.Double(x, imgHeight, x, 0));
            g.draw(new Line2D.Double(imgWidth - x, imgHeight, imgWidth - x, 0));

            xCurrent += xNumInterval;
            g.drawString("-" + Integer.toString(xCurrent), x + 2, imgHeight / 2 + 14);
            g.drawString(Integer.toString(xCurrent), imgWidth - x + 2, imgHeight / 2 + 14);
        }

        // Y axis horizontal lines & numbers
        g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10,
                                    dashPattern, (29 - (imgWidth % 30)) / 2.0f + 20));

        int yInterval = xInterval;
        int yNumInterval = xNumInterval, yCurrent = 0;
        for (int y = imgHeight / 2 - yInterval; y >= 0; y -= yInterval) {
            g.draw(new Line2D.Double(imgWidth, y, 0, y));
            g.draw(new Line2D.Double(imgWidth, imgHeight - y, 0, imgHeight - y));

            yCurrent += yNumInterval;
            g.drawString(Integer.toString(yCurrent), imgWidth / 2 + 2, y + 14);
            g.drawString("-" + Integer.toString(yCurrent), imgWidth / 2 + 2, imgHeight - y + 14);
        }
    }
}
