package com.example.planner.repository

import androidx.lifecycle.LiveData
import com.example.planner.data.PlanDao
import com.example.planner.model.Plan

class PlanRepository(private val planDao: PlanDao) {

    val readAllData: LiveData<List<Plan>> = planDao.readAllData()

    suspend fun addPlan(plan: Plan) {
        planDao.addPlan(plan)
    }

    suspend fun updatePlan(plan: Plan) {
        planDao.updatePlan(plan)
    }

    suspend fun deletePlan(plan: Plan) {
        planDao.deletePlan(plan)
    }

}