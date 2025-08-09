package com.example.taskgroup.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CartProduct::class],
    version = 1,
    exportSchema = false
)
abstract class FakeStoreDatabase : RoomDatabase() {
    abstract fun dao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: FakeStoreDatabase? = null

        fun getDatabase(context: Context): FakeStoreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FakeStoreDatabase::class.java,
                    "fake_store_database" // Changed name to match class
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}