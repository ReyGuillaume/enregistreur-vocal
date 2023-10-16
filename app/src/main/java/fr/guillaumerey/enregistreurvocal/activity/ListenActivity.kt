package fr.guillaumerey.enregistreurvocal.activity

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import fr.guillaumerey.enregistreurvocal.R
import java.io.File
import java.io.IOException

class ListenActivity : AppCompatActivity()  {
    private lateinit var mMediaPlayer :MediaPlayer
    private var listeningStarted: Boolean = false
    private var listeningStopped: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listen)
        val startLis = findViewById<ImageButton>(R.id.start_listen_btn)
        val pauseLis = findViewById<ImageButton>(R.id.pause_listen_btn)
        val stopLis = findViewById<ImageButton>(R.id.stop_listen_btn)
        val uri = intent.getStringExtra("url")

        startLis.setOnClickListener{
            playSound(uri)
        }

        pauseLis.setOnClickListener{
            pauseSound()
        }

        stopLis.setOnClickListener{
            stopSound()
        }

    }
    fun playSound(uri : String?) {
        if (!listeningStarted) {
            try{
                val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath + "/"+uri)
                val mMediaPlayer = MediaPlayer().apply {
                    setDataSource(application, Uri.fromFile(file))
                    setAudioAttributes(AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                    )
                    prepare()
                    start()
                }
            } catch (exception: IOException) {
                mMediaPlayer?.release()
            }

        } else mMediaPlayer!!.start()
    }

    fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
    }

    fun stopSound() {
        if (listeningStarted) {
            listeningStopped = true
            listeningStarted = false
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
        }
    }
}