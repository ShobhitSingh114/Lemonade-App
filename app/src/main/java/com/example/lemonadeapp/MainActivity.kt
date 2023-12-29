package com.example.lemonadeapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Lemonade(
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var num by remember {
        mutableStateOf(1)
    }
    var squeezeCount by remember {
        mutableStateOf(0)
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {

            when (num) {
                1 -> LemonTextAndImages(
                    imageId = painterResource(id = R.drawable.lemon_tree),
                    textId = "Tap the lemon tree to select a lemon", contentDes = "Lemon Tree"
                ) {
                    num = 2
                    squeezeCount = (2..4).random()
                }

                2 -> LemonTextAndImages(
                    imageId = painterResource(id = R.drawable.lemon_squeeze),
                    textId = "Squeeze the lemon $squeezeCount times", contentDes = "Lemon Squeeze"
                ) {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        num = 3
                    }
                }

                3 -> LemonTextAndImages(
                    imageId = painterResource(id = R.drawable.lemon_drink),
                    textId = "Drink Lemon juice", contentDes = "Drink Lemon"
                ) { num = 4 }

                4 -> LemonTextAndImages(
                    imageId = painterResource(id = R.drawable.lemon_restart),
                    textId = "Restart for more Lemonade", contentDes = "Restart Lemon"
                ) { num = 1 }
            }
        }
    }
}

@Composable
fun LemonTextAndImages(
    modifier: Modifier = Modifier,
    imageId: Painter, textId: String, contentDes: String,
    onImageClick: () -> Unit
) {

    Box(
        modifier = modifier
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ){
                Image(
                    painter = imageId,
                    contentDescription = contentDes,
//                    modifier = Modifier.clickable { onImageClick() }
                )
            }

            Text(text = textId)
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadePreview() {
    Lemonade(
        modifier = Modifier
            .fillMaxSize()
    )
}
