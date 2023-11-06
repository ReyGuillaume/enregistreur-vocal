package fr.guillaumerey.enregistreurvocal.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.request.InfoRequest
import org.json.JSONObject

class InfoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        title = "A propos de l'application"

        var req = InfoRequest(this)

        findViewById<Button>(R.id.data).setOnClickListener {
            findViewById<TextView>(R.id.app_name).text = req.data.getString("app")
            findViewById<TextView>(R.id.module_name).text = req.data.getString("module")
        }
    }
}