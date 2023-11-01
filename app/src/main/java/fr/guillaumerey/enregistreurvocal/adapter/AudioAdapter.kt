package fr.guillaumerey.enregistreurvocal.adapter

import android.content.Context
import android.content.Intent
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.activity.ListenActivity
import fr.guillaumerey.enregistreurvocal.activity.RecordActivity
import fr.guillaumerey.enregistreurvocal.fragment.UpdateDialogFragment
import fr.guillaumerey.enregistreurvocal.model.Record
import fr.guillaumerey.enregistreurvocal.storage.RecordStorage
import java.io.File


class AudioAdapter(private val context: Context) : RecyclerView.Adapter<AudioAdapter.AudioHolder>() {

    private lateinit var  l : List<Record>

    class AudioHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val titre: TextView = itemView.findViewById(R.id.titre)
        val dure : TextView = itemView.findViewById(R.id.dure)
        val date : TextView = itemView.findViewById(R.id.date)
        val buttonListen = itemView.findViewById<View>(R.id.start_listen)
        val buttonUpdate = itemView.findViewById<View>(R.id.update_sound)
        val buttonDelete = itemView.findViewById<View>(R.id.delete_sound)
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
            holder.buttonListen.setOnClickListener {
                val intent = Intent(context, ListenActivity::class.java)
                intent.putExtra("url",item.name)
                context.startActivity(intent)
                }

            holder.buttonUpdate.setOnClickListener{
                //UpdateDialogFragment(context,item).show(supportFragmentManager,null)
            }

            holder.buttonDelete.setOnClickListener{

                val file = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath,
                    item.name
                )

                if (file.delete()){
                    RecordStorage.get(context).delete(item.id)
                }
                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
            }
            holder.titre.text = item?.name
            holder.dure.text = timeToString(item?.time)
            holder.date.text = item?.date
        }

    }
}