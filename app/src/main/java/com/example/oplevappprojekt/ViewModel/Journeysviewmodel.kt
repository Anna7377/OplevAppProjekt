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
)

data class journeyState(
    var isJourneySelected: Boolean = false,
    var currentJourney: Journey? = null,
    var currentcountry: String? = null,
    var currentdate: String? = null,
var userjourneys: ArrayList<Journey> = arrayListOf())

class Journeysviewmodel {
    private val _uiState = mutableStateOf(journeyState())
    val uiState: State<journeyState> = _uiState
    val repo = HardcodedJourneysRepository()

    fun getJourneys() {
        var journeys: ArrayList<Journey> = arrayListOf()
        runBlocking{
            journeys = repo.getJourneys()
        }
        _uiState.value = _uiState.value.copy(userjourneys = journeys)
    }

    fun addJourney(country: String, date: String) {
        repo.addJourney(country = country, date = date)
    }

    fun selectJourney(country: String, date: String) {
        _uiState.value = _uiState.value.copy(currentcountry = country, currentdate = date)
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

