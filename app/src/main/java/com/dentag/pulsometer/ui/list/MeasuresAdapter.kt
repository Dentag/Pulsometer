package com.dentag.pulsometer.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dentag.pulsometer.databinding.ItemDateBinding
import com.dentag.pulsometer.databinding.ItemMeasureBinding
import com.dentag.pulsometer.model.RecyclerData

class MeasuresAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<RecyclerData>
        set(value) = asyncDiffer.submitList(value)
        get() = asyncDiffer.currentList

    private val asyncDiffer = AsyncListDiffer<RecyclerData>(this, differ)

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is RecyclerData.Date -> DATE_VIEW_TYPE
            is RecyclerData.PressureAndPulse -> MEASURE_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            DATE_VIEW_TYPE -> {
                val binding = ItemDateBinding.inflate(layoutInflater, parent, false)
                DateViewHolder(binding)
            }
            MEASURE_VIEW_TYPE -> {
                val binding = ItemMeasureBinding.inflate(layoutInflater, parent, false)
                PressureAndPulseViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Wrong view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DateViewHolder -> holder.bind(data[position] as RecyclerData.Date)
            is PressureAndPulseViewHolder -> holder.bind(data[position] as RecyclerData.PressureAndPulse)
        }
    }

    override fun getItemCount(): Int = data.size

    companion object {
        private const val DATE_VIEW_TYPE = 0
        private const val MEASURE_VIEW_TYPE = 1

        private val differ = object : DiffUtil.ItemCallback<RecyclerData>() {
            override fun areItemsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: RecyclerData, newItem: RecyclerData): Boolean {
                return oldItem == newItem
            }
        }
    }
}