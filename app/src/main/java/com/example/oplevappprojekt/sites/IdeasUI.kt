package com.example.oplevappprojekt.sites

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.IdeasViewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel


// s215722
@Composable
fun IdeasPg(viewModel: IdeasViewModel, navCreate: ()->Unit
            ,journeyViewModel: Journeysviewmodel,
navLoad: ()->Unit, navDash: ()->Unit){
    Scaffold(content = {
        viewModel.getCategorisedIdeas()
        Column() {
        TopCard(ImageId = journeyViewModel.uiState.value.currentImg, text = countryname,
        viewModel = journeyViewModel,
            navMain = navCreate,
            ID = journeyViewModel.uiState.value.currentJourneyID.toString()
        )
            Button(onClick = {navDash()},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(myColourString.toColorInt()))
            ) {
                Text(text="Tilbage", color = Color.White)
            }
        val ideas = viewModel.uiState.value.idealist
        IdeaGrid(list = ideas,
            randomimg = journeyViewModel.randomImg(),
            viewModel, navLoad = navLoad, navCreate, journeyViewModel)
    }}, floatingActionButton = { Fob {
        navCreate()
    }})
}