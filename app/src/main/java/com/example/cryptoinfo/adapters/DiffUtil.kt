package com.example.cryptoinfo.adapters

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.cryptoinfo.pojo.CoinPriceInfo


class CoinPriceInfoItemCallback: ItemCallback<CoinPriceInfo>() {

    override fun areItemsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem.fromsymbol == newItem.fromsymbol
    }

    override fun areContentsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem == newItem
    }

}