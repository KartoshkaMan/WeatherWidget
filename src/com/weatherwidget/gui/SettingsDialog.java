package com.weatherwidget.gui;

import com.weatherwidget.WeatherUpdater;
import com.weatherwidget.data.Settings;
import com.weatherwidget.data.sources.GetImage;
import com.weatherwidget.gui.buttons.ApproveButton;
import com.weatherwidget.gui.buttons.RejectButton;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends WWAbstractFrame {

    private JTextField cityIDField;
    private JTextField periodField;
    private JTextField recallPeriodField;

    public SettingsDialog(WeatherUpdater updater) {
        cityIDField = new JTextField(updater.getCityID());
        periodField = new JTextField("" + updater.getUpdTime());
        recallPeriodField = new JTextField("" + updater.getRclTime());

        ApproveButton approveBtn;
        RejectButton rejectBtn;
        approveBtn = new ApproveButton(this, updater);
        rejectBtn = new RejectButton(this);

        cityIDField.setEditable(false);

        SystemBtnListener listener = new SystemBtnListener();
        approveBtn.addActionListener(listener);
        rejectBtn.addActionListener(listener);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.PAGE_AXIS));
        labelPanel.setBackground(Color.WHITE);
        labelPanel.add(new JLabel("App Key: "));
        labelPanel.add(new JLabel("City ID: "));
        labelPanel.add(new JLabel("Update Period (sec): "));
        labelPanel.add(new JLabel("Recall Period (sec): "));

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.PAGE_AXIS));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.add(cityIDField);
        fieldPanel.add(periodField);
        fieldPanel.add(recallPeriodField);

        JPanel systemPanel = new JPanel();
        systemPanel.setLayout(new BoxLayout(systemPanel, BoxLayout.LINE_AXIS));
        systemPanel.setBackground(Color.WHITE);
        systemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        systemPanel.add(Box.createHorizontalGlue());
        systemPanel.add(approveBtn);
        systemPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        systemPanel.add(rejectBtn);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.LINE_AXIS));
        settingsPanel.setBackground(Color.WHITE);
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        settingsPanel.add(labelPanel);
        settingsPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        settingsPanel.add(fieldPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        mainPanel.add(settingsPanel);
        mainPanel.add(systemPanel);

        getContentPane().add(mainPanel);
        setTitle("Settings");
        setIconImage(GetImage.getSettingsImage().getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(Settings.loadSettings().getSettingsPos());
        setTitle("Settings");
        pack();
        setVisible(true);
    }

    public boolean isReal() {
        return (Integer.parseInt(this.periodField.getText()) > 0 && Integer.parseInt(this.recallPeriodField.getText()) > 0);
    }

    public String getCityID() {
        return this.cityIDField.getText();
    }
    public int getUpdTime() {
        return Integer.parseInt(this.periodField.getText());
    }
    public int getRclTime() {
        return Integer.parseInt(this.recallPeriodField.getText());
    }

}
