package com.demo.homework5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.homework5.work.EditDeleteWork
import com.demo.homework5.work.Work
import com.demo.homework5.work.WorkAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AutoRepairActivity : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main + Job())

    private lateinit var workAdapter: WorkAdapter
    private lateinit var addWork: FloatingActionButton
    private lateinit var back: AppCompatImageButton
    private lateinit var recyclerViewWork: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var modelCar: TextView
    private lateinit var producerCar: TextView
    private lateinit var numberCar: TextView
    private lateinit var dataBaseCar: DataBaseCar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_repair)

        modelCar = findViewById(R.id.modelCar)
        producerCar = findViewById(R.id.producerCar)
        numberCar = findViewById(R.id.numberCar)

        recyclerViewWork = findViewById(R.id.recyclerViewWork)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val carId = intent.getIntExtra("carId", 0)

        dataBaseCar = DataBaseCar.init(this)


        addWork = findViewById<FloatingActionButton>(R.id.addWork).apply {
            setOnClickListener {

                val intent = Intent(this@AutoRepairActivity, AddWorkActivity::class.java)
                intent.putExtra("carId", carId)
                startActivity(intent)
            }
        }

        back = findViewById<AppCompatImageButton>(R.id.back).apply {
            setOnClickListener {
                finish()
            }
        }
        recyclerViewWork.layoutManager = LinearLayoutManager(this)
        workAdapter = WorkAdapter(object : WorkAdapter.WorkClickListener {
            override fun onWorkClick(position: Int) {
                val intent = Intent(this@AutoRepairActivity, EditDeleteWork::class.java)
                val car = workAdapter.getItem(position)
                intent.putExtra(Constants.WORK_KEY, car)
                startActivity(intent)
            }

        }, arrayListOf(), this)
        recyclerViewWork.adapter = workAdapter

        getAllWork()
        infoCar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menuInflater.inflate(R.menu.search, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        getAllWork()

    }

    private fun infoCar() {
        val intent = intent
        val car = intent.getParcelableExtra<Car>(Constants.CAR_KEY)
        producerCar.text = car?.producerCar
        modelCar.text = car?.modelCar
        numberCar.text = car?.numberCar

    }

    private fun getAllWork(){
        dataBaseCar = DataBaseCar.init(this)
        val list = ArrayList<Work>()
        val carId = intent.getIntExtra("carId", 0)
        activityScope.launch {
            val deferrend = async (Dispatchers.IO){ dataBaseCar.getWorkDao().getAllWork(carId) }
            val workFromDB =  deferrend.await()
            workAdapter.setListWorks(list)
            list.addAll(workFromDB)
            workAdapter.setListWorks(list)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }

}