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

class WorkAdapter(private val workClickListener: WorkClickListener, listArray: ArrayList<Work>, context: Context) : RecyclerView.Adapter<WorkAdapter.ViewHolder>() {

    var listArrayWork = listArray
    var contextWork = context

    interface WorkClickListener {
        fun onWorkClick(position: Int)
    }


    class ViewHolder(private val workClickListener: WorkClickListener, view: View) : RecyclerView.ViewHolder(view) {
        private val nameWork = view.findViewById<TextView>(R.id.nameWork)
        private val cost = view.findViewById<TextView>(R.id.cost)
        private val progressItem = view.findViewById<ImageView>(R.id.progressItem)
//        private val data = view.findViewById<TextView>(R.id.data)


        fun bind(work: Work, position: Int, context: Context) {
            nameWork.text = work.nameWork
            cost.text = work.cost
//            data.text = work.data
            when (work.progressItem) {
                0 -> progressItem.setImageResource(R.drawable.ic_progress__2_)
                1 -> progressItem.setImageResource(R.drawable.ic_progress)
                2 -> progressItem.setImageResource(R.drawable.ic_progress__1_)
            }
            itemView.setOnClickListener {
                workClickListener.onWorkClick(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextWork)
        return ViewHolder(workClickListener, inflater.inflate(R.layout.item_work, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val work = listArrayWork[position]
        holder.bind(work, position, contextWork)
    }

    override fun getItemCount(): Int {
        return listArrayWork.size
    }

}