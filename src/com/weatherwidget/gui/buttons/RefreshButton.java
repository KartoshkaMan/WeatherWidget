package com.weatherwidget.gui.buttons;

import com.weatherwidget.WeatherUpdater;
import com.weatherwidget.data.sources.GetImage;
import com.weatherwidget.interfaces.CommandPattern;

public class RefreshButton extends SystemButton implements CommandPattern {

    WeatherUpdater updater;

    public RefreshButton() {
        super(GetImage.getRefreshImage());
    }

    public void setUpdater(WeatherUpdater updater) {
        this.updater = updater;
    }

    @Override
    public void execute() {
        updater.interrupt();
    }
}
