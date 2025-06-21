package com.devforge.fitguard.ui.datafill

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devforge.fitguard.R
import com.devforge.fitguard.data.UserEntity
import com.devforge.fitguard.databinding.ActivityLevelFillBinding
import com.devforge.fitguard.ui.MainActivity
import com.devforge.fitguard.ui.splashscreen.StartViewModel
import com.devforge.fitguard.utils.UserViewModelFactory

class LevelFillActivity : AppCompatActivity() {

    private var level: String? = null

    private lateinit var binding: ActivityLevelFillBinding
    private val viewModel by viewModels<StartViewModel> { UserViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLevelFillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        val name = intent.getStringExtra(DataFillActivity.DATA_NAME)
        val date = intent.getStringExtra(DataFillActivity.DATA_DATE)
        val gender = intent.getStringExtra(DataFillActivity.DATA_GENDER)
        val weight = intent.getIntExtra(DataFillActivity.DATA_WEIGHT, 0)
        val height = intent.getIntExtra(DataFillActivity.DATA_HEIGHT, 0)
        binding.genderToggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            level = when (checkedId) {
                binding.btnBeginner.id -> if (isChecked) "Pemula" else null
                binding.btnIntermediate.id -> if (isChecked) "Menengah" else null
                binding.btnAdvanced.id -> if (isChecked) "Lanjutan" else null
                else -> null
            }
            binding.btnSubmit.isEnabled = !level.isNullOrBlank()
        }


        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, DataFillActivity::class.java))
            level = null

        }

        binding.btnSubmit.setOnClickListener {
            viewModel.insert(
                UserEntity(
                    name = name,
                    birthDate = date,
                    gender = gender,
                    bodyWeight = weight,
                    bodyHeight = height,
                    level = level
                )
            )
            Log.d("LevelFillActivity", "Name: $name, Date: $date, Gender: $gender, Weight: $weight, Height: $height, Level: $level")
            startActivity(Intent(this@LevelFillActivity, MainActivity::class.java))
            finish()

        }



    }
}