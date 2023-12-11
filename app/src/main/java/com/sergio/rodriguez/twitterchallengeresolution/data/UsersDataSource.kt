package com.sergio.rodriguez.twitterchallengeresolution.data

import com.sergio.rodriguez.twitterchallengeresolution.R
import com.sergio.rodriguez.twitterchallengeresolution.domain.model.Twit

object UsersDataSource {

    val listTwits: List<Twit> = listOf(
        Twit(
            photoProfile = R.drawable.profile,
            username = "Aris",
            nickname = "@AristiDevs",
            time = 4,
            contentTwit = "Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto",
            imageTwit = R.drawable.profile,
            countCommentsTwit = 1,
            reTwit = 1,
            likeTwit = 1
        ),
        Twit(
            photoProfile = R.drawable.profile,
            username = "S-H-U-R-A",
            nickname = "@shura",
            time = 2,
            contentTwit = "Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto",
            imageTwit = R.drawable.profile,
            countCommentsTwit = 5434,
            reTwit = 10243,
            likeTwit = 1354234
        ),
        Twit(
            photoProfile = R.drawable.profile,
            username = "LaPuerca",
            nickname = "@laPuerca",
            time = 30,
            contentTwit = "Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto Descripción larga sobre texto",
            imageTwit = R.drawable.profile,
            countCommentsTwit = 25,
            reTwit = 43,
            likeTwit = 15
        )
    )

}