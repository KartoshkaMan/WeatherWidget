package com.weatherwidget.gui;

import com.weatherwidget.interfaces.CommandPattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WWAbstractFrame extends JFrame {

    private Point mouseLocation;

    public WWAbstractFrame() {
        super();

        setUndecorated(true);
        addMouseListener(new MouseClickListener());
        addMouseMotionListener(new FrameMoveListener(this));
    }

    private class FrameMoveListener implements MouseMotionListener {

        JFrame frame;

        public FrameMoveListener(JFrame frame) {
            this.frame = frame;
        }


        @Override
        public void mouseDragged(MouseEvent e) {
            Point newPos = new Point(e.getXOnScreen() - mouseLocation.x, e.getYOnScreen() - mouseLocation.y);
            frame.setLocation(newPos);
        }

        @Override
        public void mouseMoved(MouseEvent e) {}
    }
    private class MouseClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseLocation = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseLocation = null;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    protected class SystemBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((CommandPattern)e.getSource()).execute();
        }
    }
}
