package com.example.mindhelper

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation
import com.example.mindhelper.databinding.FragmentContactBinding


class ContactFragment : Fragment() {

    private lateinit var binding: FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val updateCheckboxesIntent = Intent("com.example.mindhelper.UPDATE_CHECKBOXES")
        LocalBroadcastManager.getInstance(requireContext())
            .sendBroadcast(updateCheckboxesIntent)

        binding = FragmentContactBinding.inflate(inflater, container, false)
        binding.apply {
            buttonLb.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_contactFragment_to_lbFragment)
            }
            buttonLpp.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_contactFragment_to_lppFragment)
            }
            buttonLpo.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_contactFragment_to_lpoFragment)
            }
            buttonOpatruj.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_contactFragment_to_opFragment)
            }
            buttonTerapie.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_contactFragment_to_terFragment)
            }
        return binding.root
    }

    }
}