package org.wit.shiploggerm1a2.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_shiplogger_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.shiploggerm1a2.R
import org.wit.shiploggerm1a2.main.MainApp
import org.wit.shiploggerm1a2.models.ShipLoggerModel
import org.wit.shiploggerm1a2.activities.ShipLoggerListener
import org.wit.shiploggerm1a2.activities.ShipLoggerAdapter



class ShipLoggerListActivity : AppCompatActivity(), ShipLoggerListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shiplogger_list)
        app = application as MainApp

        //layout and populate for display
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ShipLoggerAdapter(app.shiploggers.findAll(), this)
        loadShips()

        //enable action bar and set title
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<ShipLoggerActivity>(0)
        }
        when (item?.itemId) {
            R.id.menu_camera -> startActivityForResult<CameraActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onShipLoggerClick(shiplogger: ShipLoggerModel) {
        startActivityForResult(intentFor<ShipLoggerActivity>().putExtra("ship_edit", shiplogger), 0)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView is a widget in activity_placemark_list.xml
        recyclerView.adapter?.notifyDataSetChanged()
        loadShips()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadShips() {
        showShips(app.shiploggers.findAll())
    }

    fun showShips (shiploggers: List<ShipLoggerModel>) {
        recyclerView.adapter = ShipLoggerAdapter(shiploggers, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

}