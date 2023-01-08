package com.example.oplevappprojekt.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.oplevappprojekt.data.CollaboratorRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking

data class colstate(
   val retText: String = ""
)

class CollaboratorViewmodel {
   private val _uiState = mutableStateOf(colstate())
   val uiState: State<colstate> = _uiState
val colrepo = CollaboratorRepository()

fun addJourney(orig: String){
   var ret: String
   runBlocking { ret = colrepo.addJourney(orig) }
   _uiState.value = _uiState.value.copy(retText = ret )
}

   fun showCol(orig: String) : ArrayList<String> {
      var col: ArrayList<String>
      runBlocking { col = colrepo.showCol(orig) }
      return col
   }

}