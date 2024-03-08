package com.tws.moments.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.TWApplication
import com.tws.moments.databinding.ItemMomentHeadBinding
import com.tws.moments.domain.model.User

class HeaderViewHolder(private val binding: ItemMomentHeadBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private var imageLoader = TWApplication.imageLoader

    fun bind(user: User?) {
        if (user != null) {
            renderText(user.nick)
            renderImages(user.profileImage, user.avatar)
        }
    }

    private fun renderText(nick: String?) {
        binding.tvUserNickname.text = nick
    }

    private fun renderImages(profileImage: String?, avatar: String?) {
        with(imageLoader) {
            displayImage(profileImage, binding.ivUserProfile)
            displayImage(avatar, binding.ivUserAvatar)
        }
    }
}