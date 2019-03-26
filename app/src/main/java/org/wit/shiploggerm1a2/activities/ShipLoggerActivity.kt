package org.wit.shiploggerm1a2.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_shiplogger.*
import kotlinx.android.synthetic.main.activity_shiplogger_list.*
import kotlinx.android.synthetic.main.card_shiplogger.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.shiploggerm1a2.R
import org.wit.shiploggerm1a2.main.MainApp
import org.wit.shiploggerm1a2.models.ShipLoggerModel


class ShipLoggerActivity : AppCompatActivity(), AnkoLogger {

    var shiplogger = ShipLoggerModel()
    lateinit var app: MainApp
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shiplogger)
        app = application as MainApp

        if (intent.hasExtra("ship_edit"))
        {
            edit = true
            shiplogger = intent.extras.getParcelable<ShipLoggerModel>("ship_edit")
            shipTitle.setText(shiplogger.title)
            shipDescription.setText(shiplogger.description)
            btnAdd.setText(R.string.save_ship)
        }

        btnAdd.setOnClickListener() {
            shiplogger.title = shipTitle.text.toString()
            shiplogger.description = shipDescription.text.toString()
            if (shiplogger.title.isNotEmpty()) {
                app.shiploggers.create(shiplogger.copy())
                toast(R.string.enter_ship_title)
            } else {
                if (edit) {
                    app.shiploggers.update(shiplogger.copy())
                } else {
                    app.shiploggers.create(shiplogger.copy())
                }
            }
            info("add Button Pressed: $shipTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }
        chooseImage.setOnClickListener {
            info ("Select image")
        }

        //Add action bar and set title
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_shiplogger, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel-> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
