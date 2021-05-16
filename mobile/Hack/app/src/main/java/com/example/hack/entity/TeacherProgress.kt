package com.example.hack.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class TeacherProgress(
    var count_lessons: Int,
    var left_lessons: Int,
    var cost_lesson: Int,
    var id_game: Int,
    var learner_username: String,
    var game_name: String,
    var game_logo: String,
    var id_learner: Int,
    var id_coach: Int
) {

}