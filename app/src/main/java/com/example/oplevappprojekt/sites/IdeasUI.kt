package com.example.oplevappprojekt.sites

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.viewModel.IdeasViewModel

@Composable
fun IdeasPg(viewModel: IdeasViewModel, navCreate: ()->Unit){
    Scaffold(content = {
        Column() {
        TopCard(ImageId = R.drawable.image10, text = countryname)
        val ideas = viewModel.getCategorisedIdeas()
        IdeaGrid(list = ideas)
    }}, floatingActionButton = { Fob {
        navCreate()
    }})
}