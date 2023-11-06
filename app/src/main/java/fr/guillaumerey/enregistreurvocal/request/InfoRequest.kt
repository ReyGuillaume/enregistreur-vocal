package fr.guillaumerey.enregistreurvocal.request

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class InfoRequest(private val context: Context) {

    lateinit var data: JSONObject

    companion object {
        private  const val URL = "http://51.68.91.213/gr-3-1/info.json"
    }

    init {
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.GET,
            URL,
            null,
            {response ->
                data = response
            },
            {error ->
                Log.d("err", error.toString())
            }
        )
        queue.add(request)
        queue.start()
    }

}