package fr.guillaumerey.enregistreurvocal.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.model.Record
import fr.guillaumerey.enregistreurvocal.storage.RecordStorage
import java.io.File

class RegisterDialogFragment(@get:JvmName("getAdapterContext") private val context: Context): DialogFragment() {
    private lateinit var editText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_enregistrement, null)
        editText = view.findViewById(R.id.record_name)

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_register_title)
            .setView(view)
            .setPositiveButton(R.string.sauvegarder_btn) {_, _ ->
                val text = editText.text.toString()
                if (text.isNotEmpty()) {
                    val oldFileName =  "/.temp.mp3"
                    val newFileName = "$text.mp3"
                    val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath, oldFileName)
                    val newFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath, newFileName)
                    // On renomme l'ancien fichier temp.mp3
                    if (!file.renameTo(newFile)) {
                        Toast.makeText(
                            requireContext(),
                            "Une erreur est survenue.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{

                        RecordStorage.get(context).insert(Record(
                            0,
                            newFileName,
                            "2023-10-13",
                            100000
                        ))
                    }
                } else {
                    // Gérer le nom de fichier vide
                    Toast.makeText(requireContext(), "Il faut un nom à votre audio", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(R.string.annuler_btn, null)
            .create()
    }
}