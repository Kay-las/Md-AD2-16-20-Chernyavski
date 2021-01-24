package com.demo.homework5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.google.android.material.textfield.TextInputLayout

class AddCarActivity : AppCompatActivity() {

    private lateinit var ownerName: TextInputLayout
    private lateinit var producer: TextInputLayout
    private lateinit var model: TextInputLayout
    private lateinit var number: TextInputLayout
    private lateinit var addCar: AppCompatImageButton
    private lateinit var dataBaseCar: DataBaseCar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)

        dataBaseCar = DataBaseCar.init(this)
//        val dao  = DataBaseCar.init(this).getCarDao()
//        val carList = dao.getAllCar()

        ownerName = findViewById(R.id.ownerName)
        producer = findViewById(R.id.producer)
        model = findViewById(R.id.model)
        number = findViewById(R.id.number)
        addCar = findViewById<AppCompatImageButton>(R.id.addCar).apply {
            setOnClickListener {
                val dao = DataBaseCar.init(this@AddCarActivity).getCarDao()
                val car = Car(
                        ownerNameCar = ownerName.editText?.text.toString(),
                        producerCar = producer.editText?.text.toString(),
                        modelCar = model.editText?.text.toString(),
                        numberCar = number.editText?.text.toString())

             dataBaseCar.getCarDao().insertCar(car)
//                dao.insertCar(car)
                val intent = Intent(this@AddCarActivity, MainActivity::class.java)
                startActivity(intent)
            }

        }

    }
}