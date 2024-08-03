package com.testapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.testapp.data.database.dao.FilmDao
import com.testapp.data.database.entity.FilmEntity

@Database(entities = [FilmEntity::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao
}