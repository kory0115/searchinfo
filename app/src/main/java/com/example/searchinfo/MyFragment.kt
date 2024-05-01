package com.example.searchinfo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.searchinfo.adapter.MyAdapter
import com.example.searchinfo.databinding.FragmentMyBinding

class MyFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding : FragmentMyBinding? = null
    private lateinit var mainActivity: MainActivity
    private lateinit var adapter : MyAdapter

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
    }

    private fun initViews() {
        adapter = MyAdapter()
        binding.myRecyclerview.adapter = adapter
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