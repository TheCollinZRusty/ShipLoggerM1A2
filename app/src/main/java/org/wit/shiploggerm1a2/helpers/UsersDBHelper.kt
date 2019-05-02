package org.wit.shiploggerm1a2.helpers


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.tutorialkart.sqlitetutorial.DBContract
import org.wit.shiploggerm1a2.models.ShipLoggerModel

import java.util.ArrayList

class UsersDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(user: ShipLoggerModel): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        values.put(DBContract.UserEntry.COLUMN_USER_ID, user.id)
        values.put(DBContract.UserEntry.COLUMN_TITLE, user.title)
        values.put(DBContract.UserEntry.COLUMN_DESCRIPTION, user.description)

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db.insert(DBContract.UserEntry.TABLE_NAME, null, values)

        return true
    }

//    @Throws(SQLiteConstraintException::class)
//    fun deleteUser(id: Long): Boolean {
//        // Gets the data repository in write mode
//        val db = writableDatabase
//        // Define 'where' part of query.
//        val selection = DBContract.UserEntry.COLUMN_USER_ID + " LIKE ?"
//        // Specify arguments in placeholder order.
//        val selectionArgs = arrayOf(id)
//        // Issue SQL statement.
//        db.delete(DBContract.UserEntry.TABLE_NAME, selection, selectionArgs)
//
//        return true
//    }
    //
    fun readShip(id: Long): ArrayList<ShipLoggerModel> {
        val users = ArrayList<ShipLoggerModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME + " WHERE " + DBContract.UserEntry.COLUMN_USER_ID + "='" + id + "'", null)
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var title: String
        var description: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                title = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TITLE))
                description = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DESCRIPTION))

                users.add(ShipLoggerModel(id, title, description ))
                cursor.moveToNext()
            }
        }
        return users
    }


    //Reads from the SQL Database
    fun readAllUsers(): ArrayList<ShipLoggerModel> {
        val users = ArrayList<ShipLoggerModel>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.UserEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var id: Long
        var title: String
        var description: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = cursor.getLong(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_ID))
                title = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_TITLE))
                description = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_DESCRIPTION))

                users.add(ShipLoggerModel(id, title, description))
                cursor.moveToNext()
            }
        }
        return users
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "FeedReader.db"

        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME + " (" +
                    DBContract.UserEntry.COLUMN_USER_ID + " TEXT PRIMARY KEY," +
                    DBContract.UserEntry.COLUMN_TITLE + " TEXT," +
                    DBContract.UserEntry.COLUMN_DESCRIPTION + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME
    }

}

private fun SQLiteDatabase.delete(tablE_NAME: String, selection: Long, selectionArgs: Array<Long>) {

}


//Incomplete Unsure of how to add Database functionality to the Functions in ShipLoggerActivity etc.

//TODO: Add the Database functionality to the ShipLoggerActivity