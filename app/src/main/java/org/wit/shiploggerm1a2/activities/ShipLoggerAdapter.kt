package org.wit.shiploggerm1a2.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_shiplogger.view.*
import org.wit.shiploggerm1a2.R
import org.wit.shiploggerm1a2.helpers.readImageFromPath
import org.wit.shiploggerm1a2.models.ShipLoggerModel


    interface ShipLoggerListener {
        fun onShipLoggerClick(shiplogger: ShipLoggerModel)
    }

    class ShipLoggerAdapter constructor(private var shiploggers: List<ShipLoggerModel>,
                                       private val listener: ShipLoggerListener) : RecyclerView.Adapter<ShipLoggerAdapter.MainHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
            return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_shiplogger, parent, false))
        }

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            val shiplogger = shiploggers[holder.adapterPosition]
            holder.bind(shiplogger, listener)
        }

        override fun getItemCount(): Int = shiploggers.size

        class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(shiplogger: ShipLoggerModel,  listener : ShipLoggerListener) {
                itemView.shipTitleList.text= shiplogger.title
                itemView.shipDescriptionList.text = shiplogger.description
                itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, shiplogger.image))
                itemView.setOnClickListener { listener.onShipLoggerClick(shiplogger) }
            }
        }
    }
