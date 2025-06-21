package com.devforge.fitguard.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
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


    private val tipsList = listOf(
        "Lakukan pemanasan 5–10 menit untuk mempersiapkan otot dan sendi.",
        "Pakai pakaian olahraga yang nyaman agar pergerakan tidak terganggu.",
        "Pastikan ruang latihan aman, tidak licin, cukup ruang, dan bebas hambatan.",
        "Gunakan alas atau matras saat latihan di lantai untuk mencegah cedera lutut dan punggung.",
        "Minum air secukupnya sebelum, saat, dan sesudah latihan."
    )

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
        loadTipsData()
        loadDurationData()
    }

    private fun loadCalorieData() {
        val todayString = DateHelper.formatDateToIndo(DateHelper.getCurrentDate())

        dashboardViewModel.getAllRecords().observe(viewLifecycleOwner) { records ->
            val calorieToday = records.filter {
                it.date?.let { date ->
                    DateHelper.formatDateToIndo(date)
                } == todayString
            }.sumOf { it.calorie }

            Log.d("Calorie", calorieToday.toString())
            binding.cardCalorie.cardValue.text = calorieToday.toString()
        }
    }


    private fun loadTipsData() {
        binding.cardTips.cardTitle.text = "💡Tips"
        binding.cardTips.cardValue.text = tipsList.random()
        binding.cardTips.cardValue.textSize = 12f
        binding.cardTips.cardUnit.text = ""

    }

    private fun loadDurationData() {
        binding.cardTime.cardTitle.text = "⌚️ Waktu"
        val dateFormatted = DateHelper.formatDateToIndo(DateHelper.getCurrentDate())

        dashboardViewModel.getAllRecords().observe(requireActivity()) { record ->
            val durationToday = record.filter { it.date?.let { date ->
                DateHelper.formatDateToIndo(
                    date
                )
            } == dateFormatted }.sumOf { it.totalDuration }
            binding.cardTime.cardValue.text = durationToday.toString()
            binding.cardTime.cardUnit.text = "Detik"



        }
    }

    private fun populateChart() {
        dashboardViewModel.getAllRecords().observe(requireActivity()) { records ->
//            for ((i, day) in days.withIndex()) {
//                Log.d("DAYS", "${i}, ${day}")
//                entries = it.mapIndexed { _, record ->
//                    BarEntry(i.toFloat(), record.repetition.toFloat())
//                }
//            }
            val grouped = records
                .filter { it.date != null }
                .groupBy { DateHelper.formatDateToIndo(it.date!!) }

// Create ordered labels and bar entries
            val labels = mutableListOf<String>()
            val entries = mutableListOf<BarEntry>()

            grouped.entries.forEachIndexed { index, entry ->
                val dateLabel = entry.key // formatted date
                val totalRepetition = entry.value.sumOf { it.repetition }

                labels.add(dateLabel)
                entries.add(BarEntry(index.toFloat(), totalRepetition.toFloat()))
            }

//            val labels = it.map { r -> DateHelper.formatDateToIndo(r.date!!) }


            val data = BarDataSet(entries, "Progress").apply {
                valueTextColor = resources.getColor(R.color.main_black)
                color = resources.getColor(R.color.main_green)

            }
            binding.cardChart.barChart.data = BarData(data)

//            val days = arrayOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
            binding.cardChart.barChart.xAxis.apply {
//                valueFormatter = IndexAxisValueFormatter(days)
                valueFormatter = IndexAxisValueFormatter(labels)
                granularity = 1f
                setLabelCount(labels.size)
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
//        val defaultEntries = listOf(
//            BarEntry(0f, 12f),
//            BarEntry(1f, 12f),
//            BarEntry(2f,13f),
//            BarEntry(3f,14f),
//            BarEntry(4f,15f),
//            BarEntry(5f,16f),
//            BarEntry(6f,17f),
//            BarEntry(7f,18f),
//        )


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}