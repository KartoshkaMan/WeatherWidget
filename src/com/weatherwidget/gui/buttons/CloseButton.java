package com.weatherwidget.gui.buttons;

import com.weatherwidget.data.Settings;
import com.weatherwidget.data.sources.GetImage;
import com.weatherwidget.gui.WWMainWindow;
import com.weatherwidget.interfaces.CommandPattern;

public class CloseButton extends SystemButton implements CommandPattern {

    WWMainWindow window;

    public CloseButton(WWMainWindow wwMainWindow) {
        super(GetImage.getCloseImage());
        window = wwMainWindow;
    }

    @Override
    public void execute() {
        Settings settings = Settings.loadSettings();
        settings.setWidgetPos(window.getLocation());

        System.exit(0);
    }
}
