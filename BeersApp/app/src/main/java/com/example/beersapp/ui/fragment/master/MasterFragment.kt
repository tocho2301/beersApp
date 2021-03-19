package com.example.beersapp.ui.fragment.master

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beersapp.databinding.FragmentMasterBinding
import com.example.beersapp.di.BeerAppApplication
import com.example.beersapp.entity.beer.Beer
import com.example.beersapp.ui.adapter.BeerAdapter
import javax.inject.Inject

class MasterFragment : Fragment(), BeerAdapter.OnActionsListener{

    private lateinit var binding: FragmentMasterBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MasterViewModel> { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMasterBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        setUpViewListeners()
        setObservablesUp()

        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            show()
            title = "Try this beers!"
        }


        return binding.root
    }

    private fun setUpViewListeners() {
        binding.rvBeers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!viewModel.isLoading.value!!) {
                    if ((binding.rvBeers.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == binding.rvBeers.adapter!!.itemCount - 1) {
                        viewModel.getMoreBears()
                    }
                }
            }
        })

        binding.refreshLayout.setOnRefreshListener {
            (binding.rvBeers.adapter as BeerAdapter).clearBeers()
            viewModel.refreshBeers()
        }
    }

    private fun setObservablesUp() {
        viewModel.beers.observe(viewLifecycleOwner,{ beers ->
            binding.refreshLayout.isRefreshing = false
            if(binding.rvBeers.adapter == null){
                val beerAdapter = BeerAdapter(ArrayList())
                beerAdapter.setActionsListener(this)
                binding.rvBeers.adapter = beerAdapter
            }

            beers.let {
                (binding.rvBeers.adapter as BeerAdapter).addBeers(it)
            }

        })

        viewModel.errorMessage.observe(viewLifecycleOwner, { message ->
            Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
            binding.refreshLayout.isRefreshing = false
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as BeerAppApplication).getComponent().inject(this)
        viewModel.getBeers()
    }

    override fun onBeerSelected(beer: Beer) {
        findNavController().navigate(MasterFragmentDirections.actionMasterFragmentToDetailFragment(beer))
    }
}