package com.myapplication.mixonko.jokernap.presenter

import com.myapplication.mixonko.jokernap.view.MenuActivity

class MenuPresenter(private val view : MenuActivity) {
    fun onPlayClick() {
        view.startGameActivity()
    }

    fun onSettingsClick() {
        view.startSettings()
    }

    fun onWebViewClick() {
        view.startWebViewActivity()
    }
}
