package com.devforge.fitguard.ui.datafill

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devforge.fitguard.R
import com.devforge.fitguard.databinding.ActivityDataFillBinding
import com.devforge.fitguard.databinding.ActivityWelcomeBinding

class DataFillActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataFillBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDataFillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()

//        binding.btnStart.setOnClickListener {
//            navigateUser()
//        }
    }
}