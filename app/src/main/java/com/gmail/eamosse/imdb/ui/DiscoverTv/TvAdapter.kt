package com.gmail.eamosse.imdb.ui.DiscoverTv

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Tv
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.TvListItemBinding
import com.gmail.eamosse.imdb.ui.dashboard.DashboardFragmentDirections
import com.squareup.picasso.Picasso

class TvAdapter(private val items: List<Tv>) :
    RecyclerView.Adapter<TvAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: TvListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val posterMovie: ImageView = binding.movieAvatar
        val movie_vote: TextView = binding.movieVote

        fun bind(item: Tv) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(TvListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        val movie: Tv = items[position]
        // Change la couleur de la note en fonction de son niveau
        checkNote(movie, holder)
        holder.itemView.setOnClickListener {
            val action =
                TvFragmentDirections.actionNavigationDiscoverToNavigationHomeThird(
                    position.toString(),
                    items[position].name,
                    items[position].id.toString()
                )
            Navigation.findNavController(it).navigate(action)
        }
        // Display Neighbour Avatar
        Picasso.get()
            // .load("https://image.tmdb.org/t/p/w185/wdE6ewaKZHr62bLqCn7A2DiGShm.jpg") //fonctionnel
            .load(movie.poster) // fonctionnel
            .error(R.drawable.ic_baseline_arrow_forward_ios_24)
            .into(holder.posterMovie)
    }

    private fun checkNote(movie: Tv, holder: ViewHolder) {
        val nbr: Float = movie.vote.toFloat()
        if (8.0 <= nbr) {
            // Log.d("Movie ${movie.name}, nbr: ${nbr}","superieur a 8")
            holder.movie_vote.setTextColor(Color.parseColor("#33CC66"))
        } else if ((6.0 <= nbr) && (8.0 > nbr)) {
            // Log.d("Movie ${movie.name}, nbr: ${nbr}","entre 6 et 8")
            holder.movie_vote.setTextColor(Color.parseColor("#f4d03f"))
        } else if (nbr < 6.0) {
            // Log.d("Movie ${movie.name}, nbr: ${nbr}","inferieur à 6")
            holder.movie_vote.setTextColor(Color.parseColor("#e33712"))
        }
    }
}
