package com.demo.homework5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private val activityScope = CoroutineScope(Dispatchers.Main + Job())

    private lateinit var carAdapter: CarAdapter
    private lateinit var buttonAdd: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var dataBaseCar: DataBaseCar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
                intent.putExtra("carId", carAdapter.getItem(position).id)
                val car = carAdapter.getItem(position)
                intent.putExtra(Constants.CAR_KEY, car)
                startActivity(intent)

            }

            override fun onCarEditClick(position: Int) {
                val intent = Intent(this@MainActivity, CarInfoActivity::class.java)
                val car = carAdapter.getItem(position)
                intent.putExtra(Constants.CAR_KEY, car)
                startActivity(intent)

            }

        }, arrayListOf(), this)
        recyclerView.adapter = carAdapter
        getAllCar()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        getAllCar()

    }

    private fun getAllCar(){
        dataBaseCar = DataBaseCar.init(this)
        val list = ArrayList<Car>()
        activityScope.launch {
           val deferrend = async (Dispatchers.IO){ dataBaseCar.getCarDao().getAllCar() }
            val carFromDB =  deferrend.await()
            carAdapter.setListCars(list)
            list.addAll(carFromDB)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }

}

