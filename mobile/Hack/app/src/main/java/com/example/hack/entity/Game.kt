package com.example.hack.entity

import com.fasterxml.jackson.annotation.JsonProperty

class Game(
    var id: Int,
    @JsonProperty("name") var title: String,
    @JsonProperty("logo_url") var url: String) {

}