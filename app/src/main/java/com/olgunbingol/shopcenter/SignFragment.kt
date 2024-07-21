package com.olgunbingol.shopcenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

private lateinit var emailText : TextView
private lateinit var passwordText : TextView
private lateinit var password2Text : TextView
private lateinit var signClicked : Button
private lateinit var auth : FirebaseAuth








class SignFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        emailText = view.findViewById(R.id.emailText)
        passwordText = view.findViewById(R.id.passwordText)
        password2Text = view.findViewById(R.id.password2Text)
        signClicked = view.findViewById(R.id.signClicked)

        signClicked.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()
            val password2 = password2Text.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()) {

                if(password == password2) {
                auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                    findNavController().navigate(R.id.action_signFragment_to_shopListFragment)
                }.addOnFailureListener {
                    Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()

                }


                }else {
                    Toast.makeText(context, "Passwords do not match:)", Toast.LENGTH_LONG).show()

                }


            }else {
                Toast.makeText(context, "Email or Password is Empty :)", Toast.LENGTH_LONG).show()


            }

        }



    }


}