package com.tws.moments.ui.adapters

import android.text.method.LinkMovementMethod
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tws.moments.R
import com.tws.moments.domain.model.Comment
import com.tws.moments.utils.clickableSpan
import com.tws.moments.utils.inflate

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentHolder>() {

    var comments: List<Comment> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        return CommentHolder(parent.inflate(R.layout.item_comment))
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.bind(comments[position])
    }

    inner class CommentHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val commentTV: TextView = itemView.findViewById(R.id.tv_simple_comment)

        init {
            commentTV.movementMethod = LinkMovementMethod.getInstance()
        }

        fun bind(comment: Comment) {
            val spannableString = comment.senderNick.clickableSpan {
                Toast.makeText(it.context, "${comment.senderNick} info.", Toast.LENGTH_SHORT).show()
            }
            commentTV.text = spannableString
            commentTV.append(":" + comment.content)
        }
    }
}
