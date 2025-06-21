package com.devforge.fitguard.ui.datafill

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devforge.fitguard.R
import com.devforge.fitguard.databinding.ActivityDataFillBinding
import com.devforge.fitguard.databinding.ActivityWelcomeBinding
import com.devforge.fitguard.ui.splashscreen.StartViewModel
import com.devforge.fitguard.ui.welcome.WelcomeActivity
import com.devforge.fitguard.utils.UserViewModelFactory
import java.util.Calendar

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()

        binding.inputDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(this, { _, year, month, day ->
                    val selectedDate = String.format("%d/%d/%d", day, month + 1, year)
                    binding.inputDate.setText(selectedDate)

                }, year, month, day)

            datePickerDialog.show()
        }
        binding.genderToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btn_male -> {
                        gender = "Pria"
                    }

                    R.id.btn_female -> {
                        gender = "Wanita"
                    }
                }
            }
        }



                binding.btnNext.setOnClickListener {
                    name = binding.inputName.text.toString()
                    date = binding.inputDate.text.toString()
                    height = binding.inputHeight.text.toString().toInt()
                    weight = binding.inputWeight.text.toString().toInt()



                    if (!name.isNullOrBlank() && !date.isNullOrBlank() && !gender.isNullOrBlank() && weight > 0 && height > 0) {
                        val intent = Intent(this, LevelFillActivity::class.java)
                        intent.putExtra(DATA_NAME, name)
                        intent.putExtra(DATA_DATE, date)
                        intent.putExtra(DATA_GENDER, gender)
                        intent.putExtra(DATA_WEIGHT, weight)
                        intent.putExtra(DATA_HEIGHT, height)
                        startActivity(intent)
                        Log.d("DataFillActivity", "Name: $name, Date: $date, Gender: $gender, Weight: $weight, Height: $height")
                        finish()
                }
        }



        supportActionBar?.hide()

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            name = null
            date = null
            finish()

        }

//        binding.btnStart.setOnClickListener {
//            navigateUser()
//        }
    }

    companion object {
        const val DATA_NAME = "data_name"
        const val DATA_DATE = "data_date"
        const val DATA_GENDER = "data_gender"
        const val DATA_WEIGHT = "data_weight"
        const val DATA_HEIGHT = "data_height"
    }
}