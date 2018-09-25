package com.xw.sample.slidinguppanellayout.demo1;

import java.util.List;

/**
 * <p>
 * Created by woxingxiao on 2017-07-10.
 */

public class WeatherModel {

    private String city;
    private int code;
    private String describe;
    private String tempNow;
    private String tempMin;
    private String tempMax;
    private String aqiDesc;
    private List<WeatherModel> forecasts;

    public WeatherModel(String city, int code, String tempNow, String tempMin, String tempMax, String aqiDesc) {
        this.city = city;
        this.code = code;
        this.tempNow = tempNow;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.aqiDesc = aqiDesc;
    }

    public WeatherModel(int code, String tempMin, String tempMax) {
        this.code = code;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescribe() {
        if (code == 1) {
            describe = "多云";
        } else if (code == 2) {
            describe = "雨";
        } else {
            describe = "晴";
        }
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTempNow() {
        return tempNow;
    }

    public void setTempNow(String tempNow) {
        this.tempNow = tempNow;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getAqiDesc() {
        return aqiDesc;
    }

    public void setAqiDesc(String aqiDesc) {
        this.aqiDesc = aqiDesc;
    }

    public List<WeatherModel> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<WeatherModel> forecasts) {
        this.forecasts = forecasts;
    }
}
