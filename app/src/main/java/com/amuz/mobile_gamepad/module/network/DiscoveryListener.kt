package com.amuz.mobile_gamepad.module.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.connectsdk.device.ConnectableDevice
import com.connectsdk.discovery.DiscoveryManager
import com.connectsdk.discovery.DiscoveryManagerListener
import com.connectsdk.service.command.ServiceCommandError

object DiscoveryListener : DiscoveryManagerListener {

    private var mDiscoveryManager: DiscoveryManager? = null
    private val _mDeviceList: MutableLiveData<List<ConnectableDevice>> = MutableLiveData()
    val mDeviceList: LiveData<List<ConnectableDevice>> = _mDeviceList

    fun initialize(context: Context) {
        DiscoveryManager.init(context.applicationContext, null)
        mDiscoveryManager = DiscoveryManager.getInstance()
        mDiscoveryManager?.addListener(this)
        mDiscoveryManager?.pairingLevel = DiscoveryManager.PairingLevel.ON
    }

    fun startScan() {
//        _mDeviceList.value = emptyList()
        mDiscoveryManager?.start()
        mDiscoveryManager?.stop()
    }
    fun stopScan() {
        DiscoveryManager.getInstance().removeListener(this)
    }

    override fun onDeviceAdded(manager: DiscoveryManager?, device: ConnectableDevice?) {
        Log.d("onDeviceAdded", device.toString())
        device?.let {
            val serviceNames = device.services.map { it.serviceName }
            Log.d("onDeviceAdded serviceNames", serviceNames.toString())
            if ("webOS TV" in serviceNames) {
                val currentList = _mDeviceList.value ?: emptyList()
                if (currentList.none { it.id == device.id }) {
                    _mDeviceList.postValue(currentList + device)
                }
            }
        }
    }

    override fun onDeviceUpdated(manager: DiscoveryManager?, device: ConnectableDevice?) {
        Log.d("onDeviceUpdated serviceNames", device.toString())
        device?.let {
            val serviceNames = device.services.map { it.serviceName }
            Log.d("serviceNames", serviceNames.toString())
            if ("webOS TV" in serviceNames) {
                val currentList = _mDeviceList.value ?: emptyList()
                if (currentList.none { it.id == device.id }) {
                    _mDeviceList.postValue(currentList + device)
                }
            }
        }
    }

    override fun onDeviceRemoved(manager: DiscoveryManager?, device: ConnectableDevice?) {
        Log.d("onDeviceRemoved", device.toString())
    }

    override fun onDiscoveryFailed(manager: DiscoveryManager?, error: ServiceCommandError?) {
        Log.d("onDiscoveryFailed", error.toString())
    }
}