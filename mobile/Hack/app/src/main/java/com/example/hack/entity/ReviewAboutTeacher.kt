package com.example.hack.entity

import com.fasterxml.jackson.annotation.JsonProperty

class ReviewAboutTeacher(
    var raiting: Int,
    var sociability: Int,
    @JsonProperty("coach_level") var coachLevel: Int,
    var qualification: Int,
    @JsonProperty("id_learner") var idStudent: Int,
    @JsonProperty("id_coach") var idTeacher: Int
) {}