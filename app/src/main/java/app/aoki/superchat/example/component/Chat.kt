package app.aoki.superchat.example.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.aoki.superchat.example.R

@Composable
fun Chat(author: String, text: String, amount: Int?) {
    if (amount == null || amount == 0) {
        NormalChat(author, text)
    } else {
        SuperChat(author, text, amount)
    }
}

@Composable
fun NormalChat(author: String, text: String) {
    RawNormalChat(author) {

        Text(
            text = text,
            style = androidx.compose.material.MaterialTheme.typography.body1,
            modifier = androidx.compose.ui.Modifier.padding(2.dp),
            color = androidx.compose.ui.graphics.Color(
                0xff000000
            )
        )

    }
}

@Composable
fun RawNormalChat(author: String, content: @Composable () -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "User Icon",
            modifier = Modifier
                .padding(4.dp)
                .size(24.dp)
                .clip(CircleShape)
        )
        Text(
            text = author,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(2.dp),
            color = Color(0xcc000000)
        )
        content()
    }
}

@Composable
fun SuperChat(author: String, text: String, amount: Int) {
    RawSuperChat(author = author, amount = amount) {
        Text(text, color = Color(0xffffffff), fontWeight = FontWeight.Normal)
    }
}
@Composable
fun RawSuperChat(author: String, amount: Int, content: @Composable () -> Unit) {
    Card {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xffd20000)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "User Icon",
                    modifier = Modifier
                        .padding(4.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Column {
                    Text(author, color = Color(0xccffffff), fontWeight = FontWeight.Bold)
                    Text(
                        "${amount.toString()} sats",
                        color = Color(0xffffffff),
                        fontWeight = FontWeight.Black
                    )
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xffe82117))
                    .padding(16.dp),
            ) {
                content()
            }
        }
    }
}


@Preview
@Composable
fun SuperChatPreview() {
    Chat(
        "おせう",
        "ぺこーらいつもありがとう!\n" +
                "最近ぺこーらへ感謝するのが日課になりつつあります!\n" +
                "単刀直入に我慢してたこと書いちゃう!\n" +
                "ぺこーら愛してるぞおおおお\n" +
                "(ps.厄介野うさぎだと思われてそうですが長文赤スパ失礼!\n" +
                "ちなみに読まれてる頃にはあまりの恥ずかしさにユニバーサル大回転ぺこぺこの舞( ◝(‘ω’)◟ ))(( ◝(‘ω’)◜ ))しながらベットの上で暴れてると思うので率直な一言もらってもいいですか?w最後に一言!配信をはじめ本当にいつもありがとう!野うさぎ達を大切に思ってくれてる姿勢冗談抜きで本当に好きです。応援するしがいがあります!",
        1000
    )
}

@Preview
@Composable
fun NormalChatPreview() {
    Chat("omanko", "fuck hololive", 0)
}