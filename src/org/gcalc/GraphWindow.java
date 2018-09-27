package org.gcalc;

import javax.swing.*;
import java.awt.*;

public class GraphWindow extends JFrame {
    public GraphWindow() {
        this.setTitle("Graphing Calculator");

        Container pane = this.getContentPane();
        pane.setPreferredSize(new Dimension(1024, 600));
        pane.setMinimumSize(new Dimension(800, 600));

        pane.add(new Sidebar(300, 600), BorderLayout.LINE_START);
        pane.add(new Graph(1024 - 300, 600), BorderLayout.CENTER);

        this.pack();
        this.setResizable(true);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GraphWindow();
    }
}
