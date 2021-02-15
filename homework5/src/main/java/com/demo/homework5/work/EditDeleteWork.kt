package com.demo.homework5.work

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import com.demo.homework5.Constants
import com.demo.homework5.DataBaseCar
import com.demo.homework5.R
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class EditDeleteWork : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main + Job())

    private lateinit var nameWork: TextInputLayout
    private lateinit var cost: TextInputLayout
    private lateinit var description: TextInputLayout

    private lateinit var workInfo: TextView
    private lateinit var toolbar: Toolbar

    private lateinit var pending: AppCompatImageButton
    private lateinit var progress: AppCompatImageButton
    private lateinit var completed: AppCompatImageButton

    private lateinit var back: AppCompatImageButton
    private lateinit var save: AppCompatImageButton
    private lateinit var delete: AppCompatImageButton
    private lateinit var dataBaseCar: DataBaseCar

    private var state: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_delete_work)

        dataBaseCar = DataBaseCar.init(this)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        workInfo = findViewById(R.id.workInfo)
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

        nameWork = findViewById(R.id.nameWork)
        cost = findViewById(R.id.cost)

        back = findViewById<AppCompatImageButton>(R.id.back).apply {
            setOnClickListener {
                finish()
            } }
        save = findViewById<AppCompatImageButton>(R.id.save).apply {
            setOnClickListener {
                val work = intent.getParcelableExtra<Work>(Constants.WORK_KEY) as Work
                work.nameWork = nameWork.editText?.text.toString()
                work.cost = cost.editText?.text.toString()
                work.description = description.editText?.text.toString()
                work.progressItem = state

                val intent = intent
                intent.putExtra(Constants.WORK_KEY, work)
                editingWork(work)
//                dataBaseCar.getWorkDao().updateWork(work)
                finish()
            }  }
        delete = findViewById<AppCompatImageButton>(R.id.delete).apply {
            setOnClickListener { deleteDialog() }  }

        editing()
        infoWork()
    }

    private fun deleteDialog(){
        val alertDialogBuilder = AlertDialog.Builder(this)
               alertDialogBuilder.setTitle(R.string.delete)
                alertDialogBuilder.setMessage(R.string.question)
        alertDialogBuilder.setNegativeButton(R.string.no, null)
                alertDialogBuilder.setPositiveButton(R.string.yes) { dialogInterface, i ->
                    val work = intent.getParcelableExtra<Work>(Constants.WORK_KEY) as Work
//                    DataBaseCar.init(this).getWorkDao().deleteWork(work)
                    deleteWork(work)
                    finish()
                }
                        .show()

    }

    private fun editing(){
        val  intent = intent
        val work = intent.getParcelableExtra<Work>(Constants.WORK_KEY) as Work
        nameWork.editText?.setText(work.nameWork)
        cost.editText?.setText(work.cost)
        description.editText?.setText(work.description)

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


    private fun infoWork() {
        val intent = intent
        val work = intent.getParcelableExtra<Work>(Constants.WORK_KEY)
       workInfo.text = work?.nameWork


    }

    private fun editingWork(work: Work){
        dataBaseCar = DataBaseCar.init(this)
//        val list = ArrayList<Work>()
        activityScope.launch {
            val deferrend = async (Dispatchers.IO){ dataBaseCar.getWorkDao().updateWork(work) }
            val carFromDB =  deferrend.await()
//            workAdapter.setListWorks(list)
//            list.addAll(carFromDB)
        }
    }

    private fun deleteWork(work: Work){
        dataBaseCar = DataBaseCar.init(this)
//        val list = ArrayList<Work>()
        activityScope.launch {
            val deferrend = async (Dispatchers.IO){ dataBaseCar.getWorkDao().deleteWork(work) }
            val carFromDB =  deferrend.await()
//            workAdapter.setListWorks(list)
//            list.addAll(carFromDB)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        activityScope.cancel()
    }
}