package com.covid19.app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.covid19.app.R
import com.covid19.app.pojo.IndiaModel
import com.covid19.app.retrofit.ApiHelper
import com.covid19.app.retrofit.RetrofitBuilder
import com.covid19.app.ui.adapter.StateWiseAdapter
import com.covid19.app.ui.viewmodel.HomeViewModel
import com.covid19.app.utils.Constants
import com.covid19.app.utils.Status
import com.covid19.app.utils.Utility
import com.covid19.app.viewmodelfactory.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.text.DecimalFormat

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: StateWiseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        setUpViewmodel()
        setUpRecyclerView(root)
        setupObservers()
        return root
    }


    private fun setUpViewmodel() {
        homeViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiIndService))
        ).get(HomeViewModel::class.java)


    }

    private fun setUpRecyclerView(root: View) {
        root.stateListRv.layoutManager = LinearLayoutManager(activity)
        adapter = StateWiseAdapter(arrayListOf(), activity)
        root.stateListRv.addItemDecoration(
            DividerItemDecoration(
                root.stateListRv.context,
                (root.stateListRv.layoutManager as LinearLayoutManager).orientation
            )
        )
        root.stateListRv.adapter = adapter

    }

    private fun setupObservers() {
        activity?.let {
            homeViewModel.getHomeIndia().observe(it, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            //recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            resource.data?.let { users -> homeIndiaData(users) }
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            if (it.status.equals("429")) {
                                setupObservers()
                            } else {
                                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                            }
                        }
                        Status.LOADING -> {
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun homeIndiaData(indiaModel: IndiaModel) {

        val date: String = Utility.parseDate(
            indiaModel.tested?.get(indiaModel.tested.size - 1)!!.updatetimestamp,
            Constants.indiaYmdFormat,
            Constants.EEEddMMMyyyy
        ).toString()

        dateTimeStampTv.text =
            "Updated on ${date}"

        val convertedString: String =
            DecimalFormat("#,##,###").format(indiaModel.statewise!!.get(index = 0).confirmed!!.toDouble())

        totConfirmedCaseTv.text = convertedString


        confirmedTodayTv.text =
            "+" + DecimalFormat("#,##,###").format(indiaModel.casesTimeSeries?.get(index = (indiaModel.casesTimeSeries.size) - 1)!!.dailyconfirmed!!.toDouble())

        totalActiveCaseTv.text =
            DecimalFormat("#,##,###").format(indiaModel.statewise.get(index = 0).active!!.toDouble())

        activeTodayTv.text =
            "+" + DecimalFormat("#,##,###").format(indiaModel.casesTimeSeries.get(index = (indiaModel.casesTimeSeries.size) - 1).dailyconfirmed!!.toDouble())

        totRecoveredCaseTv.text =
            DecimalFormat("#,##,###").format(indiaModel.statewise.get(index = 0).recovered!!.toDouble())

        todayRecoveredCaseTv.text =
            "+" + DecimalFormat("#,##,###").format(indiaModel.casesTimeSeries.get(index = (indiaModel.casesTimeSeries.size) - 1).dailyrecovered!!.toDouble())


        totDeceasedCaseTv.text =
            DecimalFormat("#,##,###").format(indiaModel.statewise.get(index = 0).deaths!!.toDouble())

        todayDeceasedTv.text =
            "+" + DecimalFormat("#,##,###").format(indiaModel.casesTimeSeries.get(index = (indiaModel.casesTimeSeries.size) - 1).dailydeceased!!.toDouble())


        adapter.apply {
            indiaModel.statewise.let { it.let { it1 -> addUsers(it1) } }
            notifyDataSetChanged()
        }
    }


}
