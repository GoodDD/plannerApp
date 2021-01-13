package com.example.planner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.planner.data.PlanDatabase
import com.example.planner.model.Plan
import com.example.planner.repository.PlanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Plan>>
    private val repository: PlanRepository

    init {
        val planDao = PlanDatabase.getDatabase(application).planDao()
        repository = PlanRepository(planDao)
        readAllData = planDao.readAllData()
    }

    fun addPlan(plan: Plan){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlan(plan)
        }
    }

    fun updatePlan(plan: Plan){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePlan(plan)
        }
    }

    fun deletePlan(plan: Plan){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePlan(plan)
        }
    }
}