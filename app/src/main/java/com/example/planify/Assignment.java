package com.example.planify;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assignment_list")
public class Assignment {
    private String assignName;
    private String dueDate;
    private String classAssoc;
    private String dayRepeat;
    private String locRm;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public Assignment(String title, String time, String instructor, String dayRepeat, String locationRmNum) {
        this.assignName = title;
        this.dueDate = time;
        this.classAssoc = instructor;
        this.dayRepeat = dayRepeat;
        this.locRm = locationRmNum;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String title) {
        this.assignName = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String time) {
        this.dueDate = time;
    }

    public String getClassAssoc() {
        return classAssoc;
    }

    public void setClassAssoc(String instructor) {
        this.classAssoc = instructor;
    }

    public String getDayRepeat() {
        return dayRepeat;
    }

    public void setDayRepeat(String dayRepeat) {
        this.dayRepeat = dayRepeat;
    }

    public String getLocRm() {
        return locRm;
    }

    public void setLocRm(String locationRmNum) {
        this.locRm = locationRmNum;
    }
    public String toString() {
        return "{" + assignName + "," + dueDate + "," + classAssoc + "," + dayRepeat + "," + locRm + "}";
    }
}
