package com.example.oplevappprojekt.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.oplevappprojekt.data.CollaboratorRepository
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
   fun showCol(ID: String) : ArrayList<String>{
var col: ArrayList<String> = arrayListOf()
      runBlocking {
         col = colrepo.showCol(ID)
      }
      return col
   }
   fun uncollab(ID: String){
      runBlocking {
         colrepo.uncollab(ID)
      }
   }
}