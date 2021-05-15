package com.example.hack.entity

import com.fasterxml.jackson.annotation.JsonProperty

class ReviewAboutStudent(
    var raiting: Int,
    var sociability: Int,
    var adequacy: Int,
    var qualification: Int,
    @JsonProperty("id_learner") var idStudent: Int,
    @JsonProperty("id_coach") var idTeacher: Int
) {}
