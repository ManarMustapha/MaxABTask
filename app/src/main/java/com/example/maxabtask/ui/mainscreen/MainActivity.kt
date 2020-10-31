package com.example.maxabtask.ui.mainscreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maxabtask.R
import com.example.maxabtask.util.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity(), OnBottomReachedListener {

    private val viewModel: MainViewModel by inject()

    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initUserState()
    }

    private fun initRecyclerView() {
        userRecyclerView.addItemDecoration(
            DividerItemDecoration(
                userRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        adapter = UserAdapter(viewModel, this)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = adapter
    }

    private fun initUserState() {
        viewModel.userState.observe(this, Observer {
            when (it) {
                is Failed -> {
                    Toast.makeText(this, it.error.message, Toast.LENGTH_SHORT).show()
                }
                is Loading -> {
                    progressBar.visibility = View.VISIBLE

                }
                is StopLoading -> {
                    progressBar.visibility = View.GONE
                }
                is Success -> {
                    it.data.dispatchUpdatesTo(adapter)
                }
            }
        })
    }

    override fun loadMore() {
        viewModel.fetchUsers()
    }
}