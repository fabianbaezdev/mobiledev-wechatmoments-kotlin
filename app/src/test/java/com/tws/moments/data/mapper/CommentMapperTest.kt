package com.tws.moments.data.mapper

import com.tws.moments.factory.CommentFactory.makeCommentsBeanWithData
import com.tws.moments.factory.CommentFactory.makeCommentsWithoutData
import com.tws.moments.factory.CommentFactory.makeCommentsWithoutSender
import org.junit.Assert.assertEquals
import org.junit.Test

class CommentMapperTest {
    private val mapper = CommentMapper()

    @Test
    fun `given CommentsBean, when remoteToDomain, then Comment`() {
        val bean = makeCommentsBeanWithData()

        val comment = with(mapper) { bean.remoteToDomain() }

        assertEquals("content", comment.content)
        assertEquals("nick", comment.senderNick)
        assertEquals("avatar", comment.senderAvatar)
        assertEquals("username", comment.senderUsername)
    }

    @Test
    fun `given CommentsBean without sender, when remoteToDomain, then Comment`() {
        val bean = makeCommentsWithoutSender()

        val comment = with(mapper) { bean.remoteToDomain() }

        assertEquals("content", comment.content)
        assertEquals("", comment.senderNick)
        assertEquals("", comment.senderAvatar)
        assertEquals("", comment.senderUsername)
    }

    @Test
    fun `given empty CommentsBean, when remoteToDomain, then Comment`() {
        val bean = makeCommentsWithoutData()

        val comment = with(mapper) { bean.remoteToDomain() }

        assertEquals("", comment.content)
        assertEquals("", comment.senderNick)
        assertEquals("", comment.senderAvatar)
        assertEquals("", comment.senderUsername)
    }

    @Test
    fun `given CommentsBean list, when remoteToDomain, then Comment list`() {
        val beans = listOf(
            makeCommentsBeanWithData(),
            makeCommentsWithoutSender(),
            makeCommentsWithoutData()
        )

        val comments = with(mapper) { beans.remoteToDomain() }

        beans.zip(comments.orEmpty()).map {
            assertEquals("content", it.first.content.orEmpty(), it.second.content)
            assertEquals("nick", it.first.sender?.nick.orEmpty(), it.second.senderNick)
            assertEquals("avatar", it.first.sender?.avatar.orEmpty(), it.second.senderAvatar)
            assertEquals("username", it.first.sender?.username.orEmpty(), it.second.senderUsername)
        }
    }
}