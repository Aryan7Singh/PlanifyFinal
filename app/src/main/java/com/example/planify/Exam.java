package com.example.planify;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "exam_list")

public class Exam {
    private String name;
    private String date;
    private String time;
    private String location;
    private String details;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Exam(String name, String date, String time, String location, String details) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
