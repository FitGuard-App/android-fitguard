package com.devforge.fitguard.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devforge.fitguard.R
import com.devforge.fitguard.databinding.ActivityWelcomeBinding
import com.devforge.fitguard.ui.MainActivity
import com.devforge.fitguard.ui.datafill.DataFillActivity
import com.devforge.fitguard.ui.splashscreen.StartViewModel
import com.devforge.fitguard.utils.UserViewModelFactory

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private val viewModel by viewModels<StartViewModel> { UserViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()

        binding.btnStart.setOnClickListener {
            navigateUser()
        }
    }

    private fun navigateUser() {
        viewModel.getUser().observe(this) {
            if (it != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, DataFillActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}