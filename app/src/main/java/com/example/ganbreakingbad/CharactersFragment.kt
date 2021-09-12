package com.example.ganbreakingbad

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ganbreakingbad.adapter.RecyclerViewAdapter
import com.example.ganbreakingbad.databinding.FragmentCharactersBinding
import com.example.ganbreakingbad.viewModel.CharactersViewModel
import javax.inject.Inject

class CharactersFragment : Fragment(), SearchView.OnQueryTextListener {

    @Inject
    lateinit var viewModel: CharactersViewModel

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        val view = binding.root
        initView()
        initViewModel()
        return view
    }

    private fun initView() {
        binding.searchView.setOnQueryTextListener(this)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.onItemClick = { character ->
            val action =
                CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(
                    character
                )
            findNavController().navigate(action)
        }
    }

    private fun initViewModel() {
        viewModel.charactersLiveData.observe(viewLifecycleOwner) { result ->
            result.onSuccess {
                recyclerViewAdapter.setUpdatedData(it)
            }
                .onFailure {
                    showErrrorMessage()
                }

        }
        viewModel.fetchCharacters()
    }

    private fun showErrrorMessage() {
        Toast.makeText(context, getString(R.string.error_connecting_to_server), Toast.LENGTH_LONG)
            .show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        recyclerViewAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        recyclerViewAdapter.filter.filter(newText)
        return false
    }
}