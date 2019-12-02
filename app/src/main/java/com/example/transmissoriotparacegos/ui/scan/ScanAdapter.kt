package com.example.transmissoriotparacegos.ui.scan

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.transmissoriotparacegos.MainActivity
import com.example.transmissoriotparacegos.R
import com.example.transmissoriotparacegos.models.IoTDevice
import kotlinx.android.synthetic.main.list_item.view.*

class ScanAdapter(var dataset: List<IoTDevice>, val activity: MainActivity) : RecyclerView.Adapter<ScanAdapter.ScanViewHolder>() {
    inner class ScanViewHolder(internal val layout: LinearLayout) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScanViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false) as LinearLayout
        return ScanViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ScanViewHolder, position: Int) {
        holder.layout.textView.text = dataset[position].nome
        holder.layout.setOnClickListener {
            activity.navigateDetails(dataset[position])
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}