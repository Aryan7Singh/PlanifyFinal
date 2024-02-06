package com.example.planify;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_list")
public class Course {
    private String title;
    private String time;
    private String instructor;
    private String dayRepeat;
    private String locationRmNum;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public Course(String title, String time, String instructor, String dayRepeat, String locationRmNum) {
        this.title = title;
        this.time = time;
        this.instructor = instructor;
        this.dayRepeat = dayRepeat;
        this.locationRmNum = locationRmNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDayRepeat() {
        return dayRepeat;
    }

    public void setDayRepeat(String dayRepeat) {
        this.dayRepeat = dayRepeat;
    }

    public String getLocationRmNum() {
        return locationRmNum;
    }

    public void setLocationRmNum(String locationRmNum) {
        this.locationRmNum = locationRmNum;
    }
//    public String toString() {
//        return "{" + title + "," + time + "," + instructor + "}";
//    }
}
