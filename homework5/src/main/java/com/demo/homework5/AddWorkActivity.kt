package com.demo.homework5

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.demo.homework5.work.Work
import com.google.android.material.textfield.TextInputLayout
import java.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class AddWorkActivity : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main + Job())

    private lateinit var nameWork: TextInputLayout
    private lateinit var cost: TextInputLayout
    private lateinit var description: TextInputLayout

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
        description = findViewById(R.id.description)
        pending = findViewById<AppCompatImageButton>(R.id.pending).apply {
            setOnClickListener { onStateClicked(0) }
        }
        progress = findViewById<AppCompatImageButton>(R.id.progress).apply {
            setOnClickListener { onStateClicked(1) }
        }
        completed = findViewById<AppCompatImageButton>(R.id.completed).apply {
            setOnClickListener { onStateClicked(2) }
        }


        val carId = intent.getIntExtra("carId", 0)

        pending.isSelected = true



        save = findViewById<AppCompatImageButton>(R.id.save).apply {
            setOnClickListener {

                if(nameWork.editText?.text.toString().isNotEmpty() && cost.editText?.text.toString().isNotEmpty() &&
                        description.editText?.text.toString().isNotEmpty()){
                val work = Work(
                        nameWork = nameWork.editText?.text.toString(),
                        cost = cost.editText?.text.toString(),
                        description = description.editText?.text.toString(),
                        carId = carId,
                        progressItem = state)


                    addWork(work)
                finish()
                }else {
                    Toast.makeText(context, R.string.note, Toast.LENGTH_SHORT).show()
                }
            }
        }

        back = findViewById<AppCompatImageButton>(R.id.back).apply {
            setOnClickListener {

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
    private fun addWork(work: Work){
        dataBaseCar = DataBaseCar.init(this)

        activityScope.launch {
            val deferrend = async (Dispatchers.IO){ dataBaseCar.getWorkDao().insertWork(work) }
             deferrend.await()
            finish()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }
}