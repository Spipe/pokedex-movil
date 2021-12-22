package com.example.poke_proyecto.pokeapi;


import com.example.poke_proyecto.models.HabilidadRespuesta;
import com.example.poke_proyecto.models.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface PokeApiService {
    @GET("pokemon")
    public Call<PokemonRespuesta> obtenerListaPokemon();

    @GET("pokemon/{id}")
    public Call<HabilidadRespuesta> obtenerListaHabilidades(@Path("id")String id);
}
