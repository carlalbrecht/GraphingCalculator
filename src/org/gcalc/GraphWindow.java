package org.gcalc;

import com.bulenkov.darcula.DarculaLaf;

import javax.swing.*;
import java.awt.*;

public class GraphWindow extends JFrame {
    public GraphWindow() throws Exception {
        UIManager.setLookAndFeel(new DarculaLaf().getClass().getName());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        try {
            new GraphWindow();
        } catch (Exception e) {
            System.err.println("Failed to create graph window.");
            System.exit(1);
        }
    }
}
