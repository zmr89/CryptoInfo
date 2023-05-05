package com.example.cryptoinfo.presentation.adapters

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.cryptoinfo.domain.sumin.CoinInfoEntity


class CoinPriceInfoItemCallback: ItemCallback<CoinInfoEntity>() {

    override fun areItemsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem.fromsymbol == newItem.fromsymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
        return oldItem == newItem
    }

}