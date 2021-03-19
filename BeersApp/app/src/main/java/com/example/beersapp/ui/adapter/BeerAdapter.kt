package com.example.beersapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beersapp.databinding.ItemBeerBinding
import com.example.beersapp.entity.beer.Beer

class BeerAdapter (private var beers : MutableList<Beer>?): RecyclerView.Adapter<BeerAdapter.ViewHolder>() {

    private lateinit var onActionsListener : OnActionsListener

    fun setActionsListener(onActionsListener: OnActionsListener){
        this.onActionsListener = onActionsListener
    }

    fun addBeers(beers: MutableList<Beer>?){
        this.beers = beers
        notifyDataSetChanged()
    }

    fun clearBeers(){
        this.beers!!.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBeerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(beers!![position])
        holder.itemView.setOnClickListener { onActionsListener.onBeerSelected(beers!![position]) }
    }

    override fun getItemCount(): Int {
        return if (beers.isNullOrEmpty()) 0 else beers!!.size
    }

    class ViewHolder(private val binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: Beer){
            binding.beer = beer
        }
    }

    interface OnActionsListener{
        fun onBeerSelected(beer: Beer)
    }
}