package com.fabiendegarne.boxotop.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fabiendegarne.boxotop.R
import com.fabiendegarne.boxotop.ui.adapters.MovieAdapter
import com.fabiendegarne.boxotop.ui.viewmodels.MovieViewModel

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var movieAdapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview) as RecyclerView
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.menu_search)
        if (searchItem != null){
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    bindMovies(query.toString(), 1)
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            });
        }

        return super.onCreateOptionsMenu(menu)
    }

    fun bindMovies(searchText: String, page: Int)
    {
        var movieViewModel: MovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.getArrayMovies(searchText, page).observe(this, Observer { movieViewModels ->

            println("view Mod: " + movieViewModels.toString())
            movieAdapter = MovieAdapter(movieViewModels)
            movieAdapter!!.onItemClick = { pos, view ->
                launchDetailActivity(movieViewModels[pos])
            }
            recyclerView!!.setLayoutManager(LinearLayoutManager(this))
            recyclerView!!.setAdapter(movieAdapter)

        })
    }

    fun launchDetailActivity(movieModel: MovieViewModel)
    {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movie_id", movieModel.imdbID)
        startActivity(intent)
    }
}
