package com.example.cryptoinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoinfo.adapters.CoinInfoAdapter
import com.example.cryptoinfo.pojo.CoinPriceInfo
import kotlinx.android.synthetic.main.activity_coin_price_list.*

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var coinViewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)

        val coinInfoAdapter = CoinInfoAdapter(this)
        rvCoinPriceList.adapter = coinInfoAdapter

        coinInfoAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity
                    .newIntent(this@CoinPriceListActivity, coinPriceInfo.fromsymbol)
                startActivity(intent)
                Log.d("test_onCoinClick", coinPriceInfo.fromsymbol.toString())
            }

        }

        coinViewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        coinViewModel.allCoinPriceInfoListFromDB.observe(this, Observer {
            coinInfoAdapter.listCoinPriceInfo = it
            Log.d("test_load", "Success in activity: $it")
        })

    }


}