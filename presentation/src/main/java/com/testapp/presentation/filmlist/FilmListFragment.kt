package com.testapp.presentation.filmlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.testapp.presentation.R
import com.testapp.presentation.databinding.FragmentFilmListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmListFragment : Fragment(R.layout.fragment_film_list) {

    private val adapter: FilmListAdapter by lazy { FilmListAdapter() }

    private val viewModel: FilmListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentFilmListBinding.bind(view)
        binding.filmRecycler.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.onUpdate()
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is FilmListState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is FilmListState.Loaded -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.submitList(state.films)
                }
            }
        }

        viewModel.events.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let { filmListEvent ->
                when (filmListEvent) {
                    is FilmListEvent.Error -> {
                        Snackbar.make(
                            binding.root,
                            filmListEvent.message ?: getString(R.string.error_message),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }

                    is FilmListEvent.StopLoading -> {
                        binding.swipeRefresh.isRefreshing = false
                    }
                }
            }
        }

    }
}