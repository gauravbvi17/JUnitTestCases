package com.example.android.codelabs.sunandsandsports.listener

import com.example.android.codelabs.sunandsandsports.model.UserResult

interface ItemClickListener {
    fun passData(position:Int,data: UserResult)
}