package com.example.cryptoinfo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoinfo.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw Exception("FragmentDetailBinding = null")

    lateinit var coinViewModel: CoinViewModel

    private var fsym: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            fsym = it.getString(FROM_SYMBOL_EXTRA)
        }

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val FROM_SYMBOL_EXTRA = "fsym"

        fun newInstance(fsym: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(FROM_SYMBOL_EXTRA, fsym)
                }
            }
    }
}