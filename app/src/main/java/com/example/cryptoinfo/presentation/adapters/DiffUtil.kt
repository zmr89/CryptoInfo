package com.example.cryptoinfo.presentation.adapters

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.cryptoinfo.data.database.model.CoinInfoDbModel


class CoinPriceInfoItemCallback: ItemCallback<CoinInfoDbModel>() {

    override fun areItemsTheSame(oldItem: CoinInfoDbModel, newItem: CoinInfoDbModel): Boolean {
        return oldItem.fromsymbol == newItem.fromsymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfoDbModel, newItem: CoinInfoDbModel): Boolean {
        return oldItem == newItem
    }

}