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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Appp()
        }
    }
}

@Composable
fun FABlol() {
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        Toast.makeText(context, "Lol", Toast.LENGTH_SHORT).show()
    }) {
        Text("X")
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appp() {
    val context = LocalContext.current
    Scaffold(

        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Mira este perfil: https://github.com/S3Jon")
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
        },

        bottomBar = {
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
        },
        floatingActionButton = { FABlol()},
        floatingActionButtonPosition = FabPosition.End,
        content = {
            val context = LocalContext.current
            Column(modifier = Modifier.padding(it)) {
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
                                        text = "Web Develompment",
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
    )
}
