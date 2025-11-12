package com.example.mvc_kotlin.common.dependencyinjection

import com.example.mvc_kotlin.common.Constants
import com.example.mvc_kotlin.networking.StackoverflowApi
import com.example.mvc_kotlin.screens.common.dialogs.DialogsEventBus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CompositionRoot {

    private var retrofit: Retrofit? = null
    private var dialogsEventBus: DialogsEventBus?=null
    fun getStackoveflowApi(): StackoverflowApi {
        return getRetrofit().create(StackoverflowApi::class.java)
    }

    private fun getRetrofit(): Retrofit {
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun getDialogsEventBus(): DialogsEventBus {
        if(dialogsEventBus == null){
            dialogsEventBus = DialogsEventBus()
        }
        return dialogsEventBus!!
    }


}