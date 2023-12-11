package com.sergio.rodriguez.twitterchallengeresolution

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sergio.rodriguez.twitterchallengeresolution.data.UsersDataSource
import com.sergio.rodriguez.twitterchallengeresolution.domain.model.Twit
import com.sergio.rodriguez.twitterchallengeresolution.ui.theme.TwitterChallengeResolutionTheme

enum class OptionsTwit(
    @DrawableRes val iconOutlined: Int,
    @DrawableRes val iconFilled: Int
){
    COMMENT(
        iconOutlined = R.drawable.ic_chat,
        iconFilled = R.drawable.ic_chat_filled
    ),
    RETWIT(
        iconOutlined = R.drawable.ic_rt,
        iconFilled = R.drawable.ic_rt
    ),
    LIKE(
        iconOutlined = R.drawable.ic_like,
        iconFilled = R.drawable.ic_like_filled
    ),
}

@Composable
fun TwitScreen(
    twits: List<Twit>,
    modifier: Modifier = Modifier
){
    ListTwits(
        twits = twits,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ListTwits(
    twits: List<Twit>,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ){
        items(twits){ twit: Twit ->
            ItemTwit(twit = twit)
            Divider(
                color = Color(0xFF343844),
                thickness = 2.dp
            )
        }
    }
}

@Composable
fun ItemTwit(
    twit: Twit,
    modifier: Modifier = Modifier
){
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        ImageProfileTwit(
            image = twit.imageTwit,
            modifier = Modifier
                .padding(end = 16.dp)
        )
        ContentTwit(
            twit = twit,
            modifier = Modifier
                .weight(1f)
        )
    }
}


@Composable
fun ImageProfileTwit(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Image Profile",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            .size(55.dp)
            .clip(CircleShape)
    )
}

@Composable
fun ContentTwit(
    twit: Twit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        HeaderTwit(
            username = twit.username,
            nickname = twit.nickname,
            time = twit.time,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        BodyTwit(
            text = twit.contentTwit,
            image = twit.imageTwit
        )
        FooterTwit(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun HeaderTwit(
    username: String,
    nickname: String,
    time: Int,
    modifier: Modifier = Modifier
) {

    val time: String = if (time > 12){
        stringResource(id = R.string.nickname_hour, time)
    } else {
        stringResource(id = R.string.nickname_minutes, time)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = username,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
        Text(
            text = nickname,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )
        Text(
            text = time,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Filled.MoreHoriz,
            contentDescription = "options",
            modifier = Modifier.clickable {  }
        )

    }
}

@Composable
fun BodyTwit(
    text: String,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Justify
        )
        Image(
            painter = painterResource(id = image),
            contentDescription = "Image Twit",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(192.dp)
                .clip(shape = RoundedCornerShape(16.dp))
        )
    }
}

@Composable
fun FooterTwit(
    modifier: Modifier = Modifier
) {
    var stateCountComment: Int by rememberSaveable {
        mutableIntStateOf(1)
    }
    var stateComment: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    var stateCountRetwit: Int by rememberSaveable {
        mutableIntStateOf(1)
    }
    var stateRetwit: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    var stateCountLike: Int by rememberSaveable {
        mutableIntStateOf(1)
    }
    var stateLike: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        IconTwit(
            state = stateComment,
            stateCount = stateCountComment,
            tint = if(stateComment) Color(0xFF838c96) else Color.Gray,
            icon = if(stateComment) OptionsTwit.COMMENT.iconFilled else OptionsTwit.COMMENT.iconOutlined,
            description = "",
            onClickIcon = {
                stateComment = it
                if(stateComment) stateCountComment++ else stateCountComment--
            }
        )
        IconTwit(
            state = stateRetwit,
            stateCount = stateCountRetwit,
            tint = if(stateRetwit) Color(0xFF328e49) else Color.Gray,
            icon = OptionsTwit.RETWIT.iconOutlined,
            description = "",
            onClickIcon = {
                stateRetwit = it
                if(stateRetwit) stateCountRetwit++ else stateCountRetwit--
            }
        )
        IconTwit(
            state = stateLike,
            stateCount = stateCountLike,
            tint = if(stateLike) Color(0xFFfa0202) else Color.Gray,
            icon = if(stateLike) OptionsTwit.LIKE.iconFilled else OptionsTwit.LIKE.iconOutlined,
            description = "",
            onClickIcon = {
                stateLike = it
                if(stateLike) stateCountLike++ else stateCountLike--
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun IconTwit(
    state: Boolean,
    stateCount: Int,
    tint: Color,
    onClickIcon: (Boolean) -> Unit,
    @DrawableRes icon: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    Row {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = description,
            tint = tint,
            modifier = Modifier
                .clickable {
                    onClickIcon(!state)
                }
        )
        AnimatedContent(
            targetState = stateCount,
            label = "",
            transitionSpec = {

                val transition: ContentTransform = if (targetState > initialState){
                    (slideInVertically { height -> height } + fadeIn())
                        .togetherWith(
                            slideOutVertically { height -> -height } + fadeOut()
                        )
                }else{
                    (slideInVertically { height -> - height } + fadeIn())
                        .togetherWith(
                            slideOutVertically { height -> height } + fadeOut()
                        )
                }

                transition.using(
                    SizeTransform(clip = false)
                )
            }
        ) {
            Text(
                text = it.toString(),
                modifier = Modifier
                    .padding(start = 4.dp)
            )
        }
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
fun TwitScreenPreview(){
    TwitterChallengeResolutionTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .fillMaxSize(),
        ) {

            val dataSource: List<Twit> = UsersDataSource.listTwits

            TwitScreen(twits = dataSource)
        }
    }
}