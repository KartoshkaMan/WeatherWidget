package com.weatherwidget.gui.buttons;

import com.weatherwidget.data.Settings;
import com.weatherwidget.data.sources.GetImage;
import com.weatherwidget.gui.SettingsDialog;
import com.weatherwidget.interfaces.CommandPattern;

public class RejectButton extends SystemButton implements CommandPattern {

    SettingsDialog dialog;

    public RejectButton(SettingsDialog dialog) {
        super(GetImage.getCloseImage());
        this.dialog = dialog;
    }

    @Override
    public void execute() {
        Settings settings = Settings.loadSettings();
        settings.setSettingsPos(dialog.getLocation());
        dialog.dispose();
    }
}
