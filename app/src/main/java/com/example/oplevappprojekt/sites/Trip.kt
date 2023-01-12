package com.example.oplevappprojekt

/*

//s215726





// Acts as a popup
@Composable
fun Trip(navMain: ()->Unit, viewModel: Journeysviewmodel) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color(myColor.toColorInt()))
                .height(350.dp)
                .width(350.dp),
        )

        {
            Text(
                text = "Nyt Rejsemål",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            )
            var expanded by remember {
                mutableStateOf(false)
            }
            var temp = "Vælg land"
            if(viewModel.uiState.value.isJourneySelected){
                temp = viewModel.uiState.value.currentcountry.toString()
            }
            var selectedItem by remember {
                mutableStateOf(temp)
            }
            var list = Countries.countries

            MaterialTheme(
                content ={
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.height(200.dp)

                    ){
                        Box(
                            modifier = Modifier
                                .offset(x = 30.dp)
                                .offset(y = 30.dp)
                                .background(Color.White)
                                .height(30.dp)
                                .width(250.dp)
                        )
                        {
                            TextButton(onClick = {expanded = true}) {
                                Row {
                                    Text(text = "$selectedItem",
                                        fontSize = 10.sp,
                                        color = Color.Black,
                                    )
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                                }
                            }
                            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                                list.forEach{
                                    DropdownMenuItem(onClick = {
                                        expanded = false
                                        selectedItem = it
                                    }) {
                                        Text(text = it)
                                    }
                                }
                            }
                        }
                    }


                }
            )
            Text(
                text = "Rejsedato",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(30.dp)
                    .offset(y = 160.dp)
            )
            val dato = Dato()
           val month = Month()
            val year = Year()



            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = 275.dp)
            ){
                var onClick = {viewModel.addJourney(country = selectedItem, date = dato + "/" + month + "/"+year)
                navMain() }
                System.out.println("is Journey selected? " + viewModel.uiState.value.isJourneySelected)
                if(viewModel.uiState.value.isJourneySelected){
                    onClick = {viewModel.editJourney(country = selectedItem,
                        date =dato + "/" + month + "/"+year,
                        ID=viewModel.uiState.value.currentJourneyID.toString())
                    navMain()}
                }
                Button(
                    onClick = onClick,
                    shape = RoundedCornerShape(60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                ) {
                    Text(
                        text = "Opret",
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun Dato(): String {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("Dag")
    }

    var list = listOf("1", "2", "3", "4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31")

    MaterialTheme(
        content ={
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(400.dp)

            ){
                Box(
                    modifier = Modifier
                        .offset(x = 30.dp)
                        .offset(y = 55.dp)
                        .background(Color.White)
                        .height(30.dp)
                        .width(80.dp)
                )
                {
                    TextButton(onClick = {expanded = true}) {
                        Row {
                            Text(text = "$selectedItem",
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                        }
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        list.forEach{
                            DropdownMenuItem(onClick = {
                                expanded = false
                                selectedItem = it
                            }) {
                                Text(text = it)
                            }
                        }
                    }
                }
            }
        }
    )
    return selectedItem
}



@Composable
fun Month() : String {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("Måned")
    }
    var list = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")

    MaterialTheme(
        content ={
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(400.dp)

            ){
                Box(
                    modifier = Modifier
                        .offset(x = 112.dp)
                        .offset(y = 55.dp)
                        .background(Color.White)
                        .height(30.dp)
                        .width(80.dp)
                )
                {
                    TextButton(onClick = {expanded = true}) {
                        Row {
                            Text(text = "$selectedItem",
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                        }
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        list.forEach{
                            DropdownMenuItem(onClick = {
                                expanded = false
                                selectedItem = it
                            }) {
                                Text(text = it)
                            }
                        }
                    }
                }
            }
        }
    )
   return selectedItem
}
@Composable
fun Year() : String {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedItem by remember {
        mutableStateOf("År")
    }
    var list = listOf("2020", "2021", "2022", "2023","2024","2025","2026","2027","2028","2029","2030")

    MaterialTheme(
        content ={
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.height(400.dp)

            ){
                Box(
                    modifier = Modifier
                        .offset(x = 194.dp)
                        .offset(y = 55.dp)
                        .background(Color.White)
                        .height(30.dp)
                        .width(80.dp)
                )
                {
                    TextButton(onClick = {expanded = true}) {
                        Row {
                            Text(text = "$selectedItem",
                                fontSize = 10.sp,
                                color = Color.Black
                            )
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                        } }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        list.forEach{
                            DropdownMenuItem(onClick = {
                                expanded = false
                                selectedItem = it
                            }) {
                                Text(text = it)
                            } } } } } })
    return selectedItem
}

 */






