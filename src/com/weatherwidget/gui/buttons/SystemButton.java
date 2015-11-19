package com.weatherwidget.gui.buttons;

import javax.swing.*;

public class SystemButton extends JButton {
    public SystemButton(ImageIcon image) {
        super(image);
        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
    }
}
