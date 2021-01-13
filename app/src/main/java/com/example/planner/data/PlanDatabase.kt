package com.example.planner.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.planner.model.Plan


@Database(entities = [Plan::class], version = 1, exportSchema = false)
abstract class PlanDatabase: RoomDatabase() {

    abstract fun planDao(): PlanDao

    companion object {
        @Volatile
        private var INSTANCE: PlanDatabase? = null

        fun getDatabase(context: Context): PlanDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlanDatabase::class.java,
                    "plan_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}