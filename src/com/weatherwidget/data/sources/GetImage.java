package com.weatherwidget.data.sources;

import javax.swing.*;
import java.awt.*;

public final class GetImage {
    private final static String SYSTEM_ICON_PATH = "./resource/images/system/";
    private final static String WEATHER_ICON_PATH = "./resource/images/weather/";

    public static ImageIcon getApproveImage() {
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(SYSTEM_ICON_PATH + "ic_check_black_18dp.png"));
    }
    public static ImageIcon getCloseImage(){
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(SYSTEM_ICON_PATH + "ic_close_black_18dp.png"));
    }
    public static ImageIcon getMainImage() {
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(SYSTEM_ICON_PATH + "main.png"));
    }
    public static ImageIcon getRefreshImage() {
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(SYSTEM_ICON_PATH + "ic_refresh_black_18dp.png"));
    }
    public static ImageIcon getSettingsImage() {
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(SYSTEM_ICON_PATH + "ic_settings_black_18dp.png"));
    }
    public static ImageIcon getWeatherImage(String imgName) {
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(WEATHER_ICON_PATH + imgName));
    }
}
