package com.weatherwidget.gui.buttons;

import com.weatherwidget.WeatherUpdater;
import com.weatherwidget.data.Settings;
import com.weatherwidget.data.sources.GetImage;
import com.weatherwidget.gui.SettingsDialog;

import javax.swing.*;
import java.awt.*;

public class ApproveButton extends SystemButton implements com.weatherwidget.interfaces.CommandPattern {

    SettingsDialog dialog;
    WeatherUpdater updater;

    public ApproveButton(SettingsDialog dialog, WeatherUpdater updater) {
        super(GetImage.getApproveImage());
        this.dialog = dialog;
        this.updater = updater;
    }

    @Override
    public void execute() {
        if (dialog.isReal()) {
            updater.setSettings(new Settings(dialog));
            dialog.dispose();

        }
        else {
            JOptionPane.showMessageDialog(dialog,
                    "Some settings are invalid.",
                    "Settings error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
