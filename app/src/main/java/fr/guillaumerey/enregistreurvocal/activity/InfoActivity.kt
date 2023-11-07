package fr.guillaumerey.enregistreurvocal.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.adapter.ContribAdapter
import fr.guillaumerey.enregistreurvocal.model.Contrib
import fr.guillaumerey.enregistreurvocal.request.InfoRequest

class InfoActivity: AppCompatActivity() {
    private lateinit var list: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        title = "A propos de l'application"

        var req = InfoRequest(this)

        list = findViewById(R.id.contrib_list)
        val adapter = ContribAdapter()
        list.adapter = adapter

        findViewById<Button>(R.id.data).setOnClickListener {
            findViewById<TextView>(R.id.app_name).text = req.data.getString("app")
            findViewById<TextView>(R.id.module_name).text = req.data.getString("module")

            val listType = object : TypeToken<List<Contrib>>() {}.type
            val contributeursJsonArray = req.data.getJSONArray("contributeurs")
            adapter.setL(Gson().fromJson(contributeursJsonArray.toString(), listType))
            adapter.notifyDataSetChanged()
        }
    }
}