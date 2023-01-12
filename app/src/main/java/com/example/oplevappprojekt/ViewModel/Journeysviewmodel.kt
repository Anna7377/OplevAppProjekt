package com.example.oplevappprojekt.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.data.HardcodedJourneysRepository
import kotlinx.coroutines.runBlocking
import java.sql.Timestamp
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList

//s215722
data class Journey(
    val country: String = "",
    val date: String = "",
    val time: Date = Date(System.currentTimeMillis()),
    val userID: String = "",
    var JourneyID: String = "",
    var isPinned : Boolean = false,
    var originalJourneyID: String = "",
)

data class journeyState(
    var isJourneySelected: Boolean = false,
    var currentJourneyID: String? = null,
    var currentcountry: String? = null,
    var currentdate: String? = null,
    var userjourneys: ArrayList<Journey> = arrayListOf(),
    var isPinned: Boolean = false)

class Journeysviewmodel {
    private val _uiState = mutableStateOf(journeyState())
    val uiState: State<journeyState> = _uiState
    val repo = HardcodedJourneysRepository()

    fun getJourneys() {
        var journeys: ArrayList<Journey> = arrayListOf()
        /*
        var pinnedJourneys: ArrayList<Journey> = arrayListOf()
        var nonPinnedJourneys: ArrayList<Journey> = arrayListOf()
         */

        runBlocking{
            journeys = repo.getJourneys()
        }



        _uiState.value = _uiState.value.copy(userjourneys = journeys)
    }

    fun addJourney(country: String, date: String) {
        repo.addJourney(country = country, date = date)
    }

    fun selectJourney(country: String, date: String, ID: String) {
        _uiState.value = _uiState.value.copy(currentcountry = country, currentdate = date, currentJourneyID = ID, isJourneySelected = true)
    }
    fun deselect(){
        _uiState.value = _uiState.value.copy(isJourneySelected = false)
    }

    fun editJourney(country: String, date: String, ID: String, isPinned: Boolean){
        repo.editJourney(country=country, date=date, journeyID = ID, pinVal = isPinned)
        _uiState.value = _uiState.value.copy(currentcountry = country, currentdate = date, currentJourneyID = ID, isJourneySelected = true)
    }

    fun deleteJourney(){
        repo.deleteJourney(uiState.value.currentJourneyID.toString())
        getJourneys()
    }



    fun randomImg(): Int {
        val i = ThreadLocalRandom.current().nextInt(0, 5)
        var img: Int = R.drawable.image6
        when (i) {
            1 -> img = R.drawable.image1
            2 -> img = R.drawable.image2
            3 -> img = R.drawable.image3
            4 -> img = R.drawable.image4
            5 -> img = R.drawable.image5
        }
        return img
    }
}

