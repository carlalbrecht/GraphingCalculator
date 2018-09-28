package org.gcalc;

import com.bulenkov.darcula.DarculaLaf;

import javax.swing.*;
import java.awt.*;

public class GraphWindow extends JFrame {
    public GraphWindow() throws Exception {
        // Set Darcula as UI theme
        UIManager.setLookAndFeel(DarculaLaf.class.getName());

        // Set window properties
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Graphing Calculator");

        Container pane = this.getContentPane();
        pane.setPreferredSize(new Dimension(1024, 600));
        pane.setMinimumSize(new Dimension(800, 600));

        // Inner elements
        Graph graph = new Graph(1024-300, 600);
        graph.setMinimumSize(new Dimension(1024 - 400, 0));

        Sidebar sidebar = new Sidebar(300, 600);
        sidebar.setMinimumSize(new Dimension(200, 0));

        // Capture equation create / delete events
        sidebar.addEquationListener(graph);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                          sidebar, graph);
        split.setDividerLocation(300);
        pane.add(split, BorderLayout.CENTER);

        // Display window
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            // Create root window instance, or just give up
            new GraphWindow();
        } catch (Exception e) {
            System.err.println("Failed to create graph window. Exception:\n");
            System.err.println(e.toString());
            System.exit(1);
        }
    }
}
