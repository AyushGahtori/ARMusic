package io.synople.csmusic.fragments.pickerdialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import io.synople.csmusic.R
import io.synople.csmusic.model.ForBlock
import kotlinx.android.synthetic.main.dialog_fragment_for_picker.*


class ForPickerDialogFragment : DialogFragment() {

    private lateinit var onFinish: (ForBlock) -> Unit

    fun show(fm: FragmentManager, onFinish: (ForBlock) -> Unit) {
        this.onFinish = onFinish
        show(fm, "IfPickerDialogFragment")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.dialog_fragment_for_picker, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivLoopPlus.setOnClickListener {
            var iter = tvIterations.text.toString().toInt()
            iter++
            tvIterations.text = iter.toString()
        }
        ivLoopMinus.setOnClickListener {
            var iter = tvIterations.text.toString().toInt()
            if (iter > 2)
                iter--
            tvIterations.text = iter.toString()
        }

        btnAddLoops.setOnClickListener {
            onFinish(ForBlock(tvIterations.text.toString().toInt()))
            dismiss()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ForPickerDialogFragment()
    }
}