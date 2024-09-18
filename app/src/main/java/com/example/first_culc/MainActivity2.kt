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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // оголосили змінні усіх полей вводу
        val H: EditText = findViewById(R.id.editTextText)
        val C: EditText = findViewById(R.id.editTextText1)
        val S: EditText = findViewById(R.id.editTextText2)
        val Q: EditText = findViewById(R.id.editTextText3)
        val O: EditText = findViewById(R.id.editTextText4)
        val W: EditText = findViewById(R.id.editTextText5)
        val A: EditText = findViewById(R.id.editTextText6)
        val V: EditText = findViewById(R.id.editTextText7)


        // тут кнопки оголосили
        val btn_rest: ImageButton = findViewById(R.id.imageButton3)
        val btn_result: ImageButton = findViewById(R.id.imageButton2)

        // фуенція для очистки полів вводу
        btn_rest.setOnClickListener {
            H.setText("")
            C.setText("")
            S.setText("")
            Q.setText("")
            O.setText("")
            W.setText("")
            A.setText("")
            V.setText("")

        }

        // функція розрахунків
        btn_result.setOnClickListener {
            val HVal = H.text.toString().toDoubleOrNull() ?: 0.0
            val CVal = C.text.toString().toDoubleOrNull() ?: 0.0
            val SVal = S.text.toString().toDoubleOrNull() ?: 0.0
            val QVal = Q.text.toString().toDoubleOrNull() ?: 0.0
            val OVal = O.text.toString().toDoubleOrNull() ?: 0.0
            val WVal = W.text.toString().toDoubleOrNull() ?: 0.0
            val AVal = A.text.toString().toDoubleOrNull() ?: 0.0
            val VVal = V.text.toString().toDoubleOrNull() ?: 0.0

            // перевірка даних
            if (HVal < 0 || CVal < 0 || SVal < 0 || QVal < 0 || OVal < 0 || WVal < 0 || AVal < 0 || VVal < 0) {
                Toast.makeText(this, "Введіть коректні значення", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Формула для розрахунку нижчої теплоти згоряння на робочу масу
            val Q_wm = (QVal * (100 - WVal - AVal)) / 100

            val HValс = HVal * (100-WVal - AVal)/100
            val CValс = CVal * (100-WVal - AVal)/100
            val SValс = SVal * (100-WVal - AVal)/100
            val OValс = OVal * (100-WVal - AVal)/100
            val AValс = AVal * (100-WVal)/100
            val VValс = VVal * (100-WVal)/100
            // Виведення результату
            val resultText = """
                
                Вуглець: ${"%.2f".format(CValс)}
                Водень: ${"%.2f".format(HValс)}
                Кисень: ${"%.2f".format(OValс)}
                Сірка: ${"%.2f".format(SValс)}
                Зола: ${"%.2f".format(AValс)}
                Нижча теплота згоряння на робочу масу: ${"%.2f".format(Q_wm)} МДж/кг
                Вміст ванадію: ${"%.2f".format(VValс)} мг/кг
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
        val btn_tsk2: Button = findViewById(R.id.button_task2)

        btn_tsk2.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}