package com.example.oplevappprojekt.sites

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.IdeasViewModel

@Composable
fun ideas(viewModel: IdeasViewModel, navCreate: ()->Unit){
    Scaffold(content = {
        TopCard(ImageId = R.drawable.image10, text = countryname)
        val ideas = viewModel.getCategorisedIdeas()
        IdeaGrid(list = ideas)
    }, floatingActionButton = { Fob {
        navCreate()
    }})

}