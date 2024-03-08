package com.tws.moments.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.TWApplication
import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.databinding.ItemMomentHeadBinding

class HeaderViewHolder(private val binding: ItemMomentHeadBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private var imageLoader = TWApplication.imageLoader

    fun bind(userBean: UserBean?) {
        if (userBean != null) {
            renderText(userBean.nick)
            renderImages(userBean.profileImage, userBean.avatar)
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