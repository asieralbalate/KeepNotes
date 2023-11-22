package com.example.keepnotes

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.keepnotes.ui.theme.MyYellow


@SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Portada(navController: NavHostController) {

    Scaffold(containerColor = Color.Black, topBar = { MyTopBar() }, content = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            KeepNotesCards()
        }
    })
}

@SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun KeepNotesCards() {
    val comments = listOf<String>(
        "Some loose, I still recommend it.",
        "Centeral and cozy. We'll come back safely.",
        "The setting very good, but on the top floor a little bit...",
        "The food was delicious and quite well priced, lots of variety of dishes.",
        "The very friendly staff, they allowed us to see the whole establishment.",
        "Very good.",
        "Excellent. Highlight the extensive coffee chart.",
        "Good atmos,phere and good service. I recommend it.",
        "On holidays too much waiting time. The waiters are not enough. I don't recommend it. I won't come back.",
        "We will repeat. Great selection of cakes and coffees.",
        "Everything I've tried in the cafeteria is rich, sweet or salty.",
        "The very nice dishes all of design that in the surroundings of the bar is ideal.",
        "Negative points: the service is very slow and prices are a little high."
    )
    val lazyListState = rememberLazyListState()
    var scrolled = 0f
    var previousOffset = 0

    Column {
        LazyColumn(
            state = lazyListState
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.portada),
                    contentDescription = "null",
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            scrolled += lazyListState.firstVisibleItemScrollOffset - previousOffset
                            translationY = scrolled * 0.5f
                            previousOffset = lazyListState.firstVisibleItemScrollOffset
                        }, contentScale = ContentScale.Crop
                )
            }
            items(comments) { comment ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {
                    Column (modifier = Modifier.fillMaxWidth().background(MyYellow)){
                        Text(
                            text = comment,
                            modifier = Modifier.padding(6.dp)
                        )
                    }

                }
            }
        }
    }
}

@SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MyTopBar() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.portada),
            contentDescription = "null",
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentScale = ContentScale.Crop
        )
        TopAppBar(
            title = {
                Text(text = "KeepNotes", color = Color.White, fontSize = 20.sp)
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            actions = {
                Row() {
                    IconButton(
                        onClick = {
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent)
        )
    }

}