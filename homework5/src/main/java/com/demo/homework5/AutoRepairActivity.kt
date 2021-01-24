package com.demo.homework5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.ViewGroup
import android.view.ViewManager
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.homework5.work.Work
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class AutoRepairActivity : AppCompatActivity() {

    private lateinit var addWork: FloatingActionButton
    private lateinit var add: AppCompatImageButton
    private lateinit var recyclerViewWork: RecyclerView
    private lateinit var toolbar: Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_repair)

        recyclerViewWork = findViewById(R.id.recyclerViewWork)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val list = ArrayList<Work>()
        list.add(Work(R.drawable.ic_progress, "engine", "21.02.2020", "1200"))

        addWork = findViewById<FloatingActionButton>(R.id.addWork).apply { setOnClickListener {

            val intent = Intent(this@AutoRepairActivity, AddWorkActivity::class.java)
            startActivity(intent)
        } }

        add = findViewById<AppCompatImageButton>(R.id.add).apply {
            setOnClickListener {

            }
        }
        recyclerViewWork.layoutManager = LinearLayoutManager(this)
        recyclerViewWork.adapter

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


}