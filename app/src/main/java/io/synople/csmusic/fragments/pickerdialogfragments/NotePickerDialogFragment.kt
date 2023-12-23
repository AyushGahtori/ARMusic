package io.synople.csmusic.fragments.pickerdialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import io.synople.csmusic.R
import io.synople.csmusic.model.NoteBlock
import kotlinx.android.synthetic.main.dialog_fragment_note_picker.*


class NotePickerDialogFragment : DialogFragment() {

    private lateinit var onFinish: (NoteBlock) -> Unit

    fun show(fm: FragmentManager, onFinish: (NoteBlock) -> Unit) {
        this.onFinish = onFinish
        show(fm, "NotePickerDialogFragment")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.dialog_fragment_note_picker, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvKeyValue.text = "C"
        tvNoteValue.text = "c"
        tvOctaveValue.text = "3"
        tvLengthValue.text = "q"

        ivKeyPlus.setOnClickListener {
            when (tvKeyValue.text) {
                "C" -> tvKeyValue.text = "D"
                "D" -> tvKeyValue.text = "G"
                "G" -> tvKeyValue.text = "C"
            }
        }
        ivKeyMinus.setOnClickListener {
            when (tvKeyValue.text) {
                "C" -> tvKeyValue.text = "G"
                "D" -> tvKeyValue.text = "C"
                "G" -> tvKeyValue.text = "D"
            }
        }

        ivNotePlus.setOnClickListener {
            when (tvKeyValue.text) {
                "C" -> {
                    var indexOf = NoteBlock.keyC.indexOf(tvNoteValue.text)
                    indexOf++
                    tvNoteValue.text = NoteBlock.keyC[indexOf]

                    if (indexOf > NoteBlock.keyC.size) indexOf = 0
                    tvNoteValue.text = NoteBlock.keyC[indexOf]
                }
                "D" -> {
                    var indexOf = NoteBlock.keyD.indexOf(tvNoteValue.text)
                    indexOf++
                    tvNoteValue.text = NoteBlock.keyD[indexOf]
                    if (indexOf > NoteBlock.keyD.size) indexOf = 0

                    tvNoteValue.text = NoteBlock.keyD[indexOf]
                }
                "G" -> {
                    var indexOf = NoteBlock.keyG.indexOf(tvNoteValue.text)
                    indexOf++
                    tvNoteValue.text = NoteBlock.keyG[indexOf]
                    if (indexOf > NoteBlock.keyG.size) indexOf = 0

                    tvNoteValue.text = NoteBlock.keyG[indexOf]
                }
            }
        }
        ivNoteMinus.setOnClickListener {
            when (tvKeyValue.text) {
                "C" -> {
                    var indexOf = NoteBlock.keyC.indexOf(tvKeyValue.text)
                    indexOf--
                    if (indexOf < 0) indexOf = 7

                    tvNoteValue.text = NoteBlock.keyC[indexOf]
                }
                "D" -> {
                    var indexOf = NoteBlock.keyD.indexOf(tvNoteValue.text)
                    indexOf--
                    if (indexOf < 0) indexOf = 7

                    tvNoteValue.text = NoteBlock.keyD[indexOf]

                }
                "G" -> {
                    var indexOf = NoteBlock.keyG.indexOf(tvNoteValue.text)
                    indexOf--
                    if (indexOf < 0) indexOf = 7

                    tvNoteValue.text = NoteBlock.keyG[indexOf]
                }
            }
        }

        ivOctavePlus.setOnClickListener {
            var num = tvOctaveValue.text.toString().toInt()
            num++
            tvOctaveValue.text = num.toString()
            if (num > 5) num = 3
            tvOctaveValue.text = num.toString()
        }

        ivOctaveMinus.setOnClickListener {
            var num = tvOctaveValue.text.toString().toInt()

            tvOctaveValue.text = num.toString()
            if (num > 3) num-- else num = 5
            tvOctaveValue.text = num.toString()

        }

        ivLengthPlus.setOnClickListener {
            var indexOf = NoteBlock.countStrings.indexOf(tvLengthValue.text.toString())
            indexOf++
            if (indexOf >= NoteBlock.countStrings.size) indexOf = 0

            tvLengthValue.text = NoteBlock.countStrings[indexOf]
        }
        ivLengthMinus.setOnClickListener {
            var indexOf = NoteBlock.countStrings.indexOf(tvLengthValue.text.toString())

            if (indexOf > 0) indexOf-- else indexOf = NoteBlock.countStrings.size - 1

            if (indexOf >= NoteBlock.countStrings.size) indexOf = NoteBlock.countStrings.size - 1

            tvLengthValue.text = NoteBlock.countStrings[indexOf]
        }

        btnAdd.setOnClickListener {
            val key = when (tvKeyValue.text.toString()) {
                "C" -> 0
                "D" -> 1
                "G" -> 2
                "R" -> 3
                else -> 0
            }
            onFinish(
                NoteBlock(
                    key,
                    NoteBlock.keys[key].indexOf(tvNoteValue.text.toString()),
                    NoteBlock.octStrings.indexOf(tvOctaveValue.text.toString()),
                    NoteBlock.countStrings.indexOf(tvLengthValue.text.toString())
                )
            )
            dismiss()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotePickerDialogFragment()
    }
}
