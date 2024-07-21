package com.olgunbingol.shopcenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.olgunbingol.shopcenter.databinding.ShopListRowBinding
import com.olgunbingol.shopcenter.model.Siparisler

class siparisler_adapter (val siparislerList : ArrayList<Siparisler>) : RecyclerView.Adapter<siparisler_adapter.SiparislerHolder> (){
    class SiparislerHolder(val binding : ShopListRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiparislerHolder {
val binding = ShopListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return SiparislerHolder(binding)}

    override fun getItemCount(): Int {
       return siparislerList.size
    }

    override fun onBindViewHolder(holder: SiparislerHolder, position: Int) {

        holder.binding.urunadiLabel.text = siparislerList.get(position).urunAdi
        holder.binding.fiyatLabel.text = siparislerList.get(position).fiyat



    }


}


