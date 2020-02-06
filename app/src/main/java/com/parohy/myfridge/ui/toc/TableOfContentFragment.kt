package com.parohy.myfridge.ui.toc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.parohy.myfridge.R
import timber.log.Timber

class TableOfContentFragment: Fragment() {
    private lateinit var viewModel: TableOfContentViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TableOfContentViewModel::class.java)
        viewModel.foodList.observe(viewLifecycleOwner, Observer {
            Timber.d("List: $it")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toc, container, false)
    }
}