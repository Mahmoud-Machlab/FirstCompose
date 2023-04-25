package com.example.firstcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

import com.example.firstcompose.ui.theme.FirstComposeTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    MessageCard(Message("Frank","ein kleiner Text"))
                    Conversation(messages = messageList)
                }
            }
        }
    }
}

data class Message(val author:String, val body:String)

val messageList = listOf(
    Message("Hans","ein kleiner Text um das Beispiel besser zu erklären"),
    Message("Hans","ein kleiner Text um das Beispiel besser zu erklären"),
    Message("Hans","ein kleiner Text um das Beispiel besser zu erklären"),
    Message("Hans","ein kleiner Text um das Beispiel besser zu erklären ein kleiner Text um das Beispiel besser zu erklären ein kleiner Text um das Beispiel besser zu erklären"),
    Message("Hans","ein kleiner Text um das Beispiel besser zu erklären"),
    Message("Hans","ein kleiner Text um das Beispiel besser zu erklären ein kleiner Text um das Beispiel besser zu erklären ein kleiner Text um das Beispiel besser zu erklären"),
    Message("Hans","ein kleiner Text um das Beispiel besser zu erklären"),
    Message("Hans","ein kleiner Text um das Beispiel besser zu erklären"),
    Message("Hans","ein kleiner Text um das Beispiel besser zu erklären"),
    Message("Hans","ein kleiner Text um das Beispiel besser zu erklären"),
)

@Composable
fun MessageCard(msg:Message, image: AsyncImagePainter, modifier: Modifier = Modifier) {



    Row(
        modifier = Modifier.padding(all =  16.dp),
    ){
        Image(
            painter = image ,//painterResource(id = R.drawable.einstein),

            contentDescription = "Profilbild von Einstein",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)

        )

        // 2. Variante mit coil
//        AsyncImage(
//            model = "https://www.businessinsider.de/wp-content/uploads/2016/03/albert_einstein_head.png",
//            contentDescription = null,
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
//
//        )


        Spacer(modifier = Modifier.width(8.dp))

        var isExpandet by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.clickable { isExpandet = !isExpandet }
        ) {
            Text(
                text = msg.author,
                modifier = modifier,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = msg.body,
                maxLines = if (isExpandet) Int.MAX_VALUE else 2,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Composable
fun Conversation(messages: List<Message>){

    val einstein =rememberAsyncImagePainter( "https://www.businessinsider.de/wp-content/uploads/2016/03/albert_einstein_head.png")

    LazyColumn{
        items(messages){message ->
            MessageCard(msg = message,einstein)
        }
    }
}

@Preview(name = "Tagmodus",
    showBackground = true)
//@Preview(name = "Nachtmodus",
//    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    FirstComposeTheme {
        Conversation(messages = messageList)
//        MessageCard(Message("Frank","ein kleiner Text"))
//        WeatherScreen()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen() {
    var city = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        OutlinedTextField(
            value = city.value,
            onValueChange = { city.value= it },
            label = { Text("Stadt eingeben") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 8.dp)
        )

        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp, end = 16.dp)
        ) {
            Text(text = "Wetter")
        }

        Row(
            modifier = Modifier
                .padding(top = 24.dp)
                .align(Alignment.Start)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "TextView",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "TextView",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}