package com.tws.moments.data.mapper

import com.tws.moments.factory.UserFactory.makeUserBeanWithData
import com.tws.moments.factory.UserFactory.makeUserBeanWithoutData
import org.junit.Assert.assertEquals
import org.junit.Test

class UserMapperTest {
    private val mapper = UserMapper()

    @Test
    fun `given UserBean, when remoteToDomain, then User`() {
        val bean = makeUserBeanWithData()

        val user = with(mapper) { bean.remoteToDomain() }

        assertEquals("username", user.username)
        assertEquals("nick", user.nick)
        assertEquals("avatar", user.avatar)
        assertEquals("profileImage", user.profileImage)
    }

    @Test
    fun `given empty UserBean, when remoteToDomain, then User`() {
        val bean = makeUserBeanWithoutData()

        val user = with(mapper) { bean.remoteToDomain() }

        assertEquals("", user.username)
        assertEquals("", user.nick)
        assertEquals("", user.avatar)
        assertEquals("", user.profileImage)
    }
}