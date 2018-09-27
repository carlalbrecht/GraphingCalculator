package org.gcalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

public class Graph extends JLabel implements ComponentListener, EquationListener {
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

    }
}
