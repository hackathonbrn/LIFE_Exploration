package com.example.hack.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class ReviewAboutStudent(
    var raiting: Int,
    var sociability: Int,
    var adequacy: Int,
    var qualification: Int,
    var id_learner: Int,
    var id_coach: Int
) {}
