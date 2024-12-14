package dev.unand.app.demokelasb.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.unand.app.demokelasb.model.Movie

@Database(entities = [Movie::class], version =1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}