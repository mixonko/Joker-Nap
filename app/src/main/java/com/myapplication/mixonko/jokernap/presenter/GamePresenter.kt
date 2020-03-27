package com.myapplication.mixonko.jokernap.presenter

import com.myapplication.mixonko.jokernap.contract.GameContract

class GamePresenter(val view: GameContract)   {
    fun onImageClick() {
        view.setFirstGameField()
    }

}
