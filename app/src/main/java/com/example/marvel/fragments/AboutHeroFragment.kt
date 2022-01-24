package com.example.marvel.fragments

import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.adapter.UrlAdapter
import com.example.marvel.base.BaseFragment
import com.example.marvel.viewModel.MarvelViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_about_user.*
import kotlinx.android.synthetic.main.url_item.*


class AboutHeroFragment : BaseFragment<MarvelViewModel>() {

    var asap = 1
    lateinit var urlAdapter: UrlAdapter


    override fun getLayoutId(): Int {
        return R.layout.fragment_about_user
    }

    override fun getViewModel(): Class<MarvelViewModel> {
        return MarvelViewModel::class.java

    }

    override fun start() {
        drawView()
        initRecyclerView()
        back.setOnClickListener {
            fragmentManager?.popBackStack()
        }

    }


    private fun drawView() {
        Picasso.get().load(mViewModel.heroData!!.thumbnail.portrait()).into(image)
        name.text = mViewModel.heroData!!.name
        description.text = mViewModel.heroData!!.showDescription()
        film_detail_favorites.setOnClickListener{
            if (asap == 1) {
                film_detail_favorites.setImageResource(R.drawable.ic_in_favorites)
                asap = 0
            } else {
                film_detail_favorites.setImageResource(R.drawable.ic_not_in_favorite)
                asap = 1
            }
        }
    }

    private fun initRecyclerView() {
        urlAdapter = UrlAdapter(mViewModel.heroData!!.urls) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        }
        urlRV.layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        urlRV.adapter = urlAdapter

    }
}