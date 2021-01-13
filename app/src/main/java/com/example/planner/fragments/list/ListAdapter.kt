package com.example.planner.fragments.list

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.example.planner.R
import com.example.planner.model.Plan
import kotlinx.android.synthetic.main.plan_row.view.*
import java.nio.channels.SelectionKey

class ListAdapter(private val listener: ClickListeners): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var planList = emptyList<Plan>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun getItemCount(): Int {
        return planList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.plan_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = planList[position]
        holder.itemView.planRowTitle_tw.text = currentItem.title
        holder.itemView.planRowDesc_tw.text = currentItem.description
        holder.itemView.planRowDate_tw.text = currentItem.date


        holder.itemView.setOnClickListener {
            listener.onPlanClick(currentItem, it)
        }

        holder.itemView.setOnLongClickListener {
            listener.onPlanLongClick(currentItem, it)
            true
        }
    }

    fun setData(data: List<Plan>){
        this.planList = data
        notifyDataSetChanged()
    }

    public interface ClickListeners {
        fun onPlanClick(plan: Plan, view: View)
        fun onPlanLongClick(plan: Plan, view: View)
    }
}