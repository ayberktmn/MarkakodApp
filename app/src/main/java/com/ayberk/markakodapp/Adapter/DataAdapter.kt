package com.ayberk.markakodapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.markakodapp.Models.Base
import com.ayberk.markakodapp.R
import com.ayberk.markakodapp.databinding.DataItemBinding

class DataAdapter: RecyclerView.Adapter<DataAdapter.MyCustomHolder>() {

    var liveData : List<Base>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {

        val binding = DataItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(binding)

    }

    override fun getItemCount(): Int {
        return if (liveData != null) {
            liveData!!.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        holder.bind(liveData!!.get(position))
    }

    inner class MyCustomHolder(private var binding: DataItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data : Base) {
            binding.txtdataid.text = data.id.toString().plus(". ürün:")
            binding.txtdatatitle.text = data.title
        }
    }
    fun setList(liveData: List<Base>){
        this.liveData = liveData
        notifyDataSetChanged()
    }
}