package com.gmail.eamosse.imdb.ui.DiscoverTv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gmail.eamosse.imdb.databinding.FragmentDiscovertvBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvFragment : Fragment() {

    private val TvViewModel: TvViewModel by viewModel()
    private lateinit var binding: FragmentDiscovertvBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiscovertvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(TvViewModel) {
            getTv()

            TvDiscover.observe(
                viewLifecycleOwner,
                Observer {
                    binding.movieList.adapter = TvAdapter(it)
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
