package org.gcalc;

import com.bulenkov.darcula.DarculaLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class of the standalone program, but can also be used in other programs.
 * Instantiating this class will create a new window containing a graph and an
 * equation editor sidebar.
 */
public class GraphWindow extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private Graph graph;
    private Sidebar sidebar;

    private JMenuItem createNew, deleteAll, zoomIn, zoomOut, zoomReset;

    /**
     * Creates a new window to display and edit equations.
     *
     * @throws Exception if anything went wrong /shrug
     */
    public GraphWindow() throws Exception {
        // Try to set Darcula as UI theme. Otherwise, we'll just use the default
        try {
            UIManager.setLookAndFeel(DarculaLaf.class.getName());
        } catch (Exception e) { }

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
        split.setContinuousLayout(true);
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

    /**
     * Contains repetitive code to populate the menubar with items.
     *
     * @param menubar The Swing menubar instance to add everything to
     */
    private void createMenuBar(JMenuBar menubar) {
        /*
         * Expressions menu
         */
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

        /*
         * View menu
         */
        JMenu viewMenu = new JMenu("View");
        viewMenu.setMnemonic(KeyEvent.VK_V);
        viewMenu.getAccessibleContext().setAccessibleDescription(
                "Adjust graph drawing settings");
        menubar.add(viewMenu);

        this.zoomIn = new JMenuItem("Increase Zoom");
        this.zoomIn.addActionListener(this);
        this.zoomIn.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_EQUALS, KeyEvent.CTRL_MASK));
        this.zoomIn.getAccessibleContext().setAccessibleDescription(
                "Increases the zoom level of the graph");
        viewMenu.add(this.zoomIn);

        this.zoomOut = new JMenuItem("Decrease Zoom");
        this.zoomOut.addActionListener(this);
        this.zoomOut.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_MINUS, KeyEvent.CTRL_MASK));
        this.zoomOut.getAccessibleContext().setAccessibleDescription(
                "Decreases the zoom level of the graph");
        viewMenu.add(this.zoomOut);

        this.zoomReset = new JMenuItem("Reset Zoom");
        this.zoomReset.addActionListener(this);
        this.zoomReset.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_0, KeyEvent.CTRL_MASK));
        this.zoomReset.getAccessibleContext().setAccessibleDescription(
                "Resets the zoom level of the graph to the default value");
        viewMenu.add(this.zoomReset);
    }

    /**
     * Called in this case when a menubar item has been triggered, either by
     * clicking on it directly, or via keyboard shortcut.
     *
     * @param actionEvent The event supplied by Swing, used to determine which
     *                    menubar item was clicked
     */
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();

        if (source.equals(this.createNew)) {
            this.sidebar.newEquation();
        } else if (source.equals(this.deleteAll)) {
            this.sidebar.deleteAllEquations();
        } else if (source.equals(this.zoomIn)) {
            this.graph.increaseScale();
        } else if (source.equals(this.zoomOut)) {
            this.graph.decreaseScale();
        } else if (source.equals(this.zoomReset)) {
            this.graph.setScale(1);
        }
    }

    /**
     * Program entrypoint. Simply creates a `GraphWindow` instance. Failing
     * that, the exception thrown is printed, then the program terminated
     * with an error code.
     *
     * @param args Unused. Don't even try.
     */
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
