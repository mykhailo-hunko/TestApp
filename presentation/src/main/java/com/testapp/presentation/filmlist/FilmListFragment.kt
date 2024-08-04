package com.testapp.presentation.filmlist

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.testapp.presentation.R
import com.testapp.presentation.databinding.FragmentFilmListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.UnknownHostException


class FilmListFragment : Fragment(R.layout.fragment_film_list) {

    private val adapter: FilmListAdapter by lazy { FilmListAdapter() }

    private val viewModel: FilmListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentFilmListBinding.bind(view)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.bindViews()
        subscribeToViewModel(binding)

    }

    private fun subscribeToViewModel(binding: FragmentFilmListBinding) {
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
                        val message = when (filmListEvent.error) {
                            is UnknownHostException -> R.string.shot_error_message
                            else -> R.string.error_message
                        }.let(::getString)
                        Snackbar.make(
                            binding.root,
                            message,
                            Snackbar.LENGTH_SHORT
                        ).setAction(
                            getString(R.string.dismiss)
                        ) { }
                            .setActionTextColor(resources.getColor(R.color.primary))
                            .show()
                        binding.swipeRefresh.isRefreshing = false
                    }

                    is FilmListEvent.StopLoading -> {
                        binding.swipeRefresh.isRefreshing = false
                    }
                }
            }
        }
    }

    private fun FragmentFilmListBinding.bindViews() {
        filmRecycler.adapter = adapter
        swipeRefresh.apply {
            setOnRefreshListener {
                viewModel.onUpdate()
            }
            setColorSchemeColors(resources.getColor(R.color.primary))
            setProgressBackgroundColorSchemeColor(resources.getColor(R.color.white90))
        }
    }
}