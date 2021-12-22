package com.example.poke_proyecto.models;

import java.io.Serializable;

public class Habilidades implements Serializable {
    private Habilidad ability;
    private Boolean is_hidden;
    private Integer slot;

    public Habilidad getAbility() {
        return ability;
    }

    public void setAbility(Habilidad ability) {
        this.ability = ability;
    }

    public Boolean getIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(Boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }
}
