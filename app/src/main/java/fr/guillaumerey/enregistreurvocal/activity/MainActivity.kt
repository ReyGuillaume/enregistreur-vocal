package fr.guillaumerey.enregistreurvocal.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.adapter.AudioAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var list:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        // Recycler View
        list= findViewById(R.id.audio_list)
        list.adapter = AudioAdapter(this, supportFragmentManager)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_info -> {
            val intent = Intent(applicationContext, InfoActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()

        list.adapter?.notifyDataSetChanged()
    }
}