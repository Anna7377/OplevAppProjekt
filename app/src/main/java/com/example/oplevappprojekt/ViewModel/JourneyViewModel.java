package com.example.oplevappprojekt.ViewModel;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.oplevappprojekt.model.Journey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JourneyViewModel {

    public List<Journey>  journeys = new ArrayList<>();

    private String year, month, day;
    private Date date;

    public void setDay(String day) {
        this.day = day;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public Date CreateDate(){
        year=getYear();
        month=getMonth();
        day=getDay();
     int inday = parseInt(day);
     int inmonth = parseInt(month);
     int inyear=parseInt(year);
     Date date = new Date(inyear,inmonth,inday);
     return date;
    }

    public void newJourey(String country, Date date, int img){
       Journey journey1 = new Journey(country, date, img);
journeys.add(journey1);
CreateDate();
System.out.println("Journey created");
    }

}

