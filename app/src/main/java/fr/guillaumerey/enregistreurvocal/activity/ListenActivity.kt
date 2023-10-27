package fr.guillaumerey.enregistreurvocal.activity

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import fr.guillaumerey.enregistreurvocal.R
import java.io.File

class ListenActivity : AppCompatActivity()  {
    private lateinit var mMediaPlayer :MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listen)

        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath + "/"+intent.getStringExtra("url"))
        val myUri = Uri.fromFile(file)

        mMediaPlayer = MediaPlayer().apply {
            setDataSource(application, myUri)
            setAudioAttributes(AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
            )
            prepare()
        }

        val startLis = findViewById<ImageButton>(R.id.start_listen_btn)
        val pauseLis = findViewById<ImageButton>(R.id.pause_listen_btn)

        startLis.setOnClickListener{
            mMediaPlayer.start()
        }

        pauseLis.setOnClickListener{
            mMediaPlayer.pause()
        }

    }

}