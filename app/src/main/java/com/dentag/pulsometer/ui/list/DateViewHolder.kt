package com.dentag.pulsometer.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.dentag.pulsometer.databinding.ItemDateBinding
import com.dentag.pulsometer.model.RecyclerData

class DateViewHolder(
    private val binding: ItemDateBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(date: RecyclerData.Date) {
        binding.itemDateTV.text = date.date
    }
}