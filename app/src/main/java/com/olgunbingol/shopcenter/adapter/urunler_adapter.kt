package com.olgunbingol.shopcenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.olgunbingol.shopcenter.databinding.UrunRowBinding
import com.olgunbingol.shopcenter.model.Urunler

class UrunlerAdapter(
    private val urunlerList: ArrayList<Urunler>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<UrunlerAdapter.UrunlerHolder>() {

    interface OnItemClickListener {
        fun onItemClick(urun: Urunler)
    }

    class UrunlerHolder(val binding: UrunRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UrunlerHolder {
        val binding = UrunRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UrunlerHolder(binding)
    }

    override fun getItemCount(): Int {
        return urunlerList.size
    }

    override fun onBindViewHolder(holder: UrunlerHolder, position: Int) {
        val urun = urunlerList[position]
        holder.binding.urunAdiText.text = urun.urunAdi
        holder.binding.priceText.text = urun.fiyat.toString()
        val auth = FirebaseAuth.getInstance()

        holder.binding.root.setOnClickListener {
            itemClickListener.onItemClick(urun)
        }

        holder.binding.ekleClicked.setOnClickListener {
            val db = FirebaseFirestore.getInstance()
            val urunMap = hashMapOf<String, Any>()
            urunMap["urunismi"] = urun.urunAdi
            urunMap["fiyat"] = urun.fiyat.toString()
            db.collection("Taleplerim").add(urunMap).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Toast.makeText(context,"Success!!!",Toast.LENGTH_LONG).show()
                } else {
                    // Toast.makeText(context, task.exception?.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
