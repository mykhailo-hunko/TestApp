package com.testapp.presentation.filmlist

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
                        Snackbar.make(
                            binding.root,
                            filmListEvent.message ?: getString(R.string.error_message),
                            Snackbar.LENGTH_SHORT
                        ).setAction(
                            getString(android.R.string.ok)
                        ) { }.show()
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
        swipeRefresh.setOnRefreshListener {
            viewModel.onUpdate()
        }
       /* val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(Color.parseColor("#ff34344d"),
                Color.parseColor("#13192B"))
        );
        gradientDrawable.cornerRadius = 0f

        //Set Gradient
        root.background = gradientDrawable*/
    }
}