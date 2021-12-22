package com.example.poke_proyecto

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.poke_proyecto.models.Habilidades
import com.example.poke_proyecto.models.Stats
import kotlinx.android.synthetic.main.adapter_stat.view.*
import kotlinx.android.synthetic.main.detalle_actividades.view.*

class StatsAdapter (private val mContext: Context, private val listaStat:List<Stats>?) :
    ArrayAdapter<Stats>(mContext,0,
        listaStat as MutableList<Stats>
    ){
    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.adapter_stat, parent, false)
        val stat = listaStat?.get(position)
        layout.textStatName.text = stat?.stat?.name.toString()
        layout.textBaseStat.text = stat?.base_stat.toString()
        return layout
    }

}