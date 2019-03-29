package com.tekprosoft.plainnotes.utilities

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes

class MyViewBinder {
    companion object {

        @JvmStatic
        fun <T : View> Activity.bind(@IdRes res : Int) : Lazy<T> {
            @Suppress("UNCHECKED_CAST")
            return lazy(LazyThreadSafetyMode.NONE){ findViewById<T>(res) }
        }
    }
}