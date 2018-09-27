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
        this.setPreferredSize(new Dimension(width, 50));

        this.editor = new JTextField();
        this.editor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        this.editor.setFont(new Font("monospaced", Font.PLAIN, 16));
        this.add(this.editor);

        // Make odd rows darker
        if (id % 2 == 1) {
            darkenComponent(this);
            darkenComponent(this.editor);
        }
    }

    public int getID() {
        return this.id;
    }

    /**
     * Lowers the background HSB's brightness by 5%.
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
                Color.HSBtoRGB(hsv[0], hsv[1], hsv[2] - (float) 0.05)));
    }
}
