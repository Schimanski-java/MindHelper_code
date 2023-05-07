package com.example.mindhelper

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.Navigation
import com.example.mindhelper.databinding.FragmentDisBinding

class DisFragment : Fragment() {

    private lateinit var binding: FragmentDisBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val updateCheckboxesIntent = Intent("com.example.mindhelper.UPDATE_CHECKBOXES")
        LocalBroadcastManager.getInstance(requireContext())
            .sendBroadcast(updateCheckboxesIntent)

        binding = FragmentDisBinding.inflate(inflater, container, false)
        binding.apply {
            buttonDep.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_disFragment_to_depFragment)
            }
            buttonBpd.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_disFragment_to_bpdFragment)
            }
            buttonEd.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_disFragment_to_edFragment2)
            }
            buttonPerson.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_disFragment_to_personFragment)
            }
            buttonSchizo.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_disFragment_to_schizoFragment)
            }
            buttonPtsd.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_disFragment_to_ptsdFragment)
            }
            buttonAnx.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_disFragment_to_anxFragment)
            }
        }
        return binding.root
    }
}


