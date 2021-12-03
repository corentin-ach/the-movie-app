package com.gmail.eamosse.imdb.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.MoviesListItemBinding
import com.google.android.gms.fido.fido2.api.common.RequestOptions
import com.squareup.picasso.Picasso
import java.lang.reflect.Array.get
import java.util.*

class MovieAdapter(private val items: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MoviesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val posterMovie: ImageView = binding.movieAvatar

        fun bind(item: Movie) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(MoviesListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            val action =
                //Log.d("CALL FRAG THIRD","onBindViewHolder")
                HomeSecondFragmentDirections.actionNavigationHomeSecondToNavigationHomeThird(position.toString(), items[position].name, items[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
        
        val movie : Movie = items[position]
        // Display Neighbour Avatar
        Picasso.get()
            //.load("https://image.tmdb.org/t/p/w185/wdE6ewaKZHr62bLqCn7A2DiGShm.jpg") //fonctionnel
            .load(movie.poster) //fonctionnel
            .error(R.drawable.ic_baseline_arrow_forward_ios_24)
            .into(holder.posterMovie);
    }
}
