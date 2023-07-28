package com.example.cryptoinfo.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoinfo.R
import com.example.cryptoinfo.databinding.ActivityCoinDetailBinding

class CoinDetailActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        if (!intent.hasExtra(FROM_SYMBOL_EXTRA)) {
            finish()
            return
        }
        val fsym = intent.getStringExtra(FROM_SYMBOL_EXTRA) ?: EMPTY_SYMBOL

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerForDetailActivity, DetailFragment.newInstance(fsym))
                .commit()
        }

    }

    companion object {
        private const val FROM_SYMBOL_EXTRA = "fsym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fsym: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(FROM_SYMBOL_EXTRA, fsym)
            return intent
        }
    }

}