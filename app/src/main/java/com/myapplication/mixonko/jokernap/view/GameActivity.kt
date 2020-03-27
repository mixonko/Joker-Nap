package com.myapplication.mixonko.jokernap.view

import android.os.Bundle 
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.mixonko.jokernap.R
import com.myapplication.mixonko.jokernap.contract.GameContract
import com.myapplication.mixonko.jokernap.presenter.GamePresenter


class GameActivity: AppCompatActivity(), GameContract{

    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
    private lateinit var youWin: ImageView
    private lateinit var shadow: ImageView
    private lateinit var time: TextView

    private lateinit var presenter: GamePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        presenter = GamePresenter(this)

        init()

        setOnClickListener()

        setTag()
    }

    private fun init(){
        time = findViewById(R.id.time)
        youWin = findViewById(R.id.you_win)
        shadow = findViewById(R.id.shadow)
        imageView1 = findViewById(R.id.image_view_1)
        imageView2 = findViewById(R.id.image_view_2)
        imageView3 = findViewById(R.id.image_view_3)
    }

    private fun setOnClickListener(){
        imageView1.setOnClickListener {
            presenter.onImageClick()
        }
        imageView2.setOnClickListener {
            presenter.onImageClick()
        }
        imageView3.setOnClickListener {
            presenter.onImageClick()
        }

    }

    private fun setTag(){
        imageView1.setTag(0)
        imageView2.setTag(1)
        imageView3.setTag(2)
    }

    override fun showYouWin() {
        youWin.visibility = View.VISIBLE
        shadow.visibility = View.VISIBLE
    }

    override fun setEnable(b: Boolean) {
        imageView1.isEnabled = false
        imageView2.isEnabled = false
        imageView3.isEnabled = false
    }

    override fun setFirstGameField() {
//        val frame: FrameLayout = findViewById(R.id.frame)
//        val layoutInflater: LayoutInflater = LayoutInflater.from(applicationContext)
//        val view = layoutInflater.inflate(R.layout.second_game_field, null)
    }

    override fun setSecondGameField() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animate12() {

        imageView1.animate()
            .translationXBy(180F)
            .translationYBy(-100F)
            .scaleYBy(-0.3F)
            .scaleXBy(-0.3F)
            .withEndAction{
                imageView1.animate()
                    .translationXBy(180F)
                    .translationYBy(100F)
                    .scaleYBy(0.3F)
                    .scaleXBy(0.3F)
                    .start()
            }.start()

        imageView2.animate()
            .translationXBy(-180F)
            .translationYBy(100F)
            .scaleYBy(0.2F)
            .scaleXBy(0.2F)
            .withEndAction{
                imageView2.animate()
                    .translationXBy(-180F)
                    .translationYBy(-100F)
                    .scaleYBy(-0.2F)
                    .scaleXBy(-0.2F)
                    .start()
            }.start()

    }

    override fun animate13() {
        imageView1.animate()
            .translationXBy(360F)
            .translationYBy(-150F)
            .scaleYBy(-0.3F)
            .scaleXBy(-0.3F)
            .withEndAction{
                imageView1.animate()
                    .translationXBy(360F)
                    .translationYBy(150F)
                    .scaleYBy(0.3F)
                    .scaleXBy(0.3F)
                    .start()
            }.start()

        imageView3.animate()
            .translationXBy(-360F)
            .translationYBy(100F)
            .scaleYBy(0.2F)
            .scaleXBy(0.2F)
            .withEndAction{
                imageView3.animate()
                    .translationXBy(-360F)
                    .translationYBy(-100F)
                    .scaleYBy(-0.2F)
                    .scaleXBy(-0.2F)
                    .start()
            }.start()
    }

    override fun animate23() {
        imageView2.animate()
            .translationXBy(180F)
            .translationYBy(-100F)
            .scaleYBy(-0.3F)
            .scaleXBy(-0.3F)
            .withEndAction{
                imageView2.animate()
                    .translationXBy(180F)
                    .translationYBy(100F)
                    .scaleYBy(0.3F)
                    .scaleXBy(0.3F)
                    .start()
            }.start()

        imageView3.animate()
            .translationXBy(-180F)
            .translationYBy(100F)
            .scaleYBy(0.2F)
            .scaleXBy(0.2F)
            .withEndAction{
                imageView3.animate()
                    .translationXBy(-180F)
                    .translationYBy(-100F)
                    .scaleYBy(-0.2F)
                    .scaleXBy(-0.2F)
                    .start()
            }.start()
    }

    override fun animate21() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animate31() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animate32() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
