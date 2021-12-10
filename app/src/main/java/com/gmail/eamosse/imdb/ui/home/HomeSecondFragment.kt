package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentHomeSecondBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeSecondFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val args: HomeSecondFragmentArgs by navArgs()
    private lateinit var binding: FragmentHomeSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Afficher le titre du film dans l'action bar
        (activity as AppCompatActivity).supportActionBar?.title = args.myCat

        with(homeViewModel) {
            token.observe(
                viewLifecycleOwner,
                Observer {
                    // récupérer les catégories
                    getMovies(args.catId)
                }
            )
            movies.observe(
                viewLifecycleOwner,
                Observer {
                    binding.movieList.adapter = MovieAdapter(it)
                }
            )
            error.observe(
                viewLifecycleOwner,
                Observer {
                    // afficher l'erreur
                }
            )
        }
    }
}
