package com.example.oplevappprojekt.sites

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.oplevappprojekt.ViewModel.InspirationViewModel
import com.example.oplevappprojekt.data.InspirationRepository




// S215722

class InspirationUI{
}

@Composable
fun Inspiration(navMain: () -> Unit, navProfile: () -> Unit, viewModel: InspirationViewModel){
    var text = viewModel.read()
    val currentText = rememberSaveable {
        mutableStateOf(text)
    }
   // runBlocking {
      //  currentText.value = repo.read() }

    Scaffold(bottomBar = {BottomBar(onClick1 = {}, onClick2 = {navMain()}, onClick3 = {navProfile()})},
        content =
        {  Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            Column(
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
                    .absoluteOffset(15.dp, 15.dp)
            ) {
                Logo()
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                SmallTitle(text = "Mine Inspirationskilder")
                val scrollstate = rememberScrollState()

                Column(
                    modifier = Modifier

                        .verticalScroll(scrollstate)
                        .border(
                            width = 3.dp,
                            color = Color(
                                myColourString.toColorInt()
                            )
                        )
                        .width(300.dp)
                        .height(600.dp)
                ) {

                    TextField(
                        onValueChange = { currentText.value = it },
                        modifier = Modifier.width(300.dp),
                        placeholder = {
                            Text(text = "Indsæt inspirationskilder...") },
                        value = currentText.value,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                } } } } )
    viewModel.update(currentText.value)
}






