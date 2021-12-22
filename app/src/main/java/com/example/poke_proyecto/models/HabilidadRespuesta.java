package com.example.poke_proyecto.models;

import java.util.ArrayList;

public class HabilidadRespuesta {
    private ArrayList<Habilidades>abilities;
    private ArrayList<Stats>stats;

    public ArrayList<Habilidades> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Habilidades> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<Stats> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Stats> stats) {
        this.stats = stats;
    }
}
