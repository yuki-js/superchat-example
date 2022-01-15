package app.aoki.superchat.example.ui.screens.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.aoki.superchat.example.data.model.Live
import app.aoki.superchat.example.data.repository.LiveRepository
import app.aoki.superchat.example.ui.screens.watchlive.WatchLiveActivity
import kotlinx.coroutines.launch

class HomeViewModel(val context: Context): ViewModel() {
    private val liveRepository = LiveRepository()

    private val _lives = MutableLiveData<List<Live>>()
    val lives: LiveData<List<Live>> = _lives

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private fun loadLives() {
        viewModelScope.launch {
            _isLoading.value = true
            _lives.value = liveRepository.getCurrentLives()
            _isLoading.value = false
        }
    }
    init {
        loadLives()
    }
    fun refresh() {
        loadLives()
    }
    fun navigateToLive(live: Live) {
        WatchLiveActivity.launch(context, live.id)
    }
}