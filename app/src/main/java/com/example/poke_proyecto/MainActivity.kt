package com.example.poke_proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import com.example.poke_proyecto.models.Pokemon
import com.example.poke_proyecto.models.PokemonRespuesta
import com.example.poke_proyecto.pokeapi.PokeApiService
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var retroFit:Retrofit
    private lateinit var pokemonAdapter: pokemonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        retroFit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.obtenerDatos()

    }
    private fun obtenerDatos() {
        var service = retroFit.create<PokeApiService>(PokeApiService::class.java)
        service.obtenerListaPokemon().enqueue(object:Callback<PokemonRespuesta>{
            override fun onResponse(
                call: Call<PokemonRespuesta>,
                response: Response<PokemonRespuesta>
            ) {
                val pokemones = response.body()
                val pokemonesResultados: ArrayList<Pokemon>? = pokemones?.getResults()
                pokemonAdapter = pokemonAdapter(this@MainActivity,pokemonesResultados)
                listaPokemones.adapter=pokemonAdapter
                listaPokemones.setOnItemClickListener{parent,view,position,id ->
                    val intent = Intent(this@MainActivity,DetallePokemonActivity::class.java)
                    intent.putExtra("Pokemon", pokemonesResultados?.get(position))
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<PokemonRespuesta>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}