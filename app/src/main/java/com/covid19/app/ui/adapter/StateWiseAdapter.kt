package com.covid19.app.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.covid19.app.R
import com.covid19.app.pojo.IndiaStatewise
import kotlinx.android.synthetic.main.state_list_item.view.*
import java.text.DecimalFormat

class StateWiseAdapter(
    private val indiaStateWise: ArrayList<IndiaStatewise>, private val activity: FragmentActivity?

) :
    RecyclerView.Adapter<StateWiseAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(indiaStatewise: IndiaStatewise) {
            itemView.apply {
                confirmedCaseTv.text =
                    DecimalFormat("#,##,###").format(indiaStatewise.confirmed!!.toDouble())
                stateNameTv.text = indiaStatewise.state
                activeCaseTv.text =
                    DecimalFormat("#,##,###").format(indiaStatewise.active!!.toDouble())
                recoveredCaseTv.text =
                    DecimalFormat("#,##,###").format(indiaStatewise.recovered!!.toDouble())
                deceasedCaseTv.text =
                    DecimalFormat("#,##,###").format(indiaStatewise.deaths!!.toDouble())

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.state_list_item, parent, false)
        )

    override fun getItemCount(): Int = indiaStateWise.size - 1

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(indiaStateWise[position + 1])
        if (position % 2 == 0) {
            holder.itemView.staticLl!!.setBackgroundColor(activity?.resources!!.getColor(R.color.colorLiteGrey))
        } else {
            holder.itemView.staticLl!!.setBackgroundColor(activity?.resources!!.getColor(R.color.colorPrimary))
        }
    }

    fun addUsers(indiaStatewise: List<IndiaStatewise>) {
        this.indiaStateWise.apply {
            clear()
            addAll(indiaStatewise)
        }

    }
}