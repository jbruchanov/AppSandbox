package com.scurab.android.appsandbox.ui.main

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.scurab.android.appsandbox.R
import com.scurab.android.appsandbox.databinding.MainFragmentBinding
import com.scurab.appsandbox.core.android.lifecycle.lifecycleLazy
import com.scurab.appsandbox.core.ref
import kotlinx.android.parcel.Parcelize

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private val sampleArgs: SampleArgs? by lazy {
        arguments?.let {
            MainFragmentArgs.fromBundle(it).sampleArgs
        }
    }

    private val viewBinding by lifecycleLazy { MainFragmentBinding.bind(view.ref) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            sampleArgs?.let { args ->
                message.append("\n${args.name}")
            }
            button.setOnClickListener { testNavigation() }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = defaultViewModelProviderFactory.create(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun testNavigation() {
        val actionMainFragmentToMainFragment2 =
            MainFragmentDirections.actionMainFragmentToMainFragment2(
                SampleArgs(
                    "Arguments passed",
                    1
                )
            )
        findNavController().navigate(actionMainFragmentToMainFragment2)
    }
}

@Parcelize
class SampleArgs(
    val name: String,
    val value: Int
) : Parcelable