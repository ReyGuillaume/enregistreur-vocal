package fr.guillaumerey.enregistreurvocal.adapter

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.storage.RecordStorage

class AudioAdapter(private val context: Context) : RecyclerView.Adapter<AudioAdapter.AudioHolder>() {

    class AudioHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titre: TextView = itemView.findViewById(R.id.titre)
        val dure : TextView = itemView.findViewById(R.id.dure)
        val date : TextView = itemView.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sound, parent,false)
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
        Log.d("nomdebug", position.toString())
        val item = RecordStorage.get(context).find(position+1)
        Log.d("DEBUGBDD",RecordStorage.get(context).find(position).toString())
        holder.titre.text = item?.name
        holder.dure.text = timeToString(item?.time)
        holder.date.text = item?.date
    }
}