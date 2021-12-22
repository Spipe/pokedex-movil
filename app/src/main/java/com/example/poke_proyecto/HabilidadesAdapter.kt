package com.example.poke_proyecto

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.poke_proyecto.models.Habilidad
import com.example.poke_proyecto.models.Habilidades
import kotlinx.android.synthetic.main.detalle_actividades.view.*
import kotlinx.android.synthetic.main.pokemon_class.view.*

class HabilidadesAdapter( private val mContext: Context,private val listaHabilidades:List<Habilidades>?) :
    ArrayAdapter<Habilidades>(mContext,0,
listaHabilidades as MutableList<Habilidades>
){
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.detalle_actividades, parent, false)
        val habilidad = listaHabilidades?.get(position)
        layout.nombreDetalle.text = habilidad?.ability?.name.toString()
        return layout
    }

}