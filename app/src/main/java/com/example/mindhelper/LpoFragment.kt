package com.example.mindhelper

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.Manifest
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.mindhelper.databinding.FragmentLpoBinding


class LpoFragment : Fragment() {

    private lateinit var binding: FragmentLpoBinding
    private val REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLpoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCallLpo.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:116006"))
            startActivity(callIntent)
        } else {
            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CODE)
        }
        }
        binding.buttonWebsiteLpo.setOnClickListener {
            val websiteIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.116006.cz/"))
            startActivity(websiteIntent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:116006"))
                    startActivity(callIntent)
                } else {
                    Toast.makeText(requireContext(), "Povolení k hovoru zamítnuto", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}