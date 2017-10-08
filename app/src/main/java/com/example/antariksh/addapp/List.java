package com.example.antariksh.addapp;

/**
 * Created by Antariksh on 10/7/2017.
 */

public class List {

    private String Club;
    private String Event;
    private String Venue;
    private String Date;
    private String Time;
    private String Description;

    public List()
    {

    }

    public List(String club, String event, String venue, String date, String time, String description) {
        this.Club = club;
        this.Event = event;
        this.Venue = venue;
        this.Date = date;
        this.Time = time;
        this.Description = description;
    }

    public String getClub() {
        return Club;
    }

    public void setClub(String club) {
        this.Club = club;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        this.Event = event;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        this.Venue = venue;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}
