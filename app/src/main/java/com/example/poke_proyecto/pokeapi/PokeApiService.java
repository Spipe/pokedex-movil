package com.example.poke_proyecto.pokeapi;


import com.example.poke_proyecto.models.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApiService {
    @GET("pokemon")
    public Call<PokemonRespuesta> obtenerListaPokemon();
}
