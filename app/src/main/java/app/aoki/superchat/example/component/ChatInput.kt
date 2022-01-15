package app.aoki.superchat.example.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun CollapsedChatInput(text: String, onTextChange: (String) -> Unit, onSend: () -> Unit, onSuperChat: () -> Unit) {
    TextField(
        value = text,
        onValueChange = { it -> onTextChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp).navigationBarsWithImePadding(),
        placeholder = { Text("Send a message", color = Color(0x44000000)) },
        leadingIcon = {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Super Chat",
                tint = Color(0x44000000),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable(onClick = {
                        onSuperChat()
                    })
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Send,
                contentDescription = "Send Chat",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable(onClick = {
                        onSend()
                    })
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSend = { onSend() }
        )
    )
}

val amountStyle = TextStyle(fontSize = 16.sp, color = Color(0xFF000000))

@Composable
fun ExpandedChatInput(
    text: String, onTextChange: (String) -> Unit,
    amount: String, onAmountChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "Send Superchat",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(6.dp)
        )
        Box(modifier = Modifier.padding(8.dp)) {
            RawSuperChat(author = "You", amount = amount.toIntOrNull() ?: 0) {
                TextField(
                    value = text,
                    onValueChange = { it -> onTextChange(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp).navigationBarsWithImePadding(),
                    placeholder = { Text("Send a message", color = Color(0x44000000)) },

                    )
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = amount,
                onValueChange = { it -> onAmountChange(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                ),
                textStyle = amountStyle,
                modifier = Modifier.width(60.dp),
                singleLine = true,
            )
            Text(text = "Satoshi", style = amountStyle)

        }
        Button(onClick = { onSend() }) {
            Text(text = "Send")
        }
    }
}

@Composable
fun HandleUI() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(2.dp)
                .background(Color(0x44000000))
                .clip(RoundedCornerShape(6.dp))
        )
    }
}
