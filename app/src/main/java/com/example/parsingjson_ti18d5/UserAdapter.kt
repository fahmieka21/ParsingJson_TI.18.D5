package com.example.parsingjson_ti18d5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.parsingjson_ti18d5.model.DataItem

class UserAdapter (private val users: MutableList<DataItem>) :
        RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ListViewHolder {
        val view : View=
                LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent,false)
        return ListViewHolder(
                view
        )

    }

    fun addUser(newUser: DataItem) {
        users.add(newUser)
        notifyItemInserted(users.lastIndex)
    }

    fun clear() {
        users.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size
    override fun onBindViewHolder(holder: UserAdapter.ListViewHolder, position: Int) {
        val user = users[position]

        Glide.with(holder.itemView.context)
                .load(user.avatar)
                .apply(RequestOptions().override(80, 80).placeholder(R.drawable.icon_avatar))
                .transform(CircleCrop())
                .into(holder.ivAvatar)

        holder.tvUserName.text = "${user.firsName}${user.lastName}"
        holder.tvEmail.text = user.email

    }
    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvUserName: TextView = itemView.findViewById(R.id.itemName)
        val tvEmail: TextView = itemView.findViewById(R.id.itemEmail)
        val ivAvatar: ImageView = itemView.findViewById(R.id.itemAvatar)
    }
}