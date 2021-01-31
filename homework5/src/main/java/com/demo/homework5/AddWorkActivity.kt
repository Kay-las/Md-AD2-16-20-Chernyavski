package com.demo.homework5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageButton
import com.demo.homework5.work.Work
import com.google.android.material.textfield.TextInputLayout
import java.util.ArrayList

class AddWorkActivity : AppCompatActivity() {

    private lateinit var nameWork: TextInputLayout
    private lateinit var cost: TextInputLayout

    //    private lateinit var data: TextInputLayout
//    private lateinit var progressItem: ImageView

    private lateinit var save: AppCompatImageButton
    private lateinit var back: AppCompatImageButton
    private lateinit var pending: AppCompatImageButton
    private lateinit var progress: AppCompatImageButton
    private lateinit var completed: AppCompatImageButton
    private lateinit var dataBaseCar: DataBaseCar

    private var state: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addwork)

        nameWork = findViewById(R.id.nameWork)
        cost = findViewById(R.id.cost)
        pending = findViewById<AppCompatImageButton>(R.id.pending).apply {
            setOnClickListener { onStateClicked(0) }
        }
        progress = findViewById<AppCompatImageButton>(R.id.progress).apply {
            setOnClickListener { onStateClicked(1) }
        }
        completed = findViewById<AppCompatImageButton>(R.id.completed).apply {
            setOnClickListener { onStateClicked(2) }
        }

        dataBaseCar = DataBaseCar.init(this)
//        getData()

        val carId = intent.getIntExtra("carId", 0)

//        pending.isSelected = true

        val list = ArrayList<Work>()

        val workFromDB: List<Work> = dataBaseCar.getWorkDao().getAllWork(carId)
        list.addAll(workFromDB)

        save = findViewById<AppCompatImageButton>(R.id.save).apply {
            setOnClickListener {
//                val dao = DataBaseCar.init(this@AddWorkActivity).getWorkDao()
                val work = Work(
                        nameWork = nameWork.editText?.text.toString(),
                        cost = cost.editText?.text.toString(),
                        carId = carId,
//                        data = data.editText?.text.toString(),
                        progressItem = state)

                dataBaseCar.getWorkDao().insertWork(work)
                finish()
            }
        }

        back = findViewById<AppCompatImageButton>(R.id.back).apply {
            setOnClickListener {
                finish()
            }
        }
        refreshState()
    }


    private fun onStateClicked(newState: Int) {
        state = newState
        refreshState()
    }

    private fun refreshState() {
        pending.isSelected = state == 0
        progress.isSelected = state == 1
        completed.isSelected = state == 2
    }

//    private fun getData() {
//
//        val dao = dataBaseCar.getWorkDao()
//
//    }


}