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
import java.io.File

class UpdateDialogFragment (private val context: Context, private val oldFileName : Record): DialogFragment()
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
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(R.string.annuler_btn,null)
            .create()
    }
    }
