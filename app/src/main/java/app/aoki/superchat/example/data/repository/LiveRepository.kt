package app.aoki.superchat.example.data.repository

import app.aoki.superchat.example.data.model.Live

class LiveRepository {
    fun getCurrentLives(): List<Live> {
        return listOf(
            Live(
                id = "a",
                title = "Live 1",
                author = "omanko"

            ),
            Live(
                id = "b",
                title = "Live 2",
                author = "omanko"
            )
        )
    }
}