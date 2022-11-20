package com.example.oplevappprojekt.ViewModel;

import com.example.oplevappprojekt.R;
import com.example.oplevappprojekt.data.JourneyData;
import com.example.oplevappprojekt.model.Journey;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyJourneysViewModel {

   private  JourneyData journeyData;

    public JourneyData getJourneyData() {
        return journeyData;
    }

    public void setJourneyData(JourneyData journeyData) {
        this.journeyData = journeyData;
    }


    public MyJourneysViewModel() {
        Journey journey1 = new Journey("Danmark", new Date(2000,2,20), R.drawable.image1);
        ArrayList<Journey> journeys = new ArrayList<>();
        journeys.add(journey1);
        //Her skal der ikke laves hel ny journeydata. den skal hente hvad JourneyViewModel har gemt.
        setJourneyData(new JourneyData());
        getJourneyData().setJourneys(journeys);


    }

}
