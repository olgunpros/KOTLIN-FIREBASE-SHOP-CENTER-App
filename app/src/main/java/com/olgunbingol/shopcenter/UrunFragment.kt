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
import com.google.firebase.ktx.Firebase
import com.olgunbingol.shopcenter.adapter.UrunlerAdapter
import com.olgunbingol.shopcenter.databinding.FragmentDetayBinding
import com.olgunbingol.shopcenter.databinding.FragmentUrunBinding
import com.olgunbingol.shopcenter.model.Urunler


class UrunFragment : Fragment() {
    private lateinit var binding : FragmentUrunBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var urunArrayList : ArrayList<Urunler>
    private lateinit var urunler_Adapter: UrunlerAdapter
    private lateinit var ekleClicked : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_urun, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       binding = FragmentUrunBinding.bind(view)
        db = FirebaseFirestore.getInstance()
        urunArrayList = ArrayList<Urunler>()
        veriAl()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        //urunler_Adapter = UrunlerAdapter(urunArrayList)
        binding.recyclerView.adapter = urunler_Adapter
        ekleClicked = view.findViewById(R.id.ekleClicked)

        ekleClicked.setOnClickListener {
            if(auth.currentUser == null) {
                findNavController().navigate(R.id.action_urunFragment_to_signFragment)
            }
        }

    }


   override fun onItemClick(urun: Urunler) {
       val action = UrunFragmentDirections.actionUrunFragmentToDetayFragment(urun)
        //findNavController().navigate(action)
    }

    private fun veriAl() {
        db.collection("Urunler").addSnapshotListener { value, error ->
            if(error != null) {
                Toast.makeText(context,"Error!!!",Toast.LENGTH_LONG).show()
            }
            else {
               if(value != null) {
                   if(!value.isEmpty) {
                       val documents = value.documents
                       for(document in documents) {
                           val urunAdi = document.get("urunAdi") as String
                           val urunFiyat = document.get("urunFiyat") as String
                           val imageUrl = document.get("imageUrl") as String
                           val urun = Urunler(urunAdi, imageUrl, urunFiyat)
                           urunArrayList.add(urun)
                       }
                       urunler_Adapter.notifyDataSetChanged()
                   }
               }
            }
        }
    }




}