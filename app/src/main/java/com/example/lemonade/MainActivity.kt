package com.example.lemonade

import android.graphics.Color.rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize(),
                    content = {
                        LemonadeApp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var result by remember {
        mutableIntStateOf(1)
    }
    var stayCounter by remember {
        mutableIntStateOf(0)
    }
    val imageResource = when(result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val labelResource = when(result) {
        1 -> R.string.label1
        2 -> R.string.label2
        3 -> R.string.label3
        else -> R.string.label4
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier,
        content = {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(45.dp))
                    .background(Color(rgb(195, 236, 210)))
                    .clickable {
                        if (result == 2 && stayCounter < 3) {
                            stayCounter++
                        } else {
                            stayCounter = 0
                            result = if (result < 4) result + 1 else 1
                        }
                    }
                    .border(
                        color = Color(rgb(105, 205, 216)),
                        width = 1.dp,
                        shape = RoundedCornerShape(45.dp)
                    ),
                content = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(40.dp),
                        content = {
                            Image(
                                painter = painterResource(id = imageResource),
                                contentDescription = stringResource(id = R.string.img1Desc)
                            )
                        }
                    )
                }
            )
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            Text(
                text = stringResource(id = labelResource),
                fontSize = 20.sp
            )
        }
    )
}