package com.devforge.fitguard.utils

class FormAnalyzerHelper {
    var message : String? = null

    fun runModel(jointAngles: List<Float>, categories: String, repetisi: Int, level: String): String? {
        //push up
        // * Overextension (siku terlalu turun (<30 derajat))
        // * Repetisi terlalu banyak
        // * tangan terlalu lebar


        //sit up
        // * repetisi terlalu banyak

        //squat
        // * badan terlalu condong

        // * repetisi terlalu banyak

        when(level) {
            "Pemula" -> {
                if(repetisi > 15) {
                    message = "Repetisi terlalu banyak"
                }
            }

            "Menengah" -> {
                if(repetisi > 30) {
                    message = "Repetisi terlalu banyak"
                }
            }

            "Lanjutan" -> {
                if(repetisi > 50) {
                    message = "Repetisi terlalu banyak"
                }
            }
        }

        when(categories) {
            "push up" -> {
                if (jointAngles[7] < 40) {
                    message = "Overextension (siku terlalu turun (<30 derajat))"
                }

                else if (jointAngles[6] > 30) {
                    message = "tangan terlalu lebar"
                }
            }

            "sit up" -> {

            }

            "squat" -> {
                if (jointAngles[9] < 40) {
                    message = "Badan terlalu condong"
                }
            }
        }

        if(message == null) {
            message = "Tidak ada kesalahan"
        }

        return message
    }
}