package com.example.planner.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "plan_table")
data class Plan(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val title: String,
        val description: String,
        val date: String
): Parcelable
