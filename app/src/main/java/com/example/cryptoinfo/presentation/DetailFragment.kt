package com.example.cryptoinfo.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoinfo.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw Exception("FragmentDetailBinding = null")

    @Inject
    lateinit var coinViewModelFactory: CoinViewModelFactory
    private val coinViewModel: CoinViewModel by lazy {
        ViewModelProvider(this, coinViewModelFactory).get(CoinViewModel::class.java)
    }

    private val component by lazy {
        (requireActivity().application as CoinApplication).component
    }

    lateinit var fsym: String

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fsym = getFromSymbol()

        coinViewModel.getDetailInfo(fsym).observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it.imageurl).into(binding.ivLogoDetail)
            binding.tvTsym.text = it.tosymbol
            binding.tvFsym.text = it.fromsymbol
            binding.tvPrice.text = it.price.toString()
            binding.tvMinPrice.text = it.lowday.toString()
            binding.tvMaxPrice.text = it.highday.toString()
            binding.tvLastDeal.text = it.lastmarket
            binding.tvLastUpdate.text = it.lastupdate
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val FROM_SYMBOL_EXTRA = "fsym"
        private const val EMPTY_SYMBOL = ""

        fun newInstance(fsym: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(FROM_SYMBOL_EXTRA, fsym)
                }
            }
    }

    private fun getFromSymbol(): String {
        return requireArguments().getString(FROM_SYMBOL_EXTRA) ?: EMPTY_SYMBOL
    }
}