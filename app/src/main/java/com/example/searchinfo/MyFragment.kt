package com.example.searchinfo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.searchinfo.adapter.MyAdapter
import com.example.searchinfo.databinding.FragmentMyBinding
import com.example.searchinfo.home.HomeViewModel
import com.example.searchinfo.preference.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding : FragmentMyBinding? = null
    private lateinit var mainActivity: MainActivity
    private lateinit var adapter : MyAdapter

    private val prefer by lazy { SharedPreferences(requireContext()) }
    private lateinit var viewModel : HomeViewModel
    //private val db by lazy { DatabaseProvider.provideDB(requireContext()).searchDao() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is MainActivity) mainActivity = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
        upDateViews()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViews() {
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        adapter = MyAdapter(onClick = {
            /*launch {
                db.deleteSearch(it.thumbnailurl)
            }*/
            viewModel.deleteDb(it.thumbnailurl)
            prefer.saveLike(it.thumbnailurl, false)
            bindViews()
        })
        binding.myRecyclerview.adapter = adapter
    }

    private fun bindViews() {
        /*launch {
            getInfo()
        }*/
        getInfo()
        initViews()
    }

    private fun upDateViews() {
        binding.actionButton.setOnClickListener {
            bindViews()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getInfo() {
        viewModel.getAllDb()
        viewModel.db.observe(requireActivity(), Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

    companion object {
        fun newInstance() = MyFragment()
        const val TAG = "MyFragment"
                /*.apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
    }


}