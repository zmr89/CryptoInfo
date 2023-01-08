package com.example.cryptoinfo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoinfo.R
import com.example.cryptoinfo.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var listCoinPriceInfo = listOf<CoinPriceInfo>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var onCoinClickListener: OnCoinClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coinPriceInfo = listCoinPriceInfo[position]
        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastTimeUpdateTemplate = context.resources.getString(R.string.last_time_update_template)
        with(holder) {
            tvSymbols.text = String.format(symbolsTemplate, coinPriceInfo.fromsymbol, coinPriceInfo.tosymbol)
            tvPrice.text = coinPriceInfo.price.toString()
            tvLastUpdate.text = String.format(lastTimeUpdateTemplate, coinPriceInfo.getFormattedTime())
            Picasso.get().load(coinPriceInfo.getFullImageUrl()).into(ivLogo)

            itemView.setOnClickListener({ onCoinClickListener?.onCoinClick(coinPriceInfo) })
        }
    }

    override fun getItemCount(): Int {
        return listCoinPriceInfo.size
    }

    //try delete "inner". Try use sintetic
    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogo = itemView.findViewById<ImageView>(R.id.ivLogo)
        val tvSymbols = itemView.findViewById<TextView>(R.id.tvSymbols)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        val tvLastUpdate = itemView.findViewById<TextView>(R.id.tvLastUpdate)
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }

}