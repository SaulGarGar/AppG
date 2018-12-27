package com.garcia.saul.appg.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.garcia.saul.appg.R
import com.garcia.saul.appg.data.api.client.GrinClient
import com.garcia.saul.appg.data.model.MBluetoothDevice
import com.garcia.saul.appg.interactor.RemoteDevicesInteractor
import com.garcia.saul.appg.presenter.RemoteDevicesPresenter
import com.garcia.saul.appg.view.adapter.RemoteDevicesAdapter
import com.garcia.saul.appg.view.listener.RecyclerDeviceListener
import kotlinx.android.synthetic.main.activity_remote_devices.*

class RemoteDevicesActivity : AppCompatActivity(), RemoteDevicesPresenter.View {

    private lateinit var presenter: RemoteDevicesPresenter
    private var grinClient: GrinClient = GrinClient()

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: RemoteDevicesAdapter
    private val layoutManager by lazy { LinearLayoutManager(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_devices)

        initPresenter()
        initUi()
        showProgress()
        onClickReorder()

    }

    fun initPresenter(){
        presenter = RemoteDevicesPresenter(RemoteDevicesInteractor(grinClient))
        presenter.view = this
        presenter.onSearchDevices()
    }

    fun initUi(){
        recycler = remoteDevsRecyclerView as RecyclerView
    }

    override fun onClickReorder() {
        fabReorder.setOnClickListener{
            adapter.sort()
        }
    }

    override fun renderDevices(devices: List<MBluetoothDevice>) {
        recycler.setHasFixedSize(true)
        recycler.layoutManager = layoutManager
        adapter = (RemoteDevicesAdapter(devices, object: RecyclerDeviceListener{
            override fun onClick(mBluetoothDevice: MBluetoothDevice, position: Int) {}

        }))

        recycler.adapter = adapter

        hideProgress()

    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }
}
