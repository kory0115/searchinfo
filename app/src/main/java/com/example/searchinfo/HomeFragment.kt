package com.example.searchinfo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.searchinfo.adapter.HomeAdapter
import com.example.searchinfo.data.ImageEntity
import com.example.searchinfo.databinding.FragmentHomeBinding
import com.example.searchinfo.home.HomeViewModel
import com.example.searchinfo.preference.SharedPreferences
import com.example.searchinfo.room.DatabaseProvider
import com.example.searchinfo.room.RoomEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class HomeFragment : Fragment(), CoroutineScope {
    private val binding get() = _binding!!
    private var _binding : FragmentHomeBinding? = null
    private lateinit var mainActivity: MainActivity
    private lateinit var adapter : HomeAdapter
    private lateinit var viewModel: HomeViewModel

    private val imme by lazy { requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private val prefer by lazy { SharedPreferences(requireContext()) }
    private val db by lazy {  DatabaseProvider.provideDB(requireContext()).searchDao() }
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

        _binding = FragmentHomeBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bindViews()
    }

    private fun initViews() {
        binding.editTextView.setText(prefer.loadData())
        adapter = HomeAdapter(requireContext(), onClick = {
            prefer.saveLike(it.first.thumbnailurl, it.second)
            launch {
                searchItem(it)
            }
        })

        //val repository = RetrofitRepository()
        //val viewModelFactory = HomeViewModelFactory(repository)

        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        if(binding.editTextView.text.toString().isEmpty()) {
            viewModel.getResult("아이브", "accuracy")
        } else {
            viewModel.getResult(binding.editTextView.text.toString(), "accuracy")
        }

        viewModel.assamble.observe(requireActivity(), Observer {
            adapter.submitList(it.body()?.items!!)
            binding.gridRecyclerView.adapter = adapter
        })
    }

    private suspend fun searchItem(item: Pair<ImageEntity, Boolean>) = withContext(Dispatchers.IO) {
        val searchData = RoomEntity(
            item.first.collection,
            item.first.thumbnailurl,
            item.first.datetime
        )
        if(item.second) {
            db.saveSearch(searchData)
        } else {
            db.deleteSearch(searchData.thumbnailurl)
        }

    }

    private fun bindViews() {
        binding.findButton.setOnClickListener {
            imme.hideSoftInputFromWindow(binding.editTextView.windowToken, 0)
            prefer.saveData(binding.editTextView.text.toString())
            initViews()
        }
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