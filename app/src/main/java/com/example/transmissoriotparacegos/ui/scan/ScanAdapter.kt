package com.example.transmissoriotparacegos.ui.scan

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.transmissoriotparacegos.MainActivity
import com.example.transmissoriotparacegos.R
import com.example.transmissoriotparacegos.models.IoTDevice

class ScanAdapter(var dataset: List<IoTDevice>, val activity: MainActivity) : RecyclerView.Adapter<ScanAdapter.ScanViewHolder>() {
    inner class ScanViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScanViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false) as TextView
        return ScanViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ScanViewHolder, position: Int) {
        holder.textView.text = dataset[position].nome
        holder.textView.setOnClickListener {
            activity.navigateDetails(dataset[position])
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}