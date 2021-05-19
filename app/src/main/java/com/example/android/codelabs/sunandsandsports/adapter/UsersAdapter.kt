package com.example.android.codelabs.sunandsandsports.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.codelabs.sunandsandsports.R
import com.example.android.codelabs.sunandsandsports.databinding.RowUserBinding
import com.example.android.codelabs.sunandsandsports.listener.ItemClickListener
import com.example.android.codelabs.sunandsandsports.model.UserResult

class UsersAdapter (
    private val context: Context,
    private val users: ArrayList<UserResult>,private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(){

    override fun getItemCount() = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_user,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val data=users[position]
        holder.bind(data,position,context)
    }

    fun updateList(list:ArrayList<UserResult>)
    {
        users.addAll(list)
        notifyDataSetChanged()
    }


    inner class UsersViewHolder(
        val binding: RowUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserResult, position: Int, context: Context) {
            data.name?.apply {
                binding.textViewName.text="${title} ${first} ${last}"
            }
            binding.textViewGender.text=data.gender.toString()
            binding.lnrRoot.setOnClickListener {
                itemClickListener.passData(position,data)
            }
            data.picture?.let {
                Glide.with(context).load(data.picture.large).placeholder(R.drawable.ic_placeholder).
                error(R.drawable.ic_placeholder).into(binding.imageView)
            }
        }
    }

}