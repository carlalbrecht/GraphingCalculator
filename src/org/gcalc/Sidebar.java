package org.gcalc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sidebar extends JPanel {
    private ArrayList<EquationListener> listeners = new ArrayList();
    private ArrayList<EquationEditor> editors = new ArrayList();

    int width, height;

    public Sidebar(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.width = width;
        this.height = height;

        // Create initial equation editor
        EquationEditor editor = new EquationEditor(0, width);
        this.add(editor);
        this.editors.add(editor);

        EquationEditor editor_test = new EquationEditor(1, width);
        this.add(editor_test);
        this.editors.add(editor_test);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
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
