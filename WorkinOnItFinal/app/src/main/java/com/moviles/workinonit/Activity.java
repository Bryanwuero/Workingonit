package com.moviles.workinonit;

/**
 * Created by arroy_000 on 11/9/2016.
 */
public class Activity {
    String name;
    String description;
    String dateStart;
    String dateFinish;
    boolean status;

    public Activity() {
    }

    public Activity(String name, String description, String dateStart, String dateFinish, boolean status) {
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.status = status;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateFinish() {return dateFinish;}

    public void setDateFinish(String dateFinish) {this.dateFinish = dateFinish;}

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {this.status = status;}
}
