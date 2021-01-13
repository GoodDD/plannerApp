package com.example.planner.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.planner.R
import com.example.planner.model.Plan
import com.example.planner.viewmodel.PlanViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mPlanViewModel: PlanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_update, container, false)

        mPlanViewModel = ViewModelProvider(this).get(PlanViewModel::class.java)

        root.updateTitle_et.setText(args.currentPlan.title)
        root.updateDescription_et.setText(args.currentPlan.description)

        setHasOptionsMenu(true)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.menuUpdate_update -> {
                updatePlan()
            }

            R.id.menuUpdate_delete -> {
                deletePlan()
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun updatePlan() {
        val title = updateTitle_et.text.toString()
        val description = updateDescription_et.text.toString()

        val currentDateTime = LocalDateTime.now()
        val formattedCurrentDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

        val plan = Plan(args.currentPlan.id, title, description, formattedCurrentDateTime)
        mPlanViewModel.updatePlan(plan)
        Toast.makeText(requireContext(), "Plan updated!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun deletePlan() {
        mPlanViewModel.deletePlan(args.currentPlan)
        Toast.makeText(requireContext(), "Plan deleted!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }
}