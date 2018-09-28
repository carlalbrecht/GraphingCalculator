package org.gcalc;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class EquationEditor extends JPanel {
    private int id, width;

    private JLabel title;
    private JButton deleteBtn;
    private JTextField editor;

    public EquationEditor(int id) {
        this.id = id;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Create editor title
        JPanel titleRow = new JPanel();
        titleRow.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.title = new JLabel("Expression " + Integer.toString(id + 1));
        this.title.setForeground(Graph.lineColours[id % Graph.lineColours.length]);
        titleRow.add(this.title);
        this.add(titleRow);

        // Create expression editor
        this.editor = new JTextField();
        this.editor.setFont(new Font("monospaced", Font.PLAIN, 16));
        this.editor.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                EquationEditor.this.equationChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                EquationEditor.this.equationChanged();
            }

            public void changedUpdate(DocumentEvent e) {
                EquationEditor.this.equationChanged();
            }
        });
        this.add(this.editor);

        // Line up buttons on bottom row
        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Create button to remove the expression
        this.deleteBtn = new JButton("Delete");
        buttonRow.add(this.deleteBtn);

        this.add(buttonRow);

        // Make odd rows darker
        if (id % 2 == 1) {
            darkenComponent(this);
            darkenComponent(titleRow);
            darkenComponent(this.title);
            darkenComponent(this.editor);
            darkenComponent(buttonRow);
            darkenComponent(this.deleteBtn);
        }
    }

    public int getID() {
        return this.id;
    }

    public void setWidth(int width) {
        this.width = width;
        this.setPreferredSize(new Dimension(width, 92));
        this.setMaximumSize(new Dimension(width, 92));
        this.editor.setMaximumSize(new Dimension(width - 10, 30));

        this.revalidate();
        this.repaint();
    }

    /**
     * Triggers processing of a new equation when the equation field is
     * modified.
     */
    protected void equationChanged() {
        System.out.println("modified");
    }

    /**
     * Lowers the background HSB's brightness by 3.5%.
     *
     * @param c The component to darken
     */
    private void darkenComponent(Component c) {
        Color origColour = c.getBackground();
        float[] hsv = Color.RGBtoHSB(
                origColour.getRed(),
                origColour.getGreen(),
                origColour.getBlue(),
                null);
        c.setBackground(new Color(
                Color.HSBtoRGB(hsv[0], hsv[1], hsv[2] - (float) 0.035)));
    }
}
