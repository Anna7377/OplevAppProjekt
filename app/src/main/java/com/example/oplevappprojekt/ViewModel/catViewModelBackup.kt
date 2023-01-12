package com.example.oplevappprojekt.ViewModel

import com.example.oplevappprojekt.data.backupRepoCat
import kotlinx.coroutines.runBlocking

data class catState(
  val isCategorySelected: Boolean = false,
  val currentCatID: String = ""
)

class CatViewModel(){


  fun selectCategory(ID: String){

  }

}
