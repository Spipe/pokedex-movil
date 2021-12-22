package com.example.poke_proyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.poke_proyecto.models.HabilidadRespuesta
import com.example.poke_proyecto.models.Habilidades
import com.example.poke_proyecto.models.Pokemon
import com.example.poke_proyecto.models.Stats
import com.example.poke_proyecto.pokeapi.PokeApiService
import kotlinx.android.synthetic.main.activity_detalle_pokemon.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pokemon_class.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class DetallePokemonActivity : AppCompatActivity() {
    lateinit var pokemonImage:ImageView
    lateinit var retrofit: Retrofit
    private lateinit var habilidadesAdapter: HabilidadesAdapter
    private lateinit var statsAdapter: StatsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pokemon)
        pokemonImage = findViewById(R.id.imageViewPokemon)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var pokemon = intent.getSerializableExtra("Pokemon") as Pokemon
        nombrePokemonDetalle.text=pokemon.name
        var urlDivide = pokemon.url.split("/")
        Glide.with(this)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${urlDivide[6]}.png")
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(pokemonImage)
        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        this.obtenerDatos(urlDivide[6])
    }
    private fun obtenerDatos(id:String){
        var service = retrofit.create<PokeApiService>(PokeApiService::class.java)
        Log.i("ID",id)
        service.obtenerListaHabilidades("$id").enqueue(object:Callback<HabilidadRespuesta> {
            override fun onResponse(
                call: Call<HabilidadRespuesta>,
                response: Response<HabilidadRespuesta>
            ) {
                val habilidades = response.body()
                val habilidadesResultados: ArrayList<Habilidades>? = habilidades?.getAbilities()
                val statsResultados :ArrayList<Stats>?= habilidades?.getStats()
                statsAdapter = StatsAdapter(this@DetallePokemonActivity,statsResultados)
                habilidadesAdapter = HabilidadesAdapter(this@DetallePokemonActivity,habilidadesResultados)
                listaEstadisticas.adapter=statsAdapter
                listaHabilidades.adapter=habilidadesAdapter

            }

            override fun onFailure(call: Call<HabilidadRespuesta>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}