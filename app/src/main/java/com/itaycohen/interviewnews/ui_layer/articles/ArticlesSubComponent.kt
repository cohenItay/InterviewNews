package com.itaycohen.interviewnews.ui_layer.articles

import com.itaycohen.interviewnews.dependency_injection.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ArticlesSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create() : ArticlesSubComponent
    }
}