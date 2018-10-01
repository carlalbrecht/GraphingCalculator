package org.gcalc;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;

public class EquationEditor extends JPanel implements AncestorListener {
    private int id, width;

    private JLabel title;
    private JButton deleteBtn;
    private JTextField editor;

    private Color editorNormalColor;

    private ArrayList<EquationEditorListener> listeners = new ArrayList<>();

    private Equation equation = new Equation("");

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

        // Move focus to new editor field so the user doesn't have to click it
        // NOTE: this is performed as a callback, otherwise it doesn't work
        this.editor.addAncestorListener(this);

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
        } else {
            lightenComponent(this);
            lightenComponent(titleRow);
            lightenComponent(this.title);
            lightenComponent(this.editor);
            lightenComponent(buttonRow);
            lightenComponent(this.deleteBtn);
        }

        this.editorNormalColor = this.editor.getBackground();
    }

    @Override
    public void ancestorAdded(AncestorEvent ancestorEvent) {
        final AncestorListener a = this;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JComponent c = ancestorEvent.getComponent();
                c.requestFocusInWindow();
                c.removeAncestorListener(a);
            }
        });
    }

    @Override
    public void ancestorRemoved(AncestorEvent ancestorEvent) { }

    @Override
    public void ancestorMoved(AncestorEvent ancestorEvent) { }

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

    public void addEquationEditorListener(EquationEditorListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Called when the equation is initially modified, as we automatically
     * assume that the new equation will be valid. Sets the JTextField's
     * background colour to it's normal value.
     */
    public void setValid() {
        this.editor.setBackground(this.editorNormalColor);
    }

    /**
     * Called when the Graph attempts to evaluate the equation, if it finds the
     * equation to be invalid.
     */
    public void setInvalid() {
        this.editor.setBackground(new Color(228, 48, 0));
    }

    /**
     * Retrieves the EquationEditor's Equation
     *
     * @return The equation representing the EquationEditor's contents
     */
    public Equation getEquation() {
        return this.equation;
    }

    /**
     * Triggers processing of a new equation when the equation field is
     * modified.
     */
    protected void equationChanged() {
        try {
            this.equation = new Equation(this.editor.getText());
        } catch (Exception e) {
            // Something is very wrong with the equation if we can't even get
            // this far. Bail here
            this.setInvalid();
            return;
        }

        this.setValid();

        for (EquationEditorListener l : this.listeners) {
            l.equationEdited(this.id, this.equation);
        }
    }

    /**
     * Lowers the background HSB's brightness by 2%.
     *
     * @param c The component to darken
     */
    private void darkenComponent(Component c) {
        this.hsvDecrease(c, 0.02f);
    }

    /**
     * Raises the background HSB's brightness by 3.5%.
     *
     * @param c The component to lighten
     */
    private void lightenComponent(Component c) {
        this.hsvDecrease(c, -0.035f);
    }


    /**
     * Retrieves a component's current colour, then decreases it's HSB brightness
     * by hsvPercentage.
     *
     * @param c The component to adjust
     * @param hsvPercentage The amount to alter the colour brightness by
     */
    private void hsvDecrease(Component c, float hsvPercentage) {
        Color origColour = c.getBackground();
        float[] hsv = Color.RGBtoHSB(
                origColour.getRed(),
                origColour.getGreen(),
                origColour.getBlue(),
                null);
        c.setBackground(new Color(
                Color.HSBtoRGB(hsv[0], hsv[1], hsv[2] - hsvPercentage)));
    }
}
