package com.example.oplevappprojekt.sites

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.IdeasViewModel
import com.example.oplevappprojekt.ViewModel.Journeysviewmodel

@Composable
fun IdeasPg(viewModel: IdeasViewModel, navCreate: ()->Unit
            ,journeyViewModel: Journeysviewmodel,
navLoad: ()->Unit){
    Scaffold(content = {
        Column() {
        TopCard(ImageId = R.drawable.image10, text = countryname)
        val ideas = viewModel.getCategorisedIdeas()
        IdeaGrid(list = ideas,
            randomimg = journeyViewModel.randomImg(),
            viewModel, navLoad = navLoad, navCreate)
    }}, floatingActionButton = { Fob {
        navCreate()
    }})
}