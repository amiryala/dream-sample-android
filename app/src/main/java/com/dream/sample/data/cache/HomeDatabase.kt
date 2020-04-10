package com.dream.sample.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dream.sample.data.model.Home

//@Database(entities = [Home::class], version = 1)
abstract class HomeDatabase : RoomDatabase() {
    abstract fun homeDao() : HomeDao
}