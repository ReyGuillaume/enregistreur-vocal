package fr.guillaumerey.enregistreurvocal.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import fr.guillaumerey.enregistreurvocal.R
import fr.guillaumerey.enregistreurvocal.adapter.AudioAdapter
import fr.guillaumerey.enregistreurvocal.model.Record
import fr.guillaumerey.enregistreurvocal.storage.RecordStorage
import java.io.File

class UpdateDialogFragment (private val context: Context, private val item : Record, private val adapter: AudioAdapter): DialogFragment()
{
    private lateinit var editText: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_enregistrement, null)
        editText = view.findViewById(R.id.record_name)

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_update_title)
            .setView(view)
            .setPositiveButton(R.string.modifier_btn){_, _ ->
                val text = editText.text.toString()
                if (text.isNotEmpty()){
                    val oldFileName = "/${item.name}"
                    val newFileName = "$text.mp3"
                    val oldFile:File = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath, oldFileName)
                    val newFile:File = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath, newFileName)
                    // On renomme l'ancien fichier
                    if (!oldFile.renameTo(newFile)){
                        Toast.makeText(requireContext(), "Erreur Survenue !", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        item.newName(newFileName)
                        RecordStorage.get(context).update(item.id,item)
                        adapter.newL()
                        adapter.notifyDataSetChanged()
                    }
                }
                else {
                    Toast.makeText(requireContext(), "Il faut un Nom !", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(R.string.annuler_btn,null)
            .create()
    }
    }
