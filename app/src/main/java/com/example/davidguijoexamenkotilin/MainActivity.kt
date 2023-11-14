package com.example.davidguijoexamenkotilin

//import androidx.compose.foundation.layout.BoxScopeInstance.align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.davidguijoexamenkotilin.ui.theme.DavidGuijoExamenkotilinTheme
import kotlin.random.Random

//color pasado como variables
val alumno7 = Color(0xFF00BFA5)
//color de fondo
val background7 = Color(0xFF89A5A1)

val listaColores = listOf(Color.Green, Color.Blue, Color.Red, Color.Cyan)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DavidGuijoExamenkotilinTheme {
                var colorRandom = remember {
                    mutableStateOf(background7)
                }
                Box(modifier = Modifier.padding(16.dp)) {
                    Column {
                        Box {
                            Row {
                                Image(
                                    painter = painterResource(R.drawable.avatar7),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .border(
                                            1.5.dp,
                                            MaterialTheme.colorScheme.secondary,
                                            CircleShape
                                        )
                                        .fillMaxSize()
                                )
                                Box (modifier = Modifier.padding(10.dp)){
                                    Text(text = "David Guijo MuÃ±oz",
                                        fontSize = 25.sp,
                                    )
                                }
                            }
                        }


                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { cambiarColor(colorRandom) },
                            colors = ButtonDefaults.buttonColors(colorRandom.value)
                        ) {
                            Text(text = "Nuevo Color")
                        }
                        Conversation(Mensajes.conversationSample, colorRandom)

                    }

                }

            }
        }
    }
}
data class Mensaje(val author: String, val body: String) {}

fun cambiarColor(colorRandom: MutableState<Color>){
    val indiceRandom = Random.nextInt(listaColores.size)
    if (listaColores[indiceRandom] == colorRandom.value) {
        cambiarColor(colorRandom)
    } else {
        colorRandom.value = listaColores[indiceRandom]
    }
}

@Composable
fun MessageCard(msg: Mensaje, colorRandom: MutableState<Color>) {

    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.profesor),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            targetValue = if (isExpanded) colorRandom.value else MaterialTheme.colorScheme.surface, label = ""
        )

        Column(modifier = Modifier.clickable { isExpanded =!isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all=4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    //style = MaterialTheme.typography.body2

                )
            }
        }
    }
}

@Composable
fun Conversation(message: List<Mensaje>, colorRandom: MutableState<Color>){
    LazyColumn{
        items(message){
                message ->
            MessageCard(message, colorRandom)
        }
    }
}



@Preview
@Composable
fun PreviewConversation(){
    DavidGuijoExamenkotilinTheme {
        //Conversation(Mensajes.conversationSample, colorRandom = Color.Blue)
    }
}
