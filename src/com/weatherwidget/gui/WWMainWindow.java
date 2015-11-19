package com.weatherwidget.gui;

import com.weatherwidget.WeatherUpdater;
import com.weatherwidget.data.Settings;
import com.weatherwidget.data.Weather;
import com.weatherwidget.data.sources.GetImage;
import com.weatherwidget.data.sources.GetLabel;
import com.weatherwidget.gui.buttons.CloseButton;
import com.weatherwidget.gui.buttons.RefreshButton;
import com.weatherwidget.gui.buttons.SettingsButton;
import com.weatherwidget.interfaces.Observer;

import javax.swing.*;
import java.awt.*;

public class WWMainWindow extends WWAbstractFrame implements Observer {

    private JLabel cityLabel;
    private JLabel lastUpdateLabel;
    private JLabel temperatureLabel;
    private JLabel weatherIcon;
    private JLabel weatherLabel;

    private CloseButton closeButton;
    private RefreshButton refreshButton;
    private SettingsButton settingsButton;

    public WWMainWindow(WeatherUpdater updater) {
        SystemBtnListener btnListener = new SystemBtnListener();
        Weather weather = Weather.loadWeather();

        updater.addObserver(this);

        cityLabel = GetLabel.getCityLabel(weather.getCity());
        lastUpdateLabel = GetLabel.getLastUpdateLabel(weather.getUpdTime());
        temperatureLabel = GetLabel.getDegreeLabel(weather.getDegree());
        weatherIcon = GetLabel.getWeatherIconLabel(weather.getIcon());
        weatherLabel = GetLabel.getWeatherLabel(weather.getWeather());

        closeButton = new CloseButton(this);
        closeButton.addActionListener(btnListener);
        refreshButton = new RefreshButton();
        refreshButton.addActionListener(btnListener);
        refreshButton.setUpdater(updater);
        settingsButton = new SettingsButton();
        settingsButton.addActionListener(btnListener);
        settingsButton.setUpdater(updater);

        // Frame //
        Settings settings = Settings.loadSettings();
        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setBackground(Color.WHITE);
        container.add(this.createMainPanel());
        setTitle("WeatherWidget");
        setIconImage(GetImage.getMainImage().getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(settings.getWidgetPos());
        pack();
        setVisible(true);
        ///////////
    }

    private void setWeather(Weather weather){
        cityLabel.setText(weather.getCity());
        lastUpdateLabel.setText("Last update: " + weather.getUpdTime());
        temperatureLabel.setText(weather.getDegree() + "\u00B0");
        weatherIcon.setIcon(GetImage.getWeatherImage(weather.getIcon()));
        weatherLabel.setText(weather.getWeather());
    }

    // Panels
    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.LINE_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        infoPanel.add(lastUpdateLabel);
        infoPanel.add(Box.createHorizontalGlue());
        infoPanel.add(GetLabel.getInfoLabel());
        return infoPanel;
    }
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        mainPanel.add(this.createWeatherPanel());
        mainPanel.add(this.createInfoPanel());
        return mainPanel;
    }
    private JPanel createSystemPanel() {
        JPanel systemPanel = new JPanel();
        systemPanel.setLayout(new BoxLayout(systemPanel, BoxLayout.LINE_AXIS));
        systemPanel.setBackground(Color.WHITE);
        systemPanel.add(Box.createHorizontalGlue());
        systemPanel.add(settingsButton);
        systemPanel.add(refreshButton);
        systemPanel.add(closeButton);
        return systemPanel;
    }
    private JPanel createTemperaturePanel() {
        JPanel temperatureAlignmentPanel = new JPanel();
        temperatureAlignmentPanel.setBackground(Color.WHITE);
        temperatureAlignmentPanel.setLayout(new BoxLayout(temperatureAlignmentPanel, BoxLayout.LINE_AXIS));
        temperatureAlignmentPanel.add(Box.createHorizontalGlue());
        temperatureAlignmentPanel.add(temperatureLabel);

        JPanel weatherAlignmentPanel = new JPanel();
        weatherAlignmentPanel.setBackground(Color.WHITE);
        weatherAlignmentPanel.setLayout(new BoxLayout(weatherAlignmentPanel, BoxLayout.LINE_AXIS));
        weatherAlignmentPanel.add(Box.createHorizontalGlue());
        weatherAlignmentPanel.add(weatherLabel);

        JPanel cityAlignmentPanel = new JPanel();
        cityAlignmentPanel.setBackground(Color.WHITE);
        cityAlignmentPanel.setLayout(new BoxLayout(cityAlignmentPanel, BoxLayout.LINE_AXIS));
        cityAlignmentPanel.add(Box.createHorizontalGlue());
        cityAlignmentPanel.add(cityLabel);

        Dimension tempPanelSize = new Dimension(120, 128);
        JPanel tempPanel = new JPanel();
        tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.PAGE_AXIS));
        tempPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        tempPanel.setBackground(Color.WHITE);
        tempPanel.setMinimumSize(tempPanelSize);
        tempPanel.setPreferredSize(tempPanelSize);
        tempPanel.setMaximumSize(tempPanelSize);
        tempPanel.add(this.createSystemPanel());
        tempPanel.add(Box.createVerticalGlue());
        tempPanel.add(temperatureAlignmentPanel);
        tempPanel.add(weatherAlignmentPanel);
        tempPanel.add(cityAlignmentPanel);

        return tempPanel;
    }
    private JPanel createWeatherPanel() {
        JPanel weatherPanel = new JPanel();
        weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.LINE_AXIS));
        weatherPanel.setBackground(Color.WHITE);
        weatherPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        weatherPanel.add(weatherIcon);
        weatherPanel.add(this.createTemperaturePanel());
        return weatherPanel;
    }

    @Override
    public void update(Weather weather) {
        this.setWeather(weather);
    }
}
