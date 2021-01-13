package com.example.planner.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.planner.model.Plan

@Dao
interface PlanDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlan(plan: Plan)

    @Update
    suspend fun updatePlan(plan: Plan)

    @Delete
    suspend fun deletePlan(plan: Plan)

    @Query("SELECT * FROM plan_table")
    fun readAllData(): LiveData<List<Plan>>

}