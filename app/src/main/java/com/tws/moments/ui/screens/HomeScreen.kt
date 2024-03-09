package com.tws.moments.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.tws.moments.domain.model.Tweet
import com.tws.moments.presentation.viewmodels.MainViewModel
import com.tws.moments.ui.theme.colorTweetContent

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    Column(Modifier.fillMaxSize()) {
        Header(viewModel)
        TweetFeed(viewModel)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Header(viewModel: MainViewModel) {
    val user = viewModel.userBean.observeAsState().value
    ConstraintLayout {
        val (userProfile, userAvatar, userNick) = createRefs()

        AsyncImage(
            model = user?.profileImage,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(userProfile) {
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .height(200.dp)
        )

        AsyncImage(
            model = user?.avatar,
            contentDescription = "Avatar",
            modifier = Modifier
                .constrainAs(userAvatar) {
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom)
                }
                .size(70.dp)
        )

        Text(
            text = user?.nick.orEmpty(),
            modifier = Modifier
                .constrainAs(userNick) {
                    bottom.linkTo(userProfile.bottom, margin = 5.dp)
                    end.linkTo(userAvatar.start, margin = 10.dp)
                },
            fontSize = 18.sp,
            color = Color.White
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TweetFeed(viewModel: MainViewModel) {
    val refreshing = viewModel.isRefreshing.collectAsState().value
    val tweets = viewModel.tweets.observeAsState().value
    val state = rememberPullRefreshState(refreshing, viewModel::refreshTweets)

    Box(Modifier.pullRefresh(state)) {
        LazyColumn(Modifier.fillMaxSize()) {
            if (!refreshing)
                this.itemsIndexed(tweets.orEmpty()) { index, tweet ->
                    TweetCard(tweet)
                    if (index < (tweets?.lastIndex ?: 0))
                        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                }
        }
        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun TweetCard(tweet: Tweet) {
    Row(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = tweet.senderAvatar,
            contentDescription = "Sender Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)
                .size(40.dp)
                .background(Color.White)
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = tweet.senderNick,
                modifier = Modifier
                    .padding(top = 6.dp, start = 6.dp),
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            if (tweet.content.isNotEmpty())
                Text(
                    text = tweet.content,
                    color = colorTweetContent,
                    modifier = Modifier
                        .padding(6.dp, 0.dp),
                    textAlign = TextAlign.Left,
                    fontSize = 14.sp,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )

            if (tweet.images.size == 1) {
                AsyncImage(
                    model = tweet.images[0],
                    contentScale = ContentScale.Crop,
                    contentDescription = "Sender Avatar",
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                        .padding(10.dp)
                )
            } else if (tweet.images.size > 1) {
                val maxH = (tweet.images.size / 2 + tweet.images.size % 2) * (100.dp)
                LazyVerticalGrid(
                    modifier = Modifier
                        .height(maxH)
                        .width(200.dp),
                    columns = GridCells.Fixed(2)
                ) {
                    items(tweet.images) { img ->
                        AsyncImage(
                            model = img,
                            contentScale = ContentScale.Crop,
                            contentDescription = "Sender Avatar",
                            modifier = Modifier
                                .size(94.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, end = 10.dp)
                    .background(
                        color = Color.LightGray,
                        shape = RoundedCornerShape(5.dp)
                    )
            ) {
                for (comment in tweet.comments) {
                    Text(
                        text = comment.senderNick + ": " + comment.content,
                        modifier = Modifier
                            .padding(bottom = 3.dp, start = 5.dp),
                        fontSize = 14.sp,
                        color = Color.DarkGray
                    )
                }
            }

        }
    }
}

@Preview(widthDp = 600)
@Composable
fun SimpleComposablePreview() {
    TweetCard(
        tweet = Tweet(
            content = "hello",
            images = emptyList(),
            senderUsername = "wanana",
            senderNick = "wanana",
            senderAvatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/001.jpeg",
            comments = emptyList()
        )
    )
}

