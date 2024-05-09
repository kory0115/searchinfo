package com.example.searchinfo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.searchinfo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        initNavi()
    }

    private fun initViews() {
        showFragment(HomeFragment.newInstance(), HomeFragment.TAG)


    }

    private fun initNavi() {
        binding.bottomNavi.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    showFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                }
                R.id.my_page -> {
                    showFragment(MyFragment.newInstance(), MyFragment.TAG)
                }
            }
            true
        }

    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.fragments.forEach { f ->
            supportFragmentManager.beginTransaction().hide(f).commitAllowingStateLoss()
        }
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?:kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, tag)
                .commitAllowingStateLoss()
        }
    }
}