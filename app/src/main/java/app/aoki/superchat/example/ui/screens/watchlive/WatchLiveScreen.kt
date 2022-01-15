package app.aoki.superchat.example.ui.screens.watchlive

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.aoki.superchat.example.component.*
import app.aoki.superchat.example.ui.screens.watchlive.WatchLiveActivity.Companion.launch
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WatchLiveScreen() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        sheetContent = {
            ChatInput(bottomSheetScaffoldState)
        },
        sheetPeekHeight = 70.dp,
        scaffoldState = bottomSheetScaffoldState,
        backgroundColor = Color.White,
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
            topStart = 12.dp,
            topEnd = 12.dp
        ),
        sheetElevation = 8.dp,
        drawerElevation = 8.dp,
    ) {
        WatchLiveScreenContent()
    }
}


@Composable
fun WatchLiveScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Player()
        Chats()
    }
}

@Composable
fun Player() {
    var showPlayerUi by remember { mutableStateOf(false) }
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(showPlayerUi) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
        systemUiController.isStatusBarVisible = showPlayerUi
    }
    val composableScope = rememberCoroutineScope()
    var timerJob: Job by remember {
        mutableStateOf(
            composableScope.launch {
                delay(3000)
                showPlayerUi = false
            }
        )
    }
    var cnt by remember { mutableStateOf(0) }
    val playerClicked = {
        if (!showPlayerUi) {
            showPlayerUi = true
            timerJob.cancel()
            timerJob = composableScope.launch {
                delay(3000)
                showPlayerUi = false

            }

        }else {
            showPlayerUi = false
            timerJob.cancel()
        }
        cnt++
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color.Black)
            .clickable { playerClicked() }
    ) {
        Text(text = "Player $cnt", color = Color.White)
    }
}

@Composable
fun Chats() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        LazyColumn {
            items(10) {
                Box(modifier = Modifier.padding(8.dp)) {
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
            }
            items(5) {
                Box(modifier = Modifier.padding(8.dp)) {
                    Chat(
                        "omanko",
                        "fuck",
                        0
                    )
                }
            }
            item { ChatInputSpacer() }
        }
        ChatInputSpacer()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatInput(bottomSheetScaffoldState: BottomSheetScaffoldState) {
    val composableScope = rememberCoroutineScope()

    var text by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(400.dp)
    ) {
        HandleUI()
        if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
            ExpandedChatInput(text = text, amount = amount, onTextChange = {
                text = it
            }, onSend = {
                composableScope.launch {

                }
            }, onAmountChange = {
                amount = it
            })
        } else {
            CollapsedChatInput(
                text = text,
                onTextChange = {
                    text = it
                },
                onSend = {
                    composableScope.launch {
                        Log.d("ChatInput", "send")
                    }
                },
                onSuperChat = {
                    composableScope.launch {
                        Log.d("ChatInput", "super")
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    }
                }
            )
        }
    }


}

@Composable
fun ChatInputSpacer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    )
}

@Preview
@Composable
fun WatchLiveScreenPreview() {
    WatchLiveScreen()
}
