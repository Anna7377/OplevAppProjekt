package com.example.oplevappprojekt.sites
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.oplevappprojekt.R
import com.example.oplevappprojekt.ViewModel.*
import com.example.oplevappprojekt.model.Idea
import com.example.oplevappprojekt.model.Journey

// s215718 & s213370


@Composable
fun CategoryPage(navigationInsp: () -> Unit,
                 navCreate: () -> Unit, navCategories: () -> Unit, navIdeas: () -> Unit, navProfile: () -> Unit, navInvite: () -> Unit,viewModel:CategoryViewModel){
    Scaffold(bottomBar = { BottomBar(onClick1 = {navigationInsp()}, onClick2 = { navProfile() }, onClick3 = {navProfile})},
        content =
        {
            Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
                Column (
                    modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                    var categorylist = ArrayList<com.example.oplevappprojekt.ViewModel.Category>()
                    TopCard(ImageId = R.drawable.image10,
                        text = "Kategorier")
                    viewModel.getCategories()

                    for (userCategory in viewModel.uiState.value.userCategories) {
                        if (userCategory.journeyID.equals(viewModel.uiState.value.currentJourneyID)){
                            categorylist.add(userCategory)
                        }
                    }

                    if (categorylist.isEmpty()){
                        Text(text = "Ingen Kategorier", color = Color.White, textAlign = TextAlign.Center, fontSize = 40.sp)
                    }else
                        CategoryList(list = categorylist, navIdeas = navIdeas, viewModel = viewModel)

                } }} ,
        floatingActionButton = { Fob (navCreate = navCreate)})
}
@Composable
fun CategoryList(viewModel: CategoryViewModel, list: ArrayList<com.example.oplevappprojekt.ViewModel.Category>,navIdeas: () -> Unit){
    LazyColumn(){
        items(list){
            CategoryCards( img = R.drawable.image10,
                il = it.categoryID,
                viewModel = viewModel,
                category = it.title?:"",
                navIdeas = navIdeas)
        } } }



@Composable
fun CategoryCards(img:Int, category: String, il: String, navIdeas: () -> Unit,viewModel: CategoryViewModel ){
    Card(modifier = Modifier
        .padding(20.dp)
        .clickable {
            viewModel.selectCategory(title = category, il = il)
            viewModel.uiState.value.isCategorySelected = true
            navIdeas()
        }){
        Box() {
            Image(
                painter = painterResource(id = img),
                contentDescription = "",
                modifier = Modifier. fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = category,
                modifier = Modifier
                    .padding(20.dp)
                    .width(380.dp)
                    .height(70.dp)
                    .clip(RoundedCornerShape(15))
            ) } }
}

