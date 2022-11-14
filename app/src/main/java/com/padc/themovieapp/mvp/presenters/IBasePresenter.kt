package com.padc.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {
    fun onUiReady(owner: LifecycleOwner)
}