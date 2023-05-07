package com.example.mindhelper

import android.content.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.mindhelper.databinding.FragmentTaskBinding


class TaskFragment : Fragment(), LifecycleObserver {

    private lateinit var binding: FragmentTaskBinding

    private val firstTask: MutableList<String> = mutableListOf("Udělej si své oblíbené jídlo", "Pusť si svojí oblíbenou písničku", "Pusť si svůj oblíbený film/seriál", "Přečti si kapitolu svojí oblíbené knihy", "Pusť si svojí oblíbenou pohádku z dětství")
    private val secondTask: MutableList<String> = mutableListOf("Projdi se do lesa", "Jdi na čerstvý vzduch", "Udělej si projížďku, autem nebo na kole", "Zahraj si svůj oblíbený sport", "Napiš své oblíbené osobě")
    private val thirdTask: MutableList<String> = mutableListOf("Ukliď si pokoj a kochej se", "Udělej pro sebe něco hezkého (pochval se, udělej oblíbenou činnost)", "Převlékni si postel a udělej si útulné spací prostředí", "Vypij sklenici vody", "Udělej si vnitřní a vnější hygienu")

    private lateinit var _currentFirstTask: String
    val currentFirstTask: String
        get() = _currentFirstTask
    private lateinit var _currentSecTask: String
    val currentSecTask: String
        get() = _currentSecTask
    private lateinit var _currentThirdTask: String
    val currentThirdTask: String
        get() = _currentThirdTask

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)

        val sharedPrefs = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.checkTaskOne.isChecked = sharedPrefs.getBoolean("checkbox1", false)
        binding.checkTaskTwo.isChecked = sharedPrefs.getBoolean("checkbox2", false)
        binding.checkTaskThree.isChecked = sharedPrefs.getBoolean("checkbox3", false)

        _currentFirstTask = sharedPrefs.getString("task1", firstTask.random())!!
        _currentSecTask = sharedPrefs.getString("task2", secondTask.random())!!
        _currentThirdTask = sharedPrefs.getString("task3", thirdTask.random())!!

        binding.apply {
            checkTaskOne.text = currentFirstTask
            checkTaskTwo.text = currentSecTask
            checkTaskThree.text = currentThirdTask
        }


        binding.checkTaskOne.setOnCheckedChangeListener { _, isChecked ->
            sharedPrefs.edit().putBoolean("checkbox1", isChecked).apply()
            updateCheckboxes()
        }
        binding.checkTaskTwo.setOnCheckedChangeListener { _, isChecked ->
            sharedPrefs.edit().putBoolean("checkbox2", isChecked).apply()
            updateCheckboxes()
        }
        binding.checkTaskThree.setOnCheckedChangeListener { _, isChecked ->
            sharedPrefs.edit().putBoolean("checkbox3", isChecked).apply()
            updateCheckboxes()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateCheckboxes()

    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            updateCheckboxesReceiver,
            IntentFilter("com.example.mindhelper.UPDATE_CHECKBOXES")
        )
    }

    override fun onPause() {
        super.onPause()
        updateCheckboxes()
        LocalBroadcastManager.getInstance(requireContext())
            .unregisterReceiver(updateCheckboxesReceiver)
    }

    private val updateCheckboxesReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            binding.apply {
                checkTaskOne.text = currentFirstTask
                checkTaskTwo.text = currentSecTask
                checkTaskThree.text = currentThirdTask
                checkTaskOne.isChecked = false
                checkTaskTwo.isChecked = false
                checkTaskThree.isChecked = false
                textView2.text = ""
            }
        }
    }

    private fun updateCheckboxes() {
        val allChecked = binding.checkTaskOne.isChecked && binding.checkTaskTwo.isChecked && binding.checkTaskThree.isChecked
        binding.checkTaskOne.isEnabled = !allChecked || binding.checkTaskOne.isChecked
        binding.checkTaskTwo.isEnabled = !allChecked || binding.checkTaskTwo.isChecked
        binding.checkTaskThree.isEnabled = !allChecked || binding.checkTaskThree.isChecked

        if (allChecked) {
            binding.checkTaskOne.isEnabled = false
            binding.checkTaskTwo.isEnabled = false
            binding.checkTaskThree.isEnabled = false
            binding.textView2.setText(R.string.splneno)
        }
    }
}





