package com.example.movietrip.data

import android.content.Context
import androidx.room.*

@TypeConverters(DbTypeConverters::class)
@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieListDao(): MovieListDao
    abstract fun movieDetailDao(): MovieDetailDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getDatabase(context: Context) = instance
                ?: synchronized(this) {
                    Room.databaseBuilder(
                            context.applicationContext,
                            MovieDatabase::class.java,
                            "movie_database"
                    ).build().also { instance = it }
                }
    }
}