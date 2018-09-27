package org.gcalc;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graphing Calculator");

        Container pane = frame.getContentPane();
        pane.setPreferredSize(new Dimension(1024, 600));
        pane.setMinimumSize(new Dimension(800, 600));

        pane.add(new Sidebar(300, 600), BorderLayout.LINE_START);
        pane.add(new Graph(1024 - 300, 600), BorderLayout.CENTER);

        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
