package org.gcalc;

import javax.swing.*;
import java.awt.*;

public class EquationEditor extends JPanel {
    private int id, width;

    private JTextField editor;

    public EquationEditor(int id, int width) {
        this.id = id;
        this.width = width;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(width, 300));

        this.editor = new JTextField();
        this.add(this.editor);
    }

    public int getID() {
        return this.id;
    }
}
