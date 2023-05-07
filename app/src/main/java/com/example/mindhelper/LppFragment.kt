package com.example.mindhelper

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.mindhelper.databinding.FragmentLppBinding

class LppFragment : Fragment() {

    private lateinit var binding: FragmentLppBinding
    private val REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCallLppD.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:116123"))
                startActivity(callIntent)
            } else {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CODE)
            }
        }
        binding.buttonWebsiteLpp.setOnClickListener {
            val websiteIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://cestazkrize.net/"))
            startActivity(websiteIntent)
        }
        binding.buttonCallLppR.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:116000"))
                startActivity(callIntent)
            } else {
                requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:116123"))
                    startActivity(callIntent)
                } else {
                    Toast.makeText(requireContext(), "Povolení k hovoru zamítnuto", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}