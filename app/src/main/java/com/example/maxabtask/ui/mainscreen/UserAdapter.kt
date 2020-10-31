package com.example.maxabtask.ui.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maxabtask.R
import com.example.maxabtask.model.User
import com.example.maxabtask.util.GlideApp
import com.example.maxabtask.util.OnBottomReachedListener
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(
    private val viewModel: MainViewModel,
    private val onBottomReachedListener: OnBottomReachedListener
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_user, parent,
            false
        )
    )

    override fun getItemCount() = viewModel.users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        if (position == viewModel.users.size - 1 && viewModel.users.isNotEmpty()) {
            onBottomReachedListener.loadMore()
        }
        holder.bind(viewModel.users[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            GlideApp.with(itemView.context)
                .load(user.picture.image)
                .centerCrop()
                .into(itemView.userImageView)

            itemView.nameTextView.text = user.name.firstName + " " + user.name.lastName
            itemView.genderTextView.text = user.gender
        }
    }
}