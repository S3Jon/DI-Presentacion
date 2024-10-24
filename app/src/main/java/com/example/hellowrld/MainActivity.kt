package com.example.hellowrld

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Appp()
        }
    }
}

class GalleryViewModel : ViewModel() {
    var twoColumns = mutableStateOf(false)
    
    var artworkList = mutableStateOf(loadArtworks())
}

data class Artwork(
    val name: String,
    val imageLocation: Int
)

fun loadArtworks(): List<Artwork> {
    return listOf(
        Artwork(
            name = "Home Instinct",
            imageLocation = R.drawable.homing_instinct
        ),
        Artwork(
            name = "Flightless Bird",
            imageLocation = R.drawable.flightless_bird
        ),
        Artwork(
            name = "Resurrection",
            imageLocation = R.drawable.resurrection
        ),
        Artwork(
            name = "Prophecy",
            imageLocation = R.drawable.prophecy
        ),
        Artwork(
            name = "Champion's Song",
            imageLocation = R.drawable.champion_song
        ),
        Artwork(
            name = "Redmane",
            imageLocation = R.drawable.redmane
        ),
        Artwork(
            name = "Sorcerer",
            imageLocation = R.drawable.sorcerer
        ),
        Artwork(
            name = "Domain of Dragons",
            imageLocation = R.drawable.dod
        ),
        Artwork(
            name = "Sacred Tower",
            imageLocation = R.drawable.sacred_tower
        ),
        Artwork(
            name = "Incursion",
            imageLocation = R.drawable.incursion
        )
    )
}

@Composable
fun FABlol() {
    val context = LocalContext.current
    IconButton(onClick = {
        Toast.makeText(context, "Lol", Toast.LENGTH_SHORT).show()
    }, modifier = Modifier.size(80.dp)) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Enviar mail",
            tint = Color(0xFF95B013),
            modifier = Modifier.size(500.dp)
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topBarFunc() {
    val context = LocalContext.current
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            IconButton(onClick = {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Mira este proyecto: https://github.com/S3Jon/DI-Presentacion")
                    type = "text/plain"
                }
                val chooserIntent = Intent.createChooser(shareIntent, "Compartir con:")
                context.startActivity(chooserIntent)
            }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = "Compartir")
            }
        },
        title = { Text("About me") },
    )

}

