package com.demo.homework5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CarAdapter(private val carClickListener: CarClickListener, listArray: ArrayList<Car>, context: Context) : RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    private var listArrayRec = listArray
    var contextRec = context

    interface CarClickListener {
        fun onCarClick(position: Int)
        fun onCarEditClick(position: Int)
    }

    class ViewHolder(private val carClickListener: CarClickListener, view: View) : RecyclerView.ViewHolder(view) {

        private val producerCar = view.findViewById<TextView>(R.id.producerCar)
        private val modelCar = view.findViewById<TextView>(R.id.modelCar)
        private val name = view.findViewById<TextView>(R.id.name)
        private val numberCar = view.findViewById<TextView>(R.id.numberCar)
        private val editingCar = view.findViewById<ImageView>(R.id.editingCar)


        fun bind(car: Car, position: Int, context: Context) {

            producerCar.text = car.producerCar
            modelCar.text = car.modelCar
            name.text = car.ownerNameCar
            numberCar.text = car.numberCar
            editingCar.setOnClickListener {
                carClickListener.onCarEditClick(position)
            }
            itemView.setOnClickListener {
                carClickListener.onCarClick(position)
//                Toast.makeText(context, "present: ${producerCar.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextRec)
        return ViewHolder(carClickListener, inflater.inflate(R.layout.item_car, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = listArrayRec.get(position)
        holder.bind(car, position, contextRec)

    }

    override fun getItemCount(): Int {
        return listArrayRec.size
    }

    fun setListCars(cars: ArrayList<Car>) {
        this.listArrayRec = cars
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Car {
        return this.listArrayRec[position]
    }

}