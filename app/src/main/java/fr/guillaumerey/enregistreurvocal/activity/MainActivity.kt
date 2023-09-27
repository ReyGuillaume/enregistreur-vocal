package fr.guillaumerey.enregistreurvocal.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.adapter.AudioAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list: RecyclerView = findViewById(R.id.audio_list)
        list.adapter = AudioAdapter()
    }
}