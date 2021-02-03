package com.demo.homework5

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.google.android.material.textfield.TextInputLayout

class AddCarActivity : AppCompatActivity() {

    private lateinit var ownerName: TextInputLayout
    private lateinit var producer: TextInputLayout
    private lateinit var model: TextInputLayout
    private lateinit var number: TextInputLayout
    private lateinit var back: AppCompatImageButton
    private lateinit var save: AppCompatImageButton
    private lateinit var dataBaseCar: DataBaseCar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)

        dataBaseCar = DataBaseCar.init(this)


        ownerName = findViewById(R.id.ownerName)
        producer = findViewById(R.id.producer)
        model = findViewById(R.id.model)
        number = findViewById(R.id.number)
        back = findViewById<AppCompatImageButton>(R.id.back).apply {
            setOnClickListener {
                finish()
            }
        }
        save = findViewById<AppCompatImageButton>(R.id.save).apply {
            setOnClickListener {
                if (ownerName.editText?.text.toString().isNotEmpty() && producer.editText?.text.toString().isNotEmpty() &&
                        model.editText?.text.toString().isNotEmpty() && number.editText?.text.toString().isNotEmpty()) {
                    val car = Car(
                            ownerNameCar = ownerName.editText?.text.toString(),
                            producerCar = producer.editText?.text.toString(),
                            modelCar = model.editText?.text.toString(),
                            numberCar = number.editText?.text.toString())

                    dataBaseCar.getCarDao().insertCar(car)
                    val intent = Intent(this@AddCarActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(context, R.string.note, Toast.LENGTH_SHORT).show()
                }

            }


        }

    }
}