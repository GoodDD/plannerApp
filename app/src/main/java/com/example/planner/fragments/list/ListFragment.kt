package com.example.planner.fragments.list

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planner.R
import com.example.planner.model.Plan
import com.example.planner.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment(), ListAdapter.ClickListeners {

    private var multiMode: Boolean = false
    private var selected = ArrayList<Plan>()
    private lateinit var deleteButton: MenuItem
    private lateinit var recyclerView: RecyclerView
    private lateinit var mPlanViewModel: PlanViewModel
    private lateinit var actionBar: ActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_list, container, false)

        actionBar = (activity as AppCompatActivity).supportActionBar!!

        // RecyclerView
        recyclerView = root.plan_list
        val adapter = ListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // userViewModel
        mPlanViewModel = ViewModelProvider(this).get(PlanViewModel::class.java)
        mPlanViewModel.readAllData.observe(viewLifecycleOwner, Observer { plan ->
            adapter.setData(plan)
        })

        root.add_btn_fab.setOnClickListener {
            selected.removeAll(selected)
            multiCheck(multiMode)
            selectedItemsCheck(selected)
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)
        return root
    }

    override fun onPlanClick(plan: Plan, view: View) {

        if (multiMode){

            if (selected.contains(plan)){
                selected.remove(plan)
                view.setBackgroundResource(R.drawable.custom_list_item)
            } else {
                selected.add(plan)
                view.setBackgroundResource(R.drawable.custom_list_item2)
            }

            multiCheck(multiMode)
            selectedItemsCheck(selected)

        } else {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(plan)
            findNavController().navigate(action)
            return
        }
    }

    override fun onPlanLongClick(plan: Plan, view: View) {

        multiMode = true

        if (selected.contains(plan)){
            selected.remove(plan)
            view.setBackgroundResource(R.drawable.custom_list_item)
        } else {
            selected.add(plan)
            view.setBackgroundResource(R.drawable.custom_list_item2)
        }

        multiCheck(multiMode)
        selectedItemsCheck(selected)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)

        deleteButton = menu.findItem(R.id.menuList_delete)
        deleteButton.isVisible = false

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){

            android.R.id.home -> {

                multiMode = false

                for (i in 0 until recyclerView.childCount) {
                    var view = recyclerView.getChildViewHolder(recyclerView.getChildAt(i))
                    view.itemView.setBackgroundResource(R.drawable.custom_list_item)
                }

                selected.removeAll(selected)
                multiCheck(multiMode)

                return true
            }

            R.id.menuList_delete -> {

                multiMode = false

                for (i in selected){
                    mPlanViewModel.deletePlan(i)
                }

                for (i in 0 until recyclerView.childCount) {
                    var view = recyclerView.getChildViewHolder(recyclerView.getChildAt(i))
                    view.itemView.setBackgroundResource(R.drawable.custom_list_item)
                }

                selected.removeAll(selected)
                multiCheck(multiMode)

            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun multiCheck(multiMode: Boolean){
        if (multiMode){
            actionBar.setDisplayHomeAsUpEnabled(true)
            deleteButton.isVisible = true
        } else {
            actionBar.setDisplayHomeAsUpEnabled(false)
            deleteButton.isVisible = false
        }
    }

    private fun selectedItemsCheck(selected: ArrayList<Plan>){
        if (selected.size == 0) {
            Log.e(null, "Selected items size: ${selected.size}")
            multiMode = false
            multiCheck(multiMode)
        } else {
            return
        }
    }

}