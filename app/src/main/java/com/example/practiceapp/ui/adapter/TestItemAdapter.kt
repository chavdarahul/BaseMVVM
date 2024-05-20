package com.example.practiceapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceapp.data.model.UserData
import com.example.practiceapp.databinding.ItemUserBinding


class TestItemAdapter(
    items: List<UserData?>,
) : RecyclerView.Adapter<TestItemAdapter.ViewHolder>() {

    private var listItems = items
    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val itemBinding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listItems[position]?.let { holder.bind(it, position) }
    }

    override fun getItemCount(): Int = listItems.size


    private fun ViewHolder.bind(list: UserData, position: Int) {
        binding.tvName.text = list.firstName
//        Glide.with(context).load(list.airline?.first()?.logo)
//            .into(binding.ivLogo)
    }

    fun filterList(filterList: ArrayList<UserData>) {
        listItems = filterList
        notifyItemRangeChanged(0, listItems.size - 1)
    }

    class ViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

}