package fr.guillaumerey.enregistreurvocal.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.adapter.AudioAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list: RecyclerView = findViewById(R.id.audio_list)
        list.adapter = AudioAdapter()

       val button = findViewById<View>(R.id.start_record)
        button.setOnClickListener {
            //val intent = Intent(applicationContext, RecordActivity::class.java)
            //startActivity(intent)
            Toast.makeText(applicationContext, "Coucou !", Toast.LENGTH_SHORT).show()
        }
    }
}