package com.example.transmissoriotparacegos.ui.scan

import android.content.Context
import android.net.wifi.WifiManager
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.transmissoriotparacegos.MainActivity
import com.example.transmissoriotparacegos.R
import kotlinx.android.synthetic.main.scan_fragment.*


class ScanFragment : Fragment() {

    companion object {
        fun newInstance() = ScanFragment()
    }

    private lateinit var viewModel: ScanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.scan_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val wm = activity!!.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        viewModel = ViewModelProviders.of(this, ScanViewModelFactory(wm, this)).get(ScanViewModel::class.java)
        val viewManager = LinearLayoutManager(this.context)
        val viewAdapter = ScanAdapter(listOf(), activity as MainActivity)
        val recyclerView = (activity as AppCompatActivity).findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        viewModel.devices.observe(this, Observer {
            (recyclerView.adapter as ScanAdapter).dataset = it
            (recyclerView.adapter as ScanAdapter).notifyDataSetChanged()
        })
    }

}
