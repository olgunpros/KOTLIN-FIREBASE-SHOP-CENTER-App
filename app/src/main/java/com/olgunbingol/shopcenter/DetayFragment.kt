package com.olgunbingol.shopcenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.olgunbingol.shopcenter.databinding.FragmentDetayBinding

class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private lateinit var adText : TextView
    private lateinit var fiyatText : TextView
    private lateinit var buyClickedd : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adText = view.findViewById(R.id.adText)
        fiyatText = view.findViewById(R.id.fiyatText)
        buyClickedd = view.findViewById(R.id.buyClickedd)

        buyClickedd.setOnClickListener {
            Toast.makeText(context,"This is Demo Project :)",Toast.LENGTH_LONG).show()
        }

        val urun = arguments?.let { DetayFragmentArgs.fromBundle(it).urun }

        binding.adText.text = urun?.urunAdi
        binding.fiyatText.text = urun?.fiyat

    }
}
