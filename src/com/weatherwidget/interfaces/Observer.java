package com.weatherwidget.interfaces;

import com.weatherwidget.data.Weather;

public interface Observer {
    void update(Weather weather);
}
