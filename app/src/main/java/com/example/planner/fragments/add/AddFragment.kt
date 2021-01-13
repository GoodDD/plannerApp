package com.example.planner.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.planner.R
import com.example.planner.model.Plan
import com.example.planner.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddFragment : Fragment() {

    private lateinit var mPlanViewModel: PlanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_add, container, false)

        mPlanViewModel = ViewModelProvider(this).get(PlanViewModel::class.java)

        setHasOptionsMenu(true)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menuAdd_add){
            insertDataToDatabase()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDatabase(){
        val title = addTitle_et.text.toString()
        val description = addDescription_et.text.toString()

        val currentDateTime = LocalDateTime.now()
        val formattedCurrentDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

        val plan = Plan(0, title, description, formattedCurrentDateTime)
        mPlanViewModel.addPlan(plan)

        Toast.makeText(requireContext(), "Plan added!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }
}