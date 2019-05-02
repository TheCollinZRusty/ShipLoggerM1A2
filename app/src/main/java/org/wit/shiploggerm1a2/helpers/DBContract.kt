package com.tutorialkart.sqlitetutorial

import android.provider.BaseColumns

object DBContract {

    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "Ships"
            val COLUMN_USER_ID = "id"
            val COLUMN_TITLE = "title"
            val COLUMN_DESCRIPTION = "description"
        }
    }
}