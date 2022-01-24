package com.example.marvel.fragments

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.adapter.HeroAdapter
import com.example.marvel.base.BaseFragment
import com.example.marvel.utils.LOADED_DATA_SUCCESS
import com.example.marvel.utils.addTo
import com.example.marvel.utils.showAlertDialog
import com.example.marvel.viewModel.MarvelViewModel
import kotlinx.android.synthetic.main.fragment_marvel.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MarvelFragment : BaseFragment<MarvelViewModel>() {

    private var isDelete = false

    override fun getLayoutId(): Int {
        return R.layout.fragment_marvel
    }

    override fun getViewModel(): Class<MarvelViewModel> {
        return MarvelViewModel::class.java
    }

    override fun start() {
        initRecyclerView()
        mViewModel.getSavedHeroDataFromDB(mActivity)
        checkIsNeedToShowEmptyState()
        searchButtonLogic()
    }

    private fun searchButtonLogic() {
        searchIcon.setOnClickListener {
            title.visibility = View.GONE
            search.visibility = View.VISIBLE
            searchNewHero()
        }
    }

    private fun searchNewHero() {
        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, before: Int, p3: Int) {
                isDelete = before != 0
            }

            override fun afterTextChanged(s: Editable?) {
                if (isDelete) {
                    if (s.isNullOrEmpty()) {
                        changeLoadingState(false)
                        checkIsNeedToShowEmptyState()
                        mViewModel.mAdapter.update(mViewModel.mHeroesList)
                    }
                } else {
                    changeLoadingState(true)
                    emptyState.visibility = View.GONE
                    GlobalScope.launch(Dispatchers.Main) {
                        delay(700)
                        mViewModel.getHero(s.toString()) {
                            if (it != LOADED_DATA_SUCCESS) {
                                searchBoxAnimation()
                                mActivity.showAlertDialog()
                            }
                            changeLoadingState(false)
                        }

                    }
                }
            }

        })

        search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                searchBoxAnimation()
                true
            } else false
        }
    }

    private fun initRecyclerView() {
        mViewModel.mAdapter = HeroAdapter(ArrayList()) {
            mViewModel.heroData = it
            mViewModel.addHeroToDB(mActivity, it)
            AboutHeroFragment().addTo(mActivity, R.id.container)
        }
        marvelRV.layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
        marvelRV.adapter = mViewModel.mAdapter
    }

    private fun searchBoxAnimation() {
        title.visibility = View.VISIBLE
        search.visibility = View.GONE
        mRootView.let {
            val imm =
                mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun checkIsNeedToShowEmptyState() {
        if (mViewModel.mHeroesList.isNotEmpty()) {
            emptyState.visibility = View.GONE
        } else {
            emptyState.visibility = View.VISIBLE
        }
    }


}