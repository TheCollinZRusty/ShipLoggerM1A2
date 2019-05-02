package org.wit.shiploggerm1a2.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.shiploggerm1a2.ShipLoggerStore

var lastId = 0L

    internal fun getId(): Long {
    return lastId++
}

class ShipLoggerMemStore : ShipLoggerStore, AnkoLogger {

    val shiploggers = ArrayList<ShipLoggerModel>()

    override fun findAll(): List<ShipLoggerModel> {
        return shiploggers
        }

    override fun create(shiplogger: ShipLoggerModel) {
        shiplogger.id = getId()
        shiploggers.add(shiplogger)
        logAll()
        }

    override fun update(shiplogger: ShipLoggerModel) {
    var foundShiplogger: ShipLoggerModel? = shiploggers.find { p -> p.id == shiplogger.id }
    if (foundShiplogger != null) {
        foundShiplogger.title = shiplogger.title
        foundShiplogger.description = shiplogger.description
        foundShiplogger.image = shiplogger.image
        logAll()
        }
        }

    fun logAll() {
        shiploggers.forEach { info("${it}") }
        }
    override fun delete(shiplogger: ShipLoggerModel) {
        shiploggers.remove(shiplogger)
        }
        }
