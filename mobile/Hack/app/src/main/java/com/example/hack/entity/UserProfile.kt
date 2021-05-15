package com.example.hack.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class UserProfile(
    var id: Int,
    @JsonProperty("username") var nickname: String,
    var money: String,
    @JsonProperty("acc_status") var type: Boolean,
    var verification: Boolean,
    @JsonProperty("avatar_url") var imageUrl: String,
    @JsonProperty("steam_id") var steamId: String,
    @JsonProperty("games_user") var gamesInfo: List<GameInfo>,
    @JsonProperty("reviews_learner") var reviewsAboutStudent: List<ReviewAboutStudent>,
    @JsonProperty("reviews_coach") var reviewsAboutTeacher: List<ReviewAboutTeacher>,
    @JsonProperty("learners_learner") var studentProgress: List<StudentProgress>,
    @JsonProperty("learners_coach") var teacherProgress: List<TeacherProgress>
) {

}
