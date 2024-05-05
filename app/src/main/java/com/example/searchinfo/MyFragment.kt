package com.example.searchinfo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.searchinfo.adapter.MyAdapter
import com.example.searchinfo.databinding.FragmentMyBinding
import com.example.searchinfo.room.DatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MyFragment : Fragment(), CoroutineScope {
    private val binding get() = _binding!!
    private var _binding : FragmentMyBinding? = null
    private lateinit var mainActivity: MainActivity
    private lateinit var adapter : MyAdapter
    private val db by lazy { DatabaseProvider.provideDB(requireContext()).searchDao() }
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

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
        adapter = MyAdapter(onClick = {
            launch {
                db.deleteSearch(it.thumbnailurl)
            }
            bindViews()
        })
        binding.myRecyclerview.adapter = adapter
    }

    private fun bindViews() {
        launch {
            getInfo()
        }
        initViews()
    }

    private fun upDateViews() {
        binding.actionButton.setOnClickListener {
            bindViews()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private suspend fun getInfo() = withContext(Dispatchers.IO) {
        val getData = db.getAll()
        withContext(Dispatchers.Main) {
            adapter.submitList(getData)
            adapter.notifyDataSetChanged()
        }
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