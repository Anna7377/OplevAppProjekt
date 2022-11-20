package com.example.oplevappprojekt.ViewModel;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.example.oplevappprojekt.data.JourneyData;
import com.example.oplevappprojekt.data.JourneysRepository;
import com.example.oplevappprojekt.model.Journey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JourneyViewModel extends ViewModel {

    private JourneyData journeyData;
    private JourneysRepository repo;
    public JourneyViewModel(JourneysRepository repo) {
        this.repo = repo;
    }

    public JourneyData getJourneyData() {
        return journeyData;

    }

    public void setJourneyData(JourneyData journeyData) {
        this.journeyData = journeyData;
    }

    private int year, month, day;
    private Date date;

    public void setDay(String day) {
        this.day = parseInt(day);
    }

    public void setYear(String year) {
        this.year = parseInt(year);
    }

    public void setMonth(String month) {
        this.month = parseInt(month);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Date CreateDate(int year, int month, int day){
     Date date = new Date(year,month,day);
     return date;
    }

    public void newJourey(String country, int year, int month, int day, int img){
       Journey journey1 = new Journey(country, CreateDate(year, month, day), img);
       repo.addJourney(journey1);

    }
}

