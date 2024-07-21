package com.olgunbingol.shopcenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.olgunbingol.shopcenter.adapter.UrunlerAdapter
import com.olgunbingol.shopcenter.adapter.siparisler_adapter
import com.olgunbingol.shopcenter.databinding.FragmentShopListBinding
import com.olgunbingol.shopcenter.databinding.FragmentUrunBinding
import com.olgunbingol.shopcenter.model.Siparisler


class ShopListFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var binding: FragmentShopListBinding
    private lateinit var siparislerAdapter: siparisler_adapter
    private lateinit var siparisArrayList: ArrayList<Siparisler>
    private lateinit var cancelClicked : Button
    private lateinit var buyClicked : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelClicked = view.findViewById(R.id.cancelClicked)
        buyClicked = view.findViewById(R.id.buyClicked)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        siparisArrayList = ArrayList()
        getData()
        binding.yeniView.layoutManager = LinearLayoutManager(context)
        siparislerAdapter = siparisler_adapter(siparisArrayList)
        binding.yeniView.adapter = siparislerAdapter
        sonucuTopla()
        buyClicked.setOnClickListener {
            Toast.makeText(context,"This is Demo Project :)",Toast.LENGTH_LONG).show()
        }
        cancelClicked.setOnClickListener {
            findNavController().navigate(R.id.action_shopListFragment_to_homeFragment)
        }



    }



    private fun getData() {
        db.collection("Siparisler").addSnapshotListener { value, error ->
            if(error != null) {
                Toast.makeText(context,"Error!",Toast.LENGTH_LONG).show()
            }
            else {
                if(value!= null) {
                    if(!value.isEmpty) {
                        val documents = value.documents
                        for (document in documents) {
                            val urunismi  = document.get("urunismi") as String
                            val imageUrl = document.get("imageUrl") as String
                            val fiyat = document.get("fiyat") as String
                        }
                        siparislerAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
    fun sonucuTopla() {
        db.collection("Siparisler").get().addOnSuccessListener { result ->
            var totalPrice = 0.0
            for(document in result) {
                val priceString = document.getString("fiyat")
                val price = priceString?.toDoubleOrNull()
                if(price != null) {
                    totalPrice += price
                }
                binding.totalText.text = "Toplam Fiyat: $totalPrice"
            }
        }
    }


}