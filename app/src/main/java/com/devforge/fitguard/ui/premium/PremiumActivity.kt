package com.devforge.fitguard.ui.premium

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devforge.fitguard.R
import com.devforge.fitguard.databinding.ActivityPremiumBinding

class PremiumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPremiumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPremiumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.subscribeButton.setOnClickListener {
            Toast.makeText(this, "Belum tersedia saat ini", Toast.LENGTH_SHORT).show()
        }


    }
}