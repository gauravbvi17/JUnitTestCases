package com.example.android.codelabs.sunandsandsports.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.android.codelabs.sunandsandsports.R
import com.example.android.codelabs.sunandsandsports.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var toolbar:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViews()
    }

    private fun initializeViews() {
        dataBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        toolbar=dataBinding.toolbar
        setSupportActionBar(toolbar)
    }

    fun changeToolbar(title : String,isVisible : Boolean)
    {

        if(isVisible)
        {
            dataBinding.topBar.visibility=View.VISIBLE
        }
        else
        {
            dataBinding.topBar.visibility=View.GONE
        }

    }

}