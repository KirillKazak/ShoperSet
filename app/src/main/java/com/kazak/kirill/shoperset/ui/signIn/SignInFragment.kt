package com.kazak.kirill.shoperset.ui.signIn

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.FragmentSignInBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {
    private val vb: FragmentSignInBinding by viewBinding()
    private val vm by viewModel<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startObserveAuthorizationMessageLiveData()
        onBtnSignClick()
    }

    private fun onBtnSignClick() {
        with(vb) {
            btnSignIn.setOnClickListener {
                vm.getAuthorizationMessage(
                    firstName = edtFirstNameSignIn.text.toString(),
                    lastName = edtLastNameSignIn.text.toString(),
                    email = edtEmailSignIn.text.toString()
                )
            }
        }
    }

    private fun startObserveAuthorizationMessageLiveData() {
        vm.authorizationMessageLiveData.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}