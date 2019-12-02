package com.example.transmissoriotparacegos.ui.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer

import com.example.transmissoriotparacegos.R
import com.example.transmissoriotparacegos.models.IoTDevice

class DetailsFragment(val device: IoTDevice) : Fragment() {

    companion object {
        fun newInstance(device: IoTDevice) = DetailsFragment(device)
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, DetailsViewModelFactory(device.ip)).get(DetailsViewModel::class.java)
        val textView = activity!!.findViewById<TextView>(R.id.textView)
        val headerDeviceName = activity!!.findViewById<TextView>(R.id.headerDeviceName)
        headerDeviceName.text = "Temperatura do ${device.nome}"
        viewModel.resultado.observe(this, Observer {
            textView.text = "${it.valor}${it.unidade}"
        })
    }

}
