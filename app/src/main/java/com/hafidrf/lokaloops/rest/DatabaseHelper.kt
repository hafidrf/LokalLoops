package com.hafidrf.lokaloops.rest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.hafidrf.lokaloops.models.ListItem
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context)
    : ManagedSQLiteOpenHelper(ctx, "Foodlups.db", null, 1)  {

    companion object {

        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper{

            if (instance == null){
                instance = DatabaseHelper(ctx.applicationContext)
            }

            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            com.hafidrf.lokaloops.models.ListItem.TB_ITEM, true,
            com.hafidrf.lokaloops.models.ListItem.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            com.hafidrf.lokaloops.models.ListItem.NAME to TEXT,
            com.hafidrf.lokaloops.models.ListItem.PRICE to TEXT,
            com.hafidrf.lokaloops.models.ListItem.FOTO to TEXT,
            com.hafidrf.lokaloops.models.ListItem.BARCODE to TEXT,
            com.hafidrf.lokaloops.models.ListItem.QUANTITY to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(com.hafidrf.lokaloops.models.ListItem.TB_ITEM, true)
    }
}

val Context.database : DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)