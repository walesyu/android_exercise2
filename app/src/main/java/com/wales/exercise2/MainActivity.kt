package com.wales.exercise2

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val _cameraRequestCode = 1
    private val _albumRequestCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_select_pic.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    Toast.makeText(this, getString(R.string.you_have_revoke_the_permission), Toast.LENGTH_SHORT);
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100);
                }
            } else {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, _cameraRequestCode)
            }
        }

        btn_select_album.setOnClickListener {
            val getIntent = Intent(Intent.ACTION_GET_CONTENT)
            getIntent.type = "image/*"

            val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickIntent.type = "image/*"

            val chooserIntent = Intent.createChooser(getIntent,  getString(R.string.select_album))
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))
            startActivityForResult( chooserIntent, _albumRequestCode)
        }

        btn_next.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("degree", 100)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            _cameraRequestCode -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    imageView.setImageBitmap(data.extras.get("data") as Bitmap)
                }
            }

            _albumRequestCode -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val resolver = this.contentResolver
                    val bitmap = MediaStore.Images.Media.getBitmap(resolver, data.data)
                    imageView.setImageBitmap(bitmap)
                }
            }
            else -> {

            }
        }
    }
}
