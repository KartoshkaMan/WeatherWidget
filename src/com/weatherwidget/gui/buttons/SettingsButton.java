package com.weatherwidget.gui.buttons;

import com.weatherwidget.WeatherUpdater;
import com.weatherwidget.data.sources.GetImage;
import com.weatherwidget.gui.SettingsDialog;
import com.weatherwidget.interfaces.CommandPattern;

public class SettingsButton extends SystemButton implements CommandPattern {

    WeatherUpdater updater;

    public SettingsButton() {
        super(GetImage.getSettingsImage());
    }

    public void setUpdater(WeatherUpdater updater) {
        this.updater = updater;
    }

    @Override
    public void execute() {
        new SettingsDialog(updater);
    }
}
