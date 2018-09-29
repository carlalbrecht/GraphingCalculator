// Based off https://www.formdev.com/blog/swing-tip-jsplitpane-with-zero-size-divider/
package org.gcalc;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

public class JMinimalSplitPane extends JSplitPane {
    private int dividerDragSize = 9;
    private int dividerDragOffset = 4;

    public JMinimalSplitPane() {
        this(HORIZONTAL_SPLIT);
    }

    public JMinimalSplitPane(int orientation) {
        super(orientation, null, null);
    }

    public JMinimalSplitPane(int orientation,
                             Component component, Component component1) {
        super(orientation, component, component1);
        this.setContinuousLayout(true);
        this.setDividerSize(1);
    }

    @Override
    public void doLayout() {
        super.doLayout();

        // Increase divider width or height
        BasicSplitPaneDivider divider =
                ((BasicSplitPaneUI) this.getUI()).getDivider();
        Rectangle bounds = divider.getBounds();
        if (this.orientation == HORIZONTAL_SPLIT) {
            bounds.x -= this.dividerDragOffset;
            bounds.width = this.dividerDragSize;
        } else {
            bounds.y -= this.dividerDragOffset;
            bounds.height = this.dividerDragSize;
        }

        divider.setBounds(bounds);
    }

    @Override
    public void updateUI() {
        this.setUI(new JMinimalSplitPaneUI());
        this.revalidate();
    }

    public int getDividerDragSize() {
        return this.dividerDragSize;
    }

    public void setDividerDragSize(int dividerDragSize) {
        this.dividerDragSize = dividerDragSize;
        this.revalidate();
    }

    public int getDividerDragOffset() {
        return this.dividerDragOffset;
    }

    public void setDividerDragOffset(int dividerDragOffset) {
        this.dividerDragOffset = dividerDragOffset;
        this.revalidate();
    }

    private class JMinimalSplitPaneUI extends BasicSplitPaneUI {
        @Override
        public BasicSplitPaneDivider createDefaultDivider() {
            return new ZeroSizeDivider(this);
        }
    }

    private class ZeroSizeDivider extends BasicSplitPaneDivider {
        public ZeroSizeDivider(BasicSplitPaneUI ui) {
            super(ui);
            super.setBorder(null);
            this.setBackground(UIManager.getColor("controlShadow"));
        }

        @Override
        public void setBorder(Border border) { }

        @Override
        public void paint(Graphics g) {
            g.setColor(this.getBackground());
            if (this.orientation == HORIZONTAL_SPLIT) {
                g.drawLine(JMinimalSplitPane.this.dividerDragOffset, 0,
                           JMinimalSplitPane.this.dividerDragOffset, this.getHeight() - 1);
            } else {
                g.drawLine(0, JMinimalSplitPane.this.dividerDragOffset,
                           this.getHeight() - 1, JMinimalSplitPane.this.dividerDragOffset);
            }
        }

        @Override
        protected void dragDividerTo(int location) {
            super.dragDividerTo(location + JMinimalSplitPane.this.dividerDragOffset);
            System.out.println(((BasicSplitPaneUI) JMinimalSplitPane.this.getUI()).getDivider().getBounds());
        }

        @Override
        protected void finishDraggingTo(int location) {
            super.finishDraggingTo(location + JMinimalSplitPane.this.dividerDragOffset);
        }
    }

}
