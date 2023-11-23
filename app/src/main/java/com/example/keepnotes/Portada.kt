package com.example.keepnotes

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.keepnotes.ui.theme.MyYellow
import com.example.keepnotes.ui.theme.Purple40


@SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Portada(navController: NavHostController) {

    Scaffold(
        floatingActionButton = { MyFAB() },
        containerColor = Color.Black,
        topBar = { MyTopBar() },
        content = {
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MyYellow)
                    ) {
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
            actions = {
                val value by rememberInfiniteTransition().animateFloat(
                    initialValue = 25f,
                    targetValue = -25f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(
                            durationMillis = 600,
                            easing = LinearEasing
                        ),
                        repeatMode = RepeatMode.Reverse
                    )
                )
                Icon(
                    imageVector = Icons.Default.Notifications,
                    tint = Color.White,
                    contentDescription = "null",
                    modifier = Modifier
                        .graphicsLayer(
                            transformOrigin = TransformOrigin(
                                pivotFractionX = 0.5f,
                                pivotFractionY = 0.0f,
                            ),
                            rotationZ = value
                        )
                )
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
            },
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
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent)
        )
    }

}

@Composable
fun MyFAB() {
    val value by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )
    val colors = listOf(
        Color(0xFF405DE6),
        Color(0xFFC13584),
        Color(0xFFFD1D1D),
        Color(0xFFFFDC80)
    )
    var gradientBrush by remember {
        mutableStateOf(
            Brush.horizontalGradient(
                colors = colors,
                startX = -10.0f,
                endX = 400.0f,
                tileMode = TileMode.Repeated
            )
        )
    }
    Box(modifier = Modifier
        .drawBehind {
            rotate(value) {
                drawCircle(
                    gradientBrush, style = Stroke(width = 20.dp.value)
                )
            }
        }
        .size(70.dp)
    ) {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = Purple40,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            modifier = Modifier.size(70.dp),
            shape = CircleShape
        ) {
            Icon(modifier = Modifier.size(30.dp),imageVector = Icons.Default.Create, contentDescription = "null", tint = Color.White)
        }
    }
}