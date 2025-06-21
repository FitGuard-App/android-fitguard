package com.devforge.fitguard.ui.datafill

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.devforge.fitguard.R
import com.devforge.fitguard.databinding.ActivityDataFillBinding
import com.devforge.fitguard.ui.welcome.WelcomeActivity
import java.util.Calendar
import androidx.core.widget.doAfterTextChanged

class DataFillActivity : AppCompatActivity() {

    private var name: String? = null
    private var date: String? = null
    private var gender: String? = null
    private var weight: Int = 0
    private var height: Int = 0

    private lateinit var binding: ActivityDataFillBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDataFillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Date picker setup
        binding.inputDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = String.format("%d/%d/%d", selectedDay, selectedMonth + 1, selectedYear)
                binding.inputDate.setText(selectedDate)
            }, year, month, day)

            datePickerDialog.show()
        }

        binding.genderToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            gender = if (isChecked) {
                when (checkedId) {
                    R.id.btn_male -> "Pria"
                    R.id.btn_female -> "Wanita"
                    else -> null
                }
            } else null
            updateNextButtonState()
        }

        // Real-time validation
        binding.inputName.doAfterTextChanged { updateNextButtonState() }
        binding.inputDate.doAfterTextChanged { updateNextButtonState() }
        binding.inputHeight.doAfterTextChanged { updateNextButtonState() }
        binding.inputWeight.doAfterTextChanged { updateNextButtonState() }

        // Next button click
        binding.btnNext.setOnClickListener {
            name = binding.inputName.text.toString()
            date = binding.inputDate.text.toString()
            height = binding.inputHeight.text.toString().toIntOrNull() ?: 0
            weight = binding.inputWeight.text.toString().toIntOrNull() ?: 0

            if (validateForm()) {
                val intent = Intent(this, LevelFillActivity::class.java).apply {
                    putExtra(DATA_NAME, name)
                    putExtra(DATA_DATE, date)
                    putExtra(DATA_GENDER, gender)
                    putExtra(DATA_WEIGHT, weight)
                    putExtra(DATA_HEIGHT, height)
                }
                Log.d("DataFillActivity", "Name: $name, Date: $date, Gender: $gender, Weight: $weight, Height: $height")
                startActivity(intent)
                finish()
            }
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            name = null
            date = null
            finish()
        }
    }

    private fun validateForm(): Boolean {
        val nameValid = !binding.inputName.text.isNullOrBlank()
        val dateValid = !binding.inputDate.text.isNullOrBlank()
        val heightValid = !binding.inputHeight.text.isNullOrBlank() &&
                (binding.inputHeight.text.toString().toIntOrNull() ?: 0) > 0
        val weightValid = !binding.inputWeight.text.isNullOrBlank() &&
                (binding.inputWeight.text.toString().toIntOrNull() ?: 0) > 0
        val genderValid = !gender.isNullOrBlank()

        return nameValid && dateValid && heightValid && weightValid && genderValid
    }

    private fun updateNextButtonState() {
        binding.btnNext.isEnabled = validateForm()
    }

    companion object {
        const val DATA_NAME = "data_name"
        const val DATA_DATE = "data_date"
        const val DATA_GENDER = "data_gender"
        const val DATA_WEIGHT = "data_weight"
        const val DATA_HEIGHT = "data_height"
    }
}
