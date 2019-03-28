package org.wit.shiploggerm1a2.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize


data class ShipLoggerModel (var id: Long =0,
                            var title: String = "",
                            var description: String = "",
                            var image: String = "") : Parcelable {
}