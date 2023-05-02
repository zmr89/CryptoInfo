package com.example.cryptoinfo.presentation.adapters

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.cryptoinfo.data.model.CoinPriceInfo


class CoinPriceInfoItemCallback: ItemCallback<CoinPriceInfo>() {

    override fun areItemsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem.fromsymbol == newItem.fromsymbol
    }

    override fun areContentsTheSame(oldItem: CoinPriceInfo, newItem: CoinPriceInfo): Boolean {
        return oldItem == newItem
    }

}