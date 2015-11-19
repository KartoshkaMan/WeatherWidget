package com.weatherwidget.data.sources;

import javax.swing.*;
import java.awt.*;

public final class GetLabel {
    public static JLabel getCityLabel(String city) {
        JLabel cityLabel = new JLabel(city);
        cityLabel.setFont(new Font("Consolas", Font.BOLD, 12));
        return cityLabel;
    }
    public static JLabel getDegreeLabel(String temperature) {
        JLabel degreeLabel = new JLabel(temperature + "\u00B0");
        degreeLabel.setFont(new Font("Consolas", Font.BOLD, 48));
        return degreeLabel;
    }
    public static JLabel getInfoLabel() {
        JLabel infoLabel = new JLabel("Powered By OpenWeather");
        infoLabel.setFont(new Font("Consolas", Font.BOLD, 8));
        return infoLabel;
    }
    public static JLabel getLastUpdateLabel(String date) {
        JLabel lastUpdateLabel = new JLabel("Last update: " + date);
        lastUpdateLabel.setFont(new Font("Consolas", Font.BOLD, 8));
        return lastUpdateLabel;
    }
    public static JLabel getStatusLabel() {
        JLabel statusLabel = new JLabel("up-to-date");
        statusLabel.setFont(new Font("Consolas", Font.BOLD, 8));
        return statusLabel;
    }
    public static JLabel getWeatherIconLabel(String iconName) {
        JLabel weatherIcon = new JLabel(GetImage.getWeatherImage(iconName));
        weatherIcon.setAlignmentY(Component.CENTER_ALIGNMENT);
        return weatherIcon;
    }
    public static JLabel getWeatherLabel(String weather) {
        JLabel weatherLabel = new JLabel(weather);
        weatherLabel.setFont(new Font("Consolas", Font.BOLD, 16));
        return weatherLabel;
    }
}
