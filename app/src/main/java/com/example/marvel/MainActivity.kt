package com.example.marvel

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.marvel.fragments.MarvelFragment
import com.example.marvel.utils.addTo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            MarvelFragment().addTo(this@MainActivity, R.id.container)
            splash.visibility = View.GONE
        }
    }


    fun changeLoadingState(isVisible: Boolean) {
        if (isVisible) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }

    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0 || count == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}