@Composable
fun botBarFunc(navController: NavHostController) {
    /* Vieja Bottombar
    BottomAppBar(
                modifier = Modifier.height(70.dp),
                actions = {
                    Spacer(Modifier.weight(1f)) // Mueve el icono a la derecha
                    IconButton(onClick = {
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:")
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("correo@ejemplo.com"))
                        }
                        try {
                            context.startActivity(Intent.createChooser(emailIntent, "Enviar correo con:"))
                        } catch (e: Exception) {
                            Toast.makeText(context, "Error al abrir correo", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Enviar mail",
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            )
     */

    NavigationBar {
        NavigationRailItem(
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Home")
                   },
            selected = navController.currentBackStackEntry?.destination?.route == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier.weight(1f)
        )
        NavigationRailItem(
            icon = {
                Icon(Icons.Default.Info, contentDescription = "Information")
            },
            selected = navController.currentBackStackEntry?.destination?.route == "information",
            onClick = {
                navController.navigate("information") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier.weight(1f)
        )
        NavigationRailItem(
            icon = {
                Icon(Icons.Default.AccountBox, contentDescription = "Gallery")
            },
            selected = navController.currentBackStackEntry?.destination?.route == "gallery",
            onClick = {
                navController.navigate("gallery") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier.weight(1f)
        )
        NavigationRailItem(
            icon = {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            },
            selected = navController.currentBackStackEntry?.destination?.route == "settings",
            onClick = {
                navController.navigate("settings") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun infoScreen(innerPadding: PaddingValues, navController: NavHostController) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(innerPadding)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                val rainbowColorsBrush = remember {
                    Brush.sweepGradient(
                        listOf(
                            Color(0xFF9575CD),
                            Color(0xFFBA68C8),
                            Color(0xFFE57373),
                            Color(0xFFFFB74D),
                            Color(0xFFFFF176),
                            Color(0xFFAED581),
                            Color(0xFF4DD0E1),
                            Color(0xFF9575CD)
                        )
                    )
                }
                val image: Painter = painterResource(id = R.drawable.placeholder)
                Image(
                    painter = image,
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                        .border(
                            BorderStroke(4.dp, rainbowColorsBrush),
                            CircleShape
                        )
                        .clip(CircleShape)
                )
                Text(
                    text = "Jon Sánchez",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = "Web developer, App development student, and aspiring cybersecurity analyst.",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(start = 16.dp, bottom = 16.dp)
                )
                Column(modifier = Modifier
                    .padding(start = 14.dp, top = 14.dp),
                    Arrangement.spacedBy(20.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement
                            .spacedBy(16.dp)
                    ) {
                        val image: Painter = painterResource(id = R.drawable.baseline_school_24)
                        Image(
                            painter = image,
                            contentDescription = "",
                            modifier = Modifier
                                .size(70.dp)
                        )
                        Column {
                            Text(
                                text = "Education",
                                color = Color.White,
                                fontSize = 24.sp,
                                style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Web Development",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement
                            .spacedBy(16.dp)
                    ) {
                        val image: Painter = painterResource(id = R.drawable.baseline_create_24)
                        Image(
                            painter = image,
                            contentDescription = "",
                            modifier = Modifier
                                .size(70.dp)
                        )
                        Column {
                            Text(
                                text = "Hobbies",
                                color = Color.White,
                                fontSize = 24.sp,
                                style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Creative writing and worldbuilding",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement
                            .spacedBy(16.dp)
                    ) {
                        val image: Painter = painterResource(id = R.drawable.baseline_fastfood_24)
                        Image(
                            painter = image,
                            contentDescription = "",
                            modifier = Modifier
                                .size(70.dp)
                        )
                        Column {
                            Text(
                                text = "Favourite food",
                                color = Color.White,
                                fontSize = 24.sp,
                                style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Kebab, kebabito, kebardo",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement
                            .spacedBy(16.dp)
                    ) {
                        val image: Painter = painterResource(id = R.drawable.baseline_sports_gymnastics_24)
                        Image(
                            painter = image,
                            contentDescription = "",
                            modifier = Modifier
                                .size(70.dp)
                        )
                        Column {
                            Text(
                                text = "Sports",
                                color = Color.White,
                                fontSize = 24.sp,
                                style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Swimming and hiking",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val context = LocalContext.current
                    val image: Painter = painterResource(id = R.drawable.x_logo)
                    Image(
                        painter = image,
                        contentDescription = "",
                        modifier = Modifier
                            .size(75.dp)
                            .clickable {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://x.com/S3Jon")
                                )
                                ContextCompat.startActivity(context, intent, null)
                            }
                    )
                    val image2: Painter = painterResource(id = R.drawable.github)
                    Image(
                        painter = image2,
                        contentDescription = "",
                        modifier = Modifier
                            .size(75.dp)
                            .clickable {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://github.com/S3Jon")
                                )
                                ContextCompat.startActivity(context, intent, null)
                            }
                    )
                    val image3: Painter = painterResource(id = R.drawable.linkedin)
                    Image(
                        painter = image3,
                        contentDescription = "",
                        modifier = Modifier
                            .size(75.dp)
                            .clickable {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.linkedin.com/in/s3jon/")
                                )
                                ContextCompat.startActivity(context, intent, null)
                            }
                    )
                }

            }
        }

    }
}

@Composable
fun homeScreen(innerPadding: PaddingValues, navController: NavHostController) {
    Column (modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()) {
        Text(
            text = "Aquí va el home, supongo",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun paintingCard(piece: Artwork) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .shadow(5.dp, RoundedCornerShape(5.dp))
        .background(Color.White)
    ) {
        Column {
            Image(
                painter = painterResource(id = piece.imageLocation),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(1f)
            )
            Text(
                text = piece.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
            )
        }
    }
}

@Composable
fun galleryScreen(innerPadding: PaddingValues, navController: NavHostController, viewModel: GalleryViewModel) {
    val paintings = viewModel.artworkList.value
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
        items(
            count = paintings.size,
            key = { index -> paintings[index].name}
        ) { index ->
            val piece = paintings[index]
            paintingCard(piece)
        }
    }
}

@Composable
fun settingsScreen(innerPadding: PaddingValues, navController: NavHostController) {
    Column (modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()) {
        Text(
            text = "Y aquí los settings, cuando los haga",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appp() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val loadedView = navController.currentBackStackEntryAsState().value?.destination?.route
    val galleryViewModel: GalleryViewModel = viewModel()
    Scaffold(
        topBar = {
            when (loadedView) {
                "gallery" -> topBarFunc() //TODO
                else -> topBarFunc()
            }
        },
        floatingActionButton = { FABlol()},
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            botBarFunc(navController)
        }
    ) { innerPadding ->
        NavHost(navController = navController, startDestination = "information") {
            composable("home") {homeScreen(innerPadding, navController)}
            composable("information") {infoScreen(innerPadding, navController)}
            composable("gallery") {galleryScreen(innerPadding, navController, galleryViewModel)}
            composable("settings") {settingsScreen(innerPadding, navController)}
        }
        
    }
}