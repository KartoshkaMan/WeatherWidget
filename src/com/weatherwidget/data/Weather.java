package com.weatherwidget.data;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather implements Serializable {

    private static final File weatherPath = new File("./resource/data/");
    private static final File weatherFile = new File("./resource/data/weather.ww");

    private String city = "Unknown";
    private String degree = "0";
    private String icon = "default.png";
    private String updTime = "--.--.--, --:--";
    private String weather = "Unknown";

    // returns default weather
    private Weather() {

    }

    public Weather(String response) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject mainObject = (JSONObject) parser.parse(response);
            JSONObject weatherObject = (JSONObject) ((JSONArray) mainObject.get("weather")).get(0);

            this.city = mainObject.get("name").toString();
            this.icon = weatherObject.get("icon").toString() + ".png";
            this.degree = "" + (Math.round(Double.parseDouble(((JSONObject) mainObject.get("main")).get("temp").toString())) - 273);
            this.updTime = new SimpleDateFormat("dd.MM.yyyy, kk:mm").format(new Date());
            this.weather = weatherObject.get("main").toString();

            this.writeWeather();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
    }

    public static Weather loadWeather() {
        try {
            FileInputStream fis = new FileInputStream(weatherFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Weather newWeather = (Weather) ois.readObject();
            ois.close();
            fis.close();
            return newWeather;
        } catch (Exception e) {
            return new Weather();
        }
    }

    public String getCity() {
        return city;
    }
    public String getDegree() {
        return degree;
    }
    public String getIcon() {
        return icon;
    }
    public String getUpdTime() {
        return updTime;
    }
    public String getWeather() {
        return weather;
    }

    private void writeWeather() {
        try {
            if (!weatherPath.exists()) {
                weatherPath.mkdirs();
                weatherFile.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(weatherFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
