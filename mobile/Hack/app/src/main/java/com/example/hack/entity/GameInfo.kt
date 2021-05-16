package com.example.hack.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class GameInfo(
    var id_user: String,
    var id_game: String,
    var rank: Int,
    var hours_game: Int,
    var number_matches: Int,
    var cost_lesson: Int,
    var game_name: String,
    var game_logo: String
) {

}