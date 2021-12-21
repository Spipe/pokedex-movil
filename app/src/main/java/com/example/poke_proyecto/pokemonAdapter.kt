package com.example.poke_proyecto

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.poke_proyecto.models.Pokemon
import kotlinx.android.synthetic.main.pokemon_class.view.*

class pokemonAdapter(private val mContext:Context,private val listaPokemones:List<Pokemon>?) :ArrayAdapter<Pokemon>(mContext,0,
    listaPokemones as MutableList<Pokemon>
){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.pokemon_class, parent, false)
        val pokemon = listaPokemones?.get(position)
        layout.nombrePokemon.text = pokemon?.name
        layout.numeroPokemon.text = pokemon?.url
        var urlSplited = pokemon?.url?.split("/")
        var pokemonNumero = urlSplited?.get(6)
        Glide.with(mContext)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonNumero}.png")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(layout.imageView2)
        return layout
    }

}