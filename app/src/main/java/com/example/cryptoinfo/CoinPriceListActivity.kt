package com.example.cryptoinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoinfo.adapters.CoinInfoAdapter
import com.example.cryptoinfo.databinding.ActivityCoinPriceListBinding
import com.example.cryptoinfo.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var coinViewModel: CoinViewModel
    lateinit var binding: ActivityCoinPriceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coinInfoAdapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = coinInfoAdapter

        coinInfoAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                if (isLandscapeScreen()){
                    launchFragment(coinPriceInfo.fromsymbol)
                } else {
                    val intent = CoinDetailActivity
                        .newIntent(this@CoinPriceListActivity, coinPriceInfo.fromsymbol)
                    startActivity(intent)
                }
                Log.d("test_onCoinClick", coinPriceInfo.fromsymbol.toString())
            }
        }

        coinViewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        coinViewModel.allCoinPriceInfoListFromDB.observe(this, Observer {
            coinInfoAdapter.submitList(it)
            Log.d("test_load", "Success in activity: $it")
        })

    }

    private fun isLandscapeScreen(): Boolean {
        return binding.detailFragmentContainer != null
    }

    private fun launchFragment(fsym: String) {
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.detailFragmentContainer, DetailFragment.newInstance(fsym)).commit()
    }




}