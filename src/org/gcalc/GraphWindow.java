package org.gcalc;

import com.bulenkov.darcula.DarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GraphWindow extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private Graph graph;
    private Sidebar sidebar;

    private JMenuItem createNew, deleteAll;

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
        this.graph = new Graph(1024-300, 600);
        this.graph.setMinimumSize(new Dimension(1024 - 400, 0));

        this.sidebar = new Sidebar(300, 600);
        this.sidebar.setMinimumSize(new Dimension(200, 0));

        // Capture equation create / delete events
        this.sidebar.addEquationListener(this.graph);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                          this.sidebar, this.graph);
        split.setDividerLocation(300);
        pane.add(split, BorderLayout.CENTER);

        // Create, initialise and add menu bar
        this.menuBar = new JMenuBar();
        this.createMenuBar(this.menuBar);
        this.setJMenuBar(this.menuBar);

        // Display window
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
    }

    private void createMenuBar(JMenuBar menubar) {
        JMenu exprMenu = new JMenu("Expressions");
        exprMenu.setMnemonic(KeyEvent.VK_X);
        exprMenu.getAccessibleContext().setAccessibleDescription(
                "Perform actions on expressions");
        menubar.add(exprMenu);

        this.createNew = new JMenuItem("New");
        this.createNew.addActionListener(this);
        this.createNew.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        this.createNew.getAccessibleContext().setAccessibleDescription(
                "Creates an additional, separate expression");
        exprMenu.add(this.createNew);

        exprMenu.add(new JSeparator());

        this.deleteAll = new JMenuItem("Delete all");
        this.deleteAll.addActionListener(this);
        this.deleteAll.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK));
        this.deleteAll.getAccessibleContext().setAccessibleDescription(
                "Deletes all existing expressions");
        exprMenu.add(this.deleteAll);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.createNew)) {
            this.sidebar.newEquation();
        }
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
