package com.example.marvel.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.marvel.api.ApiRepository
import com.example.marvel.db.models.NetworkResponse
import com.example.marvel.adapter.HeroAdapter
import com.example.marvel.db.models.HeroData
import com.example.marvel.db.repository.HeroRepository
import com.example.marvel.utils.LOADED_DATA_SUCCESS

class MarvelViewModel : ViewModel() {

    lateinit var mAdapter: HeroAdapter
    lateinit var mHeroesList: ArrayList<HeroData>
    var heroData: HeroData? = null

    fun getHero(name: String, callback: (String) -> Unit) {
        when (val response = ApiRepository().getHero(name)) {
            is NetworkResponse.Success -> {
                mAdapter.update(response.value.data.results)
                callback(LOADED_DATA_SUCCESS)
            }

            is NetworkResponse.ApiError -> {
                callback(response.body.message)
            }
            is NetworkResponse.NetworkError -> {
                callback(response.error.toString())
            }
            is NetworkResponse.UnknownError -> {
                callback(response.error.toString())
            }
        }
    }

    fun addHeroToDB(context: Context, heroData: HeroData) {
        HeroRepository(context).addHero(heroData)
    }

    fun getSavedHeroDataFromDB(context: Context) {
        HeroRepository(context).getHeroesList()?.let {
            mHeroesList = it.toCollection(ArrayList())
            mAdapter.update(mHeroesList)
        }

    }
}