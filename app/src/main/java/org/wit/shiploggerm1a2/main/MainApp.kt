    package org.wit.shiploggerm1a2.main

    import android.app.Application
    import org.jetbrains.anko.AnkoLogger
    import org.jetbrains.anko.info
    import org.wit.shiploggerm1a2.ShipLoggerStore
    import org.wit.shiploggerm1a2.models.ShipLoggerJSONStore
    import org.wit.shiploggerm1a2.models.ShipLoggerMemStore

    class MainApp: Application(), AnkoLogger {


        lateinit var shiploggers: ShipLoggerStore

        override fun onCreate() {
            super.onCreate()
            shiploggers = ShipLoggerJSONStore(applicationContext)
    //        shiploggers = ShipLoggerMemStore()
            info("ShipLogger started")
        }
    }