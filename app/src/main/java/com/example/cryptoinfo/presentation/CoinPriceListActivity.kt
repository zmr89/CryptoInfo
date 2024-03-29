package com.example.cryptoinfo.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoinfo.R
import com.example.cryptoinfo.databinding.ActivityCoinPriceListBinding
import com.example.cryptoinfo.domain.CoinInfoEntity
import com.example.cryptoinfo.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    @Inject
    lateinit var coinViewModelFactory: CoinViewModelFactory

    private val coinViewModel: CoinViewModel by lazy {
        ViewModelProvider(this, coinViewModelFactory).get(CoinViewModel::class.java)
    }

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val component by lazy {
    (application as CoinApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val coinInfoAdapter = CoinInfoAdapter(this)
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

        binding.rvCoinPriceList.adapter = coinInfoAdapter
        binding.rvCoinPriceList.itemAnimator = null

        coinViewModel.coinInfoList.observe(this, Observer {
            coinInfoAdapter.submitList(it)
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