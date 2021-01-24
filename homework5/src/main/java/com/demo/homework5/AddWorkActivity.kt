package com.demo.homework5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.AppCompatImageButton

class AddWorkActivity : AppCompatActivity() {

    private lateinit var save: AppCompatImageButton
    private lateinit var back: AppCompatImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addwork)

        save = findViewById<AppCompatImageButton>(R.id.save).apply {  }
        back = findViewById<AppCompatImageButton>(R.id.back).apply {  }

    }



}