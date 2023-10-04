package fr.guillaumerey.enregistreurvocal.activity

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import fr.guillaumerey.enregistreurvocal.R
import java.io.IOException

class RecordActivity : AppCompatActivity() {

    private fun checkPermission(permissions: Array<String>): Boolean {
        var allPermissions = true

        for (permission in permissions) {
            Log.d("permission",ContextCompat.checkSelfPermission(this, permission).toString())
            Log.d("permission2",PackageManager.PERMISSION_GRANTED.toString())
            if ((ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)) {
                Log.d("test","coucou")
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    Log.d("test2","bonjour")
                    ActivityCompat.requestPermissions(this, permissions, 0)
                }
                allPermissions = false
            }
        }
        return allPermissions
    }

    // variables utilisées dans l'enregistrement
    private var output: String? = null
    private lateinit var mediaRecorder: MediaRecorder
    private var state: Boolean = false
    private var recordingStopped: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enregistrement)

        mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(this)
        } else {
            MediaRecorder()
        }

        output = Environment.getExternalStorageDirectory().absolutePath + "/recording.mp3"

        val startRec = findViewById<ImageButton>(R.id.start_record_btn)
        val pauseRec = findViewById<ImageButton>(R.id.pause_record_btn)
        val stopRec = findViewById<ImageButton>(R.id.stop_record_btn)

        val permissions = arrayOf(
            android.Manifest.permission.RECORD_AUDIO,
            //android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        startRec.setOnClickListener {
            if(checkPermission(permissions)){
                startRecording()
            }


        }

        pauseRec.setOnClickListener {
            pauseRecording()
        }

        stopRec.setOnClickListener {
            stopRecording()
        }

    }

    private fun startRecording() {
        try {
            Toast.makeText(this, "Recording go!", Toast.LENGTH_SHORT).show()
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB)
            mediaRecorder.setOutputFile(output)
            mediaRecorder.prepare()
            mediaRecorder.start()
            state = true
            Toast.makeText(this, "Recording started!", Toast.LENGTH_SHORT).show()
        } catch (e: IllegalStateException) {
            Toast.makeText(this, "err", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        } catch (e: IOException) {
            Toast.makeText(this, "err1", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    private fun stopRecording(){
        if(state){
            mediaRecorder.stop()
            mediaRecorder.release()
            state = false
        }else{
            Toast.makeText(this, "You are not recording right now!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pauseRecording() {
        if(state) {
            if(!recordingStopped){
                Toast.makeText(this,"Stopped!", Toast.LENGTH_SHORT).show()
                mediaRecorder.pause()
                recordingStopped = true
            }else{
                resumeRecording()
            }
        }
    }

    private fun resumeRecording() {
        Toast.makeText(this,"Resume!", Toast.LENGTH_SHORT).show()
        mediaRecorder.resume()
        recordingStopped = false
    }

}