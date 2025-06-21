package com.devforge.fitguard.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.devforge.fitguard.R
import com.devforge.fitguard.databinding.FragmentDashboardBinding
import com.devforge.fitguard.utils.DateHelper
import com.devforge.fitguard.utils.UserViewModelFactory
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    private val dashboardViewModel by viewModels<DashboardViewModel> { UserViewModelFactory.getInstance(requireContext()) }


    private val tipsList = listOf("")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardViewModel.getUser().observe(requireActivity()) {
            val string = String.format(resources.getString(R.string.halo), it.name)
            binding.tvHello.text = string
        }
        populateChart()
        loadCalorieData()
    }

    private fun loadCalorieData() {
        val thisDay = DateHelper.getCurrentDate()
        val dateFormatted = DateHelper.formatDateToIndo(thisDay)

        dashboardViewModel.getAllRecords().observe(requireActivity()) {
            for (i in it) {
                if (i.date == dateFormatted) {
                    binding.cardCalorie.cardValue.text = it[0].calorie.toString()
                } else {
                    binding.cardCalorie.cardValue.text = "0"
                }

            }
        }
    }

    private fun loadTipsData() {
        binding.cardTips.cardTitle.text = "💡Tips"

    }

    private fun populateChart() {
        val defaultEntries = listOf(
            BarEntry(0f, 12f),
            BarEntry(1f, 12f),
            BarEntry(2f,13f),
            BarEntry(3f,14f),
            BarEntry(4f,15f),
            BarEntry(5f,16f),
            BarEntry(6f,17f),
            BarEntry(7f,18f),
        )

        val data = BarDataSet(defaultEntries, "Progress").apply {
            color = resources.getColor(R.color.main_green)

        }

        binding.cardChart.barChart.data = BarData(data)

        val days = arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
        binding.cardChart.barChart.xAxis.apply {
            valueFormatter = IndexAxisValueFormatter(days)
            position = XAxis.XAxisPosition.BOTTOM
            textColor = resources.getColor(R.color.main_black)
        }

        binding.cardChart.barChart.axisLeft.apply {
            textColor = resources.getColor(R.color.main_black)
        }

        data.valueTextColor = resources.getColor(R.color.main_black)
        binding.cardChart.barChart.axisRight.isEnabled = false
        binding.cardChart.barChart.description.isEnabled = false
        binding.cardChart.barChart.legend.isEnabled = false

        binding.cardChart.barChart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}