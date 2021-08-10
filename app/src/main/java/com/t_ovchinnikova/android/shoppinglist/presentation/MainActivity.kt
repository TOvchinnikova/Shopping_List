package com.t_ovchinnikova.android.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.t_ovchinnikova.android.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this) [MainViewModel::class.java]

        viewModel.shopList.observe(this) {
            Log.d("MyLog", it.toString())
        }

        viewModel.getShopList()

    }
}