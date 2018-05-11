package edu.maine.castonguay.grateful_today;

import java.util.ArrayList;

public class GratitudeList {

    private static ArrayList<String> GratitudeList = new ArrayList<>();

    static public ArrayList<String> createInitialGratitudeList(ArrayList<String> gratitudes){

        if(gratitudes.isEmpty() || gratitudes == null){
            GratitudeList.add("Love");
            GratitudeList.add("Hope");
            GratitudeList.add("Plumbing");
        } else {
            GratitudeList = gratitudes;
        }

        return GratitudeList;
    }

    static public ArrayList<String> getGratitudeList(){
        return GratitudeList;
    }

    static public void setGratitudeList(ArrayList<String> gratitudes){
        GratitudeList = gratitudes;
    }



}
