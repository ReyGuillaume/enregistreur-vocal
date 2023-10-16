package fr.guillaumerey.enregistreurvocal.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.activity.ListenActivity
import fr.guillaumerey.enregistreurvocal.activity.RecordActivity
import fr.guillaumerey.enregistreurvocal.model.Record
import fr.guillaumerey.enregistreurvocal.storage.RecordStorage


class AudioAdapter(private val context: Context) : RecyclerView.Adapter<AudioAdapter.AudioHolder>() {

    private lateinit var  l : List<Record>

    class AudioHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titre: TextView = itemView.findViewById(R.id.titre)
        val dure : TextView = itemView.findViewById(R.id.dure)
        val date : TextView = itemView.findViewById(R.id.date)
        val button = itemView.findViewById<View>(R.id.start_listen)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sound, parent,false)
        l =  RecordStorage.get(context).findAll()
        return AudioHolder(view)
    }

    override fun getItemCount(): Int {
        return RecordStorage.get(context).size()
    }

    private fun timeToString(time:Int?):String{
        val seconds = (time?.div(1000))
        val minutes = seconds?.div(60)
        return String.format("%02d:%02d", minutes,seconds)
    }

    override fun onBindViewHolder(holder: AudioHolder, position: Int) {
        if (l.isNotEmpty()){
            val item = l.get(position)
            holder.button.setOnClickListener {
                val intent = Intent(context, ListenActivity::class.java)
                intent.putExtra("url",item.name)
                context.startActivity(intent)
                }
            holder.titre.text = item?.name
            holder.dure.text = timeToString(item?.time)
            holder.date.text = item?.date
        }

    }
}