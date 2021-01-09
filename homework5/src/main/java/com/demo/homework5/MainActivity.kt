package com.demo.homework5

import android.content.Intent
import android.os.Bundle
import android.view.ViewManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity() {



    private lateinit var buttonAdd: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: ViewManager
    private lateinit var dataBaseCar: DataBaseCar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataBaseCar = DataBaseCar.init(this)
        getData()

        var list = ArrayList<Car>()

        val carFromDB: List<Car> = dataBaseCar.getCarDao().getAllCar()
        list.addAll(carFromDB)

        recyclerView = findViewById(R.id.recyclerView)
        toolbar = findViewById(R.id.toolbar)
        buttonAdd = findViewById<FloatingActionButton>(R.id.buttonAdd).apply {
            setOnClickListener {
                val intent = Intent(this@MainActivity, AddCarActivity::class.java)
                startActivity(intent)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CarAdapter(list, this)


    }
 private fun getData (){

     val dao = dataBaseCar.getCarDao()

 }
}

