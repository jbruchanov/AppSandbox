package com.scurab.android.feature1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.scurab.android.feature1.di.Feature1ComponentProvider
import com.scurab.appsandbox.core.android.BaseFragment
import com.scurab.appsandbox.core.android.view.HasProgressBar
import com.scurab.appsandbox.core.android.util.AndroidInjector.provider
import com.scurab.appsandbox.feature1.R
import com.scurab.appsandbox.model.Person

class ListFragment : BaseFragment() {
    override val viewModel by viewModels<ListFragmentViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        viewModel.test()
        requireActivity().onBackPressedDispatcher.addCallback {  }
        lifecycleScope.launchWhenResumed {

        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun observe() {
        viewModel.apply {
            (activity as? HasProgressBar?)?.bind(viewModel, this@ListFragment)
            persons.observe(viewLifecycleOwner, Observer<List<Person>> {
                logger.d("Data") {
                    "Data ${it.size}"
                }
            })
        }
    }

    override fun inject() {
        provider(requireContext(), Feature1ComponentProvider::class.java)
            .feature1Component
            .inject(this)
    }
}