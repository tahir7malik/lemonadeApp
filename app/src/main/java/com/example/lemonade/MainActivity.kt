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
    /*
    Composables are stateless by default, which means that they don't hold a value
    and can be recomposed any time by the system, which results in the value
    being reset. However, Compose provides a convenient way to avoid this.
    Composable functions can store an object in memory using the remember
    composable.
    */

    // result variable stores
    var result by remember { // storing result value in memory using remember
        mutableIntStateOf(1) // (default result value = 1)
        /*
        The mutableStateOf() function returns an observable. When the value of the result variable
        changes, a recomposition is triggered, the value of the result is reflected, and the UI
        refreshes.
        */
    }

    // stayCounter variable stores counter-value for tapping lemon case on 2 image
    var stayCounter by remember {
        mutableIntStateOf(0)
    }

    // imageResource stores value according to case(when result = 1 or 2 or 3 or else)
    val imageResource = when(result) {
        1 -> R.drawable.lemon_tree // case1
        2 -> R.drawable.lemon_squeeze // case2
        3 -> R.drawable.lemon_drink // case3
        else -> R.drawable.lemon_restart // case4
    }

    // labelResource stores value according to case(when result = 1 or 2 or 3 or else)
    val labelResource = when(result) {
        1 -> R.string.label1 // case1
        2 -> R.string.label2 // case2
        3 -> R.string.label3 // case3
        else -> R.string.label4 // case4
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
                    .clickable { // this will make our box clickable

                        // logic for tapping and going for next slide
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
                                // id gets imageResource value in the form of {1, 2, 3 or else(4)}
                                painter = painterResource(id = imageResource),
                                contentDescription = stringResource(id = R.string.img1Desc)
                            )
                        }
                    )
                }
            )

            // provides space between Box and Text
            Spacer(
                modifier = Modifier
                    .height(20.dp)
            )
            Text(
                // id gets stringResource value in the form of {1, 2, 3 or else(4)}
                text = stringResource(id = labelResource),
                fontSize = 20.sp
            )
        }
    )
}