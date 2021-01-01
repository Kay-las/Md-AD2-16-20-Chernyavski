package com.demo.homework5

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var buttonAdd: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: ViewManager


            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        toolbar = findViewById(R.id.toolbar)
        buttonAdd = findViewById<FloatingActionButton>(R.id.buttonAdd).apply {
            setOnClickListener {
                val intent = Intent(this@MainActivity, AddCarActivity::class.java)
                startActivity(intent)
            }
        }

        val list = ArrayList<Car>()

        list.add(Car(R.drawable.ic_edit_info, R.drawable.ic_number_plate_background, "Дед Мороз","БМВ", "301", "2020DF"))
        list.add(Car(R.drawable.ic_edit_info,  R.drawable.ic_number_plate_background,  "Император", "Мерседес", "2121", "2020DF"))
        list.add(Car(R.drawable.ic_edit_info, R.drawable.ic_number_plate_background,  "Император", "Жигули", "2121", "2020DF"))
        list.add(Car(R.drawable.ic_edit_info,  R.drawable.ic_number_plate_background,  "Император", "ВАЗ", "2121", "2020DF"))
        list.add(Car(R.drawable.ic_edit_info, R.drawable.ic_number_plate_background,   "Император", "ВАЗ", "2121", "2020DF"))
        list.add(Car(R.drawable.ic_edit_info, R.drawable.ic_number_plate_background,  "Император", "ВАЗ", "2121", "2020DF"))
        list.add(Car(R.drawable.ic_edit_info,  R.drawable.ic_number_plate_background,  "Император", "ВАЗ", "2121", "2020DF"))
        list.add(Car(R.drawable.ic_edit_info,  R.drawable.ic_number_plate_background,  "Император", "ВАЗ", "2121", "2020DF"))


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CarAdapter(list, this)



    }




}

