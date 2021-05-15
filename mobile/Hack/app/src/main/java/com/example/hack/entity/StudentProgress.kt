package com.example.hack.entity

import com.fasterxml.jackson.annotation.JsonProperty

class StudentProgress(
    @JsonProperty("count_lessons") var countLessons: Int,
    @JsonProperty("left_lessons") var leftLessons: Int,
    @JsonProperty("cost_lesson") var costLesson: Int,
    @JsonProperty("id_game") var idGame: Int,
    @JsonProperty("coach_username") var teacherUsername: String,
    @JsonProperty("game_name") var gameName: String,
    @JsonProperty("game_logo") var gameLogo: String,
    @JsonProperty("id_learner") var idStudent: Int,
    @JsonProperty("id_coach") var idTeacher: Int
) {}
