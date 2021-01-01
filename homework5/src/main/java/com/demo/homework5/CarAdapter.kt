package com.demo.homework5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(listArray: ArrayList<Car>, context: Context) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    var listArrayRec = listArray
    var contextRec = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val producerCar = view.findViewById<TextView>(R.id.producerCar)
        private val modelCar = view.findViewById<TextView>(R.id.modelCar)
        private val name = view.findViewById<TextView>(R.id.name)
        private val editingCar = view.findViewById<ImageView>(R.id.editingCar)
        private val imFrame = view.findViewById<ImageView>(R.id.imFrame)
//        private val carImage = view.findViewById<ImageView>(R.id.carImage)
        private val numberCar = view.findViewById<TextView>(R.id.numberCar)

        fun bind(car: Car, context: Context) {

            producerCar.text = car.producerCar
            modelCar.text = car.modelCar
            name.text = car.ownerNameCar
            numberCar.text = car.numberCar
            editingCar.setImageResource(car.imageInfo)
            imFrame.setImageResource(car.imFrame)
//            carImage.setImageResource(car.carImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextRec)
        return ViewHolder(inflater.inflate(R.layout.item_car, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = listArrayRec.get(position)
        holder.bind(car, contextRec)

    }

    override fun getItemCount(): Int {
        return listArrayRec.size
    }

}