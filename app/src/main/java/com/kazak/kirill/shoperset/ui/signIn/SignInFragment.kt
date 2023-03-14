package com.kazak.kirill.shoperset.ui.signIn

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.FragmentSignInBinding
import com.kazak.kirill.shoperset.util.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val vb: FragmentSignInBinding by viewBinding()
    private val vm by viewModel<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObserveSignInMessageLiveData()
        onBtnSignInClick()
        onTvLogInClick()
    }

    private fun onBtnSignInClick() {
        with(vb) {
            btnSignIn.setOnClickListener {
                vm.getSignInMessage(
                    firstName = edtFirstNameSignIn.text.toString(),
                    lastName = edtLastNameSignIn.text.toString(),
                    email = edtEmailSignIn.text.toString()
                )
            }
        }
    }

    private fun onTvLogInClick() {
        vb.tvLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_logInFragment)
        }
    }

    private fun startObserveSignInMessageLiveData() {
        vm.signInMessageLiveData.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

            if (message == Constants.SUCCESS_SIGN_IN_MESSAGE) {
                findNavController().navigate(R.id.action_signInFragment_to_tabsFragment)
            }
        }
    }
}