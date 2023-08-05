package co.edu.sena.kotlinlistsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.sena.kotlinlistsapp.ui.theme.KotlinListsAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinListsAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversation(SampleData.conversationSample)
                }
            }
        }
    }

}

data class Message(val author: String, val body: String)

@Composable
private fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.perro_panzon),
            contentDescription = "Fat dog",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this  variable
        var isExpanded by remember { mutableStateOf(false) }
        // We toggle the isExpanded variable when we click on this Column


        Column(
            modifier = Modifier.clickable { isExpanded = !isExpanded }
        )

        {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.inverseSurface,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(4.dp))


            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 8.dp,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                color = if (isExpanded) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.background

            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 8.dp),
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}


@Preview
@Composable
fun ConversationPreview() {
    KotlinListsAppTheme {
        Conversation(SampleData.conversationSample)
    }
}

@Preview(showBackground = true)
@Composable
fun MessageCardPreview() {
    KotlinListsAppTheme {
        Surface {
            Conversation(SampleData.conversationSample)
        }
    }

}



