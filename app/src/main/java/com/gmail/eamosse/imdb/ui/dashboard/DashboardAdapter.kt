package com.gmail.eamosse.imdb.ui.dashboard

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.MovieTopRated
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.MoviestwoListItemBinding
import com.squareup.picasso.Picasso

class DashboardAdapter(private val items: List<MovieTopRated>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: MoviestwoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val posterMovie: ImageView = binding.movieLatestAvatar
        val movie_title: TextView = binding.movieLatestName
        fun bind(item: MovieTopRated) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(MoviestwoListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("CALL", "onBindViewHolder")
        holder.bind(items[position])
        val movie: MovieTopRated = items[position]
        Picasso.get()
            // .load("https://image.tmdb.org/t/p/w185/wdE6ewaKZHr62bLqCn7A2DiGShm.jpg") //fonctionnel
            .load(movie.poster) // fonctionnel
            .error(R.drawable.ic_baseline_arrow_forward_ios_24)
            .into(holder.posterMovie)
        holder.itemView.setOnClickListener {
            val action =
                DashboardFragmentDirections.actionNavigationDashboardHomeToNavigationHomeThird(
                    position.toString(),
                    items[position].name,
                    items[position].id.toString()
                )
            Navigation.findNavController(it).navigate(action)
        }
    }
}
