package com.demo.homework5

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.ArrayList

class CarInfoActivity : AppCompatActivity() {

    private lateinit var ownerName: TextInputLayout
    private lateinit var producer: TextInputLayout
    private lateinit var model: TextInputLayout
    private lateinit var numberCar: TextInputLayout
    private lateinit var save: AppCompatImageButton
    private lateinit var back: AppCompatImageButton
    private lateinit var dataBaseCar: DataBaseCar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_info)



        ownerName = findViewById(R.id.ownerName)
        producer = findViewById(R.id.producer)
        model = findViewById(R.id.model)
        numberCar = findViewById(R.id.numberCar)

        dataBaseCar = DataBaseCar.init(this)


        back = findViewById<AppCompatImageButton>(R.id.back).apply {
            setOnClickListener {
                finish()
            }  }

        save = findViewById<AppCompatImageButton>(R.id.save).apply {
            setOnClickListener {


                val car = Car(
                        ownerNameCar = ownerName.editText?.text.toString(),
                        producerCar = producer.editText?.text.toString(),
                        modelCar = model.editText?.text.toString(),
                        numberCar = numberCar.editText?.text.toString())

                val  intent = intent
                intent.putExtra(Constants.CAR_KEY, car)
                
                 dataBaseCar.getCarDao().updateCar(car)




//                val intent = Intent(this@CarInfoActivity, MainActivity::class.java)
//                startActivity(intent)
                finish()

            }
        }

        editing()

    }
    private fun editing(){
        val  intent = intent
        val car = intent.getParcelableExtra<Car>(Constants.CAR_KEY)
        ownerName.editText?.setText(car?.ownerNameCar)
        producer.editText?.setText(car?.producerCar)
        model.editText?.setText(car?.modelCar)
        numberCar.editText?.setText(car?.numberCar)

    }
}