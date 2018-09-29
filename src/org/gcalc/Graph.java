package org.gcalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

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
     * Called when the BufferedImage contents are stale and need updating, such
     * as when the window has been resized, or an equation has been added /
     * removed.
     */
    protected void redraw() {
        Graphics2D g = this.img.createGraphics();

        g.setBackground(Color.BLACK);
        g.clearRect(0, 0, this.img.getWidth(), this.img.getHeight());

        drawGrid(g);
    }

    /**
     * Draws the x and y axes, and the grid lines for each integer along each
     * axis.
     */
    protected void drawGrid(Graphics2D g) {
        g.setColor(new Color(64, 64, 64));
        g.setStroke(new BasicStroke(2));
        g.draw(new Line2D.Double(0, this.img.getHeight() / 2,
                                 this.img.getWidth(), this.img.getHeight() / 2));
        g.draw(new Line2D.Double(this.img.getWidth() / 2, 0,
                                 this.img.getWidth() / 2, this.img.getHeight()));
    }
}
