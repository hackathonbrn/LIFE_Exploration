package com.example.hack.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class ReviewAboutTeacher(
    var raiting: Int,
    var sociability: Int,
    var coach_level: Int,
    var qualification: Int,
    var id_learner: Int,
    var id_coach: Int
) {}