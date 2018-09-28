package org.gcalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class Sidebar extends JScrollPane implements ComponentListener {
    private ArrayList<EquationListener> listeners = new ArrayList();
    private ArrayList<EquationEditor> editors = new ArrayList();

    int width, height;

    JPanel container;

    public Sidebar(int width, int height) {
        super(new JPanel());
        this.setPreferredSize(new Dimension(width, height));
        this.width = width;
        this.height = height;

        // Create container to hold equation editors
        this.container = (JPanel) this.getViewport().getView();
        this.container.setLayout(
                new BoxLayout(this.container, BoxLayout.PAGE_AXIS));
        container.setMaximumSize(new Dimension(width - 10, Integer.MAX_VALUE));

        // Create initial equation editor
        EquationEditor editor = new EquationEditor(0);
        this.container.add(editor);
        this.editors.add(editor);

        EquationEditor editor_test = new EquationEditor(1);
        this.container.add(editor_test);
        this.editors.add(editor_test);

        this.addComponentListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }

    public void componentResized(ComponentEvent componentEvent) {
        Dimension size = this.getSize();
        size.width -= 3;
        this.container.setMaximumSize(new Dimension(size.width, Integer.MAX_VALUE));
        for (EquationEditor e : this.editors) {
            e.setWidth(size.width);
        }
    }

    // Unused component listeners
    public void componentMoved(ComponentEvent componentEvent) { }
    public void componentShown(ComponentEvent componentEvent) { }
    public void componentHidden(ComponentEvent componentEvent) { }

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
