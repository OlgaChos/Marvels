package com.example.marvel.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.marvel.MainActivity
import com.example.marvel.R
import kotlinx.android.synthetic.main.fragment_about_user.*

abstract class BaseFragment<MViewModel : ViewModel> : Fragment() {

    lateinit var mRootView: View
    lateinit var mViewModel: MViewModel
    lateinit var mActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as MainActivity
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): Class<MViewModel>

    abstract fun start()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(mActivity).get(getViewModel())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(getLayoutId(), container, false)

        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
    }

    fun changeLoadingState(isVisible: Boolean) {
        mActivity.changeLoadingState(isVisible)
    }

}