package com.demo.homework5.work

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageButton
import com.demo.homework5.R

class EditDeleteWork : AppCompatActivity() {

    private lateinit var back: AppCompatImageButton
    private lateinit var save: AppCompatImageButton
    private lateinit var delete: AppCompatImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_delete_work)
    }
}