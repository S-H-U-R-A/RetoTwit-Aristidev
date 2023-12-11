package com.sergio.rodriguez.twitterchallengeresolution.domain.model

import androidx.annotation.DrawableRes

data class Twit(
    @DrawableRes val photoProfile: Int,
    val username: String,
    val nickname: String,
    val time: Int,
    val contentTwit: String,
    @DrawableRes val imageTwit: Int,
    val countCommentsTwit: Int,
    val reTwit: Int,
    val likeTwit: Int
)
