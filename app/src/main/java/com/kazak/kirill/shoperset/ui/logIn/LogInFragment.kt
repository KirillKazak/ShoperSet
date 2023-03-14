package com.kazak.kirill.shoperset.ui.logIn

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kazak.kirill.shoperset.R
import com.kazak.kirill.shoperset.databinding.FragmentLogInBinding
import com.kazak.kirill.shoperset.util.Constants.SUCCESS_LOG_IN_MESSAGE
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInFragment : Fragment(R.layout.fragment_log_in) {
    private val vb: FragmentLogInBinding by viewBinding()
    private val vm by viewModel<LogInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startObserveLoginMessageLiveData()
        onBtnLogInClick()
        onTvSignInClick()
        onIvPasswordVisibilityClick()
    }

    private fun onBtnLogInClick() {
        with(vb) {
            btnLogIn.setOnClickListener {
                vm.getLogInMessage(
                    firstName = edtFirstNameLogIn.text.toString(),
                    password = edtPasswordLogIn.text.toString()
                )
            }
        }
    }

    private fun onIvPasswordVisibilityClick() {
        var iconIsClick = false

        with(vb.ivPasswordVisibility) {

            setOnClickListener {
                if (!iconIsClick) {
                    setBackgroundResource(R.drawable.icon_password_visibility)

                    vb.edtPasswordLogIn.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()

                    iconIsClick = true
                } else {
                    setBackgroundResource(R.drawable.icon_password_visibility_off)

                    vb.edtPasswordLogIn.transformationMethod =
                        PasswordTransformationMethod.getInstance()

                    iconIsClick = false
                }
            }

        }

    }

    private fun onTvSignInClick() {
        vb.tvSignInFromLogin.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_signInFragment)
        }
    }

    private fun startObserveLoginMessageLiveData() {
        vm.logInMessageLiveData.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

            if (message == SUCCESS_LOG_IN_MESSAGE) {
                findNavController().navigate(R.id.action_logInFragment_to_tabsFragment)
            }
        }
    }
}