package com.example.transmissoriotparacegos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentTransaction
import com.example.transmissoriotparacegos.models.IoTDevice
import com.example.transmissoriotparacegos.ui.details.DetailsFragment
import com.example.transmissoriotparacegos.ui.home.HomeFragment
import com.example.transmissoriotparacegos.ui.scan.ScanFragment
import kotlinx.android.synthetic.main.home_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            navigate("home")
        }
    }

    fun navigateScan() {
        navigate("scan")
    }

    fun navigateDetails(device: IoTDevice) {
        navigate("details", device)
    }

    private fun navigate(fragmentName: String, device: IoTDevice? = null) {
        Log.i("MainActivity", "Navegando para $fragmentName")
        val fragment = when(fragmentName) {
            "home" -> HomeFragment.newInstance()
            "scan" -> ScanFragment.newInstance()
            "details" -> DetailsFragment.newInstance(device!!)
            else -> null
        }
        fragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, it)
                .commitNow()
        }
    }

}
