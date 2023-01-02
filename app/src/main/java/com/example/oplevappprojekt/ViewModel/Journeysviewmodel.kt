package com.example.oplevappprojekt.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.data.HardcodedJourneysRepository
import java.util.concurrent.ThreadLocalRandom

//s215722
data class Journey(
    val country: String = "",
    val date: String = "",
    val UserID: String = "",
)

data class journeyState(
    val isJourneySelected: Boolean = false,
    val currentJourney: Journey? = null,
    val currentcountry: String? = null,
    val currentdate: String? = null,
val userjourneys: ArrayList<Journey> = arrayListOf(Journey("Denmark", date="25/08/02", "XYZ"))
)

class Journeysviewmodel {
    private val _uiState = mutableStateOf(journeyState())
    val uiState: State<journeyState> = _uiState
    val repo = HardcodedJourneysRepository()

    fun getJourneys() {
        val journeyslist = repo.getJourneys()
        _uiState.value = _uiState.value.copy(userjourneys = journeyslist)
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

