package com.covid19.app.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.covid19.app.pojo.GlobalData
import com.covid19.app.retrofit.ApiHelper
import com.covid19.app.retrofit.RetrofitBuilder
import com.covid19.app.ui.adapter.CountryWiseAdapter
import com.covid19.app.ui.viewmodel.WorldViewModel
import com.covid19.app.utils.Constants.EEEddMMMyyyy
import com.covid19.app.utils.Constants.ymdFormat
import com.covid19.app.utils.Status
import com.covid19.app.utils.Utility.parseDate
import com.covid19.app.viewmodelfactory.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_world.*
import kotlinx.android.synthetic.main.fragment_world.view.*
import java.text.DecimalFormat

class WorldFragment : Fragment() {


    private lateinit var worldViewModel: WorldViewModel
    private lateinit var adapter: CountryWiseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_world, container, false)

        setUpViewmodel()
        setupObservers()
        setUpRecyclerView(root)
        return root
    }

    private fun setUpViewmodel() {
        worldViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiWorldService))
        ).get(WorldViewModel::class.java)


    }

    private fun setUpRecyclerView(root: View) {

        root.countryListRv.layoutManager = LinearLayoutManager(activity)
        adapter = CountryWiseAdapter(arrayListOf(), activity)
        root.countryListRv.addItemDecoration(
            DividerItemDecoration(
                root.countryListRv.context,
                (root.countryListRv.layoutManager as LinearLayoutManager).orientation
            )
        )
        root.countryListRv.adapter = adapter


    }


    private fun setupObservers() {
        activity?.let {
            worldViewModel.getWorldData().observe(it, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            //recyclerView.visibility = View.VISIBLE
                            worldPb.visibility = View.GONE

                            resource.data?.let { countrySummary -> worldData(countrySummary) }
                        }
                        Status.ERROR -> {
                            worldPb.visibility = View.GONE

                            if (it.status.equals("429")) {
                                setupObservers()
                            } else {
                                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                            }
                        }
                        Status.LOADING -> {
                            worldPb.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun worldData(countrySummaryModel: GlobalData) {

        val date: String = parseDate(countrySummaryModel.Date, ymdFormat, EEEddMMMyyyy).toString();
        Log.i("output_string", date);

        worldDateTimeStampTv.text = "Updated on $date"

        totWorldConfirmedCaseTv.text =
            DecimalFormat("#,##,###").format(countrySummaryModel.Global.TotalConfirmed.toDouble())
        confirmedWorldTodayTv.text =
            "+" + DecimalFormat("#,##,###").format(countrySummaryModel.Global.NewConfirmed.toDouble())

        val totWorldActcase =
            (countrySummaryModel.Global.TotalConfirmed - countrySummaryModel.Global.TotalRecovered + countrySummaryModel.Global.TotalDeaths).toDouble()

        totalWorldActiveCaseTv.text = DecimalFormat("#,##,###").format(totWorldActcase)

        activeWorldTodayTv.text =
            "+" + DecimalFormat("#,##,###").format(countrySummaryModel.Global.TotalConfirmed.toDouble())

        totWorldRecoveredCaseTv.text =
            DecimalFormat("#,##,###").format(countrySummaryModel.Global.TotalRecovered.toDouble())

        todayWorldRecoveredCaseTv.text =
            "+" + DecimalFormat("#,##,###").format(countrySummaryModel.Global.NewRecovered.toDouble())


        totWorldDeceasedCaseTv.text =
            DecimalFormat("#,##,###").format(countrySummaryModel.Global.TotalDeaths.toDouble())

        todayWorldDeceasedTv.text =
            "+" + DecimalFormat("#,##,###").format(countrySummaryModel.Global.NewDeaths.toDouble())

        adapter.apply {
            countrySummaryModel.let { addUsers(countrySummaryModel.Countries) }
            notifyDataSetChanged()
        }


    }


}
