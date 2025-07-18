package com.devforge.fitguard.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devforge.fitguard.adapter.HistoryAdapter
import com.devforge.fitguard.databinding.FragmentHistoryBinding
import com.devforge.fitguard.utils.UserViewModelFactory

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel : HistoryViewModel
    private lateinit var adapter : HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val factory = UserViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[HistoryViewModel::class.java]

        adapter = HistoryAdapter()
        binding.rvHistory.adapter = adapter
        binding.rvHistory.layoutManager = LinearLayoutManager(requireActivity())

        viewModel.getAllRecords().observe(viewLifecycleOwner) { activityList ->
            if (activityList.isNullOrEmpty()) {
                adapter.setData(emptyList())
            } else {
                adapter.setData(activityList)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}