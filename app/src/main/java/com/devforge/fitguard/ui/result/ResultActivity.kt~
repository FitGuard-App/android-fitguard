package com.devforge.fitguard.ui.result

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.devforge.fitguard.R
import com.devforge.fitguard.data.RecordEntity
import com.devforge.fitguard.databinding.ActivityResultBinding
import com.devforge.fitguard.utils.DateHelper
import com.devforge.fitguard.utils.UserViewModelFactory

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        supportActionBar?.hide()

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = UserViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[ResultViewModel::class.java]

        val kalori = intent.getIntExtra("kalori", 0)
        val durasi = intent.getLongExtra("durasi", 0L)
        val repetisi = intent.getIntExtra("repetisi", 0)
        val total = intent.getIntExtra("total", 0)

        binding.textKalori.text = kalori.toString()
        binding.textDurasi.text = durasi.toString()
        binding.textRepetisi.text = repetisi.toString()
        binding.textTotal.text = total.toString()

        binding.btnSimpan.setOnClickListener{
            viewModel.insert(RecordEntity(calorie = kalori, totalDuration = durasi.toInt(), repetition = repetisi, exerciseCount = total, date = DateHelper.getCurrentDate()))

            finish()
        }
    }
}