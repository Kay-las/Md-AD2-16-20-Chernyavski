package com.demo.homework5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

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
            }
        }

        save = findViewById<AppCompatImageButton>(R.id.save).apply {
            setOnClickListener {

                val car = intent.getParcelableExtra<Car>(Constants.CAR_KEY) as Car

                car.ownerNameCar = ownerName.editText?.text.toString()
                car.producerCar = producer.editText?.text.toString()
                car.modelCar = model.editText?.text.toString()
                car.numberCar = numberCar.editText?.text.toString()

                val intent = intent
                intent.putExtra(Constants.CAR_KEY, car)

                editingCar(car)
//                dataBaseCar.getCarDao().updateCar(car)
//
//                finish()
            }
        }

        editing()
    }

    private fun editing() {
        val intent = intent
        val car = intent.getParcelableExtra<Car>(Constants.CAR_KEY)
        ownerName.editText?.setText(car?.ownerNameCar)
        producer.editText?.setText(car?.producerCar)
        model.editText?.setText(car?.modelCar)
        numberCar.editText?.setText(car?.numberCar)
    }

    private fun editingCar(car: Car) {
        dataBaseCar = DataBaseCar.init(this)
        Single.create<Car> {
            dataBaseCar.getCarDao().updateCar(car)
            it.onSuccess(car)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { _ ->
                    finish()
                }

    }


}