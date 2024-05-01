package com.example.searchinfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.searchinfo.adapter.HomeAdapter
import com.example.searchinfo.databinding.FragmentHomeBinding
import com.example.searchinfo.home.HomeViewModel
import com.example.searchinfo.home.HomeViewModelFactory
import com.example.searchinfo.repository.RetrofitRepository

class HomeFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding : FragmentHomeBinding? = null
    private lateinit var mainActivity: MainActivity
    private lateinit var adapter : HomeAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is MainActivity) mainActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        adapter = HomeAdapter(onClick = {
        })

        val repository = RetrofitRepository()
        val viewModelFactory = HomeViewModelFactory(repository)

        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(HomeViewModel::class.java)

        if(binding.editTextView.text.toString().isEmpty()) {
            viewModel.getResult("아이브", "모름")
        } else {
            viewModel.getResult(binding.editTextView.text.toString(), "모름")
        }

        viewModel.assamble.observe(requireActivity(), Observer {
            adapter.submitList(it.body()?.items!!)
            binding.gridRecyclerView.adapter = adapter
        })
    }

    companion object {
        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"

                /*.apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }
}