package org.wit.shiploggerm1a2

import org.wit.shiploggerm1a2.models.ShipLoggerModel

interface ShipLoggerStore {
    fun findAll(): List<ShipLoggerModel>
    fun create(shiplogger: ShipLoggerModel)
    fun update(shiplogger: ShipLoggerModel)
}
