package org.wit.shiploggerm1a2.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.shiploggerm1a2.ShipLoggerStore
import org.wit.shiploggerm1a2.helpers.*
import java.util.*

    val JSON_FILE = "shiplogger.json"
    val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
    val listType = object : TypeToken<java.util.ArrayList<ShipLoggerModel>>() {}.type


fun generateRandomId(): Long {
    return Random().nextLong()
}

class ShipLoggerJSONStore : ShipLoggerStore, AnkoLogger {

    val context: Context
    var shiploggers = mutableListOf<ShipLoggerModel>()

         constructor (context: Context) {
         this.context = context
         if (exists(context, JSON_FILE)) {
    deserialize()
        }
        }

    override fun findAll(): MutableList<ShipLoggerModel> {
    return shiploggers
        }

    override fun create(shiplogger: ShipLoggerModel) {
        shiplogger.id = generateRandomId()
        shiploggers.add(shiplogger)
        serialize()
        }


    override fun update(shiplogger: ShipLoggerModel) {
    val shiploggersList = findAll() as ArrayList<ShipLoggerModel>
    var foundShiplogger: ShipLoggerModel? = shiploggersList.find { p -> p.id == shiplogger.id }
    if (foundShiplogger != null) {
        foundShiplogger.title = shiplogger.title
        foundShiplogger.description = shiplogger.description
        foundShiplogger.image = shiplogger.image
        }
        serialize()
        }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(shiploggers, listType)
        write(context, JSON_FILE, jsonString)
        }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        shiploggers = Gson().fromJson(jsonString, listType)
        }
    override fun delete(shiplogger: ShipLoggerModel) {
        shiploggers.remove(shiplogger)
        serialize()
        }
        }

