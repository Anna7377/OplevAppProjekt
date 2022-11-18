package com.example.oplevappprojekt.ViewModel;

import com.example.oplevappprojekt.model.Journey;

import java.util.List;

public class MyJourneysViewModel {

    private List<Journey> journeys;
    public MyJourneysViewModel(List<Journey> journeys) {
        this.journeys = journeys;
    }
    public List<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<Journey> journeys) {
        this.journeys = journeys;
    }
}
