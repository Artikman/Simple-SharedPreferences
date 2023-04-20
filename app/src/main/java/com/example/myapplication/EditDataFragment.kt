package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentEditDataBinding
import com.example.myapplication.helper.PreferencesHelper

class EditDataFragment : Fragment() {

    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var binding: FragmentEditDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferencesHelper = PreferencesHelper(requireContext())

        with(binding) {
            buttonLogin.setOnClickListener {
                if (editUsername.text.isNotBlank() && editPassword.text.isNotBlank()) {
                    saveSession(editUsername.text.toString(), editPassword.text.toString())
                    moveIntent()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (preferencesHelper.getBoolean(PREF_IS_LOGIN)) {
            moveIntent()
        }
    }

    private fun saveSession(userName: String, password: String) {
        preferencesHelper.put(PREF_USERNAME, userName)
        preferencesHelper.put(PREF_PASSWORD, password)
        preferencesHelper.put(PREF_IS_LOGIN, true)
    }

    private fun moveIntent() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerView, GetDataFragment())
            .commit()
    }

    companion object {
        const val PREF_IS_LOGIN = "PREF_IS_LOGIN"
        const val PREF_USERNAME = "PREF_USERNAME"
        const val PREF_PASSWORD = "PREF_PASSWORD"
    }
}