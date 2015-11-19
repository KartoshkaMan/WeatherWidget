package com.weatherwidget;

import com.weatherwidget.data.Settings;
import com.weatherwidget.data.Weather;
import com.weatherwidget.gui.WWMainWindow;
import com.weatherwidget.interfaces.Observable;
import com.weatherwidget.interfaces.Observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherUpdater extends Thread implements Observable {

    private int updTime;
    private int rclTime;

    private String appKey = "";
    private String cityID = "";

    private List<Observer> observers;

    private Weather weather;
    private String rclPeriod;

    public WeatherUpdater() {
        Settings settings = Settings.loadSettings();

        appKey = settings.getAppKey();
        cityID = settings.getCityID();

        updTime = settings.getUpdTime();
        rclTime = settings.getRclTime();

        observers = new ArrayList<>();

        setDaemon(true);
    }

    public void run() {
        while (true) {
            try {
                updateWeather();
                Thread.sleep(updTime * 1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    private void updateWeather() {
        weather = new Weather(getResponse());
        notifyObservers();
    }

    private InputStream openStream() {
        String path = "http://api.openweathermap.org/data/2.5/weather?id=" + cityID + "&appid=" + appKey;
        while (true) {
            try {
                URL url = new URL(path);
                return url.openStream();
            } catch (Exception e) {
                try {
                    Thread.sleep(rclTime * 1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
    private String getResponse() {
        String response = null;
        try {
            InputStreamReader isr = new InputStreamReader(openStream());
            BufferedReader br = new BufferedReader(isr);
            response = br.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return response;
    }

    public void setSettings(Settings settings){
        appKey = settings.getAppKey();
        cityID = settings.getCityID();

        updTime = settings.getUpdTime();
        rclTime = settings.getRclTime();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(weather);
        }
    }

    // Getters
    public String getAppKey() {
        return appKey;
    }
    public String getCityID() {
        return cityID;
    }
    public int getUpdTime() {
        return updTime;
    }
    public int getRclTime() {
        return rclTime;
    }
}
