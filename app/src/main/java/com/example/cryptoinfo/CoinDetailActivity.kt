package com.example.cryptoinfo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {
    lateinit var coinViewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        if (!intent.hasExtra(FROM_SYMBOL_EXTRA)) {
            finish()
            return
        }
        val fsym = intent.getStringExtra(FROM_SYMBOL_EXTRA)

        coinViewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        coinViewModel.getCoinPriceInfoFromDB(fsym).observe(this, Observer {
            Picasso.get().load(it.getFullImageUrl()).into(ivLogoDetail)
            tvFsym.text = it.fromsymbol
            tvTsym.text = it.tosymbol
            tvPrice.text = it.price.toString()
            tvMinPrice.text = it.lowday.toString()
            tvMaxPrice.text = it.highday.toString()
            tvLastDeal.text = it.lastmarket
            tvLastUpdate.text = it.getFormattedTime()
        })

    }

    companion object {
        private const val FROM_SYMBOL_EXTRA = "fsym"

        fun newIntent(context: Context, fsym: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(FROM_SYMBOL_EXTRA, fsym)
            return intent
        }
    }

}