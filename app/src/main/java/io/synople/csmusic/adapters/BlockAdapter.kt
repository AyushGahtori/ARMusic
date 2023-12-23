package io.synople.csmusic.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.synople.csmusic.activities.MainActivity
import io.synople.csmusic.fragments.pickerdialogfragments.ForPickerDialogFragment
import io.synople.csmusic.fragments.pickerdialogfragments.NotePickerDialogFragment
import io.synople.csmusic.model.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.block_for.*
import kotlinx.android.synthetic.main.block_if.*
import kotlinx.android.synthetic.main.block_method.*
import kotlinx.android.synthetic.main.block_note.*


private const val FOR_BLOCK = 0
private const val IF_BLOCK = 1
private const val METHOD_BLOCK = 2
private const val NOTE_BLOCK = 3

class BlockAdapter(val blocks: MutableList<Block>, private val itemClick: (Block) -> Unit) :
    RecyclerView.Adapter<BlockAdapter.ViewHolder>() {

    class ViewHolder(override val containerView: View, private val itemClick: (Block) -> Unit) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindNote(noteBlock: NoteBlock) {
            tvBlockNote.text = noteBlock.fileName


            when (noteBlock.colorStatus) {
                0 -> rlBlockNote.setBackgroundColor(Color.parseColor("#ffffff"))
                1 -> rlBlockNote.setBackgroundColor(Color.parseColor("#00ff00"))
                2 -> rlBlockNote.setBackgroundColor(Color.parseColor("#006400"))
            }
        }

        fun bindFor(forBlock: ForBlock) {
            btnLoops.text = forBlock.loops.toString()
            btnLoops.setOnClickListener {
                ForPickerDialogFragment.newInstance()
                    .show((containerView.context as MainActivity).supportFragmentManager) {
                        forBlock.loops = it.loops
                        btnLoops.text = it.loops.toString()
                    }
            }

            val adapter = NoteBlockAdapter(forBlock.noteBlocks) {}
            rvForBlocks.adapter = adapter
            rvForBlocks.layoutManager =
                LinearLayoutManager(containerView.context, LinearLayoutManager.HORIZONTAL, false)

            btnForBlockAdd.setOnClickListener {
                NotePickerDialogFragment.newInstance()
                    .show((containerView.context as MainActivity).supportFragmentManager) {
                        forBlock.noteBlocks.add(mutableListOf(it))
                        adapter.notifyDataSetChanged()
                    }
            }


            when (forBlock.colorStatus) {
                0 -> rlBlockFor.setBackgroundColor(Color.parseColor("#ffffff"))
                1 -> rlBlockFor.setBackgroundColor(Color.parseColor("#00ff00"))
                2 -> rlBlockFor.setBackgroundColor(Color.parseColor("#006400"))
            }
        }

        fun bindIf(ifBlock: IfBlock) {
            btnAction.setOnClickListener {
                val b = AlertDialog.Builder(it.context)
                b.setTitle("Action")
                b.setItems(arrayOf("Chord", "Random")) { dialog, which ->
                    dialog.dismiss()
                    if (which == 0) {
                        ifBlock.list = mutableListOf(NoteBlock(), NoteBlock(), NoteBlock())
                        btnAction.text =
                            "${ifBlock.list[0].fileName}\n${ifBlock.list[1].fileName}${ifBlock.list[2].fileName}"
                    } else {
                        val noteBlock = NoteBlock()
                        noteBlock.isRandom = true
                        ifBlock.list = mutableListOf(noteBlock)
                        btnAction.text = "?"
                    }
                }
                b.show()
            }
            btnExpression.setOnClickListener {
                val b = AlertDialog.Builder(it.context)
                b.setTitle("Expression")
                b.setItems(arrayOf("Chord", "Random")) { dialog, which ->
                    dialog.dismiss()
                    ifBlock.expr = IfBlock.expression[which]

                    if (which == 0) {
                        btnExpression.text = "Chord"
                    } else {
                        btnExpression.text = "?"
                    }
                }
                b.show()
            }

            when (ifBlock.colorStatus) {
                0 -> rlBlockIf.setBackgroundColor(Color.parseColor("#ffffff"))
                1 -> rlBlockIf.setBackgroundColor(Color.parseColor("#00ff00"))
                2 -> rlBlockIf.setBackgroundColor(Color.parseColor("#006400"))
            }
        }

        fun bindMethod(methodBlock: MethodBlock) {
            tvBlockMethod.text = "M${methodBlock.methodNum}"

            when (methodBlock.colorStatus) {
                0 -> containerView.setBackgroundColor(0xFFFFFF)
                1 -> containerView.setBackgroundColor(0x006400)
                2 -> containerView.setBackgroundColor(0x00ff00)
            }
        }
    }

    override fun getItemViewType(position: Int) =
        when {
            blocks[position] is ForBlock -> FOR_BLOCK
            blocks[position] is IfBlock -> IF_BLOCK
            blocks[position] is MethodBlock -> METHOD_BLOCK
            blocks[position] is NoteBlock -> NOTE_BLOCK
            else -> super.getItemViewType(position)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                when (viewType) {
                    FOR_BLOCK -> io.synople.csmusic.R.layout.block_for
                    IF_BLOCK -> io.synople.csmusic.R.layout.block_if
                    METHOD_BLOCK -> io.synople.csmusic.R.layout.block_method
                    else -> io.synople.csmusic.R.layout.block_note
                },
                parent, false
            ), itemClick
        )
    }

    override fun onBindViewHolder(holder: BlockAdapter.ViewHolder, position: Int) {
        val block = blocks[position]

        with(holder) {
            when (getItemViewType(position)) {
                FOR_BLOCK -> bindFor(block as ForBlock)
                IF_BLOCK -> bindIf(block as IfBlock)
                METHOD_BLOCK -> bindMethod(block as MethodBlock)
                NOTE_BLOCK -> bindNote(block as NoteBlock)
            }
        }
    }

    override fun getItemCount() = blocks.size
}