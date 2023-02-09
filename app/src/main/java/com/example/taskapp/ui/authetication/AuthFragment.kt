package com.example.taskapp.ui.authetication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentAuthBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
        initViews()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }
    private fun initListeners() {
        TODO("Not yet implemented")
    }

    private fun initViews() {
        auth.setLanguageCode("ru")
        val options = PhoneAuthOptions.newBuilder(auth)
          //  .setPhoneNumber(binding.etDesc.text.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(object :PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    //showToast(p0.smsCode.toString())
                }

                override fun onVerificationFailed(p0: FirebaseException) {
               //     showToast(exception.message.toString())
                }

            })          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

   // override fun onCodeSent(p0: String, PhonAuthProvider.ForceResending)

}