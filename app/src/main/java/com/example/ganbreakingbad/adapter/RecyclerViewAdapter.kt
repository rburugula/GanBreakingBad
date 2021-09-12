package com.example.ganbreakingbad.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ganbreakingbad.databinding.CharacterRowBinding
import com.example.ganbreakingbad.model.Character
import com.squareup.picasso.Picasso

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var characters = ArrayList<Character>()
    var onItemClick: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CharacterRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(characters[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(characters[position])
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    fun setUpdatedData(characters: ArrayList<Character>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding: CharacterRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Character) {
            binding.apply {
                characterName.text = data.name

                Picasso.get()
                    .load(data.img)
                    .into(chacterImage)
            }
        }
    }

}