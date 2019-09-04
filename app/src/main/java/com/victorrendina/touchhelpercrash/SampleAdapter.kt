package com.victorrendina.touchhelpercrash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.listview_item_sample.*

class SampleAdapter : RecyclerView.Adapter<SampleAdapter.SampleListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleListHolder {
        val holderView = LayoutInflater.from(parent.context)
            .inflate(R.layout.listview_item_sample, parent, false)
        return SampleListHolder(holderView)
    }

    override fun getItemCount(): Int = 50

    override fun onBindViewHolder(holder: SampleListHolder, position: Int) {
        holder.bind()
    }


    inner class SampleListHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind() {
            listItemTextView.text = "Item $adapterPosition"
        }

    }

}