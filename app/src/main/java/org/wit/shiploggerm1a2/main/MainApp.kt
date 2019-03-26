package org.wit.shiploggerm1a2.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

import org.wit.shiploggerm1a2.models.ShipLoggerMemStore

class MainApp: Application(), AnkoLogger {

    //val placemarks = ArrayList<ShipLoggerModel>()
    val shiploggers = ShipLoggerMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Placemark Started")
        //placemarks.add(ShipLoggerModel("One", "About one..."))
        //placemarks.add(ShipLoggerModel("Two", "About two..."))
        //placemarks.add(ShipLoggerModel("Three", "About three..."))
    }
}