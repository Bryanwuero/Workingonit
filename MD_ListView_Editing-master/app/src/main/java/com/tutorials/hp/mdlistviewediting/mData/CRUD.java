package com.tutorials.hp.mdlistviewediting.mData;

import java.util.ArrayList;


public class CRUD {

    ArrayList<Activity> activities;

    public CRUD(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    //ADD
    public boolean addNew(Activity activity)
    {
        try {
            activities.add(activity);

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    //RETRIEVE
    public ArrayList<Activity> getActivities()
    {
        return activities;
    }

    //UPDATE
    public boolean update(int pos,Activity newActivity)
    {
        try {
            activities.remove(pos);
            activities.add(pos, newActivity);

            return true;

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    //DELETE
    public boolean delete(int pos)
    {
        try {

            activities.remove(pos);

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

}
