package org.wit.shiploggerm1a2.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_shiplogger_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
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

        //Layout and Adapter
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ShipLoggerAdapter(app.shiploggers.findAll(), this)
        loadShips()

        //Title and Action bar support
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    //Menu Inflater creates the Options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //Loads Menu Options
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<ShipLoggerActivity>(0)
        }
        when (item?.itemId) {
            R.id.menu_camera -> startActivityForResult<CameraActivity>(0)
        }
        when (item?.itemId) {
            R.id.menu_map -> startActivity<MapActivity>()
// startActivityForResult<MapActivity>(0)
//        }
        }
        return super.onOptionsItemSelected(item)
    }
    //Allows Editing of a ship
    override fun onShipLoggerClick(shiplogger: ShipLoggerModel) {
        startActivityForResult(intentFor<ShipLoggerActivity>().putExtra("ship_edit", shiplogger), 0)
    }
    //Loads ships when Activity starts
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        recyclerView.adapter?.notifyDataSetChanged()
        loadShips()
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun loadShips() {
        showShips(app.shiploggers.findAll())
    }
    //Loads the recyclerview/Displays Ships
    fun showShips (shiploggers: List<ShipLoggerModel>) {
        recyclerView.adapter = ShipLoggerAdapter(shiploggers, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
    }