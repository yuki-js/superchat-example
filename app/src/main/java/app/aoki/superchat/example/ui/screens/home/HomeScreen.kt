package app.aoki.superchat.example.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.aoki.superchat.example.data.model.Live
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Library Example")}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Outlined.Create, "Live")
            }
        }

    ) {
        HomeScreenContent(homeViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreenContent(homeViewModel: HomeViewModel) {
    val currentLives: List<Live> by homeViewModel.lives.observeAsState(emptyList())
    val isLoading: Boolean by homeViewModel.isLoading.observeAsState(false)

    SwipeRefresh(state = rememberSwipeRefreshState(isLoading), onRefresh = { homeViewModel.refresh() }) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            itemsIndexed(currentLives) { _, live ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    elevation = 8.dp,
                    backgroundColor = Color.White,
                    onClick = {
                        homeViewModel.navigateToLive(live)
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),

                        ) {
                        Text(live.title, style = MaterialTheme.typography.bodyMedium)
                        Text(live.author, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
