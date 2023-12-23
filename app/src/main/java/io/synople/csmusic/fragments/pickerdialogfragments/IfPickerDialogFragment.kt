package io.synople.csmusic.fragments.pickerdialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import io.synople.csmusic.R
import io.synople.csmusic.model.IfBlock
import kotlinx.android.synthetic.main.dialog_fragment_if_picker.*


class IfPickerDialogFragment : DialogFragment() {

    private lateinit var onFinish: (IfBlock) -> Unit

    fun show(fm: FragmentManager, onFinish: (IfBlock) -> Unit) {
        this.onFinish = onFinish
        show(fm, "IfPickerDialogFragment")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.dialog_fragment_if_picker, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAdd.setOnClickListener {
            onFinish(IfBlock())
            dismiss()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = IfPickerDialogFragment()
    }
}