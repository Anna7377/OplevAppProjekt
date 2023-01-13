package com.example.oplevappprojekt.ViewModel

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.asImageBitmap
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.data.HardcodedJourneysRepository
import com.example.oplevappprojekt.data.category
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList

//s215722
data class ideas(
    val title: String = "",
    val desc: String = "",
    val link: String = "",
    var categoryID: String? = null,
    val journeyID: String? = null
)

data class Journey(
    val country: String = "",
    val date: String = "",
    val time: Date = Date(System.currentTimeMillis()),
    val userID: String = "",
    var JourneyID: String = "",
    val originalJourneyID: String = ".",
    var isPinned: Boolean = false
)

data class journeyState(
    var isJourneySelected: Boolean = false,
    var currentJourneyID: String? = null,
    var currentcountry: String? = null,
    var currentdate: String? = null,
var userjourneys: ArrayList<Journey> = arrayListOf(),
var isPinned: Boolean = false,
val isOwned: Boolean = true,
val originalJourneyID: String = ".")

class Journeysviewmodel {
    private val _uiState = mutableStateOf(journeyState())
    val uiState: State<journeyState> = _uiState
    val repo = HardcodedJourneysRepository()

    fun getColCategories() : ArrayList<category>{
        var cat: ArrayList<category>
        runBlocking {
     cat = repo.getCategories(uiState.value.originalJourneyID) }
        return cat
    }

    fun getColIdeas() : ArrayList<ideas>{
        var ideas = arrayListOf<ideas>()
        runBlocking {
            ideas = repo.getOtherIdeas(uiState.value.originalJourneyID)
        }
        return ideas
    }

    fun getJourneys() {
        var journeys: ArrayList<Journey>
        runBlocking{
            journeys = repo.getJourneys()
        }
        _uiState.value = _uiState.value.copy(userjourneys = journeys)
    }

    fun addJourney(country: String, date: String) {
        repo.addJourney(country = country, date = date)
    }

    fun editCategory(name: String, ID: String){
        repo.editCategory(name=name, ID=ID)
    }

    fun selectJourney(country: String, date: String, ID: String, originalJourneyID: String) {
        var iscol: Boolean
        runBlocking {   iscol = repo.isCollaborated(ID) }

        _uiState.value = _uiState.value.copy(currentcountry = country,
            currentdate = date,
            currentJourneyID = ID,
            isJourneySelected = true,
            originalJourneyID = originalJourneyID,
        isOwned = !iscol)

    }

    fun deselect(){

        _uiState.value = _uiState.value.copy(isJourneySelected = false)
    }

    fun editJourney(country: String, date: String, ID: String, isPinned: Boolean){
        repo.editJourney(country=country, date=date, journeyID = ID, isPinned = isPinned)
        _uiState.value = _uiState.value.copy(currentcountry = country, currentdate = date, currentJourneyID = ID, isJourneySelected = true,
        isPinned = isPinned)
    }

    fun deleteJourney(){
        repo.deleteJourney(uiState.value.currentJourneyID.toString())
        getJourneys()
    }

    fun getCategories() : ArrayList<category>{
        var ret: ArrayList<category>
        runBlocking {
            System.out.println("journeyID is: "+uiState.value.currentJourneyID)
            ret = repo.getCategories(uiState.value.currentJourneyID.toString())
        }
        System.out.println("ret123: " + ret)
        return ret
    }

  fun pinJourney(){
        val journey = uiState.value.currentJourneyID
        val journeydoc = Firebase.firestore.collection("journeys").document(journey.toString())
        if(uiState.value.isPinned){
        journeydoc.update("isPinned", true)
        }
        else{
            journeydoc.update("isPinned", true)
        }

    }

    fun getOtherIdeas() : ArrayList<ideas>{
        var list: ArrayList<ideas>
runBlocking { list = repo.getOtherIdeas(uiState.value.currentJourneyID.toString()) }
        return list
    }

fun setImg(img: Bitmap?) {
    runBlocking {
    repo.setImage(uiState.value.currentJourneyID.toString(), img=img?.asImageBitmap()!!)
}}

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

