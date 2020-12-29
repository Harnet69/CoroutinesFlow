package com.harnet.coroutinesflow.view

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.harnet.coroutinesflow.viewModel.ArticlesViewModel
import com.harnet.coroutinesflow.R
import kotlinx.android.synthetic.main.articles_fragment.*

class ArticlesFragment : Fragment() {

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
        observeModel()
        viewModel.refresh()
    }

    private fun observeModel(){
        viewModel.mArticlesList.observe(viewLifecycleOwner, Observer { articles ->
            Log.i("ArticlesShowing", "observeModel: $articles")
            recyclerView.setBackgroundColor(Color.CYAN)
        })

        viewModel.mIsLoading.observe(viewLifecycleOwner, Observer {
            if(it)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.INVISIBLE

        })
    }
}