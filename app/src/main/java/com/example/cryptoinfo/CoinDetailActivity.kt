package com.example.cryptoinfo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoinfo.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    lateinit var coinViewModel: CoinViewModel


    lateinit var binding: ActivityCoinDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!intent.hasExtra(FROM_SYMBOL_EXTRA)) {
            finish()
            return
        }
        val fsym = intent.getStringExtra(FROM_SYMBOL_EXTRA)

        coinViewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        coinViewModel.getCoinPriceInfoFromDB(fsym).observe(this, Observer {
            Picasso.get().load(it.getFullImageUrl()).into(binding.ivLogoDetail)
            binding.tvTsym.text = it.tosymbol
            binding.tvFsym.text = it.fromsymbol
            binding.tvPrice.text = it.price.toString()
            binding.tvMinPrice.text = it.lowday.toString()
            binding.tvMaxPrice.text = it.highday.toString()
            binding.tvLastDeal.text = it.lastmarket
            binding.tvLastUpdate.text = it.getFormattedTime()
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