package com.arinspect.demo.view.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.arinspect.demo.R
import com.arinspect.demo.databinding.ActivityMainBinding
import com.arinspect.demo.network.RepositoryFactory
import com.arinspect.demo.view.BaseActivity
import com.arinspect.demo.view.adapter.RecyclerDataAdapter
import com.arinspect.demo.viewmodel.DataViewModel
import com.arinspect.demo.viewmodel.ViewModelFactory

class MainActivity : BaseActivity<ActivityMainBinding>(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var dataViewModel: DataViewModel

    private lateinit var mDataAdapter: RecyclerDataAdapter

    private var isSwipeRefresh = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_main)

        init()
    }

    private fun init() {

        mBinding.swipeRefreshLayout.setOnRefreshListener(this)

        // initialize viewModel class object
        dataViewModel =
            ViewModelProviders.of(this, ViewModelFactory(RepositoryFactory.createDataRepository()))
                .get(DataViewModel::class.java)

        // API call to get data
        webCallGetData()
    }

    private fun webCallGetData() {

        if (!isNetworkAvailable(this)) {
            displayShortToast(getString(R.string.msg_no_internet))
            isSwipeRefresh = false
            mBinding.swipeRefreshLayout.isRefreshing = false
            return
        }

        if (isSwipeRefresh) {
            mBinding.swipeRefreshLayout.isRefreshing = true
        } else {
            // start loader
            showLoader(this)
        }

        // make an API call
        if (::dataViewModel.isInitialized)
            dataViewModel.getDataFromServer()

        dataViewModel.responseModel.observe(this, Observer { it ->

            if (isSwipeRefresh) {
                isSwipeRefresh = false
                mBinding.swipeRefreshLayout.isRefreshing = false
            } else {
                // dismiss loader
                dismissLoader()
            }

            displayShortToast("Data Received Successfully...")

            initToolbar(
                mBinding.toolbar.toolbar,
                it.title ?: getString(R.string.app_name),
                mBinding.toolbar.txtHeaderTitle,
                showBackButton = false
            )

            mDataAdapter = RecyclerDataAdapter(this@MainActivity, it.data)
            mBinding.recyclerViewDataList.adapter = mDataAdapter

            showNoData(
                mBinding.recyclerViewDataList,
                mBinding.layoutNoData,
                it.data.isNullOrEmpty()
            )
        })
    }

    override fun onRefresh() {

        isSwipeRefresh = true

        // API call to get data
        webCallGetData()
    }
}
