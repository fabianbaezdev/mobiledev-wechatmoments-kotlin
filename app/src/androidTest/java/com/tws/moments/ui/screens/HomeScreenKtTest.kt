package com.tws.moments.ui.screens

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import com.tws.moments.factory.TweetFactory.makeTweetWithData
import com.tws.moments.factory.TweetFactory.makeTweetWithMissingData
import com.tws.moments.factory.UserFactory.makeUserWithData
import org.junit.Rule
import org.junit.Test


class HomeScreenKtTest {

    @get:Rule(order = 1)
    var composeTestRule = createComposeRule()


    @Test
    fun header() {
        val user = makeUserWithData()
        composeTestRule.setContent {
            Header(user = user)
        }
        composeTestRule.onNodeWithContentDescription("Profile Image").assertExists()
        composeTestRule.onNodeWithContentDescription("Avatar").assertExists()
        composeTestRule.onNodeWithTag("HeaderNickText").assertExists()
        composeTestRule.onNodeWithTag("HeaderBigNickText").assertDoesNotExist()

        composeTestRule.onNodeWithTag("HeaderNickText").assertTextEquals(user.nick)
    }

    @Test
    fun tweetFeed() {
        val tweets = listOf(makeTweetWithData())
        composeTestRule.setContent {
            TweetFeed(tweets = tweets, false) { }
        }
        composeTestRule.onNodeWithTag("TweetFeedContainer").assertExists()
        composeTestRule.onNodeWithTag("TweetFeedLazyColumn").assertExists()
        composeTestRule.onNodeWithTag("HeaderBigNickText").assertDoesNotExist()

    }

    @Test
    fun tweetCardWithData() {
        val tweet = makeTweetWithData()
        composeTestRule.setContent {
            TweetCard(tweet = tweet)
        }

        composeTestRule.onNodeWithContentDescription("Sender Avatar").assertExists()
        composeTestRule.onNodeWithTag("TweetCardSenderNick").assertTextEquals(tweet.senderNick)
        composeTestRule.onNodeWithTag("TweetCardContent").assertTextEquals(tweet.content)
        composeTestRule.onNodeWithTag("TweetCardImageGrid").assertExists()
        composeTestRule.onNodeWithTag("HeaderBigNickText").assertDoesNotExist()

    }

    @Test
    fun tweetCardWithMissingData() {
        val tweet = makeTweetWithMissingData()
        composeTestRule.setContent {
            TweetCard(tweet = tweet)
        }

        composeTestRule.onNodeWithContentDescription("Sender Avatar").assertExists()
        composeTestRule.onNodeWithTag("TweetCardSenderNick").assertTextEquals(tweet.senderNick)
        composeTestRule.onNodeWithTag("TweetCardContent").assertDoesNotExist()
        composeTestRule.onNodeWithContentDescription("Image Alone").assertExists()
        composeTestRule.onNodeWithTag("HeaderBigNickText").assertDoesNotExist()

    }

}