package com.demo.homework5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var carAdapter: CarAdapter
    private lateinit var buttonAdd: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var dataBaseCar: DataBaseCar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        dataBaseCar = DataBaseCar.init(this)

        val list = ArrayList<Car>()

//        val carFromDB: List<Car> = dataBaseCar.getCarDao().getAllCar()
//        list.addAll(carFromDB)

        recyclerView = findViewById(R.id.recyclerView)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        buttonAdd = findViewById<FloatingActionButton>(R.id.buttonAdd).apply {
            setOnClickListener {
                val intent = Intent(this@MainActivity, AddCarActivity::class.java)
                startActivity(intent)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        carAdapter = CarAdapter(object : CarAdapter.CarClickListener {
            override fun onCarClick(position: Int) {

                val intent = Intent(this@MainActivity, AutoRepairActivity::class.java)
                intent.putExtra("carId", list[position].id)
                val car = list[position]
                intent.putExtra(Constants.CAR_KEY, car)
                startActivity(intent)

            }

            override fun onCarEditClick(position: Int) {
                val intent = Intent(this@MainActivity, CarInfoActivity::class.java)
                val car = carAdapter.getItem(position)
                intent.putExtra(Constants.CAR_KEY, car)
                startActivity(intent)

            }

        }, list, this)
        recyclerView.adapter = carAdapter
        addAllCar()

    }

    private fun addAllCar() {
        dataBaseCar = DataBaseCar.init(this)
        dataBaseCar.getCarDao().getAllCarRX()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{carFromDB-> carAdapter }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        val list = ArrayList<Car>()
        val carFromDB: List<Car> = dataBaseCar.getCarDao().getAllCar()
        list.addAll(carFromDB)
        carAdapter.setListCars(list)
    }

}

