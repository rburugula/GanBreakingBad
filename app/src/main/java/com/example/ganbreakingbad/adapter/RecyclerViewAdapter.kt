package com.example.ganbreakingbad.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.ganbreakingbad.databinding.CharacterRowBinding
import com.example.ganbreakingbad.model.Character
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(), Filterable {

    private var characters = ArrayList<Character>()
    private var filteredCharacters = ArrayList<Character>()
    var onItemClick: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = CharacterRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(filteredCharacters[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(filteredCharacters[position])
        }
    }

    override fun getItemCount(): Int {
        return filteredCharacters.size
    }

    fun setUpdatedData(characters: ArrayList<Character>) {
        this.characters = characters
        this.filteredCharacters = this.characters
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                filteredCharacters = if (charString.isEmpty()) characters else {
                    val filteredList = ArrayList<Character>()
                    characters.filter {
                        (it.name.toLowerCase(Locale.ROOT).contains(
                            constraint!!.toString().toLowerCase(Locale.ROOT)
                        ))
                    }.forEach { filteredList.add(it) }
                    filteredList
                }
                return FilterResults().apply { values = filteredCharacters }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredCharacters = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Character>
                notifyDataSetChanged()
            }

        }
    }

}