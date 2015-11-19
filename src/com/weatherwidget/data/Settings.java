package com.weatherwidget.data;

import com.weatherwidget.gui.SettingsDialog;

import java.awt.*;
import java.io.*;

public class Settings implements Serializable {

    private int updTime = 5;
    private int rclTime = 1;

    private String appKey = "b19d19da0160086d65449691c98b1f20";
    private String cityID = "524901";

    private Point widgetPos = new Point(50, 50);
    private Point settingsPos = new Point(50, 50);

    private static final File settingsPath = new File("./resource/data/");
    private static final File settingsFile = new File("./resource/data/settings.ww");

    private Settings() {

    }

    public Settings(SettingsDialog dialog) {
        cityID = dialog.getCityID();

        updTime = dialog.getUpdTime();
        rclTime = dialog.getRclTime();

        settingsPos = dialog.getLocation();

        saveSettings();
    }

    public static Settings loadSettings() {
        try {
            FileInputStream fis = new FileInputStream(settingsFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Settings newSettings = (Settings) ois.readObject();
            ois.close();
            fis.close();
            return newSettings;
        } catch (Exception e) {
            return new Settings();
        }
    }

    public void setWidgetPos(Point position) {
        widgetPos = position;
        saveSettings();
    }
    public void setSettingsPos(Point settingsPos) {
        this.settingsPos = settingsPos;
        saveSettings();
    }

    public int getRclTime() {
        return this.rclTime;
    }
    public int getUpdTime() {
        return this.updTime;
    }

    public String getAppKey() {
        return this.appKey;
    }
    public String getCityID() {
        return this.cityID;
    }

    private void saveSettings() {
        try {
            if (!settingsPath.exists()) {
                settingsPath.mkdirs();
                settingsFile.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(settingsFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Point getWidgetPos() {
        return widgetPos;
    }
    public Point getSettingsPos() {
        return settingsPos;
    }
}
