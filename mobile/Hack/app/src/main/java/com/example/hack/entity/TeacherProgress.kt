package com.example.hack.entity

import com.fasterxml.jackson.annotation.JsonProperty

class TeacherProgress(
    @JsonProperty("count_lessons") var countLessons: Int,
    @JsonProperty("left_lessons") var leftLessons: Int,
    @JsonProperty("cost_lesson") var costLesson: Int,
    @JsonProperty("id_game") var idGame: Int,
    @JsonProperty("learner_username") var studentUsername: String,
    @JsonProperty("game_name") var gameName: String,
    @JsonProperty("game_logo") var gameLogo: String,
    @JsonProperty("id_learner") var idStudent: Int,
    @JsonProperty("id_coach") var idTeacher: Int
) {

}