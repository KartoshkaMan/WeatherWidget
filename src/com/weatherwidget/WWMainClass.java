package com.weatherwidget;

import com.weatherwidget.gui.WWMainWindow;

public class WWMainClass {

    public static void main(String[] args) {
        WeatherUpdater updater = new WeatherUpdater();
        WWMainWindow window = new WWMainWindow(updater);
        updater.start();
    }

}
