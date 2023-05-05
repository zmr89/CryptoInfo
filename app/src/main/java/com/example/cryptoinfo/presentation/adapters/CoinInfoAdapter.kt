package com.example.cryptoinfo.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoinfo.R
import com.example.cryptoinfo.data.network.ApiFactory.BASE_IMAGE_URL
import com.example.cryptoinfo.databinding.ItemCoinInfoBinding
import com.example.cryptoinfo.domain.sumin.CoinInfoEntity
import com.example.cryptoinfo.utils.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context)
    : ListAdapter<CoinInfoEntity, CoinInfoViewHolder>(CoinPriceInfoItemCallback()) {

    var onCoinClickListener: OnCoinClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return CoinInfoViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val binding = holder.binding
        val coinPriceInfo = getItem(position)
        val symbolsTemplate = context.resources.getString(R.string.symbols_template)
        val lastTimeUpdateTemplate = context.resources.getString(R.string.last_time_update_template)
        with(holder) {
            binding.tvSymbols.text = String.format(symbolsTemplate, coinPriceInfo.fromsymbol, coinPriceInfo.tosymbol)
            binding.tvPrice.text = coinPriceInfo.price.toString()
            binding.tvLastUpdate.text = String.format(lastTimeUpdateTemplate, convertTimestampToTime(coinPriceInfo.lastupdate))
            Picasso.get().load(BASE_IMAGE_URL + coinPriceInfo.imageurl).into(binding.ivLogo)

            itemView.setOnClickListener({ onCoinClickListener?.onCoinClick(coinPriceInfo) })
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinInfoEntity: CoinInfoEntity)
    }

}