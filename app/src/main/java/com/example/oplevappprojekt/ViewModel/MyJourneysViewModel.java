package com.example.oplevappprojekt.ViewModel;

import androidx.lifecycle.ViewModel;

import com.example.oplevappprojekt.R;
import java.util.Random;
import com.example.oplevappprojekt.data.HardcodedJourneysRepository;
import com.example.oplevappprojekt.data.JourneyData;
import com.example.oplevappprojekt.model.Journey;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


//s215722
public class MyJourneysViewModel extends ViewModel {

   private  JourneyData journeyData;
   private HardcodedJourneysRepository repo;
   public MyJourneysViewModel(HardcodedJourneysRepository repo) {
       this.repo = repo;
       journeyData = new JourneyData();
       journeyData.setJourneys(repo.getJourneys());
   }

    public static int randomImg(){

        int i = ThreadLocalRandom.current().nextInt(0, 5);
        int img = R.drawable.image6;
        switch (i){
            case 1: img = R.drawable.image1;
            break;
            case 2: img = R.drawable.image2;
            break;
            case 3: img = R.drawable.image3;
            break;
            case 4: img = R.drawable.image4;
            break;
            case 5: img = R.drawable.image5;
            break;
        }
        return img;
    }

}
