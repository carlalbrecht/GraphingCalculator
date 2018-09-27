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
     * @param newEquation The newly added equation
     */
    public void equationAdded(Equation newEquation) {

    }

    /**
     * Called when one or more equations have been removed. An array containing
     * all remaining equations is supplied.
     *
     * @param remaining The remaining equations after the delete operation
     */
    public void equationRemoved(Equation[] remaining) {

    }

    /**
     * Called when the BufferedImage contents are stale and need updating, such
     * as when the window has been resized, or an equation has been added /
     * removed.
     */
    protected void redraw() {

    }
}
