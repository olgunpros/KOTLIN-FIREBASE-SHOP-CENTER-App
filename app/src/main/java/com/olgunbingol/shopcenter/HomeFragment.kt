package com.olgunbingol.shopcenter

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController

private lateinit var button1 : Button
private lateinit var button2 : Button
private lateinit var button3 : Button
private lateinit var button4 : Button
private lateinit var button5 : Button
private lateinit var button6 : Button
private lateinit var sepetClicked : Button


private lateinit var image1 : ImageView
private lateinit var image2 : ImageView
private lateinit var image3 : ImageView
private lateinit var image4 : ImageView
private lateinit var image5 : ImageView
private lateinit var image6 : ImageView
private lateinit var degisenView : ImageView





class HomeFragment : Fragment() {
    private val imageList = arrayOf(
                R.drawable.resim1,
                R.drawable.resim2,
                R.drawable.resim3,
                R.drawable.resim4,
                R.drawable.resim5


    )
    private var currentIndex = 0
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable : Runnable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startImageSlideShow()
        button1 = view.findViewById(R.id.button1)
        button2 = view.findViewById(R.id.button2)
        button3 = view.findViewById(R.id.buyClickedd)
        button4 = view.findViewById(R.id.cancelClicked)
        button5 = view.findViewById(R.id.buyClicked)
        button6 = view.findViewById(R.id.button6)

        image1 = view.findViewById(R.id.image1)
        image2 = view.findViewById(R.id.image2)
        image3 = view.findViewById(R.id.image3)
        image4 = view.findViewById(R.id.image4)
        image5 = view.findViewById(R.id.image5)
        image6 = view.findViewById(R.id.image6)
        sepetClicked = view.findViewById(R.id.sepetClicked)
        degisenView = view.findViewById(R.id.degisenView)

        button1.setOnClickListener {
          findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
      }
        button2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
        }
        button3.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
        }
        button4.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
        }
        button5.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
        }
        button6.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
        }
        image1.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
        }
        image2.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
        }
        image3.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
        }
        image4.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
        }
        image5.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
        }
        image6.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_urunFragment)
        }
        sepetClicked.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_shopListFragment)
        }




    }

    private fun startImageSlideShow() {
        runnable = Runnable {
            degisenView.setImageResource(imageList[currentIndex])
            currentIndex = (currentIndex+1) % imageList.size
            handler.postDelayed(runnable,2500)
        }
        handler.post(runnable)
    }
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }


}