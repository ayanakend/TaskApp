package com.example.taskapp.ui.authentication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentAuthBinding
import com.example.taskapp.extensoins.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    private var auth = FirebaseAuth.getInstance()

    private var correctCode: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)

        initListener()

        return binding.root
    }

    private fun initListener() {
        binding.btnSend.setOnClickListener{
            if (binding.etPhone.text!!.isNotEmpty()) {
                sendPhone()
                showToast("Отправка...")
            }else{
                binding.etPhone.error = "Введите номер телефона"
            }
        }
        binding.btnConfirm.setOnClickListener{
            sendCode()
        }
    }



     private fun sendPhone(){
         auth.setLanguageCode("ru")
         val options = PhoneAuthOptions.newBuilder(auth)
             .setPhoneNumber("+996"+binding.etPhone.text.toString())       // Phone number to verify
             .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
             .setActivity(requireActivity())                 // Activity (for callback binding)
             .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                 override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                     showToast("Успешно отправленно")
                 }

                 override fun onVerificationFailed(p0: FirebaseException) {
                     showToast(p0.message.toString())
                     Log.w("ololo", "signInWithCredential:failure", p0)
                 }
                 override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                     super.onCodeSent(p0, p1)
                     correctCode =p0

                     binding.etPhoneLayout.isVisible = false
                     binding.btnSend.isVisible = false

                     binding.etCodeLayout.isVisible = true
                     binding.btnConfirm.isVisible = true

                     Log.e("ololo", "onCodeSent:$p0")
                 }

             })          // OnVerificationStateChangedCallbacks
             .build()
         PhoneAuthProvider.verifyPhoneNumber(options)
     }


    private fun sendCode() {
        val credential =
            PhoneAuthProvider.getCredential(
                correctCode.toString(),
                binding.etCode.text.toString()
            )
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful){
                    findNavController().navigate(R.id.navigation_home)
                }else{
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        showToast("Что-то пошло не так...")
                        //Log.w("ololo", "signInWithPhoneAuthCredential:failure", credential)
                    }
                }
            }
    }
}
