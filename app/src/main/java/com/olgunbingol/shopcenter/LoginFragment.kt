package com.olgunbingol.shopcenter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var emailEntry: TextView
    private lateinit var passwordEntry: TextView
    private lateinit var buttonClicked: Button
    private lateinit var checkBox: CheckBox
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailEntry = view.findViewById(R.id.emailEntry)
        passwordEntry = view.findViewById(R.id.passwordEntry)
        buttonClicked = view.findViewById(R.id.buttonClicked)
        auth = FirebaseAuth.getInstance()
        checkBox = view.findViewById(R.id.checkBox)
       sharedPreferences = requireContext().getSharedPreferences("login_prefs",Context.MODE_PRIVATE)

        var rememberChecked = sharedPreferences.getBoolean("remember_checked",false)
        checkBox.isChecked = rememberChecked

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            rememberChecked = isChecked
        }

        if(rememberChecked == true) {
            findNavController().navigate(R.id.action_loginFragment_to_shopListFragment)
        }





        buttonClicked.setOnClickListener {
            val email = emailEntry.text.toString()
            val password = passwordEntry.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {

                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    findNavController().navigate(R.id.action_loginFragment_to_shopListFragment)
                    if(rememberChecked == true)  {
                        with(sharedPreferences.edit()) {
                            putBoolean("remember_checked", true)
                            apply()
                        }
                    }
                    else {
                        with(sharedPreferences.edit()) {
                            remove("remember_checked")
                            apply()
                        }
                    }
                }.addOnFailureListener {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Email or Password is Empty :)", Toast.LENGTH_LONG).show()
            }
        }

    }
}
