package com.dentag.pulsometer.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.dentag.pulsometer.databinding.ItemMeasureBinding
import com.dentag.pulsometer.model.RecyclerData
import java.text.SimpleDateFormat
import java.util.*

class PressureAndPulseViewHolder(
    private val binding: ItemMeasureBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(measures: RecyclerData.PressureAndPulse) {
        with(binding) {
            val time = SimpleDateFormat("HH:mm", Locale("RU"))
                .format(Date(measures.timeInMillis))
            itemMeasureTime.text = time
            itemMeasureHighPressure.text = measures.highPressure.toString()
            itemMeasureLowPressure.text = measures.lowPressure.toString()
            itemMeasurePulse.text = measures.pulse.toString()
        }
    }
}