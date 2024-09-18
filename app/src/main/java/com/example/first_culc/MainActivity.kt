package com.example.first_culc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.LayoutInflaterCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // оголосили змінні усіх полей вводу
        val H: EditText = findViewById(R.id.editTextText)
        val C: EditText = findViewById(R.id.editTextText1)
        val S: EditText = findViewById(R.id.editTextText2)
        val N: EditText = findViewById(R.id.editTextText3)
        val O: EditText = findViewById(R.id.editTextText4)
        val W: EditText = findViewById(R.id.editTextText5)
        val A: EditText = findViewById(R.id.editTextText6)

        // тут кнопки оголосили
        val btn_rest: ImageButton = findViewById(R.id.imageButton3)
        val btn_result: ImageButton = findViewById(R.id.imageButton2)

        // фуенція для очистки полів вводу
        btn_rest.setOnClickListener {
            H.setText("")
            C.setText("")
            S.setText("")
            N.setText("")
            O.setText("")
            W.setText("")
            A.setText("")
        }

        // функція розрахунків
        btn_result.setOnClickListener {
            val HVal = H.text.toString().toDoubleOrNull() ?: 0.0
            val CVal = C.text.toString().toDoubleOrNull() ?: 0.0
            val SVal = S.text.toString().toDoubleOrNull() ?: 0.0
            val NVal = N.text.toString().toDoubleOrNull() ?: 0.0
            val OVal = O.text.toString().toDoubleOrNull() ?: 0.0
            val WVal = W.text.toString().toDoubleOrNull() ?: 0.0
            val AVal = A.text.toString().toDoubleOrNull() ?: 0.0

            // перевірка даних
            if (HVal < 0 || CVal < 0 || SVal < 0 || NVal < 0 || OVal < 0 || WVal < 0 || AVal < 0) {
                Toast.makeText(this, "Введіть коректні значення", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val koef_sukh = 100 / (100 - WVal)  // Коефіцієнт переходу до сухої маси
            val koef_gor = 100 / (100 - WVal - AVal)  // Коефіцієнт переходу до горючої маси

            // Склад сухої маси палива
            val H_sukh = HVal * koef_sukh
            val C_sukh = CVal * koef_sukh
            val S_sukh = SVal * koef_sukh
            val N_sukh = NVal * koef_sukh
            val O_sukh = OVal * koef_sukh
            val A_sukh = AVal * koef_sukh

            // Склад горючої маси палива
            val H_gor = HVal * koef_gor
            val C_gor = CVal * koef_gor
            val S_gor = SVal * koef_gor
            val N_gor = NVal * koef_gor
            val O_gor = OVal * koef_gor

            // Нижча теплота згоряння для робочої маси
            val Q_rm = (339 * CVal + 1030 * HVal - 108.8 * (OVal - SVal) - 25 * WVal) / 1000

            // Нижча теплота згоряння для сухої маси
            val Q_sukh = (Q_rm  + 0.025 * WVal)*(100/(100-WVal))

            // Нижча теплота згоряння для горючої маси
            val Q_gor = (Q_rm  + 0.025 * WVal)*(100/(100-WVal-AVal))


            val resultText = """
                Коефіцієнт переходу від робочої до сухої маси: ${"%.2f".format(koef_sukh)}
                Коефіцієнт переходу від робочої до горючої маси: ${"%.2f".format(koef_gor)}

                Склад сухої маси:
                H = ${"%.2f".format(H_sukh)}%
                C = ${"%.2f".format(C_sukh)}%
                S = ${"%.2f".format(S_sukh)}%
                N = ${"%.2f".format(N_sukh)}%
                O = ${"%.2f".format(O_sukh)}%
                A = ${"%.2f".format(A_sukh)}%

                Склад горючої маси:
                H = ${"%.2f".format(H_gor)}%
                C = ${"%.2f".format(C_gor)}%
                S = ${"%.2f".format(S_gor)}%
                N = ${"%.2f".format(N_gor)}%
                O = ${"%.2f".format(O_gor)}%

                Нижча теплота згоряння для робочої маси: ${"%.2f".format(Q_rm)} МДж/кг
                Нижча теплота згоряння для сухої маси: ${"%.2f".format(Q_sukh)} МДж/кг
                Нижча теплота згоряння для горючої маси: ${"%.2f".format(Q_gor)} МДж/кг
            """.trimIndent()



            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null)
            val titleView: TextView = dialogView.findViewById(R.id.dialog_title)
            val messageView: TextView = dialogView.findViewById(R.id.dialog_message)

            titleView.text = "Результати розрахунків"
            messageView.text = resultText

            AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("OK", null)
                .show()
        }

        val btn_tsk2:Button = findViewById(R.id.button_task2)

        btn_tsk2.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}