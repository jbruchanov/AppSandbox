package com.scurab.android.feature1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.scurab.android.feature1.di.Feature1ComponentProvider
import com.scurab.appsandbox.core.android.BaseFragment
import com.scurab.appsandbox.core.android.lifecycle.viewBinding
import com.scurab.appsandbox.core.android.util.AndroidInjector.provider
import com.scurab.appsandbox.core.ref
import com.scurab.appsandbox.feature1.R
import com.scurab.appsandbox.feature1.databinding.FragmentListBinding
import com.scurab.appsandbox.model.Person

class ListFragment : BaseFragment() {
    private val viewBinding by viewBinding { FragmentListBinding.bind(view.ref) }
    private val viewModel by viewModels<ListFragmentViewModel> { viewModelFactory }

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
    }

    private fun observe() {
        viewModel.persons.observe(viewLifecycleOwner, Observer<List<Person>> {
            logger.d("Data") {
                "Data ${it.size}"
            }
        })
    }

    override fun inject() {
        provider(requireContext(), Feature1ComponentProvider::class.java)
            .feature1Component
            .inject(this)
    }
}