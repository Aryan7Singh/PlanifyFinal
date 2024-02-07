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
    public Assignment(String assignName, String dueDate, String classAssoc, String dayRepeat, String locRm) {
        this.assignName = assignName;
        this.dueDate = dueDate;
        this.classAssoc = classAssoc;
        this.dayRepeat = dayRepeat;
        this.locRm = locRm;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getClassAssoc() {
        return classAssoc;
    }

    public void setClassAssoc(String classAssoc) {
        this.classAssoc = classAssoc;
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

    public void setLocRm(String locRm) {
        this.locRm = locRm;
    }
    public String toString() {
        return "{" + assignName + "," + dueDate + "," + classAssoc + "," + dayRepeat + "," + locRm + "}";
    }
}
