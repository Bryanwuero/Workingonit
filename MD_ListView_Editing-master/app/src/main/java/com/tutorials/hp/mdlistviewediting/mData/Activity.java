package com.tutorials.hp.mdlistviewediting.mData;


public class Activity {
    private String name;
    private String description;
    private String dateStart;
    private String dateFinish;

    public Activity(String name, String description, String dateStart, String dateFinish){
        this.name =name;
        this.description =description;
        this.dateStart = dateStart ;
        this.dateFinish = dateFinish;
    }

    public Activity() {

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

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }
}
