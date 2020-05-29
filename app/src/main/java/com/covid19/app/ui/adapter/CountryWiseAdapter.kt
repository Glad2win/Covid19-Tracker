package com.covid19.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.covid19.app.R
import com.covid19.app.pojo.GlobalCountry
import kotlinx.android.synthetic.main.country_list_item.view.*
import kotlinx.android.synthetic.main.state_list_item.view.staticLl
import java.text.DecimalFormat

class CountryWiseAdapter(
    private val countryModelList: ArrayList<GlobalCountry>, private val activity: FragmentActivity?

) :
    RecyclerView.Adapter<CountryWiseAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(countryModelList: GlobalCountry) {
            itemView.apply {
                confirmedCaseCountryTv.text =
                    DecimalFormat("#,##,###").format(countryModelList.TotalConfirmed.toDouble())
                countryNameTv.text = countryModelList.Country

                val activeCase =
                    countryModelList.TotalConfirmed - (countryModelList.TotalRecovered + countryModelList.TotalDeaths)

                activeCaseCountryTv.text = DecimalFormat("#,##,###").format(activeCase)
                recoveredCaseCountryTv.text =
                    DecimalFormat("#,##,###").format(countryModelList.TotalRecovered.toDouble())
                deceasedCaseCountryTv.text =
                    DecimalFormat("#,##,###").format(countryModelList.TotalDeaths.toDouble())

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.country_list_item, parent, false)
        )

    override fun getItemCount(): Int = countryModelList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(countryModelList[position])
        if (position % 2 == 0) {
            holder.itemView.staticLl!!.setBackgroundColor(activity?.resources!!.getColor(R.color.colorLiteGrey))
        } else {
            holder.itemView.staticLl!!.setBackgroundColor(activity?.resources!!.getColor(R.color.colorPrimary))
        }
    }

    fun addUsers(globalData: List<GlobalCountry>) {
        this.countryModelList.apply {
            clear()
            addAll(globalData)
        }

    }
}