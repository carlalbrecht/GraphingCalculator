package org.gcalc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sidebar extends JPanel {
    private ArrayList<EquationListener> listeners = new ArrayList();

    public Sidebar(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));

        this.add(new EquationEditor());
    }

    /**
     * Adds a new listener which will receive events when equations are
     * created or destroyed.
     *
     * @param listener The listener to additionally forward events to
     */
    public void addEquationListener(EquationListener listener) {
        listeners.add(listener);
    }
}
