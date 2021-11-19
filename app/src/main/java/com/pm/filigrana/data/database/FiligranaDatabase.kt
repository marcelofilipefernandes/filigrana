package com.pm.filigrana.data.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pm.filigrana.data.dao.ProductDao
import com.pm.filigrana.data.entities.Product

@Database(entities = [Product :: class],
    version = 2,
    exportSchema = true,
    autoMigrations = [AutoMigration (from = 1, to = 2)])
abstract class FiligranaDatabase : RoomDatabase() {

    abstract fun productDao() : ProductDao
    companion object {
        @Volatile
        private var INSTANCE: FiligranaDatabase? = null

        fun getDatabase(context: Context): FiligranaDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FiligranaDatabase::class.java,
                    "filigrana_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}