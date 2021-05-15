package com.example.hack.entity

import com.fasterxml.jackson.annotation.JsonProperty

class GameInfo(
    @JsonProperty("id_user") var idUser: String,
    @JsonProperty("id_game") var idGame: String,
    @JsonProperty("rank") var rank: Int,
    @JsonProperty("hours_game") var hoursGame: Int,
    @JsonProperty("number_matches") var numberMatches: Int,
    @JsonProperty("cost_lesson") var costLesson: Int,
    @JsonProperty("game_name") var gameName: String,
    @JsonProperty("game_logo") var gameLogo: String
) {

}