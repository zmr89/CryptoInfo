package com.example.cryptoinfo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.cryptoinfo.R
import com.example.cryptoinfo.data.mapper.CoinMapper
import com.example.cryptoinfo.data.network.ApiFactory
import com.example.cryptoinfo.presentation.adapters.CoinInfoAdapter
import com.example.cryptoinfo.databinding.ActivityCoinPriceListBinding
import com.example.cryptoinfo.domain.CoinInfoEntity
import kotlinx.coroutines.launch

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var coinViewModel: CoinViewModel
    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val coinNamesListDto = ApiFactory.apiService.getTopList(limit = 50)
            val string = CoinMapper().mapNamesListToString(coinNamesListDto)
            Log.d("TestMarsel", "string")
            Log.d("TestMarsel", string)
        }
        Log.d("TestMarsel", "string")

        setContentView(binding.root)

        coinViewModel = ViewModelProvider(this)[CoinViewModel::class.java]

        val coinInfoAdapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = coinInfoAdapter
        binding.rvCoinPriceList.itemAnimator = null

        coinInfoAdapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfoEntity: CoinInfoEntity) {
                if (isLandscapeScreen()){
                    launchFragment(coinInfoEntity.fromsymbol)
                } else {
                    launchActivity(coinInfoEntity)
                }
                Log.d("test_onCoinClick", coinInfoEntity.fromsymbol)
            }
        }



        coinViewModel.coinInfoList.observe(this, Observer {
            coinInfoAdapter.submitList(it)
            Log.d("test_load", "Success in activity: $it")
        })

    }

    private fun isLandscapeScreen(): Boolean {
        return binding.detailFragmentContainer != null
    }

    private fun launchFragment(fsym: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.detailFragmentContainer, DetailFragment.newInstance(fsym)).commit()
    }

    private fun launchActivity(coinInfoEntity: CoinInfoEntity) {
        val intent = CoinDetailActivity
            .newIntent(this@CoinPriceListActivity, coinInfoEntity.fromsymbol)
        startActivity(intent)
    }




}