package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.FragmentHomeThirdBinding
import com.squareup.picasso.Picasso
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
                        title.text = " "+it.name+" "
                        overview.text = it.overview
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

    fun chargerPosterPicasso(poster: String) {
        Picasso.get()
            // .load("https://image.tmdb.org/t/p/w185/wdE6ewaKZHr62bLqCn7A2DiGShm.jpg") //fonctionnel
            .load(poster) // fonctionnel
            .error(R.drawable.ic_baseline_arrow_forward_ios_24)
            .into(binding.movieAvatar)
    }
}
