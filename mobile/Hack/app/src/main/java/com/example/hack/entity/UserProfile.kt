package com.example.hack.entity

import com.google.gson.annotations.SerializedName

data class UserProfile(
    var id: Int,
    var username: String,
    var money: String,
    var acc_status: Boolean,
    var verification: Boolean,
    var avatar_url: String,
    var steam_id: String,
    var games_user: List<GameInfo>,
    @SerializedName("le_pararms") var le_params: AvgParams,
    @SerializedName("co_pararms") var co_params: AvgParams,
    var reviews_learner: List<ReviewAboutStudent>,
    var reviews_coach: List<ReviewAboutTeacher>,
    var learners_learner: List<StudentProgress>,
    var learners_coach: List<TeacherProgress>
)
