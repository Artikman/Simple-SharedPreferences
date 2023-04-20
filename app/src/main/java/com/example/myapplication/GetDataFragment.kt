package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentGetDataBinding
import com.example.myapplication.helper.PreferencesHelper

class GetDataFragment : Fragment() {

    private lateinit var preferencesHelper: PreferencesHelper
    private lateinit var binding: FragmentGetDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferencesHelper = PreferencesHelper(requireContext())

        with(binding) {
            textUsername.text = preferencesHelper.getString(PREF_USERNAME)

            buttonLogout.setOnClickListener {
                preferencesHelper.clear()
                moveIntent()
            }
        }
    }

    private fun moveIntent() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerView, EditDataFragment())
            .commit()
    }

    companion object {
        const val PREF_USERNAME = "PREF_USERNAME"
    }
}