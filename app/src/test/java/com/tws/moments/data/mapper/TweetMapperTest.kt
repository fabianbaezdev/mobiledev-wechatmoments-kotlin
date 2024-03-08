package com.tws.moments.data.mapper

import com.tws.moments.data.api.entry.CommentsBean
import com.tws.moments.data.api.entry.ImagesBean
import com.tws.moments.domain.model.Comment
import com.tws.moments.factory.TweetFactory.makeTweetBeanWithData
import com.tws.moments.factory.TweetFactory.makeTweetBeanWithoutComments
import com.tws.moments.factory.TweetFactory.makeTweetBeanWithoutData
import org.junit.Assert.assertEquals
import org.junit.Test

class TweetMapperTest {
    private val mapper = TweetMapper()

    @Test
    fun `given TweetBean, when remoteToDomain, then Tweet`() {
        val bean = makeTweetBeanWithData()

        val tweet = with(mapper) { bean.remoteToDomain() }

        assertEquals("content", tweet.content)
        assertEquals("nick", tweet.senderNick)
        assertEquals("avatar", tweet.senderAvatar)
        assertEquals("username", tweet.senderUsername)

        assetImages(bean.images, tweet.images)
        assetComments(bean.comments, tweet.comments)

    }

    private fun assetImages(imagesBean: List<ImagesBean>?, imagesTweet: List<String>) {
        imagesBean?.zip(imagesTweet)?.map {
            assertEquals("url", it.first.url.orEmpty(), it.second)
        }
    }

    private fun assetComments(commentsBeans: List<CommentsBean>?, comments: List<Comment>) {
        commentsBeans?.zip(comments)?.map {
            assertEquals("content", it.first.content.orEmpty(), it.second.content)
            assertEquals("nick", it.first.sender?.nick.orEmpty(), it.second.senderNick)
            assertEquals("avatar", it.first.sender?.avatar.orEmpty(), it.second.senderAvatar)
            assertEquals("username", it.first.sender?.username.orEmpty(), it.second.senderUsername)
        }
    }

    @Test
    fun `given TweetBean without comments, when remoteToDomain, then Tweet`() {
        val bean = makeTweetBeanWithoutComments()

        val tweet = with(mapper) { bean.remoteToDomain() }

        assertEquals("content", tweet.content)
        assertEquals("nick", tweet.senderNick)
        assertEquals("avatar", tweet.senderAvatar)
        assertEquals("username", tweet.senderUsername)

        assetImages(bean.images, tweet.images)
        assetComments(bean.comments, tweet.comments)
    }

    @Test
    fun `given empty TweetBean, when remoteToDomain, then Tweet`() {
        val bean = makeTweetBeanWithoutData()

        val tweet = with(mapper) { bean.remoteToDomain() }

        assertEquals("", tweet.content)
        assertEquals("", tweet.senderNick)
        assertEquals("", tweet.senderAvatar)
        assertEquals("", tweet.senderUsername)

        assetImages(bean.images, tweet.images)
        assetComments(bean.comments, tweet.comments)
    }

    @Test
    fun `given TweetBean list, when remoteToDomain, then Tweet list`() {
        val beans = listOf(
            makeTweetBeanWithData(),
            makeTweetBeanWithoutComments(),
            makeTweetBeanWithoutData()
        )

        val tweets = with(mapper) { beans.remoteToDomain() }

        beans.zip(tweets).map {
            assertEquals("content", it.first.content.orEmpty(), it.second.content)
            assertEquals("nick", it.first.sender?.nick.orEmpty(), it.second.senderNick)
            assertEquals("avatar", it.first.sender?.avatar.orEmpty(), it.second.senderAvatar)
            assertEquals("username", it.first.sender?.username.orEmpty(), it.second.senderUsername)
            assetImages(it.first.images, it.second.images)
            assetComments(it.first.comments, it.second.comments)
        }
    }
}