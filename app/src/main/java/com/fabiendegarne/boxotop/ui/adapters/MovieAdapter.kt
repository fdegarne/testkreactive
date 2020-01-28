package com.fabiendegarne.boxotop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fabiendegarne.boxotop.R
import com.fabiendegarne.boxotop.databinding.MovieBinding
import com.fabiendegarne.boxotop.ui.viewmodels.MovieViewModel

class MovieAdapter(private val arrayList: ArrayList<MovieViewModel>) : RecyclerView.Adapter<MovieAdapter.ViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: MovieBinding = DataBindingUtil.inflate(inflater, R.layout.movie_adapter, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int)
    {
        val movieViewModel = arrayList[position]
        holder.bind(movieViewModel)
    }

    var onItemClick: ((pos: Int, view: View) -> Unit)? = null

    inner class ViewHolder(val movieBinding: MovieBinding) : RecyclerView.ViewHolder(movieBinding.root), View.OnClickListener{
        fun bind(movie: MovieViewModel){
            movieBinding.movieModel = movie
            movieBinding.executePendingBindings()
        }

        override fun onClick(v: View) {
            onItemClick?.invoke(adapterPosition, v)
        }

        init {
            movieBinding.root.setOnClickListener(this)
        }
    }
}