package fr.guillaumerey.enregistreurvocal.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.fragment.RegisterDialogFragment
import java.io.IOException

class RecordActivity : AppCompatActivity() {

    private var output: String? = null
    private lateinit var mediaRecorder: MediaRecorder
    private var recordingStarted: Boolean = false
    private var recordingStopped: Boolean = false
    private lateinit var timer: CountDownTimer
    private var recordingStartTimeMillis: Long = 0
    private var recordingPausedTimeMillis: Long = 0
    private lateinit var timerView: TextView

    private fun askForPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                startActivity(intent)
            }
        }
    }

    private fun checkPermission(permissions: Array<String>): Boolean {
        var res = true
        for (permission in permissions) {
            if ((ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    ActivityCompat.requestPermissions(this, permissions, 0)
                }
                res = false
            }
        }
        return res
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enregistrement)

        mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(this)
        } else {
            MediaRecorder()
        }

        output = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath + "/.temp.mp3"

        val startRec = findViewById<ImageButton>(R.id.start_record_btn)
        val pauseRec = findViewById<ImageButton>(R.id.pause_record_btn)
        val stopRec = findViewById<ImageButton>(R.id.stop_record_btn)
        timerView = findViewById(R.id.recordTimer)

        val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO)

        startRec.setOnClickListener {
            askForPermissions()
            if(checkPermission(permissions)) startRecording()
        }

        pauseRec.setOnClickListener {
            pauseRecording()
        }

        stopRec.setOnClickListener {
            stopRecording()
        }

    }

    private fun startRecording() {
        if(!recordingStarted) {
            try {
                // initialise le recorder
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB)
                mediaRecorder.setOutputFile(output)
                mediaRecorder.prepare()
                mediaRecorder.start()
                recordingStarted = true
                recordingStartTimeMillis = System.currentTimeMillis()
                startTimer()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            resumeRecording()
        }
    }

    private fun stopRecording(){
        if(recordingStarted){
            // Arret et enregistrement du fichier audio
            mediaRecorder.stop()
            mediaRecorder.release()
            timer.cancel()
            RegisterDialogFragment().show(supportFragmentManager, null)
        }else{
            Toast.makeText(this, "L'enregistrement n'a pas commencé", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pauseRecording() {
        if(recordingStarted) {
            if(recordingStopped) {
                Toast
                    .makeText(this, "L'enregistrement est déjà en pause", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // Enregitrement mis en pause
                mediaRecorder.pause()
                recordingStopped = true
                recordingPausedTimeMillis = System.currentTimeMillis()
                Toast
                    .makeText(this, "Pause", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun resumeRecording() {
        if (recordingStopped) {
            // On relance l'enregistrement
            mediaRecorder.resume()
            recordingStopped = false
            // Maj de recordingStartTimeMillis pour déduire le temps total de la pause
            recordingStartTimeMillis += System.currentTimeMillis() - recordingPausedTimeMillis
        } else {
            Toast
                .makeText(this, "L'enregistrement est déjà lancé", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun startTimer() {
        timer = object : CountDownTimer(Long.MAX_VALUE, 10) {
            override fun onTick(millisUntilFinished: Long) {
                if (!recordingStopped) {
                    // Calcul du temps total de l'enregistrement
                    val elapsedTimeMillis = System.currentTimeMillis() - recordingStartTimeMillis
                    val seconds = (elapsedTimeMillis / 1000).toInt()
                    val minutes = seconds / 60
                    val remainingSeconds = seconds % 60
                    val milis = elapsedTimeMillis / 10 % 100
                    Log.d("test",elapsedTimeMillis.toString())

                    // Mise à jour TextView
                    val timeText = String.format("%02d:%02d:%02d", minutes, remainingSeconds, milis)
                    timerView.text = timeText
                }
            }

            override fun onFinish() {}
        }
        timer.start()
    }

}