package com.gmail.eamosse.imdb.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.idbdata.data.Company
import com.gmail.eamosse.idbdata.data.MovieDetails
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentHomeThirdBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home_third.*
import kotlinx.android.synthetic.main.fragment_home_third.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeThirdFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val args: HomeThirdFragmentArgs by navArgs()
    private lateinit var binding: FragmentHomeThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Film: " + args.myMov

        with(homeViewModel) {
            token.observe(
                viewLifecycleOwner,
                Observer {
                    getOneMovie(args.movId)
                }
            )
            with(binding) {
                movie.observe(
                    viewLifecycleOwner,
                    Observer {
                        chargerPosterPicasso(it.backdrop_path)
                        title.text = " " + it.name + " "
                        overview.text = it.overview
                        checkVote(it)


                        // checkCompany(it.production_companies)
                    }
                )
            }
            error.observe(
                viewLifecycleOwner,
                Observer {
                    // afficher l'erreur
                }
            )
        }
    }

    private fun checkVote(it: MovieDetails?) {
        if (it != null) {
            var voteavgfloat: Float = it.vote_average.toFloat()
            voteavgfloat *= 10
            binding.voteAverage.text = "%.0f".format(voteavgfloat) + "%"
            if (80 <= voteavgfloat) {
                // Log.d("Movie ${movie.name}, nbr: ${nbr}","superieur a 8")
                binding.voteAverage.setTextColor(Color.parseColor("#33CC66"))
            } else if ((60 <= voteavgfloat) && (80 > voteavgfloat)) {
                // Log.d("Movie ${movie.name}, nbr: ${nbr}","entre 6 et 8")
                binding.voteAverage.setTextColor(Color.parseColor("#f4d03f"))
            } else if (voteavgfloat <60) {
                // Log.d("Movie ${movie.name}, nbr: ${nbr}","inferieur à 6")
                binding.voteAverage.setTextColor(Color.parseColor("#e33712"))
            }
            binding.voteCount.text = it.vote_count
        }
    }

    private fun checkCompany(it: List<Company>) {
        var result: String = ""

        for (company in 0..it.count()) {
            result += " " + it[company].name + " "
        }

        binding.company.text = result
    }

    private fun chargerPosterPicasso(poster: String) {
        Picasso.get()
            // .load("https://image.tmdb.org/t/p/w185/wdE6ewaKZHr62bLqCn7A2DiGShm.jpg") //fonctionnel
            .load(poster) // fonctionnel
            .error(R.drawable.ic_baseline_arrow_forward_ios_24)
            .into(binding.movieAvatar)
    }
}
