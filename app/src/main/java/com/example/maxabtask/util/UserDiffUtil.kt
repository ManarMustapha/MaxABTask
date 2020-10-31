package com.example.maxabtask.util

import androidx.recyclerview.widget.DiffUtil
import com.example.maxabtask.model.User
import java.util.*

class UserDiffUtil(private val oldItems: List<User>, private val newItems: List<User>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id.uuid.contentEquals(newItems[newItemPosition].id.uuid)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].name.firstName.contentEquals((newItems[newItemPosition]).name.firstName)
                && oldItems[oldItemPosition].name.lastName.contentEquals((newItems[newItemPosition]).name.lastName)
                && oldItems[oldItemPosition].picture.image.contentEquals((newItems[newItemPosition]).picture.image)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)

    }
    fun getNewItems(): List<User> {
        return Collections.unmodifiableList<User>(newItems)
    }

    fun getOldItems(): List<User> {
        return Collections.unmodifiableList<User>(oldItems)
    }
}