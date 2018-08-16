package com.example.felix.dailybucketlist.Goals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Goal {

    private boolean reached = false;
    private String name;
    private Calendar date;
    private long id;

    public Goal (String name, Calendar date) {
        this.name = name;
        this.date = date;
    }

    public Goal(String name){
        this(name, null);
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    //gibt Tag in deutsch! zurück
    public String getDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        return sdf.format(d);
    }

    //gibt Monat in deutsch zurück
    public String getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        Date d = new Date();
        return sdf.format(d);
    }

    public String getDateInText() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date d = new Date();
        return sdf.format(d);
    }

    public boolean isReached() {
        return reached;
    }

    public void setReached(boolean reached) {
        this.reached = reached;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
