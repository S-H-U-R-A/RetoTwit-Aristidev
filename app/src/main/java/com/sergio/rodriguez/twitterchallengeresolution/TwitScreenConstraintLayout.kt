package com.sergio.rodriguez.twitterchallengeresolution

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sergio.rodriguez.twitterchallengeresolution.data.UsersDataSource
import com.sergio.rodriguez.twitterchallengeresolution.domain.model.Twit
import com.sergio.rodriguez.twitterchallengeresolution.ui.theme.TwitterChallengeResolutionTheme

@Composable
fun TwitScreenCons(
    twits: List<Twit>,
    modifier: Modifier = Modifier
){

    ListTwitsCons(twits = twits)
}

@Composable
fun ListTwitsCons(
    twits: List<Twit>,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ){
        itemsIndexed(twits){ index: Int, twit: Twit ->
            ItemTwitCons(
                twit = twit,
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId(index)
            )
            Divider()
        }
    }
}


@Composable
fun ItemTwitCons(
    twit: Twit,
    modifier: Modifier = Modifier
){
    ConstraintLayout(
        modifier = modifier
            .padding(all = 16.dp)
    ) {

        val startGuideLine = createGuidelineFromStart(0.2f)

        val (
            imageProfileTwit,
            contentTwit
        ) = createRefs()

        ImageProfileTwitCons(
            image = twit.imageTwit,
            modifier = Modifier
                .constrainAs(imageProfileTwit){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(startGuideLine, margin = 4.dp)
                }
        )

        ContentTwitCons(
            twit = twit,
            modifier = Modifier
                .constrainAs(contentTwit){
                    start.linkTo(startGuideLine, margin = 4.dp)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )

    }

}

@Composable
fun ImageProfileTwitCons(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Image Profile",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(55.dp)
            .clip(CircleShape)
    )
}

@Composable
fun ContentTwitCons(
    twit: Twit,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
    ) {

        val (
            headerTwit,
            bodyTwit,
            footerTwit
        ) = createRefs()

        HeaderTwitCons(
            username = twit.username,
            nickname = twit.nickname,
            time = twit.time,
            modifier = Modifier
                .constrainAs(headerTwit){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
        BodyTwitCons(
            text = twit.contentTwit,
            image = twit.imageTwit,
            modifier = Modifier
                .constrainAs(bodyTwit){
                    top.linkTo(headerTwit.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
        FooterTwitCons(
            modifier = Modifier
                .constrainAs(footerTwit){
                    top.linkTo(bodyTwit.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
    }
}


@Composable
fun HeaderTwitCons(
    username: String,
    nickname: String,
    time: Int,
    modifier: Modifier = Modifier
) {

    val timeShow: String = if (time > 12){
        stringResource(id = R.string.nickname_hour, time)
    } else {
        stringResource(id = R.string.nickname_minutes, time)
    }

    ConstraintLayout(
        modifier = modifier
    ) {

        val (
            usernameId,
            nicknameId,
            timeId,
            iconMoreId
        ) = createRefs()

        Text(
            text = username,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(usernameId){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )
        Text(
            text = nickname,
            modifier = Modifier
                .constrainAs(nicknameId){
                    start.linkTo(usernameId.end, margin = 8.dp)
                    top.linkTo(parent.top)
                }
        )
        Text(
            text = timeShow,
            modifier = Modifier
                .constrainAs(timeId){
                    start.linkTo(nicknameId.end, margin = 8.dp)
                    top.linkTo(parent.top)
                }
        )
        Icon(
            imageVector = Icons.Filled.MoreHoriz,
            contentDescription = "options",
            modifier = Modifier
                .constrainAs(iconMoreId){
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun BodyTwitCons(
    text: String,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
    ){
        val (
            textBody,
            imageBody
        ) = createRefs()

        Text(
            text = text,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .constrainAs(textBody){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(id = image),
            contentDescription = "Image Twit",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(imageBody) {
                    start.linkTo(parent.start)
                    top.linkTo(textBody.bottom, margin = 8.dp)
                    end.linkTo(parent.end)
                }
                .height(192.dp)
                .clip(shape = RoundedCornerShape(16.dp))
        )
    }
}

@Composable
fun FooterTwitCons(
    modifier: Modifier = Modifier
) {

    var stateComment: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    var stateRetwit: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    var stateLike: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    ConstraintLayout(
        modifier = modifier
            .padding(vertical = 8.dp)
    ) {

        val (
            commentId,
            retwitId,
            likeId
        ) = createRefs()

        createHorizontalChain(
            commentId,
            retwitId,
            likeId,
            chainStyle = ChainStyle.SpreadInside
        )

        IconTwitCons(
            icon = if(stateComment) OptionsTwit.COMMENT.iconFilled else OptionsTwit.COMMENT.iconOutlined,
            description = "Comentarios",
            state = stateComment,
            tint = if(stateComment) Color(0xFF838c96) else Color.Gray,
            onIconClick = {
                stateComment = it
            },
            modifier = Modifier
                .constrainAs(commentId){
                    start.linkTo(parent.start)
                    end.linkTo(retwitId.start)
                }
        )
        IconTwitCons(
            icon = if(stateRetwit) OptionsTwit.RETWIT.iconFilled else OptionsTwit.RETWIT.iconOutlined,
            description = "Comentarios",
            state = stateRetwit,
            tint = if(stateRetwit) Color(0xFF328e49) else Color.Gray,
            onIconClick = {
                stateRetwit = it
            },
            modifier = Modifier
                .constrainAs(retwitId){
                    start.linkTo(commentId.end)
                    end.linkTo(likeId.start)
                }
        )
        IconTwitCons(
            icon = if(stateLike) OptionsTwit.LIKE.iconFilled else OptionsTwit.LIKE.iconOutlined,
            description = "Comentarios",
            state = stateLike,
            tint = if(stateLike) Color(0xFFfa0202) else Color.Gray,
            onIconClick = {
                stateLike = it
            },
            modifier = Modifier
                .constrainAs(likeId){
                    start.linkTo(retwitId.end)
                    end.linkTo(parent.end)
                }
        )


    }
}

@Composable
fun IconTwitCons(
    @DrawableRes icon: Int,
    description: String,
    state: Boolean,
    tint: Color,
    onIconClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    val defaultValue: Int = 1

    ConstraintLayout(
        modifier = modifier
    ) {

        val (
            iconTwit,
            iconText
        ) = createRefs()

        Icon(
            painter = painterResource(id = icon),
            tint = tint,
            contentDescription = description,
            modifier = Modifier
                .constrainAs(iconTwit){
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .clickable {
                    onIconClick(!state)
                }
        )
        Text(
            text = if(state) "${defaultValue + 1 }" else "$defaultValue",
            modifier = Modifier
                .constrainAs(iconText){
                    start.linkTo(iconTwit.end, margin = 4.dp)
                }
        )
    }

}















@Preview(
    name = "Modo Claro",
    showBackground = true
)
@Preview(
    name = "Modo Oscuro",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun TwitScreenConsPreview(){
    TwitterChallengeResolutionTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize(),
        ) {

            val dataSource: List<Twit> = UsersDataSource.listTwits

            TwitScreenCons(twits = dataSource)
        }
    }
}