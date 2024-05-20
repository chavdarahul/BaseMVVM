package com.example.practiceapp.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.practiceapp.R
import com.example.practiceapp.data.api.Resource
import com.example.practiceapp.data.model.UserData
import com.example.practiceapp.data.viewmodel.TestViewModel
import com.example.practiceapp.databinding.ActivityMainBinding
import com.example.practiceapp.ui.adapter.TestItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<TestViewModel>()
    private lateinit var adapter: TestItemAdapter
    private var list = mutableListOf<UserData?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observerResponse()
        callAPI()
    }

    private fun callAPI() {
        viewModel.getUsers(2)
    }

    private fun observerResponse() {
        viewModel.getUserData().observe(this) {

        }

        viewModel.getUsersResponse.observe(this) { it ->
            when(it){
                Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Resource.Failure -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, "something went wrong...", Toast.LENGTH_LONG).show()
                }
                is Resource.Success -> {
                    binding.progressBar.isVisible = false
                    it.value.let { value ->
                        value.data.let { data ->
                            data?.let { it1 -> list.addAll(it1.toMutableList()) }
                        }
                    }
                    it.value.data?.let { it1 -> list.addAll(it1.toMutableList()) }
                    list.forEach { data ->
                        if (data != null) {
                            viewModel.insertUserData(data)
                        }
                    }
                    setAdapter()
                }
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter() {
        adapter = TestItemAdapter(list)
        binding.rvAirways.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}