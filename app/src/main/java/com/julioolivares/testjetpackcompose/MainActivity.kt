package com.julioolivares.testjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.julioolivares.testjetpackcompose.ui.theme.TestJetpackComposeTheme
import org.w3c.dom.NameList
import androidx.compose.runtime.getValue

import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.VerticalAlignmentLine

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.primary) {
                    MyScreenContent()
                }
            }
        }
    }
}

@Composable
fun MyScreenContent(names : List<String> = listOf("","","","","")){
    val counterState = remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally) {
        NameList(modifier = Modifier.weight(1f))
        /*
        Column(modifier = Modifier.weight(1f)) {
            for (name in names){
                Greeting(name = name)
                Divider(color = Color.Black)
            }
        }
        * */

        //Divider(color = Color.Black ,thickness = 5.dp)
        Divider(color = Color.Black)
        Counter(count = counterState.value,
            updateCount = {
            newCount-> counterState.value = newCount
        })
    }
}

@Composable
fun NameList(names : List<String> = List(1000) {"Hello Android #$it"}, modifier: Modifier){
    LazyColumn(modifier = modifier) {
        items(items = names) {
            name->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
       /*
       *  for (name in names){
            Greeting(name = name)
            Divider(color = Color.Black)
        }
       * */
    }
}
@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)
    Text(text = "Hello $name!",modifier = Modifier
        .padding(24.dp)
        .background(color = backgroundColor)
        .clickable(
        onClick = {
            isSelected = !isSelected
        }
    ),style = MaterialTheme.typography.h5)
}

@Composable
fun Counter(count : Int,updateCount : (Int) -> Unit){
    Button(onClick = { updateCount(count + 1) },
        modifier= Modifier.wrapContentHeight(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if(count > 5) Color.Green else Color.White
        )) {
        Text(text = "I've been clicked $count times")
    }
}
/*
@Composable
fun Counter(){
    val count = remember {
        mutableStateOf(0)
    }
    Button(onClick = {
        count.value++
    },Modifier.padding(15.dp)) {
        Text(text = "You Clicked ${count.value} times")
    }
}
*/

@Preview(showBackground = true,name = "Julio")
@Composable
fun DefaultPreview() {
    TestJetpackComposeTheme {
        MyScreenContent()

    }
}