package com.example.ganbreakingbad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.ganbreakingbad.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment() {

    private val args: CharacterDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            name.text = args.character.name
            occupation.text = args.character.occupation.toString()
            status.text = args.character.status
            nickName.text = args.character.nickname
            seasonAppearance.text = args.character.appearance.toString()
        }

        return view
    }
}