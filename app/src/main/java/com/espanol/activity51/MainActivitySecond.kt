package com.espanol.activity51

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import com.espanol.activity51.databinding.ActivityMainSecondBinding

class MainActivitySecond : AppCompatActivity() {

    companion object {
        private const val CAMERA_PERMISSION_CODE = 10001
        private const val LOCATION_PERMISSION_CODE = 10002
        private const val AUDIO_PERMISSION_CODE = 10003
        private const val CONTACT_PERMISSION_CODE = 10004
    }

    private lateinit var binding: ActivityMainSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTextViews()

        binding.buttonCameraPermission.setOnClickListener {
            askForPermission(android.Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE, binding.textCameraGranted)
        }

        binding.buttonLocationPermission.setOnClickListener {
            askForPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_PERMISSION_CODE, binding.textLocationGranted)
        }

        binding.buttonAudioPermission.setOnClickListener {
            askForPermission(android.Manifest.permission.RECORD_AUDIO, AUDIO_PERMISSION_CODE, binding.textAudioGranted)
        }

        binding.buttonContactPermission.setOnClickListener {
            askForPermission(android.Manifest.permission.READ_CONTACTS, CONTACT_PERMISSION_CODE, binding.textContactGranted)
        }
    }

    private fun askForPermission(permission: String, permissionCode: Int, textView: TextView) {
        if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            textView.isVisible = true
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(permission), permissionCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            when (requestCode) {
                CAMERA_PERMISSION_CODE -> {
                    checkPermission(android.Manifest.permission.CAMERA, binding.textCameraGranted)
                    binding.currentActivatedPermissionTextView.text = "${binding.currentActivatedPermissionTextView.text}\nCamera Permission: YES"
                }
                LOCATION_PERMISSION_CODE -> {
                    checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, binding.textLocationGranted)
                    binding.currentActivatedPermissionTextView.text = "${binding.currentActivatedPermissionTextView.text}\nLocation Permission: YES"
                }
                AUDIO_PERMISSION_CODE -> {
                    checkPermission(android.Manifest.permission.RECORD_AUDIO, binding.textAudioGranted)
                    binding.currentActivatedPermissionTextView.text = "${binding.currentActivatedPermissionTextView.text}\nAudio Permission: YES"
                }
                CONTACT_PERMISSION_CODE -> {
                    checkPermission(android.Manifest.permission.READ_CONTACTS, binding.textContactGranted)
                    binding.currentActivatedPermissionTextView.text = "${binding.currentActivatedPermissionTextView.text}\nContacts Permission: YES"
                }
            }
        }
    }

    private fun checkPermission(permission: String, textView: TextView) {
        if(ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            textView.isVisible = true
        }
    }

    private fun checkPermission(permission: String): Boolean {
        if(ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }

    private fun initTextViews() {
        binding.textCameraGranted.isVisible = false
        binding.textLocationGranted.isVisible = false
        binding.textAudioGranted.isVisible = false
        binding.textContactGranted.isVisible = false

        checkPermission(android.Manifest.permission.CAMERA, binding.textCameraGranted)
        checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, binding.textLocationGranted)
        checkPermission(android.Manifest.permission.RECORD_AUDIO, binding.textAudioGranted)
        checkPermission(android.Manifest.permission.READ_CONTACTS, binding.textContactGranted)

        if (checkPermission(android.Manifest.permission.CAMERA)) {
            binding.currentActivatedPermissionTextView.text = "${binding.currentActivatedPermissionTextView.text}\nCamera Permission: YES"
        }
        if (checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            binding.currentActivatedPermissionTextView.text = "${binding.currentActivatedPermissionTextView.text}\nLocation Permission: YES"
        }
        if (checkPermission(android.Manifest.permission.RECORD_AUDIO)) {
            binding.currentActivatedPermissionTextView.text = "${binding.currentActivatedPermissionTextView.text}\nAudio Permission: YES"
        }
        if (checkPermission(android.Manifest.permission.READ_CONTACTS)) {
            binding.currentActivatedPermissionTextView.text = "${binding.currentActivatedPermissionTextView.text}\nContacts Permission: YES"
        }
    }

}