package com.demo.homework5.work

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.homework5.Car
import com.demo.homework5.R

class WorkAdapter(listArray: ArrayList<Work>, context: Context) : RecyclerView.Adapter<WorkAdapter.ViewHolder>() {

    var listArrayWork = listArray
    var contextWork = context


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameWork = view.findViewById<TextView>(R.id.nameWork)
        private val cost = view.findViewById<TextView>(R.id.cost)
        private val progress = view.findViewById<ImageView>(R.id.progress)
        private val data = view.findViewById<TextView>(R.id.data)



        fun bind(work: Work, position: Int, context: Context) {
            nameWork.text = work.nameWork
            cost.text = work.cost
            progress.setImageResource(work.progress)
            data.text = work.data
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextWork)
        return ViewHolder(inflater.inflate(R.layout.item_work, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val work = listArrayWork.get(position)
        holder.bind(work, position, contextWork)
    }

    override fun getItemCount(): Int {
       return listArrayWork.size
    }

}