package com.example.oplevappprojekt.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

import java.util.Date

//s215722
data class Journey(
    val country: String = "",
    val date: String = "",
    val UserID: String = ""
)

data class Idea(
    val title: String,
    val desc: String,
    val journeyID: String
)

data class category(
    val title: String
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
    val journeys = Firebase.firestore.collection("journeys")
    val journeylist: ArrayList<Journey> = arrayListOf(Journey("Denmark", date="25/08/02", "XYZ"))
    val idea = Firebase.firestore.collection("ideas")
    val idealist: ArrayList<Idea> = arrayListOf()

    fun getJourneys() {
        journeys.get().addOnSuccessListener { documents ->
            for (document in documents) {
                if (document.get("userID")?.equals(Firebase.auth.currentUser?.uid) == true) {
                    journeylist.add(document.toObject())
                } }
        _uiState.value=_uiState.value.copy(userjourneys = journeylist)} }

    fun addJourney(country: String, date: String){
       val journey = hashMapOf(
            "country" to country,
            "userID" to Firebase.auth.currentUser?.uid.toString(),
            "date" to date
        )
        journeys.document().set(journey)
    }

    fun selectJourney(country: String, date: String){
_uiState.value = _uiState.value.copy(currentcountry = country, currentdate = date)
    }

    fun getIdeas(){
        idea.get().addOnSuccessListener { documents ->
      for (document in documents){
          if(document.get("Document ID")?.equals(Firebase.auth.currentUser?.uid) ==true){
              idealist.add(document.toObject())
          } } } }

fun getIdeaslist():ArrayList<Idea>{
    return idealist
} }