package com.gmail.eamosse.imdb.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentDashboardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModel()
    private val args: DashboardFragmentArgs by navArgs()
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(dashboardViewModel) {
            getMoviesLatest()

            movielatest.observe(
                viewLifecycleOwner,
                Observer {
                    binding.movieTestName.text = it[0].id.toString()
                    binding.movieLatestList.adapter = DashboardAdapter(it)
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
