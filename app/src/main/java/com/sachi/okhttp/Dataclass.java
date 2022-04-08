package com.sachi.okhttp;

public class Dataclass {
    String season;
    String round;
    String url;
    String raceName;


    //Circuit
    String circuitid;
    String circuitUrl;
    String circuitName;

    //Location

    String locationLat;
    String locationLatitude;
    String locationLocality;
    String locationCountry;

    String date;
    String time;

    //first Practice
    String firstPracticDate;
    String firstPractictime;

    //second Practice
    String secondPracticDate;
    String secondPractictime;

    //third Practice
    String thirdPracticDate;
    String thirdPractictime;

    //Qulifing Practice
    String qulifingPracticDate;
    String qulifingPractictime;

    public Dataclass(String season, String round, String url, String raceName, String circuitid, String circuitUrl, String circuitName, String locationLat, String locationLatitude, String locationLocality, String locationCountry, String date, String time, String firstPracticDate, String firstPractictime, String secondPracticDate, String secondPractictime, String thirdPracticDate, String thirdPractictime, String qulifingPracticDate, String qulifingPractictime) {
        this.season = season;
        this.round = round;
        this.url = url;
        this.raceName = raceName;
        this.circuitid = circuitid;
        this.circuitUrl = circuitUrl;
        this.circuitName = circuitName;
        this.locationLat = locationLat;
        this.locationLatitude = locationLatitude;
        this.locationLocality = locationLocality;
        this.locationCountry = locationCountry;
        this.date = date;
        this.time = time;
        this.firstPracticDate = firstPracticDate;
        this.firstPractictime = firstPractictime;
        this.secondPracticDate = secondPracticDate;
        this.secondPractictime = secondPractictime;
        this.thirdPracticDate = thirdPracticDate;
        this.thirdPractictime = thirdPractictime;
        this.qulifingPracticDate = qulifingPracticDate;
        this.qulifingPractictime = qulifingPractictime;
    }
}
