package com.example.mindhelper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mindhelper.databinding.FragmentDisBinding

class DisFragment : Fragment() {

    private lateinit var binding: FragmentDisBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisBinding.inflate(inflater, container, false)
        binding.apply {
            buttonDep.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_disFragment_to_depFragment)
            }
            buttonBpd.setOnClickListener { view: View ->
                Navigation.findNavController(view).navigate(R.id.action_disFragment_to_bpdFragment)
            }
        }
        return binding.root
    }
}


