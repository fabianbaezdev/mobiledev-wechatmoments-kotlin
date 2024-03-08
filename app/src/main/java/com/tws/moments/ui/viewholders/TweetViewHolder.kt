package com.tws.moments.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.TWApplication
import com.tws.moments.data.api.entry.CommentsBean
import com.tws.moments.data.api.entry.ImagesBean
import com.tws.moments.data.api.entry.SenderBean
import com.tws.moments.data.api.entry.TweetBean
import com.tws.moments.databinding.LayoutBaseTweetBinding
import com.tws.moments.ui.adapters.CommentsAdapter
import com.tws.moments.ui.adapters.ImagesAdapter
import com.tws.moments.ui.views.itemdecoration.ImagesDecoration
import com.tws.moments.ui.views.itemdecoration.MarginItemDecoration
import com.tws.moments.utils.dip

private const val IMAGE_SPAN_COUNT = 3

class TweetViewHolder(private val binding: LayoutBaseTweetBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        setupCommentsView(binding.rvComments)
        addTweetImagesView()
    }

    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var commentsAdapter: CommentsAdapter
    private var imageLoader = TWApplication.imageLoader
    fun bind(tweet: TweetBean) {
        renderTextContent(tweet.content)
        renderImages(tweet.images)
        renderSender(tweet.sender)
        renderComments(tweet.comments)
    }

    private fun renderTextContent(content: String?) {
        binding.tvTweetContent.text = content
    }

    private fun renderImages(imagesBean: List<ImagesBean>?) {
        if (imagesBean == null || imagesBean.isEmpty()) {
            binding.simpleImageView.visibility = View.GONE
            binding.imagesRecyclerView.visibility = View.GONE
            return
        }
        binding.imagesRecyclerView.layoutManager = if (imagesBean.size == 4) {
            GridLayoutManager(itemView.context, 2, RecyclerView.HORIZONTAL, false)
        } else {
            GridLayoutManager(itemView.context, IMAGE_SPAN_COUNT, RecyclerView.VERTICAL, false)
        }

        if (imagesBean.size == 1) {
            binding.simpleImageView.visibility = View.VISIBLE
            binding.imagesRecyclerView.visibility = View.GONE
            val url = imagesBean[0].url
            imageLoader.displayImage(
                url, binding.simpleImageView
            )
            binding.simpleImageView.tag = url
            imagesAdapter.images = null
        } else {
            binding.simpleImageView.visibility = View.GONE
            binding.imagesRecyclerView.visibility = View.VISIBLE
            imagesAdapter.images =
                imagesBean.asSequence().map { it.url ?: "" }.filter { it.isNotEmpty() }.toList()
        }
    }

    private fun renderSender(sender: SenderBean?) {
        if (sender != null) {
            binding.tvSenderNickname.text = sender.nick
            imageLoader.displayImage(
                sender.avatar, binding.ivSenderAvatar
            )
        }
    }

    private fun renderComments(comments: List<CommentsBean>?) {
        if (comments.isNullOrEmpty()) {
            binding.rvComments.visibility = View.GONE
        } else {
            binding.rvComments.visibility = View.VISIBLE
            commentsAdapter.comments = comments
        }

    }

    private fun setupCommentsView(commentsView: RecyclerView) {
        commentsView.layoutManager = LinearLayoutManager(itemView.context)
        commentsAdapter = CommentsAdapter()
        commentsView.adapter = commentsAdapter

        commentsView.addItemDecoration(
            MarginItemDecoration(
                RecyclerView.VERTICAL,
                itemView.context.dip(5)
            )
        )
    }

    private fun addTweetImagesView() {
        imagesAdapter = ImagesAdapter()
        binding.imagesRecyclerView.addItemDecoration(ImagesDecoration(itemView.context.dip(5)))
        binding.imagesRecyclerView.adapter = imagesAdapter
    }
}