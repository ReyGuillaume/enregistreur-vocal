package fr.guillaumerey.enregistreurvocal.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.model.Contrib
import org.json.JSONObject


class ContribAdapter : RecyclerView.Adapter<ContribAdapter.ContribHolder>() {

    private var l : List<Contrib> = emptyList()

    class ContribHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nom: TextView = itemView.findViewById(R.id.contrib_name)
        val prenom : TextView = itemView.findViewById(R.id.contrib_firstname)
        val situation : TextView = itemView.findViewById(R.id.contrib_status)
    }

    fun setL(list: List<Contrib>) {
        this.l = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContribHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contrib, parent,false)
        return ContribHolder(view)
    }


    override fun getItemCount(): Int {
        Log.d("sizetruc", l.size.toString())
        Log.d("sizetruc1", l.toString())
        return l.size
    }


    override fun onBindViewHolder(holder: ContribHolder, position: Int) {
        if (l.isNotEmpty()){
            val item = l.get(position)

            holder.nom.text = item.nom
            holder.prenom.text = item.prenom
            holder.situation.text = item.situation
        }
    }

}