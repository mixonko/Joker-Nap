package com.myapplication.mixonko.jokernap.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.mixonko.jokernap.R
import com.myapplication.mixonko.jokernap.contract.GameContract
import com.myapplication.mixonko.jokernap.presenter.GamePresenter
import java.lang.Exception


class GameActivity : AppCompatActivity(), GameContract {

    private lateinit var frameLayout: FrameLayout
    private lateinit var imageView1: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var imageView3: ImageView
    private lateinit var youWin: ImageView
    private lateinit var shadow: ImageView
    private lateinit var time: TextView

    private lateinit var firstView: View
    private lateinit var secondView: View

    private var timeInMillis = 500L

    private lateinit var presenter: GamePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        presenter = GamePresenter(this)

        init()

        initImageView()

        setImageOnClickListener()

        setTag()

    }

    private fun init() { 
        time = findViewById(R.id.time)
        youWin = findViewById(R.id.you_win)
        shadow = findViewById(R.id.shadow)
        frameLayout = findViewById(R.id.frame)
        frameLayout.setOnClickListener{
            presenter.onBackgroundClick()
        }
        val layoutInflater: LayoutInflater = LayoutInflater.from(applicationContext)
        firstView = layoutInflater.inflate(R.layout.first_game_field, null)
        frameLayout.addView(firstView)

    }

    private fun initImageView() {
        imageView1 = findViewById(R.id.image_view_1)
        imageView2 = findViewById(R.id.image_view_2)
        imageView3 = findViewById(R.id.image_view_3)
    }

    private fun setImageOnClickListener() {
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

    private fun setTag() {
        imageView1.setTag(0)
        imageView2.setTag(1)
        imageView3.setTag(2)
    }

    override fun showYouWin() {
        youWin.visibility = View.VISIBLE
        shadow.visibility = View.VISIBLE
    }

    override fun setEnable(b: Boolean) {
        imageView1.isEnabled = b
        imageView2.isEnabled = b
        imageView3.isEnabled = b
    }

    private fun setFirstGameField() {

        try {
            frameLayout.removeView(firstView)
            frameLayout.removeView(secondView)
        } catch (e: Exception) { }

        firstView = layoutInflater.inflate(R.layout.first_game_field, null)

        frameLayout.addView(firstView)

        initImageView()
        setImageOnClickListener()

    }

    private fun setSecondGameField() {
        try {
            frameLayout.removeView(firstView)
            frameLayout.removeView(secondView)
        } catch (e: Exception) {  }

        secondView = layoutInflater.inflate(R.layout.second_game_field, null)

        frameLayout.addView(secondView)

        initImageView()
        setImageOnClickListener()

    }

    override fun animate12() {
        setFirstGameField()
        imageView1.animate()
            .translationXBy(180F)
            .translationYBy(-100F)
            .scaleYBy(-0.3F)
            .scaleXBy(-0.3F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView1.animate()
                    .translationXBy(180F)
                    .translationYBy(100F)
                    .scaleYBy(0.3F)
                    .scaleXBy(0.3F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()

        imageView2.animate()
            .translationXBy(-180F)
            .translationYBy(100F)
            .scaleYBy(0.2F)
            .scaleXBy(0.2F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView2.animate()
                    .translationXBy(-180F)
                    .translationYBy(-100F)
                    .scaleYBy(-0.2F)
                    .scaleXBy(-0.2F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()

    }

    override fun animate13() {
        setFirstGameField()

        imageView1.animate()
            .translationXBy(360F)
            .translationYBy(-150F)
            .scaleYBy(-0.3F)
            .scaleXBy(-0.3F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView1.animate()
                    .translationXBy(360F)
                    .translationYBy(150F)
                    .scaleYBy(0.3F)
                    .scaleXBy(0.3F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()

        imageView3.animate()
            .translationXBy(-360F)
            .translationYBy(100F)
            .scaleYBy(0.2F)
            .scaleXBy(0.2F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView3.animate()
                    .translationXBy(-360F)
                    .translationYBy(-100F)
                    .scaleYBy(-0.2F)
                    .scaleXBy(-0.2F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()
    }

    override fun animate23() {
        setFirstGameField()

        imageView2.animate()
            .translationXBy(180F)
            .translationYBy(-100F)
            .scaleYBy(-0.3F)
            .scaleXBy(-0.3F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView2.animate()
                    .translationXBy(180F)
                    .translationYBy(100F)
                    .scaleYBy(0.3F)
                    .scaleXBy(0.3F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()

        imageView3.animate()
            .translationXBy(-180F)
            .translationYBy(100F)
            .scaleYBy(0.2F)
            .scaleXBy(0.2F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView3.animate()
                    .translationXBy(-180F)
                    .translationYBy(-100F)
                    .scaleYBy(-0.2F)
                    .scaleXBy(-0.2F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    override fun animate21() {
        setSecondGameField()

        imageView2.animate()
            .translationXBy(-180F)
            .translationYBy(-100F)
            .scaleYBy(-0.3F)
            .scaleXBy(-0.3F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView2.animate()
                    .translationXBy(-180F)
                    .translationYBy(100F)
                    .scaleYBy(0.3F)
                    .scaleXBy(0.3F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()

        imageView1.animate()
            .translationXBy(180F)
            .translationYBy(100F)
            .scaleYBy(0.2F)
            .scaleXBy(0.2F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView1.animate()
                    .translationXBy(180F)
                    .translationYBy(-100F)
                    .scaleYBy(-0.2F)
                    .scaleXBy(-0.2F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()
    }

    override fun animate31() {
        setSecondGameField()

        imageView3.animate()
            .translationXBy(-360F)
            .translationYBy(-150F)
            .scaleYBy(-0.3F)
            .scaleXBy(-0.3F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView3.animate()
                    .translationXBy(-360F)
                    .translationYBy(150F)
                    .scaleYBy(0.3F)
                    .scaleXBy(0.3F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()

        imageView1.animate()
            .translationXBy(360F)
            .translationYBy(100F)
            .scaleYBy(0.2F)
            .scaleXBy(0.2F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView1.animate()
                    .translationXBy(360F)
                    .translationYBy(-100F)
                    .scaleYBy(-0.2F)
                    .scaleXBy(-0.2F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()
    }

    override fun animate32() {
        setSecondGameField()

        imageView3.animate()
            .translationXBy(-180F)
            .translationYBy(-100F)
            .scaleYBy(-0.3F)
            .scaleXBy(-0.3F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView2.animate()
                    .translationXBy(-180F)
                    .translationYBy(100F)
                    .scaleYBy(0.3F)
                    .scaleXBy(0.3F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()

        imageView3.animate()
            .translationXBy(180F)
            .translationYBy(100F)
            .scaleYBy(0.2F)
            .scaleXBy(0.2F)
            .setDuration(timeInMillis)
            .withEndAction {
                imageView3.animate()
                    .translationXBy(180F)
                    .translationYBy(-100F)
                    .scaleYBy(-0.2F)
                    .scaleXBy(-0.2F)
                    .setDuration(timeInMillis)
                    .start()
            }.start()
    }

    override fun animateThimble1() {
        imageView1.animate()
            .translationYBy(-400F)

    }

    override fun animateThimble2() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun animateThimble3() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
