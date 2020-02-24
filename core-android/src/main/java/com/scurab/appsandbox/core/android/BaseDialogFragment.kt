package com.scurab.appsandbox.core.android

import android.content.Context
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment : DialogFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    protected abstract fun inject()
}