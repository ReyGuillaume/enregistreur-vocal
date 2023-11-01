package fr.guillaumerey.enregistreurvocal.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.adapter.AudioAdapter
import fr.guillaumerey.enregistreurvocal.storage.RecordStorage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Recycler View
        val list: RecyclerView = findViewById(R.id.audio_list)
        list.adapter = AudioAdapter(this)

        // Main button
        val button = findViewById<View>(R.id.start_record)
        button.setOnClickListener {
            val intent = Intent(applicationContext, RecordActivity::class.java)
            startActivity(intent)
        }

        // swipe refresh layout
        val refresh = findViewById<SwipeRefreshLayout>(R.id.refreshLayout)
        refresh.setOnRefreshListener {
            list.adapter?.notifyDataSetChanged()
            refresh.setRefreshing(false)
        }

    }
}