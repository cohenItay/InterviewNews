package com.itaycohen.interviewnews.ui_layer.articles

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.itaycohen.interviewnews.application.di.DaggerAppComponent
import javax.inject.Inject

class ArticleListFragment @Inject @JvmOverloads constructor(
    @LayoutRes layoutRes: Int = 0
) : Fragment(layoutRes) {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}