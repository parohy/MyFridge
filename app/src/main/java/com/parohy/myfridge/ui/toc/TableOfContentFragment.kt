package com.parohy.myfridge.ui.toc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.parohy.myfridge.R
import com.parohy.myfridge.ui.ActionFragment
import com.parohy.myfridge.ui.ActionInterface
import kotlinx.android.synthetic.main.fragment_toc.*

class TableOfContentFragment : Fragment(), ActionFragment {
    private lateinit var viewModel: TableOfContentViewModel
    private lateinit var listAdapter: FoodListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listAdapter = FoodListAdapter(requireContext())
        viewModel = ViewModelProvider(this).get(TableOfContentViewModel::class.java)
        viewModel.foodList.observe(viewLifecycleOwner, Observer {
            listAdapter.updateList(it)
        })
        foodList.layoutManager = LinearLayoutManager(requireContext())
        foodList.adapter = listAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_toc, container, false)

    override fun onAction(actionInterface: ActionInterface) {
        actionInterface.actionAdd()
    }
}