package com.example.mindhelper

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mindhelper.databinding.FragmentTaskBinding
import java.util.*

class TaskFragment : Fragment() {

    private val viewModel: TaskFragmentViewModel by viewModels()
    private lateinit var binding: FragmentTaskBinding

    private val CHANNEL_ID = "channel1"
    private val NOTIFICATION_ID = 1




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkTaskOne.isChecked = load("checkTaskOne")
        binding.checkTaskTwo.isChecked = load("checkTaskTwo")
        binding.checkTaskThree.isChecked = load("checkTaskThree")
        binding.checkTaskOne.isEnabled = load("checkTaskOneEnabled")
        binding.checkTaskTwo.isEnabled = load("checkTaskTwoEnabled")
        binding.checkTaskThree.isEnabled = load("checkTaskThreeEnabled")

        viewModel.changeTasks()
        generateRandTask()
        setCheckedChangedListener()
        binding.buttonRefresh.setOnClickListener {
            dailyRefresh()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("NOTE","onDestroy")
        save("checkTaskOne",binding.checkTaskOne.isChecked())
        save("checkTaskTwo",binding.checkTaskTwo.isChecked())
        save("checkTaskThree",binding.checkTaskThree.isChecked())
        save("checkTaskOneEnabled",binding.checkTaskOne.isEnabled())
        save("checkTaskTwoEnabled",binding.checkTaskTwo.isEnabled())
        save("checkTaskThreeEnabled",binding.checkTaskThree.isEnabled())
        saveString("stringDone",binding.textView2.toString())
    }

    private fun load(name: String): Boolean {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return false
        return sharedPref.getBoolean(name, false)
    }


    private fun save(name: String, boolean: Boolean) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putBoolean(name, boolean)
            apply()
        }
    }

    private fun saveString(name: String, text: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(name, text)
            apply()
        }
    }


    private fun generateRandTask(){
        binding.checkTaskOne.text = viewModel.currentFirstTask
        binding.checkTaskTwo.text = viewModel.currentSecTask
        binding.checkTaskThree.text = viewModel.currentThirdTask
    }

    private fun dailyRefresh(){
        reloadFragment()
        //addNotification()
    }

    private fun reloadFragment(){
        binding.checkTaskOne.isEnabled = true
        binding.checkTaskTwo.isEnabled = true
        binding.checkTaskThree.isEnabled = true
        binding.checkTaskOne.isChecked = false
        binding.checkTaskTwo.isChecked = false
        binding.checkTaskThree.isChecked = false
        generateRandTask()
    }

    private fun setCheckedChangedListener() {
        listOf(binding.checkTaskOne,binding.checkTaskTwo,binding.checkTaskThree).forEach {
            it.setOnCheckedChangeListener{ _ , _ ->
                if(binding.checkTaskOne.isChecked && binding.checkTaskTwo.isChecked && binding.checkTaskThree.isChecked) {
                    binding.checkTaskOne.isEnabled = false
                    binding.checkTaskTwo.isEnabled = false
                    binding.checkTaskThree.isEnabled = false
                    binding.textView2.setText(R.string.splneno)

                }
            }
        }
    }
   /* private fun addNotification() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notiBuilder = NotificationCompat.Builder(requireContext(),CHANNEL_ID)
        notiBuilder.setSmallIcon(R.drawable.ic_stat_name)
        notiBuilder.setContentTitle("Nové úkoly")
        notiBuilder.setContentText("Nové úkoly jsou k dispozci")
        notiBuilder.priority = NotificationCompat.PRIORITY_DEFAULT
        notiBuilder.setContentIntent(pendingIntent)
        notiBuilder.setAutoCancel(true)
        val notiManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        with(notiManager) {
            notify(NOTIFICATION_ID, notiBuilder.build())
        }
    }*/



}



