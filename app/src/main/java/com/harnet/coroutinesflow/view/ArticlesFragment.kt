package com.harnet.coroutinesflow.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.harnet.coroutinesflow.R
import com.harnet.coroutinesflow.adapter.ArticlesAdapter
import com.harnet.coroutinesflow.viewModel.ArticlesViewModel
import kotlinx.android.synthetic.main.articles_fragment.*

class ArticlesFragment : Fragment() {
    private lateinit var articlesAdapter: ArticlesAdapter
    private lateinit var viewModel: ArticlesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)
        // TODO: Use the ViewModel
        return inflater.inflate(R.layout.articles_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articlesAdapter = ArticlesAdapter(arrayListOf())

        viewModel.refresh()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articlesAdapter
        }

        // add separation line between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )

        observeModel()
    }

    private fun observeModel(){
        viewModel.mArticlesList.observe(viewLifecycleOwner, Observer { articles ->
            articles?.let {
                if(it.isNotEmpty()){
                    articlesAdapter.updateArticlesList(articles)
                    recyclerView.setBackgroundColor(Color.CYAN)
                    recyclerView.visibility = View.VISIBLE
                }

            }
        })

        viewModel.mIsLoading.observe(viewLifecycleOwner, Observer {
            if(it) {
                recyclerView.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }
            else {
                recyclerView.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
            }
        })
    }
}